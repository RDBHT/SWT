# BUI-E1 — Buildmanagement

Abgabe zur Einsendeaufgabe BUI-E1 (Softwaretechnik). Ziel: Erfahrung mit
**zwei** Build-/CI-Systemen sammeln und dokumentieren, was initial nicht
funktionierte und wie es gelöst wurde.

Repository: https://github.com/RDBHT/SWT
Pages-Reiter: https://rdbht.github.io/SWT/bui-e1.html

---

## 1. Gewählte Systeme und Begründung

| System | Rolle | Warum gewählt |
|---|---|---|
| **Maven** | Buildmanagementsystem | Echtes Buildsystem (kompilieren → testen → paketieren), deckt den Kern „Buildmanagement" der Aufgabe ab. Convention over Configuration: wenig Konfiguration, fester Lifecycle. |
| **GitHub Actions** | CI/CD-Orchestrator | Führt denselben Maven-Build bei jedem Push automatisiert in der Cloud aus. Von der Aufgabe ausdrücklich als gültige Variante genannt. |

Bewusst **nicht** zwei CI/CD-Tools (z. B. Jenkins + Actions): beide wären
Orchestratoren ohne eigenen Build und würden sich stark überschneiden. Die
Kombination *Buildsystem + CI/CD* gibt den größeren Lern-Kontrast — Actions
baut nicht selbst, sondern **ruft** Maven auf.

## 2. Beispielprojekt — was konkret passiert

Bewusst minimal gehalten — es geht um das **Prinzip der Pipeline**, nicht um
den Umfang. Eine Utility-Klasse plus zwei JUnit-5-Tests reichen, um alle
relevanten Lifecycle-Phasen auszulösen (compile → test → package → run).

```
bui-e1/maven-demo/
├── pom.xml                                  # Maven-Steuerdatei
└── src/
    ├── main/java/de/rdbht/swt/Anonymizer.java       # die Logik
    └── test/java/de/rdbht/swt/AnonymizerTest.java   # 2 JUnit-5-Tests
```

**Die Logik (`Anonymizer.maskEmail`):** nimmt eine E-Mail-Adresse, prüft, dass
genau ein `@` enthalten ist (sonst `IllegalArgumentException`), behält das
**erste Zeichen** des lokalen Teils, ersetzt den Rest durch `***` und hängt die
Domain wieder an. `"anna@example.de"` → `"a***@example.de"`. Ein kleiner,
themennaher Bezug (Datensparsamkeit) ohne echte Daten. Eine `main`-Methode gibt
Eingabe und maskierten Wert aus, damit das fertige JAR direkt ausführbar ist.

**Die Tests (`AnonymizerTest`):** zwei JUnit-5-Fälle —
1. `masksLocalPart` prüft, dass `"anna@example.de"` zu `"a***@example.de"` wird;
2. `rejectsInvalidInput` prüft, dass eine Eingabe ohne `@` die erwartete
   Exception wirft.

**Warum genau das?** Jede Datei deckt eine Build-Phase ab und macht sie
beobachtbar: der Quellcode muss *kompilieren*, die Tests müssen in der
*test*-Phase grün sein, erst dann entsteht in der *package*-Phase das JAR, das
sich anschließend *ausführen* lässt. Schlägt eine Stufe fehl, bricht der Build
ab (siehe Abschnitt 4) — genau das soll die Pipeline demonstrieren.

## 3. Maven — lokaler Build

### Umgebung aufsetzen

Die Build-Umgebung war zunächst leer (nur ein Java-**JRE**, kein Compiler, kein
Maven). Aufgesetzt wurde sie ohne Root-Rechte, rein über entpackte Tarballs ins
Home-Verzeichnis:

```bash
# 1) Volles JDK 17 (Temurin) — enthält javac (das reine JRE nicht)
curl -L -o jdk17.tar.gz \
  "https://api.adoptium.net/v3/binary/latest/17/ga/linux/aarch64/jdk/hotspot/normal/eclipse"
tar xzf jdk17.tar.gz

# 2) Maven 3.9.9 (Binary-Distribution)
curl -L -o maven.tar.gz \
  "https://archive.apache.org/dist/maven/maven-3/3.9.9/binaries/apache-maven-3.9.9-bin.tar.gz"
tar xzf maven.tar.gz

# 3) In die Umgebung einhängen
export JAVA_HOME="$PWD/jdk-17.0.19+10"
export PATH="$JAVA_HOME/bin:$PWD/apache-maven-3.9.9/bin:$PATH"

# 4) Prüfen
mvn -version
# Apache Maven 3.9.9
# Java version: 17.0.19, vendor: Eclipse Adoptium
```

Maven verbindet sich beim ersten Lauf selbst mit *Maven Central* und lädt
Plugins und Test-Bibliotheken in den lokalen Cache `~/.m2/repository` — ein
manuelles Einbinden von `.jar`-Dateien (wie bei Ant) entfällt komplett.

### Build ausführen

```bash
cd bui-e1/maven-demo
mvn -B clean package      # clean -> compile -> test -> package (JAR)
```

### Lifecycle-Phasen (Auszug aus dem echten Log)

```text
[INFO] --- clean:3.2.0:clean (default-clean) @ maven-demo ---
[INFO] --- compiler:3.13.0:compile (default-compile) @ maven-demo ---
[INFO] --- compiler:3.13.0:testCompile (default-testCompile) @ maven-demo ---
[INFO] --- surefire:3.2.5:test (default-test) @ maven-demo ---
[INFO]  T E S T S
[INFO] Running de.rdbht.swt.AnonymizerTest
[INFO] Tests run: 2, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 0.037 s
[INFO] --- jar:3.4.1:jar (default-jar) @ maven-demo ---
[INFO] Building jar: .../target/maven-demo-1.0.0.jar
[INFO] BUILD SUCCESS
[INFO] Total time:  12.052 s
```

### Erzeugtes Artefakt ausführen

```text
$ java -jar target/maven-demo-1.0.0.jar
Input : anna@example.de
Masked: a***@example.de

$ java -jar target/maven-demo-1.0.0.jar max.mustermann@klinik-berlin.de
Input : max.mustermann@klinik-berlin.de
Masked: m***@klinik-berlin.de
```

### Beobachtung: Dependency-Cache

| Lauf | Downloads | Dauer |
|---|---|---|
| 1. Lauf (leeres `~/.m2`) | viele (Plugins + JUnit + Transitive) | **12,05 s** |
| 2. Lauf (gecacht) | 0 | **1,28 s** |

Maven lädt Plugins und Bibliotheken einmalig aus *Maven Central* in den
lokalen Cache `~/.m2/repository`. Genau diesen Cache bildet später die
GitHub-Actions-Pipeline mit `cache: maven` nach.

## 4. Test-Gate demonstriert (Build bricht bei rotem Test ab)

Zum Beweis, dass die `test`-Phase als Qualitäts-Gate wirkt, wurde in einer
Wegwerf-Kopie der erwartete Wert absichtlich verfälscht:

```text
[ERROR] AnonymizerTest.masksLocalPart:14 expected: <XXX@example.de> but was: <a***@example.de>
[ERROR] Tests run: 2, Failures: 1, Errors: 0, Skipped: 0
[INFO] BUILD FAILURE
[ERROR] Failed to execute goal ...maven-surefire-plugin:3.2.5:test ...: There are test failures.
```

Maven beendet mit **Exit-Code ≠ 0** und **erreicht die `package`-Phase nicht** —
es entsteht kein JAR.

Genau dieser Exit-Code ist die Verbindung zur CI. In der Pipeline (Schritt 6)
läuft derselbe Befehl als Workflow-Schritt `run: mvn -B clean package`. GitHub
Actions wertet den Exit-Code jedes Schritts aus:

- Exit-Code **0** → Schritt grün, der Job läuft weiter;
- Exit-Code **≠ 0** (roter Test) → der Schritt **schlägt fehl**, alle folgenden
  Schritte werden übersprungen, der Job und damit der gesamte Run werden **rot**
  markiert, und das Status-Badge auf der Pages-Seite wird rot.

So wird ein fehlerhafter Commit automatisch sichtbar, *bevor* er als
funktionierend gilt — der Test-Lauf ist das Qualitäts-Gate, lokal wie in der
Cloud. (Der Schritt „Upload artifacts" läuft hier dank `if: always()` trotzdem,
damit der Testbericht zur Fehlersuche verfügbar bleibt — ein JAR enthält er
mangels `package`-Phase aber nicht.)

## 5. GitHub Actions — CI/CD

Workflow: [`.github/workflows/build.yml`](../.github/workflows/build.yml).
Bei jedem Push (der `bui-e1/maven-demo/**` betrifft) startet GitHub einen
`ubuntu-latest`-Runner und führt aus:

1. **Checkout** des Repos (`actions/checkout`)
2. **JDK 17** bereitstellen + Maven-Cache (`actions/setup-java`, `cache: maven`)
3. **`mvn -B clean package`** — derselbe Build wie lokal
4. **Artefakte hochladen** — fertiges JAR + Surefire-Testberichte

Kerngedanke: Der Build ist identisch zum lokalen — Actions **orchestriert**
ihn nur (Trigger, Umgebung, Caching, Artefakt-Ablage), baut aber nicht selbst.

### Nachweise (nach dem ersten Push einzutragen)

- Build-Status-Badge: `![build](https://github.com/RDBHT/SWT/actions/workflows/build.yml/badge.svg)`
- Konkreter Run mit Logs: _<Link zum Actions-Run — folgt nach dem Push>_

## 6. Vergleich Buildsystem vs. CI/CD

| Aspekt | Maven (Buildsystem) | GitHub Actions (CI/CD) |
|---|---|---|
| Aufgabe | Code → getestetes Artefakt | Build automatisiert ausführen |
| Auslöser | manuell (`mvn ...`) | Ereignis (Push, manuell) |
| Läuft wo | lokale Maschine | Cloud-Runner |
| Baut selbst? | **ja** | nein — ruft Maven auf |
| Ergebnis | JAR im `target/` | grüner/roter Run + Artefakt-Upload |

## 7. Was initial nicht funktionierte — und die Lösung

| Problem | Symptom | Lösung |
|---|---|---|
| Kein Compiler in der Umgebung | nur JRE vorhanden, `javac: command not found` | volles **JDK 17 (Temurin)** installiert statt nur JRE |
| Maven-Download schlug fehl | `dlcdn.apache.org/...` lieferte **404** (HTML statt Tarball), `tar: not in gzip format` | Bezug von **`archive.apache.org`** statt vom Mirror |
| Erster Build „lädt das halbe Internet" | viele `Downloading from central ...`, 12 s | normal — Cache `~/.m2` füllt sich; 2. Lauf 1,3 s. In CI über `cache: maven` abgebildet |

Diese Reibungspunkte sind genau die in der Aufgabe gemeinten Erfahrungen:
die Build-Umgebung muss erst korrekt aufgesetzt werden, bevor das Skript läuft.

## 8. Fazit

- **Maven** nimmt durch Konventionen + Lifecycle viel ab; ein sauberer
  Standardbuild steht mit minimaler `pom.xml`. Schwäche: starr bei Sonderwegen.
- **GitHub Actions** ergänzt das ideal: derselbe Build läuft reproduzierbar
  in der Cloud, jeder Push wird automatisch geprüft, Ergebnisse sind als
  Badge/Logs dauerhaft nachweisbar.
- Wichtigste Erkenntnis: Ein CI/CD-Tool **ersetzt** kein Buildsystem, es
  **automatisiert** es. Erst die Kombination ergibt eine vollständige Pipeline.

## Dateien

- [`maven-demo/pom.xml`](./maven-demo/pom.xml) — Maven-Steuerdatei
- [`maven-demo/src/main/java/de/rdbht/swt/Anonymizer.java`](./maven-demo/src/main/java/de/rdbht/swt/Anonymizer.java) — Demo-Klasse
- [`maven-demo/src/test/java/de/rdbht/swt/AnonymizerTest.java`](./maven-demo/src/test/java/de/rdbht/swt/AnonymizerTest.java) — JUnit-5-Tests
- [`../.github/workflows/build.yml`](../.github/workflows/build.yml) — GitHub-Actions-Pipeline
