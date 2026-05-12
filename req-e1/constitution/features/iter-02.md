# Iteration 2 — RE-Tool (AI-built)

**Status:** umgesetzt am 2026-05-12. Tool ist live unter `https://rdbht.github.io/SWT/re-tool.html`. Alle Features (F2.1–F2.7) implementiert; Validierungen (V2.1–V2.4) bestätigt.

## Ziel

Interaktives Requirements-Engineering-Tool als eigener Reiter, gebaut mit Vanilla JS, dokumentierte KI-Prompts.

## Features

- F2.1 Formular zum Anlegen eines Requirements mit den Attributen aus dem Skript: ID, Datum, Autor, Kurzbeschreibung, Lange Beschreibung, Status, Priorität, Kategorie, Abnahmekriterium, Abhängigkeiten, Juristische Relevanz, Anmerkungen
- F2.2 Tabellenansicht aller angelegten Requirements
- F2.3 Löschen eines Requirements
- F2.4 Persistenz im Browser (`localStorage`)
- F2.5 Export aller Requirements als JSON-Datei
- F2.6 Import aus JSON-Datei (ersetzt bestehende Daten)
- F2.7 Dokumentation der KI-Prompts in `req-e1/prompts.md`

## Validierungen

**V2.1 — Requirement anlegen**
- Given der RE-Tool-Reiter ist geöffnet
- When der Nutzer das Formular ausfüllt und "Speichern" klickt
- Then erscheint das Requirement in der Tabelle und wird im LocalStorage persistiert

**V2.2 — Persistenz über Reload**
- Given mindestens ein Requirement existiert
- When der Nutzer die Seite neu lädt
- Then ist das Requirement weiterhin sichtbar

**V2.3 — JSON-Export**
- Given mindestens ein Requirement existiert
- When der Nutzer "Export" klickt
- Then wird eine JSON-Datei heruntergeladen, die alle Requirements als Array enthält

**V2.4 — JSON-Import**
- Given eine valide JSON-Datei mit Requirements liegt vor
- When der Nutzer "Import" klickt und die Datei wählt
- Then sind die importierten Requirements in der Tabelle sichtbar und persistiert

## Out of Scope

- In-place-Editing (nur Anlegen und Löschen)
- Filter, Suche, Sortierung
- Multi-User, Backend, Datenbank
