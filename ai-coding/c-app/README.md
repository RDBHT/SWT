# Teil C — Modulare, verteilte App

Die „richtige" Umsetzung des IT-Service-Monitors: als **Maven-Multi-Module-Projekt**
in acht Module geschnitten (fünf Bibliotheken + **drei eigenständige Prozesse**),
spezifikationsgetrieben entwickelt, mit Tests und vollständig verstanden.

**Verteilt** heißt hier: `agent` und `collector` sind **getrennte Prozesse (JVMs)**,
die über HTTP kommunizieren — der Agent prüft und **sendet**, der Collector
**verarbeitet** und zeigt an. Ausführliche Erläuterung mit Diagramm:
**[VERTEILUNG.md](./VERTEILUNG.md)**.

## Aufgabe

*„Code a bigger distributed app that you understand completely! The app must consist
of separate modules! Develop small steps: step-by-step using md-Files!"*

## Module & Abhängigkeiten

| Modul | Art | Verantwortung | hängt ab von |
|---|---|---|---|
| [`checker`](./checker) | Lib | Check-Abstraktion (Strategy) + `HttpCheck`/`TcpCheck`/`DnsCheck` + `StatusEvaluator` | — |
| [`store`](./store) | Lib | `StatusRecord`, `HistoryStore` (In-Memory), `IngestCodec` (Wire-Format) | checker |
| [`alerting`](./alerting) | Lib | Ausfall-Erkennung mit Entprellung; `AlertSink` (Strategy) | checker |
| [`scheduler`](./scheduler) | Lib | periodische Ausführung; übergibt Ergebnisse an einen `ResultSink` | checker, store |
| [`dashboard-ui`](./dashboard-ui) | Lib | `StatusRenderer` (+ lokaler `DashboardServer`) | store, checker |
| [`mock-service`](./mock-service) | **Prozess** | kontrollierbares Test-Target (settable HTTP-Code) | — |
| [`agent`](./agent) | **Prozess** | prüft periodisch, sendet Ergebnisse per HTTP an den Collector | checker, scheduler, store |
| [`collector`](./collector) | **Prozess** | `POST /ingest` (store + alerting), `GET /` (Dashboard) | store, alerting, dashboard-ui, checker |

```
Bibliotheken:  checker → store → { scheduler, dashboard-ui } ;  checker → alerting
Prozesse:      agent (scheduler+checker+store) ── HTTP POST /ingest ──▶ collector (store+alerting+dashboard-ui)
               agent ── HTTP-Check ──▶ mock-service
```

## Verteilter Charakter (Kern der Aufgabe)

Drei **getrennte Prozesse**, die über das **Netzwerk** kommunizieren — kein
gemeinsamer Speicher:

1. **`mock-service`** (Prozess) — überwachtes HTTP-Ziel.
2. **`agent`** (Prozess) — führt Checks aus und **sendet** jedes Ergebnis per
   `HTTP POST /ingest` an den Collector (Produzent).
3. **`collector`** (Prozess) — **empfängt**, speichert, alarmiert und zeigt das
   Dashboard (Verarbeiter/Consumer).

Die Entkopplung im Code ist das `ResultSink`-Interface: der `scheduler` übergibt
Ergebnisse an einen Sink; im verteilten Betrieb ist das der `IngestClient`
(HTTP). Details und Start-Anleitung: **[VERTEILUNG.md](./VERTEILUNG.md)**.

## Bauen & Testen

Voraussetzung: **JDK 17 + Maven 3.9+**.

```bash
cd ai-coding/c-app
mvn -B test     # baut alle Module (Reactor) und führt die JUnit-5-Tests aus
```

Ergebnis lokal bestätigt: **`BUILD SUCCESS`, 13 Tests grün** über alle Module
(checker 5, store 3, alerting 2, scheduler 1, dashboard-ui 1, mock-service 1;
agent/collector kompilieren). Vollständiges Protokoll **beider** Läufe — inklusive
eines Tests, der einen echten Fehler gefunden hat — in **[TESTLAUF.md](./TESTLAUF.md)**.

## Verteilt starten (drei Prozesse)

```bash
mvn -q -DskipTests install                 # einmalig alle Module installieren
mvn -q -pl mock-service exec:java          # Terminal 1 — Ziel (:8081)
mvn -q -pl collector   exec:java           # Terminal 2 — Collector (:8080)
mvn -q -pl agent       exec:java           # Terminal 3 — Agent (prüft + sendet)
```

Dashboard: `http://localhost:8080/`. Ausfall simulieren:
`curl "http://localhost:8081/admin?code=500"`. Vollständige Erläuterung inkl.
Prozess-Diagramm: **[VERTEILUNG.md](./VERTEILUNG.md)**.

## Specification-Driven Development

Jedes Modul entstand step-by-step über ein Spezifikations-MD-File in
[`specs/`](./specs) — **erst Spezifikation, dann Implementierung, dann Tests**.

## Werkzeuge & Werkzeug-Nachweis

- **Claude Code (CLI):** `checker`, `store`, `alerting`, `scheduler`, `dashboard-ui`, `agent`, `collector`
- **Cline (VS-Code-Plugin):** `mock-service` — belegt die **zweite Werkzeug-Art**

## Tests im Überblick

| Modul | Test | prüft |
|---|---|---|
| checker | `StatusEvaluatorTest` | Statuslogik + Exceptions (5 Tests) |
| store | `StoreTest`, `IngestCodecTest` | Reihenfolge/Dedup; Wire-Format Round-Trip |
| alerting | `AlertingServiceTest` | Entprellung: genau ein Alarm; Recovery |
| scheduler | `SchedulerTest` | ein Ergebnis je Service an den Sink |
| dashboard-ui | `StatusRendererTest` | HTML je Service |
| mock-service | `MockServiceTest` | Health-Code setzbar |

## Nachweise (vor dem Push zu ergänzen)

- **Testlauf:** dokumentiert in [TESTLAUF.md](./TESTLAUF.md) (rot→grün, 13 Tests); optional Screenshot → `docs/img/c-app-tests.png`
- **[SCREENSHOT] 2:** drei laufende Prozesse + Dashboard → siehe [VERTEILUNG.md](./VERTEILUNG.md)
- **[SCREENSHOT] 3:** Claude Code Planning Mode / Cline (mock-service) → `docs/img/c-app-planning.png`, `docs/img/c-app-cline.png`
- **Prompts:** je Modul in [`prompts.md`](./prompts.md) eintragen

## Status

Substanz fertig und **lokal grün getestet** (acht Module, davon drei Prozesse;
Specs + 13 Tests; verteilt lauffähig — siehe [TESTLAUF.md](./TESTLAUF.md); Prompts
dokumentiert in [prompts.md](./prompts.md)). Offen: die Werkzeug-/Verteiltheits-
Screenshots (drei Prozesse + Dashboard, Cline) — ggf. Prompts an deine Session angleichen.
