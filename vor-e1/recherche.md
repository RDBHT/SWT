# Recherche — Scrum, Kanban und Tools

## Scrum

Empirisches Rahmenwerk für komplexe Produktentwicklung (Schwaber/Sutherland,
Scrum Guide 2020). Drei Säulen: **Transparenz, Inspektion, Adaption**.

- **Rollen:** Product Owner (Wert, Backlog) · Scrum Master (Prozess, Hindernisse) · Developers (Increment).
- **Events:** Sprint (Container, ≤ 1 Monat) · Sprint Planning · Daily Scrum · Sprint Review · Retrospektive.
- **Artefakt → Commitment:** Product Backlog → Product Goal · Sprint Backlog → Sprint Goal · Increment → Definition of Done.

## Kanban

Evolutionäre Methode zur Steuerung von Arbeitsfluss (David J. Anderson) — keine
Rollen, keine Iterationen, setzt auf dem bestehenden Prozess auf.

- **Sechs Praktiken:** visualisieren · WIP limitieren · Fluss managen · Regeln explizit machen · Feedback-Schleifen · gemeinsam verbessern.
- **Kennzahlen:** Lead Time · Cycle Time · Throughput · Cumulative Flow Diagram.

## Vergleich Scrum / Kanban

| Aspekt | Scrum | Kanban |
|---|---|---|
| Kadenz | feste Sprints | kontinuierlicher Fluss |
| Rollen | drei definierte Rollen | keine vorgeschrieben |
| Änderung in der Iteration | Sprint-Umfang stabil | Prioritäten jederzeit änderbar |
| Begrenzung | Sprint-Commitment | explizite WIP-Limits |
| Zentrale Metrik | Velocity | Cycle Time / Durchsatz |
| Stärke bei | Produktaufbau mit Zielbild | Betrieb, Wartung, wechselnde Prioritäten |

## Tool-Recherche

| Tool | Kosten | Scrum-Funktionen | Eignung hier |
|---|---|---|---|
| **GitHub (Issues/Milestones/Projects)** | kostenlos | Issues, Milestones, optional Projects-Board mit Iterationen | sehr hoch — im Repo, kein Kontextwechsel |
| Jira | kostenlos bis 10 Nutzer | sehr umfangreich (Sprints, Burndown) | hoch, aber Overhead |
| Trello | Free-Tier | nur Board; Scrum per Power-Ups | mittel — schwach für Backlog/Sprints |
| Azure Boards | kostenlos (klein) | vollwertig (Backlog, Sprints, Capacity) | hoch, aber fremdes Ökosystem |
| Linear | Free-Tier | modern, Cycles statt Sprints | hoch, aber Cycle ≠ klassischer Sprint |

**Gewählt: GitHub Issues + Milestones** im Repo RDBHT/SWT — kein separates
Projekt, kostenlos, Issues als Backlog-Items, Milestone als Sprint, öffentlich
verlinkbar (beweisbar). Details in `tool-experiment.md`.
