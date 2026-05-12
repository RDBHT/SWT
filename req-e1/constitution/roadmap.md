# Roadmap

Drei Iterationen, die aufeinander aufbauen.

## Iteration 1 — Grundgerüst + DVC-E1

**Ziel:** Lauffähige Site mit Navigation, GitHub Pages live, DVC-E1 nach-dokumentiert.

- `index.html` (Landing mit Übersicht)
- `dvc-e1.html` (Inhalte: 6 Aufgabenpunkte, Commit-Hashes, PR-Link)
- `style.css` (Basis-Layout, Navigation, Typografie)
- GitHub Pages aktiviert, Source = `main/docs`

## Iteration 2 — RE-Tool (Tool B, AI-built)

**Ziel:** Interaktives Requirements-Tool als eigener Reiter.

- `re-tool.html` (Formular zum Anlegen/Bearbeiten von Requirements)
- `re-tool.js` (Verwaltung im Browser, Felder gem. Attributliste aus dem Skript)
- Persistenz im Browser (LocalStorage), Export/Import als JSON
- Dokumentation der KI-Prompts in `req-e1/prompts.md`

## Iteration 3 — REQ-E1 komplett

**Ziel:** REQ-E1-Reiter mit allen Artefakten, abgabefertig.

- `req-e1.html` (Übersicht der Aufgabenteile A/B/C)
- Markdown-Tabelle mit 5–10 Requirements (Teil A)
- Constitution-Links (Teil C)
- PDF-Download für Moodle-Abgabe
