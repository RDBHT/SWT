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

> Hinweis: Der Einsatz von GitHub Actions als CI/CD-Teil wurde mit der
> Kursbetreuung abgestimmt (siehe Aufgabenstellung: „Stimmen Sie dies bitte
> mit der Kursbetreuung ab!").

## 2. Beispielprojekt

Bewusst minimal gehalten — es geht um das **Prinzip der Pipeline**, nicht um
den Umfang. Eine Utility-Klasse plus ein JUnit-5-Test, der alle relevanten
Lifecycle-Phasen auslöst (compile → test → package → run).

```
bui-e1/maven-demo/
├── pom.xml                                  # Maven-Steuerdatei
└── src/
    ├── main/java/de/rdbht/swt/Anonymizer.java       # maskEmail("anna@x.de") -> "a***@x.de"
    └── test/java/de/rdbht/swt/AnonymizerTest.java   # 2 JUnit-5-Tests
```

`Anonymizer.maskEmail(...)` maskiert den lokalen Teil einer E-Mail-Adresse —
ein kleiner, themennaher Bezug (Datensparsamkeit) ohne echte Daten.

## 3. Maven — lokaler Build

Ausgeführt in der Build-Umgebung: **JDK 17 (Temurin)**, **Maven 3.9.9**.

```bash
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

Maven beendet mit Exit-Code ≠ 0 und **erreicht die `package`-Phase nicht** —
es entsteht kein JAR. Dieselbe Mechanik stoppt in Schritt 5 die CI-Pipeline
(roter Run), bevor fehlerhafter Code weiterverarbeitet wird.

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
