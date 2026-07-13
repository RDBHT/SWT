# Spec — Modul `scheduler`

## Zweck
Taktgeber des Messpfads: führt periodisch alle Checks aus, schreibt Ergebnisse in
den Store und speist die Alerting-Pipeline.

## Öffentliche Schnittstelle
- `record MonitoredService(String name, Check check, String target)`
- `interface ResultSink { void accept(StatusRecord record); }` — Ziel jedes Ergebnisses
- `class Scheduler { Scheduler(List<MonitoredService>, ResultSink);
  void runOnce(); void start(long intervalMs); void stop(); }`

## Fachregeln
- `runOnce()` ist ein deterministischer Durchlauf über alle Services (testbar)
- je Service: Check ausführen → `StatusRecord` bauen → an den `ResultSink` übergeben
- der Scheduler kennt **weder store noch alerting** — das ist die Naht, die den
  Agenten verteilbar macht (lokaler Sink speichert; verteilter Sink sendet per HTTP)
- `start(intervalMs)` ruft `runOnce()` periodisch über einen Scheduled-Executor

## Akzeptanztests (siehe `SchedulerTest`)
- Stub-Check (immer `UP`) + `runOnce()` → 1 Datensatz im Store, Status `UP`

## Abhängigkeiten
`checker`, `store` (für `StatusRecord`). **Nicht** mehr `alerting` — Verarbeitung
liegt hinter dem `ResultSink`.
