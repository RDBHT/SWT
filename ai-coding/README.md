# Vibe & AI Coding — Präsenz 1

Präsenzaufgabe "Vibe & AI Coding".

## Durchgängiges Projekt: IT-Service-Monitor

Ein Tool, das interne und externe Services per Health-Check periodisch überwacht, den Verlauf speichert und bei Ausfällen alarmiert. Drei Check-Typen: **HTTP**, **TCP-Port**, **DNS**.

Dasselbe Projektkonzept zieht sich durch alle drei Aufgabenteile — vom schnellen Vibe-Coding bis zur modular gebauten, verstandenen App.

## Aufgabenteile

| Teil | Inhalt | Werkzeug | Ordner |
|---|---|---|---|
| A | UI für den Monitor vibe-coden | Google Stitch | [`a-ui/`](./a-ui) |
| B | Lauffähige Vibe-Version (Pet-Project) | bolt.new + GitHub Pages | [`b-vibe-app/`](./b-vibe-app) |
| C | Modulare, verteilte App — vollständig verstanden | Claude Code (CLI) | [`c-app/`](./c-app) |

## Werkzeug-Nachweis

Teil C wird überwiegend mit **Claude Code (CLI)** umgesetzt. Das Modul `mock-service` wird stattdessen mit **Cline** (VS-Code-Plugin, Anthropic-API) gebaut. Damit ist die geforderte zweite Werkzeug-Art (VS-Code-Plugin) im selben Projekt nachgewiesen — belegt über Commit-Historie, Screenshots und Prompts.

## Kern-Regeln (Aufgabentext)

- Teil C: **separate Module**, Entwicklung **step-by-step mit MD-Files** (Specification-Driven Development)
- Der Code muss **vollständig verstanden** sein — mündliche Prüfung
- Prompts zeigen, Eigenleistung dokumentieren

## Abgabe

Link auf den AI-Coding-Reiter der GitHub-Pages-Site: `https://rdbht.github.io/SWT/ai-coding.html`
