# Spec — Modul `collector` (Prozess)

## Zweck
Eigenständiger Prozess (Verarbeiter/Consumer): **empfängt** Check-Ergebnisse vom
Agenten, **verarbeitet** sie (Persistenz + Alerting) und **zeigt** das Dashboard.

## Öffentliche Schnittstelle
- `class CollectorServer { CollectorServer(HistoryStore, AlertingService); void start(int port); void stop(); }`
  - `POST /ingest` — Body (form-urlencoded) → `IngestCodec.decode` → `store.append` + `alerting.tick`
  - `GET /` — HTML-Dashboard aus `store.latestPerService()` (via `StatusRenderer`)
- `class CollectorMain { main(String[] args) }` — `args = [port]` (Default 8080)

## Fachregeln
- `POST /ingest` nur mit Methode POST (sonst 405); antwortet mit 200
- jedes empfangene Ergebnis wird gespeichert **und** in die Alerting-Pipeline gegeben
- Alerting-Fenster in der Demo: 15 s; Alarm wird auf der Konsole ausgegeben
- Dashboard ist **read-only** (schreibt nie in den Store)

## Verteiltheit
Der Collector kennt den Agenten nicht — er reagiert nur auf eingehende
`POST /ingest`. Mehrere Agenten könnten in denselben Collector schreiben.

## Abhängigkeiten
`store`, `alerting`, `dashboard-ui` (StatusRenderer), `checker`
