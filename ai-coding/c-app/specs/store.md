# Spec — Modul `store`

## Zweck
Persistiert den Verlauf der Status-Beobachtungen. Einzige Koppelstelle zwischen
Check-Agent (schreibt) und Dashboard (liest).

## Öffentliche Schnittstelle
- `record StatusRecord(String service, Status status, long responseMs, Instant timestamp)`
- `interface HistoryStore { append(...); recent(service, limit); latestPerService(); }`
- `class InMemoryHistoryStore implements HistoryStore`

## Fachregeln
- `recent(service, limit)` liefert die neuesten Datensätze **zuerst**
- `latestPerService()` liefert je Service genau den jüngsten Datensatz
- Implementierung thread-sicher (Scheduler schreibt, Dashboard liest nebenläufig)

## Akzeptanztests (siehe `StoreTest`)
- zwei Datensätze für „api" → `recent` gibt den jüngeren an Position 0
- „api" + „db" + erneut „api" → `latestPerService()` hat 2 Einträge

## Abhängigkeiten
`checker` (für `Status`)

## Austauschbarkeit
`HistoryStore` ist die Naht: eine SQLite-Implementierung ersetzt `InMemoryHistoryStore`
ohne Änderung an Scheduler oder Dashboard.
