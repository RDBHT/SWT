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

## Prompts (sinngemäß)

### Prompt 1 — Klassendiagramm (Teil A)

„Modelliere das System IT-Service-Monitor als UML-Klassendiagramm. Pflicht:
mindestens sieben Klassen, Kardinalitäten und mindestens eine Aggregation oder
Komposition. Das System überwacht Dienste per HTTP-, TCP- und DNS-Checks, plant
diese per Scheduler und löst Alarme über Regeln aus."

*Ergebnis:* 13 Klassen, fachlich korrekt, aber als eines von fünf Diagrammen
zu dicht.

### Prompt 2 — Reduktion

„Übersichtlicher — ich brauche fünf Diagramme, das erste wirkt überladen."

*Ergebnis:* Reduktion auf neun Klassen; zweite Vererbungshierarchie und
Dashboard-Klasse entfernt.

### Prompt 3 — übrige Diagramme (Teil A)

„Erzeuge die vier übrigen Diagramme, jeweils bewusst schlank: Use-Case-,
Sequenz-, Zustands- und Komponentendiagramm."

### Prompt 4 — Mermaid-Fassung (Teil B)

„Erzeuge dieselben Diagramme text-first als Mermaid für mermaid.live."

*Ergebnis:* Klassen-, Zustands- und Sequenzdiagramm. Use-Case- und
Komponentendiagramm waren nicht möglich — Mermaid unterstützt diese Typen
nicht.

## Reflexion: die Interaktion mit der generativen KI

Die Interaktion war iterativ und nicht in einem Schritt fertig. Der erste
KI-Entwurf des Klassendiagramms war überladen; erst die explizite
Folge-Anweisung „übersichtlicher" reduzierte das Modell. Lehre: Generative KI
neigt zur Über-Lieferung; Knappheit muss man aktiv einfordern, sie entsteht
nicht von selbst.

Auffällig war die Arbeitsteilung. Die KI war stark bei der Syntax — der Code
lief in den Live-Viewern ohne Korrektur durch. Die Modellierungsentscheidungen
dagegen — Komposition oder einfache Assoziation, welche Kardinalität, welcher
Detailgrad — blieben Ermessenssache und mussten menschlich geprüft werden. Die
KI schlug vor, der Mensch entschied.

Die zweite zentrale Erkenntnis betrifft die Notation selbst: Mermaid
unterstützt keine Use-Case- und keine Komponentendiagramme. Das Werkzeug
schränkt also ein, welche Sicht auf ein System man text-first überhaupt
erzeugen kann — eine Grenze, die erst im Dialog mit der KI sichtbar wurde, als
sie für diese Typen kein gültiges Mermaid liefern konnte. PlantUML deckt das
volle UML-Spektrum ab, mermaid.live punktet dafür mit Null-Setup und
sekundenschnellem Render-Zyklus.
