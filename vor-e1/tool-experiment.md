# Tool-Experiment — GitHub Issues

Teil 3 verlangt beweisbares Experimentieren mit einem Tool. Gewählt:
**GitHub Issues + Milestones** im bestehenden Repo RDBHT/SWT — kein separates
Projekt nötig, Issues sind öffentlich verlinkbar.

## Aufbau

- Jedes Backlog-Item aus `backlog.md` = ein **Issue**.
- **Labels** für Priorität (`prio:must` / `prio:should` / `prio:could`) und Epic.
- **Milestone `Sprint 1`** (2 Wochen) — die sieben Sprint-1-Items zugeordnet.
- Akzeptanzkriterium in der Issue-Beschreibung, Story Points im Titel (`[5 SP]`).
- Status über offen/geschlossen plus Label `status:in-progress`.

## Beobachtungen

- Issues + Milestones genügen als schlanker Backlog- und Sprint-Tracker — ohne
  Zusatz-Tool und ohne neues Projekt.
- Planung und Code sind gekoppelt: ein Commit `Closes #12` schließt das Item.
- Der Milestone-Fortschrittsbalken dient als einfaches Sprint-Tracking.
- Grenzen: kein Board mit WIP-Limit, keine Burndown-/Velocity-Auswertung — für
  ein kleines Projekt vertretbar.

## Nachweis

- Issues: `github.com/RDBHT/SWT/issues`
- Milestone Sprint 1: `github.com/RDBHT/SWT/milestone/1`
- Screenshots (Issue-Liste, Milestone-Ansicht): auf der Portfolio-Seite `docs/vor-e1.html`, Teil 3
