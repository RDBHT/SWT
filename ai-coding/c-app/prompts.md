# Prompts — Teil C · Modulare App

Werkzeuge: **Claude Code (CLI)** für sieben Module; **Cline** (VS-Code-Plugin) für
`mock-service`. Vorgehen je Modul spezifikationsgetrieben: Spec lesen → **Planning Mode**
(Plan zeigen/bestätigen) → Implementierung → Tests → Commit.

> **Hinweis:** Diese Prompts spiegeln den tatsächlichen Bau wider. Trag bei eigenen
> Läufen die exakten Wortlaute und je einen Planning-Mode-Screenshot ein.

## Schema je Modul
**1.** Spec (`specs/<modul>.md`) → **2.** Plan (Planning Mode) prüfen → **3.** Write →
**4.** Tests grün → **5.** Commit (Conventional Commits).

## checker (Claude Code)
> Lies `specs/checker.md`. Plane — noch **ohne** zu schreiben — die Umsetzung: `enum Status`,
> `record CheckResult`, `interface Check`, eine reine `StatusEvaluator`-Klasse (HTTP-Code +
> Antwortzeit → Status, mit `IllegalArgumentException` bei nicht-positivem Schwellwert oder
> negativer Zeit) sowie `HttpCheck`/`TcpCheck`/`DnsCheck`. Zeig mir zuerst den Plan.

Planning-Notiz: Kern (`StatusEvaluator`) I/O-frei halten → unit-testbar; Check-Typen als Strategy.
> Setz den bestätigten Plan um und schreib JUnit-5-Tests für `StatusEvaluator` inkl. der beiden Exception-Fälle.

## store (Claude Code)
> Lies `specs/store.md`. Plane `record StatusRecord`, `interface HistoryStore`
> (`append`/`recent`/`latestPerService`) und eine thread-sichere `InMemoryHistoryStore`;
> `recent()` liefert neueste zuerst. Danach umsetzen + `StoreTest`.

## alerting (Claude Code)
> Lies `specs/alerting.md`. Plane eine `AlertingService`-Zustandsmaschine mit Entprellung:
> genau **ein** Alarm pro Ausfall nach `alertAfterMs`, Reset bei Erholung; injizierte
> `Clock` und `AlertSink` (Strategy). Umsetzen + `AlertingServiceTest` mit FakeClock/CapturingSink.

Fix nach rotem Test (siehe [TESTLAUF.md](./TESTLAUF.md)):
> Der Test `recovery_allows_next_alert` schlägt fehl (erwartet 2, war 0): bei Fenster 0 hat
> jede DOWN-Strecke nur einen Tick. Zieh die Fire-Prüfung auf **denselben** Tick, der
> `downSince` setzt (`putIfAbsent`), sodass ein Null-Fenster sofort feuert. Tests wieder grün.

## scheduler (Claude Code)
> Lies `specs/scheduler.md`. Der Scheduler soll `store`/`alerting` **nicht** direkt kennen,
> sondern jedes Ergebnis an ein `ResultSink`-Interface übergeben (das macht ihn verteilbar).
> Plane `Scheduler(services, ResultSink)` mit `runOnce()`/`start()`. Umsetzen + `SchedulerTest`
> mit einem sammelnden Sink.

## dashboard-ui (Claude Code)
> Lies `specs/dashboard-ui.md`. Trenne reines Rendering (`StatusRenderer` → HTML je Service,
> testbar) von der HTTP-Auslieferung (`DashboardServer`, read-only). Umsetzen + `StatusRendererTest`.

## mock-service (Cline — VS-Code-Plugin, Werkzeug-Nachweis)
> [in Cline] Lies `specs/mock-service.md` und baue ein kontrollierbares HTTP-Test-Target:
> `GET /health` liefert einen zur Laufzeit setzbaren Code (Default 200), `GET /admin?code=NNN`
> setzt ihn, `main()` startet auf Port 8081. Kleiner Test, dass der Code setzbar ist.

**[SCREENSHOT]** Cline im Einsatz → `docs/img/c-app-cline.png`

## Verteilung: agent + collector (Claude Code)
> Bau die App **echt verteilt**: ein `agent`-Prozess (scheduler + checker) sendet jedes
> Ergebnis per HTTP POST an einen `collector`-Prozess. Plane: `IngestCodec` (form-urlencoded
> `StatusRecord` ↔ String) im `store`; `IngestClient implements ResultSink` im `agent`, der
> per `java.net.http.HttpClient` an `/ingest` postet; `CollectorServer` mit `POST /ingest`
> (decode → store + alerting) und `GET /` (Dashboard); `AgentMain` und `CollectorMain`;
> `exec-maven-plugin` je Prozess. Zeig zuerst den Plan.

Planning-Notiz: `ResultSink` ist die Naht (lokal speichern vs. verteilt senden); Protokoll
bewusst ohne JSON-Bibliothek, damit es komplett erklärbar bleibt.

---

**Reflexion:** Der Agent plante sauber entlang der Specs; bewusst **selbst** entschieden habe
ich den Modulschnitt, die `ResultSink`-Naht (Verteilbarkeit) und das schlanke Protokoll. Ein
Test hat einen echten Alerting-Grenzfall aufgedeckt — rot → Fix → grün (siehe TESTLAUF.md).
