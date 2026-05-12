# Iteration 1 — Grundgerüst + DVC-E1

**Status:** umgesetzt am 2026-05-12. Alle Features (F1.1–F1.4) sind auf der Pages-Site live; alle Validierungen (V1.1–V1.3) wurden manuell bestätigt.

## Ziel

Lauffähige Site mit Navigation auf GitHub Pages, DVC-E1 nach-dokumentiert.

## Features

- F1.1 Basis-Layout (Header, Navigation, Content-Bereich, Footer) mit Pico.css
- F1.2 Reiter **Home** mit Mission und Übersicht der Abgaben
- F1.3 Reiter **DVC-E1** mit den 6 Aufgabenpunkten, Commit-Hashes und PR-Link
- F1.4 GitHub Pages aktiviert (Source: `main/docs`)

## Validierungen

**V1.1 — Site lädt**
- Given die Site ist deployed
- When der Nutzer `https://rdbht.github.io/SWT/` aufruft
- Then wird `index.html` ohne Fehler dargestellt

**V1.2 — Navigation funktioniert**
- Given die Site ist geladen
- When der Nutzer auf "DVC-E1" klickt
- Then öffnet sich `dvc-e1.html` mit aktivem Reiter

**V1.3 — DVC-E1-Inhalte sind vollständig**
- Given der DVC-E1-Reiter ist geöffnet
- When der Nutzer die Seite durchscrollt
- Then sind alle 6 Aufgabenpunkte, alle Commit-Hashes und der PR-Link `#615` sichtbar

## Out of Scope

- RE-Tool (Iter 2)
- REQ-E1-Reiter (Iter 3)
- Dark Mode, Theme-Switching
