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
