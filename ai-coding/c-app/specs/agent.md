# Spec — Modul `agent` (Prozess)

## Zweck
Eigenständiger Prozess (Produzent): prüft periodisch die konfigurierten Ziele und
**sendet** jedes Ergebnis über HTTP an den Collector. Speichert/bewertet selbst nichts.

## Öffentliche Schnittstelle
- `class IngestClient implements ResultSink` — `accept(StatusRecord)` sendet per
  `HTTP POST /ingest` (Body via `IngestCodec.encode`)
- `class AgentMain { main(String[] args) }` — `args = [collectorIngestUrl] [httpTarget] [tcpTarget]`

## Fachregeln
- Der `scheduler` bekommt den `IngestClient` als `ResultSink` → jedes Check-Ergebnis
  wird sofort gesendet (kein lokaler Store)
- Standard: prüft `mock-http` (HTTP `/health`) und `mock-tcp` (TCP-Port) alle 5 s
- Sende-Fehler werden geloggt, brechen den Agenten nicht ab

## Verteiltheit
Netzwerk-Grenze zum Collector (`POST /ingest`) und zum Ziel (HTTP-Check). Der Agent
ist auf einem beliebigen Host lauffähig — nur die Collector-URL ändert sich.

## Abhängigkeiten
`checker`, `scheduler`, `store` (für `IngestCodec`/`StatusRecord`)
