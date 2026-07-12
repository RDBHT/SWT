# Spec — Modul `alerting`

## Zweck
Erkennt anhaltende Ausfälle und alarmiert **genau einmal** pro Ausfall, nach einem
Entprellungs-Fenster. Kein Alarm-Spam bei flatternden Diensten.

## Öffentliche Schnittstelle
- `interface Clock { long nowMillis(); }` (injizierte Zeit)
- `interface AlertSink { void fire(String service, String message); }` (Strategy: Mail/Webhook)
- `class AlertingService { AlertingService(long alertAfterMs, Clock, AlertSink); void tick(service, Status); }`

## Fachregeln
- erster `DOWN`-Tick startet die Ausfall-Uhr
- ist ein Ziel länger als `alertAfterMs` durchgehend `DOWN` → **ein** `fire(...)`
- kein zweiter Alarm während desselben Ausfalls
- jeder Nicht-`DOWN`-Status setzt den Zustand zurück (danach wieder alarmierfähig)

## Akzeptanztests (siehe `AlertingServiceTest`)
- DOWN bei 0/60s/300s/360s, Fenster 300s → genau 1 Alarm
- DOWN → UP → DOWN bei Fenster 0 → 2 Alarme

## Abhängigkeiten
`checker` (für `Status`). Testbar ohne echte Uhr/Netz/Mail (FakeClock, CapturingSink).
