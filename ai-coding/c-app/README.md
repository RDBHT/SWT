# Teil C — Modulare, verteilte App

Die "richtige" Umsetzung des IT-Service-Monitors: modular geschnitten, verteilt, mit Claude Code (CLI) entwickelt und vollständig verstanden.

## Aufgabe

*"Code a bigger distributed app that you understand completely! The app must consist of separate modules! Develop small steps: step-by-step using md-Files!"*

## Module

| Modul | Verantwortung |
|---|---|
| `checker` | Check-Abstraktion + `HttpCheck` / `TcpCheck` / `DnsCheck` (Strategy Pattern) |
| `scheduler` | periodische Ausführung der Checks |
| `store` | Persistenz der Verlaufsdaten |
| `alerting` | Benachrichtigung bei Statuswechsel |
| `dashboard-ui` | Anzeige von Status und Verlauf |
| `mock-service` | kontrollierbares Test-Target (HTTP-Status + offener/geschlossener Port) |

## Verteilter Charakter

Check-Agent (checker + scheduler) und Dashboard (dashboard-ui) sind getrennte Komponenten mit klar definierter Schnittstelle über das `store`-Modul.

## Specification-Driven Development

Jedes Modul wird step-by-step über Spezifikations-MD-Files in [`specs/`](./specs) entwickelt — erst Spezifikation, dann Implementierung, dann Tests.

## Status

_(in Bearbeitung)_
