# Vorgehensmodell — Entscheidung und Begründung

## Entscheidung: Scrum

Für den IT-Service-Monitor wird **Scrum** gewählt.

## Begründung

- **Iteratives Zielprodukt** — der MVP wird in festen Sprints inkrementell ausgeliefert.
- **Die Aufgabe verlangt eine Iteration** — „erste Iteration planen" = Sprint, ein Scrum-Kernkonzept; Kanban kennt keine festen Iterationen.
- **Nachweisbare Struktur** — Scrum-Artefakte und -Events lassen sich klar dokumentieren.
- **Lerneffekt** — Scrum einmal vollständig durchspielen.

Kanban passt besser zu kontinuierlichem Betrieb und Wartung. Es bleibt als
Ergänzung präsent: Das Sprint-Board wird als Kanban-Board mit WIP-Limit geführt
(Scrumban).

## Scrum-Setup (für ein Ein-Personen-Projekt angepasst)

Die Kernmechanik (Sprint, Backlog, Increment, Inspektion/Adaption) bleibt, der
Zeremonien-Aufwand wird skaliert:

| Element | Anpassung |
|---|---|
| Rollen | PO, Scrum Master, Developer in Personalunion; PO- und Dev-Sicht bewusst getrennt |
| Sprint-Länge | 2 Wochen |
| Daily Scrum | kurze tägliche Selbst-Synchronisation am Board |
| Sprint Review | Selbstreview, Modulbetreuung als Stakeholder |
| Retrospektive | bleibt — Kernmechanismus der Verbesserung |

**Definition of Done:** Funktion lauffähig · Code committet (Conventional
Commits) · Selbsttest bestanden · Doku bei Bedarf aktualisiert.
