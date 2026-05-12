# Iteration 3 — REQ-E1 komplett

**Status:** umgesetzt am 2026-05-12. Reiter live unter `https://rdbht.github.io/SWT/req-e1.html`. Alle Features (F3.1–F3.5) implementiert; Validierungen (V3.1–V3.4) bestätigt.

## Ziel

REQ-E1-Reiter zeigt alle Abgabe-Artefakte (Teile A, B, C) übersichtlich und abgabefertig.

## Features

- F3.1 Reiter **REQ-E1** mit drei Sektionen: Teil A, Teil B, Teil C
- F3.2 Teil A: Link auf `req-e1/requirements-table.md` (7 Requirements, 15 Attribute)
- F3.3 Teil B: Link auf den RE-Tool-Reiter (Demo-Daten = Abgabe-Requirements)
- F3.4 Teil C: Links auf `mission.md`, `roadmap.md`, `techstack.md` sowie die drei Iter-Files
- F3.5 Permalink auf den Reiter für die Moodle-Abgabe: `https://rdbht.github.io/SWT/req-e1.html`

## Validierungen

**V3.1 — Reiter zeigt alle Teile**
- Given der REQ-E1-Reiter ist geöffnet
- When der Nutzer die Seite durchscrollt
- Then sind die Überschriften "Teil A", "Teil B" und "Teil C" mit jeweiligen Inhalten sichtbar

**V3.2 — Requirements-Tabelle ist gerendert**
- Given Teil A ist sichtbar
- When der Nutzer die Tabelle betrachtet
- Then sind alle Spalten (ID, Datum, Autor, ..., Kategorie) und 5–10 Zeilen vorhanden

**V3.3 — Constitution-Links funktionieren**
- Given Teil C ist sichtbar
- When der Nutzer auf "mission.md" klickt
- Then öffnet sich die entsprechende Datei im Repo (oder ein HTML-Renderer)

**V3.4 — Direktlink für Moodle**
- Given der REQ-E1-Reiter ist deployed
- When die Modulbetreuung den Moodle-Link öffnet
- Then erscheint `https://rdbht.github.io/SWT/req-e1.html` direkt mit allen Inhalten

## Out of Scope

- Reiter für künftige Abgaben (kommen mit den jeweiligen Lerneinheiten)
- Interaktives Editieren der Tabelle (Teil A bleibt statisch)
