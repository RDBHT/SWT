# Teil C — Modulare, verteilte App

Die „richtige" Umsetzung des IT-Service-Monitors: als **Maven-Multi-Module-Projekt**
in sechs separate Module geschnitten, spezifikationsgetrieben entwickelt, mit Tests
und vollständig verstanden (prüfungsfähig).

## Aufgabe

*„Code a bigger distributed app that you understand completely! The app must consist
of separate modules! Develop small steps: step-by-step using md-Files!"*

## Module & Abhängigkeiten

| Modul | Verantwortung | hängt ab von |
|---|---|---|
| [`checker`](./checker) | Check-Abstraktion (Strategy) + `HttpCheck`/`TcpCheck`/`DnsCheck` + `StatusEvaluator` | — |
| [`store`](./store) | Persistenz der Verlaufsdaten (`HistoryStore`, In-Memory) | checker |
| [`alerting`](./alerting) | Ausfall-Erkennung mit Entprellung; `AlertSink` (Strategy) | checker |
| [`scheduler`](./scheduler) | periodische Ausführung; verbindet den Messpfad | checker, store, alerting |
| [`dashboard-ui`](./dashboard-ui) | read-only HTTP-Dashboard (liest nur über Store-API) | store, checker |
| [`mock-service`](./mock-service) | kontrollierbares Test-Target (settable HTTP-Code) | — |

```
checker ─┬─► store ─────┐
         ├─► alerting ──┤
         │              ├─► scheduler
         └──────────────┘
store, checker ─► dashboard-ui        mock-service (eigenständig)
```

## Bauen & Testen

Voraussetzung: **JDK 17 + Maven 3.9+**.

```bash
cd ai-coding/c-app
mvn -B test     # baut alle Module (Reactor) und führt die JUnit-5-Tests aus
```

Erwartung: `BUILD SUCCESS`, Tests grün in `checker`, `store`, `alerting`,
`scheduler`, `dashboard-ui`, `mock-service`.

> **[SCREENSHOT — lokal einzufügen]:** `mvn -B test` mit `BUILD SUCCESS` →
> `docs/img/c-app-tests.png` (Nachweis des lokalen Testlaufs, analog zu TST-E1).
> Build-Umgebung ohne Maven/JDK 17? Setup wie in BUI-E1 beschrieben.

## Verteilter Charakter

Check-Agent (`scheduler` + `checker` + `alerting`) und `dashboard-ui` sind getrennt
deploybare Komponenten. Einzige Koppelstelle ist die **Store-API** — das Dashboard
liest ausschließlich (`latestPerService()`), es schreibt nie.

## Verdrahtung (Demo-Betrieb)

```java
// mock-service als Test-Target starten
MockService mock = new MockService();
mock.start(8081);                               // GET /health, /admin?code=NNN

HistoryStore store = new InMemoryHistoryStore();
AlertingService alerting = new AlertingService(
        300_000, System::currentTimeMillis,     // 5-Minuten-Fenster
        (svc, msg) -> System.out.println("ALERT " + svc + ": " + msg));

Check http = new HttpCheck(new StatusEvaluator(500), 2000);
Scheduler scheduler = new Scheduler(List.of(
        new MonitoredService("mock", http, "http://localhost:8081/health")),
        store, alerting);
scheduler.start(5000);                          // alle 5 s prüfen

new DashboardServer(store).start(8080);         // Dashboard auf :8080
```

## Specification-Driven Development

Jedes Modul entstand step-by-step über ein Spezifikations-MD-File in
[`specs/`](./specs) — **erst Spezifikation, dann Implementierung, dann Tests**
(Zweck, öffentliche Schnittstelle, Fachregeln, Akzeptanztests je Modul).

## Werkzeuge & Werkzeug-Nachweis

- **Claude Code (CLI):** `checker`, `store`, `alerting`, `scheduler`, `dashboard-ui`
- **Cline (VS-Code-Plugin):** `mock-service` — belegt die **zweite Werkzeug-Art**

## Tests im Überblick

| Modul | Test | prüft |
|---|---|---|
| checker | `StatusEvaluatorTest` | Statuslogik + Exceptions (5 Tests) |
| store | `StoreTest` | Reihenfolge & Deduplizierung |
| alerting | `AlertingServiceTest` | Entprellung: genau ein Alarm; Recovery |
| scheduler | `SchedulerTest` | ein Datensatz je Service pro Durchlauf |
| dashboard-ui | `StatusRendererTest` | HTML je Service |
| mock-service | `MockServiceTest` | Health-Code setzbar |

## Nachweise (vor dem Push zu ergänzen)

- **[SCREENSHOT] 1:** `mvn -B test` grün → `docs/img/c-app-tests.png`
- **[SCREENSHOT] 2:** Claude Code im Planning Mode (ein Modul) → `docs/img/c-app-planning.png`
- **[SCREENSHOT] 3:** Cline beim Bau von `mock-service` → `docs/img/c-app-cline.png`
- **Prompts:** je Modul in [`prompts.md`](./prompts.md) eintragen

## Status

Substanz fertig (sechs Module + Specs + Tests, deploybar); offen sind nur die
eigenen Prompts, die Werkzeug-Screenshots und der lokale `mvn test`-Nachweis.
