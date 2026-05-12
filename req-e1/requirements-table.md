# Requirements — Teil A (Self-made-Tool: Markdown)

Selbst geführte Markdown-Tabelle für das Projekt **SWT-Portfolio**.
Identisch zum Inhalt im RE-Tool (Teil B, `docs/re-tool.js`, `DEMO_DATA`).

Editor: VS Code. Versionierung: Git. Keine kommerzielle Tooling-Schicht.

## Verwendete Beschreibungsattribute (15)

`ID`, `Datum`, `Autor`, `Historie`, `Kurzbeschreibung`, `Lange Beschreibung`, `Status`, `Priorität`, `Kategorie`, `Abnehmer`, `Abnahmekriterium`, `Abhängigkeiten`, `Referenz`, `Juristische Relevanz`, `Anmerkungen`

## Übersicht

| ID | Status | Prio | Kategorie | Abnehmer | Kurzbeschreibung |
|---|---|---|---|---|---|
| REQ-001 | Erledigt | A | Funktional | Modulbetreuung | Nutzer kann zwischen Reitern navigieren |
| REQ-002 | Erledigt | A | Funktional | Modulbetreuung | Site ist über GitHub Pages erreichbar |
| REQ-003 | Erledigt | A | Funktional | Tech-Lead | RE-Tool persistiert Eingaben im Browser |
| REQ-004 | Erledigt | B | Funktional | Modulbetreuung | JSON-Export und -Import |
| REQ-005 | Erledigt | A | Sicherheit | Datenschutz / Legal | Keine personenbezogenen Daten verarbeitet |
| REQ-006 | Erledigt | B | UX | Modulbetreuung | Site ist mobil bedienbar |
| REQ-007 | Erledigt | B | Funktional | Modulbetreuung | Constitution ist vom REQ-E1-Reiter aus erreichbar |

---

## Details

### REQ-001 — Nutzer kann zwischen Reitern navigieren

| Attribut | Wert |
|---|---|
| Datum | 2026-05-12 |
| Autor | RD |
| Historie | v1.0 (2026-05-12) |
| Status | Erledigt |
| Priorität | A — Muss |
| Kategorie | Funktional |
| Abnehmer | Modulbetreuung |
| Abnahmekriterium | Jeder Reiter ist von jedem anderen Reiter aus per Klick erreichbar; der aktive Reiter wird visuell markiert. |
| Abhängigkeiten | — |
| Referenz | iter-01.md / F1.1 |
| Juristische Relevanz | nein |
| Anmerkungen | Iter 1, abgeschlossen |

**Lange Beschreibung:** Die Top-Navigation der SWT-Portfolio-Site erlaubt das Wechseln zwischen Home, DVC-E1, REQ-E1 und RE-Tool von jeder Unterseite aus.

---

### REQ-002 — Site ist über GitHub Pages erreichbar

| Attribut | Wert |
|---|---|
| Datum | 2026-05-12 |
| Autor | RD |
| Historie | v1.0 (2026-05-12) |
| Status | Erledigt |
| Priorität | A — Muss |
| Kategorie | Funktional |
| Abnehmer | Modulbetreuung |
| Abnahmekriterium | URL `https://rdbht.github.io/SWT/` liefert HTTP 200 und rendert die Landing innerhalb von 2 Sekunden. |
| Abhängigkeiten | — |
| Referenz | iter-01.md / F1.4 |
| Juristische Relevanz | nein |
| Anmerkungen | Iter 1, abgeschlossen |

**Lange Beschreibung:** Die Site wird aus dem `main`-Branch, Ordner `/docs` deployed.

---

### REQ-003 — RE-Tool persistiert Eingaben im Browser

| Attribut | Wert |
|---|---|
| Datum | 2026-05-12 |
| Autor | RD |
| Historie | v1.0 (2026-05-12) |
| Status | Erledigt |
| Priorität | A — Muss |
| Kategorie | Funktional |
| Abnehmer | Tech-Lead |
| Abnahmekriterium | Nach Reload zeigt die Tabelle dieselben Einträge wie vor dem Reload. |
| Abhängigkeiten | REQ-002 |
| Referenz | iter-02.md / F2.4 |
| Juristische Relevanz | nein |
| Anmerkungen | Iter 2, abgeschlossen |

**Lange Beschreibung:** Eingegebene Requirements werden im LocalStorage unter Key `swt-re-tool` gehalten und überleben einen Page-Reload.

---

### REQ-004 — JSON-Export und -Import

| Attribut | Wert |
|---|---|
| Datum | 2026-05-12 |
| Autor | RD |
| Historie | v1.0 (2026-05-12) |
| Status | Erledigt |
| Priorität | B — Soll |
| Kategorie | Funktional |
| Abnehmer | Modulbetreuung |
| Abnahmekriterium | Export erzeugt `swt-requirements.json`; deren Import stellt den identischen Datenstand wieder her. |
| Abhängigkeiten | REQ-003 |
| Referenz | iter-02.md / F2.5 + F2.6 |
| Juristische Relevanz | nein |
| Anmerkungen | Iter 2, abgeschlossen |

**Lange Beschreibung:** Alle Requirements können als JSON-Datei heruntergeladen werden; eine valide JSON-Datei kann den Datenstand wiederherstellen.

---

### REQ-005 — Keine personenbezogenen Daten verarbeitet

| Attribut | Wert |
|---|---|
| Datum | 2026-05-12 |
| Autor | RD |
| Historie | v1.0 (2026-05-12) |
| Status | Erledigt |
| Priorität | A — Muss |
| Kategorie | Sicherheit |
| Abnehmer | Datenschutz / Legal |
| Abnahmekriterium | Keine Netz-Requests an Dritte beim Anlegen, Speichern oder Löschen eines Requirements (Pico.css wird ausschließlich beim ersten Seitenaufruf vom CDN geladen). |
| Abhängigkeiten | — |
| Referenz | DSGVO Art. 5 (Datenminimierung) |
| Juristische Relevanz | **ja** |
| Anmerkungen | DSGVO-relevant |

**Lange Beschreibung:** Das Tool läuft rein im Browser. Es gibt kein Backend, kein Tracking und keine Analytics.

---

### REQ-006 — Site ist mobil bedienbar

| Attribut | Wert |
|---|---|
| Datum | 2026-05-12 |
| Autor | RD |
| Historie | v1.0 (2026-05-12) |
| Status | Erledigt |
| Priorität | B — Soll |
| Kategorie | UX |
| Abnehmer | Modulbetreuung |
| Abnahmekriterium | Alle Reiter sind auf 320 px Viewport-Breite lesbar; die Requirements-Tabelle erhält horizontalen Scrollbar bei Bedarf. |
| Abhängigkeiten | REQ-002 |
| Referenz | Pico.css responsive defaults |
| Juristische Relevanz | nein |
| Anmerkungen | automatisch via Pico.css |

**Lange Beschreibung:** Layout und Tabellen passen sich Viewport-Breiten ab 320 px an; breite Tabellen sind horizontal scrollbar.

---

### REQ-007 — Constitution ist vom REQ-E1-Reiter aus erreichbar

| Attribut | Wert |
|---|---|
| Datum | 2026-05-12 |
| Autor | RD |
| Historie | v1.0 (2026-05-12) |
| Status | Erledigt |
| Priorität | B — Soll |
| Kategorie | Funktional |
| Abnehmer | Modulbetreuung |
| Abnahmekriterium | Alle sieben Constitution-Dateien sind aus dem REQ-E1-Reiter mit einem Klick erreichbar (Repo-Links). |
| Abhängigkeiten | REQ-001 |
| Referenz | iter-03.md / F3.4 |
| Juristische Relevanz | nein |
| Anmerkungen | Iter 3 |

**Lange Beschreibung:** Im REQ-E1-Reiter sind `mission.md`, `roadmap.md`, `techstack.md` und die drei Iterations-Feature-Specs verlinkt.
