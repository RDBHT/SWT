# Schwierigkeiten-Protokoll — UML-E1

Dieses Protokoll dokumentiert offen, wo die Modellierung des IT-Service-Monitors
schwierig war und an welchen Stellen die Diagramme bewusst unscharf bleiben.
Genau diese Stellen sind für die Bewertung am aufschlussreichsten.

> **Transparenzhinweis:** Der Aufgabentext nennt für Teil A „ohne AI". Diese
> Abgabe wurde gemäß aktueller Modulanweisung durchgehend AI-gestützt erstellt.
> Die folgenden Schwierigkeiten beziehen sich daher sowohl auf die Modellierung
> selbst als auch auf den AI-gestützten Entstehungsprozess.

## Werkzeugwahl

Für Teil A fiel die Wahl auf PlantUML, für Teil B auf Mermaid (mermaid.live).
Ausschlaggebend war ein praktischer Unterschied, der erst bei der Arbeit
sichtbar wurde: PlantUML deckt alle benötigten Diagrammtypen ab, Mermaid nicht.
Mermaid kennt weder ein Use-Case- noch ein vollwertiges Komponentendiagramm.
Die Notation schränkt also ein, welche Sicht auf das System man text-first
überhaupt erzeugen kann — eine Einschränkung, die zu Beginn nicht offensichtlich
war.

## Teil A — Klassendiagramm

### Überladung des ersten Entwurfs

Der erste Entwurf umfasste 13 Klassen samt einer zweiten Vererbungshierarchie
(NotificationChannel mit EmailChannel und WebhookChannel) und einer
Dashboard-Klasse. Das Diagramm war fachlich korrekt, aber als eines von fünf
Diagrammen schlicht zu dicht. Die Reduktion auf neun Klassen war eine bewusste
Abwägung zwischen Vollständigkeit und Lesbarkeit — kein eindeutig „richtiges"
Ergebnis.

### Komposition oder Assoziation: Check und CheckResult

Die schwierigste Einzelentscheidung. Ein CheckResult ist ohne seinen Check
fachlich sinnlos, was für eine Komposition spricht. Modelliert ist es dennoch
als einfache gerichtete Assoziation (1 zu 0..*), weil CheckResults als
historische Messpunkte auch unabhängig vom Lebenszyklus des Checks ausgewertet
werden (etwa für Verlaufsstatistiken). Beide Lesarten sind vertretbar; das
Diagramm trifft hier eine Entscheidung, die es nicht zwingend treffen müsste.

### Aggregation Service–AlertRule

`Service ◇— AlertRule` ist als Aggregation modelliert. Die Abgrenzung zur
Komposition ist hier unscharf: Würde eine AlertRule mit dem Service gelöscht,
wäre auch eine Komposition korrekt. Die Aggregation wurde gewählt, weil eine
AlertRule konzeptionell auch losgelöst (als Vorlage) existieren könnte.

### Nicht ausmodellierter Aufzählungstyp

Das Attribut `status: ServiceStatus` verweist auf einen Enum
(UNKNOWN/OK/DEGRADED/DOWN), der im Klassendiagramm nicht als eigener `enum`
dargestellt ist. Bewusste Vereinfachung zugunsten der Übersicht — streng
genommen eine Unvollständigkeit.

## Teil A — übrige Diagramme

### Inkonsistenz zwischen Sequenz- und Komponentendiagramm

Die deutlichste Imperfektion über Diagrammgrenzen hinweg: Im
Komponentendiagramm gibt es eine Komponente `store`, im Sequenzdiagramm wird
das Persistieren des CheckResult aber nicht gezeigt, und im Klassendiagramm
existiert bewusst keine Store-Klasse. Die drei Sichten sind also nicht
vollständig deckungsgleich. Das ist realistisch — verschiedene UML-Sichten
entstehen selten widerspruchsfrei — aber eine echte Inkonsistenz.

### Zustandsdiagramm: fehlender Wartungszustand

Die Klasse Check trägt ein Attribut `enabled`. Ein deaktivierter Check müsste
den Service in einen Pausiert- oder Wartungszustand bringen — dieser Zustand
fehlt im Zustandsdiagramm. Das Diagramm bildet nur den Mess-Lebenszyklus ab,
nicht den administrativen.

### Use-Case-Diagramm: vereinfachte Akteur-Vererbung

Admin ist als Spezialisierung von Operator modelliert (Admin erbt alle
Operator-Use-Cases). Das ist kompakt, aber eine Vereinfachung: In der Praxis
wären Rollen und Rechte feiner und nicht zwingend als strikte Vererbung
abzubilden.

## Teil B — AI-gestützte, text-first Erstellung

Die Erkenntnisse zur Interaktion mit der generativen KI sind in `prompts.md`
ausführlich dokumentiert. Kurz: Die KI war zuverlässig bei der Syntax
(lauffähiger Code ohne Korrektur), aber die Modellierungsentscheidungen —
Komposition vs. Assoziation, Kardinalitäten, Detailgrad — mussten durchgehend
menschlich geprüft und mehrfach nachgesteuert werden. Knappheit entstand nicht
von selbst, sie musste explizit eingefordert werden.
