# UML-E1 — Eigenes UML-Werkzeug wählen und Diagramme erstellen

Einsendeaufgabe UML-E1 (Softwaretechnik, BHT). Modelliertes System:
**IT-Service-Monitor** — das durchgehende Projekt der Vibe-&-AI-Coding-Aufgabe.
Ein Werkzeug zur Überwachung von Diensten per HTTP-, TCP- und DNS-Checks.

## Aufgabe

**Teil A** — Ein passendes UML-Werkzeug wählen und ein großes System des
Lieblingsprojekts mit mindestens fünf Diagrammen modellieren. Pflicht: ein
Klassendiagramm mit mindestens sieben Klassen, Kardinalitäten und einer
Aggregation oder Komposition.

**Teil B** — Diagramme zusätzlich mit einer KI *text-first* erzeugen und in
einem Live-Viewer (mermaid.live) anzeigen, um die Interaktion mit generativer
KI besser zu verstehen.

> **Transparenzhinweis:** Der Originaltext der Aufgabe nennt für Teil A
> „ohne AI". Diese Abgabe wurde gemäß aktueller Modulanweisung durchgehend
> AI-gestützt erstellt. Das Schwierigkeiten-Protokoll legt das offen und
> reflektiert sowohl die Modellierung als auch den AI-gestützten Prozess.

## Werkzeugwahl

- **Teil A — PlantUML.** Textbasiert, kostenlos, deckt alle fünf benötigten
  Diagrammtypen ab. Quellen als `.puml`, gerendert über den PlantUML-Server.
- **Teil B — Mermaid / mermaid.live.** Textbasiert, sofortiges Rendern im
  Browser ohne Installation — genau der in der Aufgabe genannte Live-Viewer.

## Inhalt

- `teil-a/` — fünf PlantUML-Quellen: Klassen-, Use-Case-, Sequenz-, Zustands-
  und Komponentendiagramm.
- `teil-b/` — drei Mermaid-Quellen: Klassen-, Zustands- und Sequenzdiagramm.
- `schwierigkeiten-protokoll.md` — offene Dokumentation der
  Modellierungsschwierigkeiten und bewussten Imperfektionen.
- `prompts.md` — dokumentierte KI-Prompts und Reflexion der GenAI-Interaktion.

## Live-Ansicht

Alle Diagramme gerendert auf der Portfolio-Seite: `docs/uml-e1.html`
(GitHub Pages).
