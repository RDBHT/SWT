# Spec — Modul `checker`

## Zweck
Führt einen einzelnen Health-Check gegen ein Ziel aus und liefert ein Ergebnis.
Drei Check-Typen (HTTP, TCP, DNS) hinter einer gemeinsamen Abstraktion (Strategy).

## Öffentliche Schnittstelle
- `interface Check { String type(); CheckResult run(String target); }`
- `record CheckResult(Status status, long responseMs, String detail)`
- `enum Status { UP, DEGRADED, DOWN, UNKNOWN }`
- `class StatusEvaluator` — reine Abbildung `(httpCode, responseMs) -> Status`

## Fachregeln
- HTTP-Code 2xx/3xx **und** Antwortzeit ≤ Schwellwert → `UP`
- 2xx/3xx, aber Antwortzeit > Schwellwert → `DEGRADED`
- alles andere / Fehler → `DOWN`
- `StatusEvaluator`: Schwellwert ≤ 0 oder negative Antwortzeit → `IllegalArgumentException`

## Akzeptanztests (Auszug, siehe `StatusEvaluatorTest`)
- `evaluate(200, 120)` → `UP`
- `evaluate(200, 800)` → `DEGRADED`
- `evaluate(503, 50)` → `DOWN`
- `new StatusEvaluator(0)` wirft `IllegalArgumentException`

## Abhängigkeiten
keine (Basismodul)
