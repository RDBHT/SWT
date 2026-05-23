# Prompts & GenAI-Interaktion — UML-E1

Dokumentation der KI-Prompts und der Interaktion mit der generativen KI
(Claude, Anthropic). Teil B der Aufgabe verlangt explizit die Reflexion dieser
Interaktion.

## Arbeitsweise: text-first

Der gesamte Workflow war text-first: Das System wurde der KI in natürlicher
Sprache beschrieben, die KI gab Diagramm-Code zurück (PlantUML bzw. Mermaid),
dieser wurde unverändert in einen Live-Viewer eingefügt — planttext.com für
PlantUML, mermaid.live für Mermaid — und sofort gerendert. Der Engpass
verschiebt sich dadurch vom Zeichnen zum Spezifizieren: Wer das System präzise
in Worten fasst, bekommt ein präzises Diagramm.

## Prompts Teil A (sinngemäß)

### A1 — Klassendiagramm

„Modelliere das System IT-Service-Monitor als UML-Klassendiagramm. Pflicht:
mindestens sieben Klassen, Kardinalitäten und mindestens eine Aggregation oder
Komposition. Das System überwacht Dienste per HTTP-, TCP- und DNS-Checks, plant
diese per Scheduler und löst Alarme über Regeln aus."

*Ergebnis:* 13 Klassen, fachlich korrekt, aber als eines von fünf Diagrammen
zu dicht.

### A2 — Reduktion

„Übersichtlicher — ich brauche fünf Diagramme, das erste wirkt überladen."

*Ergebnis:* Reduktion auf neun Klassen; zweite Vererbungshierarchie und
Dashboard-Klasse entfernt.

### A3 — übrige Diagramme

„Erzeuge die vier übrigen Diagramme, jeweils bewusst schlank: Use-Case-,
Sequenz-, Zustands- und Komponentendiagramm."

## Prompts Teil B (sinngemäß)

Teil B überträgt dasselbe Modell text-first nach Mermaid. Die Prompts waren
hier bewusst konkreter formuliert, weil Mermaid eine andere Syntax als PlantUML
nutzt und der Code direkt in mermaid.live lauffähig sein sollte.

### B1 — Notationswechsel, Klassendiagramm

„Erzeuge das reduzierte Neun-Klassen-Modell text-first als Mermaid-`classDiagram`,
direkt lauffähig in mermaid.live. Behalte die abstrakte Oberklasse `Check` mit
den drei Subklassen HttpCheck/TcpCheck/DnsCheck, die Komposition Service–Check,
die Aggregation Service–AlertRule und alle Kardinalitäten bei."

*Ergebnis:* gültiger `classDiagram`-Code, ohne Korrektur in mermaid.live
lauffähig. Vererbung, Komposition (`*--`) und Aggregation (`o--`) korrekt
übertragen.

### B2 — weitere Diagrammtypen

„Liefere zusätzlich Zustands- und Sequenzdiagramm als Mermaid. Gib außerdem
das Use-Case- und das Komponentendiagramm als Mermaid aus."

*Ergebnis:* Zustandsdiagramm (`stateDiagram-v2`) und Sequenzdiagramm
(`sequenceDiagram`) sauber. Für Use-Case und Komponente konnte die KI keinen
gültigen Mermaid-Code liefern — Mermaid unterstützt diese Diagrammtypen nicht.
Diese Grenze wurde erst im Dialog sichtbar.

### B3 — Live-Viewer-Anbindung

„Erzeuge zu jedem Mermaid-Diagramm einen direkten mermaid.live-Editor-Link, mit
dem das Diagramm ohne Copy-&-Paste geöffnet werden kann."

*Ergebnis:* pako-kodierte `mermaid.live/edit`-Links, auf der Portfolio-Seite
neben jedem Diagramm eingebunden.

## Reflexion: Interaktion mit der generativen KI

| Beobachtung | Konsequenz / Lehre |
|---|---|
| Erster Klassendiagramm-Entwurf hatte 13 Klassen | KI liefert eher zu viel; Knappheit muss explizit eingefordert werden („übersichtlicher") |
| Diagramm-Code lief fehlerfrei in den Live-Viewern | KI ist stark bei Notation und Syntax — kein manuelles Nachbessern nötig |
| Komposition vs. Assoziation, Kardinalitäten, Detailgrad | Modellierungsentscheidungen blieben menschlich — KI schlägt vor, Mensch entscheidet |
| Mermaid liefert kein Use-Case-/Komponentendiagramm | Die Notation begrenzt, welche Sicht text-first erzeugbar ist; Grenze erst im Dialog sichtbar |
| mermaid.live rendert sofort, ohne Installation | schneller Iterationszyklus; der Engpass ist das Spezifizieren, nicht das Zeichnen |
| Wechsel PlantUML → Mermaid für dasselbe Modell | KI überträgt ein Modell zuverlässig zwischen Notationen, sofern der Prompt konkret ist |
