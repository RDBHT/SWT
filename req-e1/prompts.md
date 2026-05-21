# AI-Prompts zur Constitution- und Tool-Erstellung

Dokumentation der Frage-Antwort-Iterationen mit Claude (Anthropic) gemäß Aufgabenvorgabe: *"Falls sie hier eine AI / LLM verwendet haben, beschreiben sie unbedingt an welcher Stelle und wie. Notieren Sie sich die Prompts!"*

**Vorgehen:** Die KI stellt gezielte Klärungsfragen, die fachlichen Entscheidungen (Projektwahl, Werkzeuge, Tiefe, Abgabeform) trifft der Nutzer mit Begründung, anschließend setzt die KI auf dieser Basis um. Kein "ein Prompt, viele Seiten".

---

## Schritt 0 — Projektwahl

**Eigene Vorgabe:**
> Als Projekt für REQ-E1 möchte ich das Aufsetzen einer **GitHub-Pages-Site** nehmen. Die Site soll nicht nur diese eine Abgabe tragen, sondern zur dauerhaften Plattform für alle Einsendeaufgaben des Moduls werden — jede Abgabe bekommt einen eigenen Reiter. Das ist ein echtes "Greenfield"-Projekt, an dem sich Requirements Engineering sauber durchspielen lässt, und es hat über die Abgabe hinaus Nutzen.

**KI-Beitrag:**
Bestätigung der Eignung, Vorschlag der Repo-Struktur (`docs/` für Pages, ein Ordner je Abgabe) und der Constitution-Gliederung gemäß Aufgabentext (mission/roadmap/techstack + Feature-Specs).

---

## Schritt 1 — Mission

**Klärungsfrage der KI:**
> a) Wer ist die Hauptzielgruppe der Site — nur die Modulbetreuung oder auch Bewerbungs-/Portfoliozwecke?
> b) Worin liegt der Mehrwert gegenüber einem reinen Repository?

**Eigene Vorgabe:**
> Zielgruppe ist ausschließlich die Modulbetreuung — es ist eine Abgabe, kein Bewerbungsportfolio, das hält den Fokus klar. Der Mehrwert gegenüber einem reinen Repo ist die Interaktivität: Sachverhalte lassen sich visuell und interaktiv darstellen statt nur als statische Markdown-Dateien. Ein lauffähiges Tool ist aussagekräftiger als ein Screenshot.

**Resultat:**
mission.md — Zielgruppe Modulbetreuung, Mehrwert Interaktivität, Erfolgsmerkmal: jede Abgabe in unter einer Minute prüf- und nachvollziehbar.

---

## Schritt 2 — Roadmap

**Klärungsfrage der KI:**
> Soll Iteration 1 bereits Inhalt für den DVC-E1-Reiter mitbringen (die abgeschlossene DVC-E1-Aufgabe nach-dokumentieren), oder zunächst nur ein Reiter-Skelett mit Repo-Verweis?

**Eigene Vorgabe:**
> Nach-dokumentieren. Wenn der DVC-E1-Reiter von Anfang an die echten Inhalte trägt (die sechs Aufgabenpunkte, Commit-Hashes, PR-Link), wirkt die Site rund und der Aufwand ist gering, weil die Aufgabe bereits abgeschlossen ist. Drei Iterationen halte ich für richtig: Grundgerüst, dann das RE-Tool, dann der REQ-E1-Reiter.

**Resultat:**
roadmap.md — drei Iterationen: (1) Grundgerüst inkl. DVC-E1-Inhalt, (2) RE-Tool, (3) REQ-E1 komplett.

---

## Schritt 3 — Techstack

**Klärungsfrage der KI:**
> a) Styling per vanilla CSS oder mit einem schlanken Class-less-Framework (Pico.css / Simple.css)?
> b) Persistenz des RE-Tools über LocalStorage plus JSON-Export/Import?

**Eigene Vorgabe:**
> Pico.css — class-less bedeutet wenig Schreibarbeit, semantisches HTML genügt, und das Ergebnis sieht ohne weiteres Zutun anständig aus. Kein Build-Tooling, die Site soll direkt aus dem Repo laufen. Für das RE-Tool genügt LocalStorage für die Single-User-Demo; der JSON-Export ist mir wichtig, damit die Modulbetreuung Demo-Daten importieren kann. Insgesamt soll der Stack bewusst minimal bleiben.

**Resultat:**
techstack.md — HTML5, Pico.css, Vanilla JS, GitHub Pages aus `main/docs`, LocalStorage + JSON-Export.

---

## Schritt 4 — Feature-Specs (3 Iterationen)

**Klärungsfrage der KI:**
> Sollen die Validierungen je Iteration manuell-prosaisch formuliert sein oder formaler im Given/When/Then-Stil?

**Eigene Vorgabe:**
> Given/When/Then. Im Softwaretechnik-Kontext ist das didaktisch wertvoller und macht die Validierung überprüfbar, ohne nennenswert mehr Schreibaufwand zu verursachen. Pro Iteration ein File mit Ziel, Features, Validierungen und einem klaren "Out of Scope".

**Resultat:**
Drei Feature-Files (iter-01 Grundgerüst + DVC-E1, iter-02 RE-Tool, iter-03 REQ-E1 komplett), jeweils mit Given/When/Then-Validierungen.

---

## Schritt 5 — RE-Tool (Teil B, AI-built)

**Eigene Vorgabe:**
> Statt eines kommerziellen Tools baue ich Teil B selbst mit KI-Unterstützung — das deckt die Aufgaben-Option "AI-built tool" ab und passt zur Interaktivitäts-Idee der Site. Das RE-Tool soll ein **eigenständiger Reiter** sein, kein versteckter Unterbereich: das Highlight-Artefakt muss klar erkennbar sein. Anforderungen: Vanilla JS, Pico.css, Pflichtfelder und Auswahllisten für die Requirement-Attribute, Persistenz im Browser, JSON-Export/Import. Demo-Daten beim ersten Aufruf, damit sofort etwas sichtbar ist.

**Prompt-Kette an die KI (Umsetzungsdetails):**

1. *"Baue ein Requirements-Tool mit Vanilla JS und Pico.css. Pflichtfelder ID, Datum, Autor, Kurz; Selects für Status, Priorität, Kategorie, juristische Relevanz; Textareas für lange Beschreibung, Abnahmekriterium, Anmerkungen."*
2. *"Persistiere in LocalStorage unter Key `swt-re-tool`. Beim ersten Aufruf, wenn leer, lade Demo-Requirements zum Projekt SWT-Portfolio."*
3. *"Verhindere doppelte IDs mit Alert. Löschen pro Zeile via Button."*
4. *"Export als JSON-Datei, Import aus JSON-Datei mit Validierung (muss Array sein)."*
5. *"Escape alle Tabellen-Inhalte gegen XSS, da Nutzer-Input direkt gerendert wird."*

**Eigene Entscheidung:**
> Demo-Daten ja — sie repräsentieren zugleich die Abgabe-Requirements. Alle Umsetzungsvorschläge übernommen.

**Resultat:**
`docs/re-tool.html` (semantisches Pico-HTML: Formular, Tabelle, Import/Export-Sektion) und `docs/re-tool.js` (~140 Zeilen Vanilla JS). RE-Tool als eigener Reiter in der Navigation.

---

## Schritt 6 — Vollständigkeitsabgleich mit dem Aufgabentext

**Eigene Vorgabe:**
> Bevor wir abschließen: bitte den Stand strikt gegen den Original-Aufgabentext prüfen. Die Aufgabe verlangt zwei Werkzeuge mit *jeweils* 5–10 Requirements und ausdrücklich *viele verschiedene* Attribute. Aktuell fehlt Teil A (Self-made-Tool) noch ganz, und die Attributzahl ist mir zu knapp. Mein Wunsch: dieselben Requirements in beiden Werkzeugen abbilden (sauberer Werkzeugvergleich), auf 7 Requirements gehen und die Attributliste deutlich erweitern.

**Resultat:**
- `re-tool.html` / `re-tool.js`: 3 weitere Attribute (Abnehmer, Referenz, Historie) → 15 Attribute, Demo-Daten von 5 auf 7 Requirements erweitert
- `req-e1/requirements-table.md`: Teil A als Markdown-Tabelle (Self-made-Tool) mit denselben 7 Requirements
- `docs/req-e1.html`: Reiter mit Sektionen Teil A / Teil B / Teil C
- Damit alle Vorgaben des Aufgabentexts nachweisbar erfüllt.
