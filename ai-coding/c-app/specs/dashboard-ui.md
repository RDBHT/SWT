# Spec — Modul `dashboard-ui`

## Zweck
Zeigt den aktuellen Status je Service an. **Read-only**: liest ausschließlich über
die Store-API, schreibt nie (Client-Server-Schnitt gegenüber dem Check-Agent).

## Öffentliche Schnittstelle
- `class StatusRenderer { String render(List<StatusRecord> latest); }` (rein, testbar)
- `class DashboardServer { DashboardServer(HistoryStore); void start(int port); void stop(); }`

## Fachregeln
- `render(...)` erzeugt je Service eine Zeile (Service, Status, Antwortzeit)
- `DashboardServer` liefert unter `/` eine HTML-Seite aus `store.latestPerService()`
- keine Schreibzugriffe auf den Store

## Akzeptanztests (siehe `StatusRendererTest`)
- zwei Services (api UP, db DOWN) → HTML enthält „api" und „DOWN"

## Abhängigkeiten
`store`, `checker`. Rendering (rein) ist von der HTTP-Auslieferung (I/O) getrennt.
