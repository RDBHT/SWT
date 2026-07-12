# Spec — Modul `scheduler`

## Zweck
Taktgeber des Messpfads: führt periodisch alle Checks aus, schreibt Ergebnisse in
den Store und speist die Alerting-Pipeline.

## Öffentliche Schnittstelle
- `record MonitoredService(String name, Check check, String target)`
- `class Scheduler { Scheduler(List<MonitoredService>, HistoryStore, AlertingService);
  void runOnce(); void start(long intervalMs); void stop(); }`

## Fachregeln
- `runOnce()` ist ein deterministischer Durchlauf über alle Services (testbar)
- je Service: Check ausführen → `StatusRecord` anhängen → `alerting.tick(...)`
- `start(intervalMs)` ruft `runOnce()` periodisch über einen Scheduled-Executor

## Akzeptanztests (siehe `SchedulerTest`)
- Stub-Check (immer `UP`) + `runOnce()` → 1 Datensatz im Store, Status `UP`

## Abhängigkeiten
`checker`, `store`, `alerting` (Orchestrierungsmodul — verbindet den Messpfad)
