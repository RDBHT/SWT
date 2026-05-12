# AI-Prompts zur Constitution-Erstellung

Dokumentation der Frage-Antwort-Iterationen mit Claude (Anthropic).

---

## Schritt 1 — Mission

**Prompt (KI):**
> a) Wer ist die Hauptzielgruppe der Site? Nur die Modulbetreuung, oder auch Bewerbungs-/Portfolio-Zwecken?
> b) Was ist der Mehrwert der Site gegenüber dem reinen Repo? (Interaktive Demos? Didaktischer Anspruch? Lernfortschritt sichtbar?)

**Antwort (Nutzer):**
> a) Modulbetreuung.
> b) Interaktiver, mehr Möglichkeiten visuell/interaktiv etwas darzustellen.

**KI-Synthese:**
mission.md mit Zielgruppe = Modulbetreuung, Mehrwert = Interaktivität, Erfolgsmerkmal = schnelle Prüfung in <1 Min.

---

## Schritt 2 — Roadmap

**Prompt (KI):**
> Soll Iteration 1 schon Inhalt für den dvc-e1.html-Reiter mitbringen (also DVC-E1-Doku nach-dokumentieren), oder nur das Skelett des Reiters mit "Siehe Repo-Ordner"-Verweis?

**Antwort (Nutzer):**
> Ja, nach-dokumentieren.

**KI-Synthese:**
roadmap.md mit drei Iterationen: (1) Grundgerüst inkl. DVC-E1-Inhalt, (2) RE-Tool, (3) REQ-E1 komplett.

---

## Schritt 3 — Techstack

**Prompt (KI):**
> a) Styling: vanilla CSS oder schlankes Class-less-Framework wie Pico.css/Simple.css?
> b) RE-Tool-Persistenz: LocalStorage + JSON-Export/Import — OK?

**Antwort (Nutzer):**
> Ja OK.

**KI-Synthese:**
techstack.md mit HTML5, Pico.css, Vanilla JS, GitHub Pages aus `main/docs`, LocalStorage + JSON-Export.

---

## Schritt 4 — Feature-Specs (3 Iterationen)

**Prompt (KI):**
> Sollen die Validierungen manuell formuliert sein oder formaler im Given/When/Then-Stil?

**Antwort (Nutzer):**
> Deine Empfehlung.

**KI-Synthese:**
Given/When/Then gewählt (methodisch sauberer, didaktisch wertvoll). Drei Feature-Files angelegt: iter-01.md (Grundgerüst + DVC-E1), iter-02.md (RE-Tool), iter-03.md (REQ-E1 komplett). Jedes File enthält Ziel, Features, Validierungen, Out of Scope.

---

## Schritt 5 — RE-Tool bauen (Iter 2)

**Prompt-Kette an die KI:**

1. *"Baue ein Requirements-Tool mit Vanilla JS und Pico.css. Pflichtfelder ID, Datum, Autor, Kurz; Selects für Status, Priorität, Kategorie, juristische Relevanz; Textareas für lange Beschreibung, Abnahmekriterium, Anmerkungen."*
2. *"Persistiere in LocalStorage unter Key `swt-re-tool`. Beim ersten Aufruf, wenn leer, lade 5 Demo-Requirements zum Projekt SWT-Portfolio."*
3. *"Verhindere doppelte IDs mit Alert. Löschen pro Zeile via Button mit ×."*
4. *"Export als JSON-Datei (`swt-requirements.json`), Import aus JSON-Datei mit Validierung (muss Array sein)."*
5. *"Escape alle Tabellen-Inhalte gegen XSS, da Nutzer-Input direkt gerendert wird."*
6. *"Zwei zusätzliche Buttons: 'Demo-Daten laden' (mit Confirm-Dialog) und 'Alle löschen' (mit Confirm-Dialog)."*

**Nutzer-Entscheidung:**
> Demo-Daten ja, alle Vorschläge übernommen.

**KI-Synthese:**
`docs/re-tool.html` (semantisches Pico-HTML, Form + Tabelle + IO-Sektion) und `docs/re-tool.js` (~140 Zeilen Vanilla JS) angelegt. Navigation in `index.html` und `dvc-e1.html` um "RE-Tool"-Reiter erweitert.
