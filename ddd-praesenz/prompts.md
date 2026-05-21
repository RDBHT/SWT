# KI-Prompts zur DDD-Präsenzaufgabe

Dokumentation der gezielten Mini-Prompts (Claude, Anthropic) und der Eingaben.
Vorgehen: pro Teilaufgabe fokussierte Fragen, eigene fachliche Entscheidung, danach Ausarbeitung als HTML/SVG-Artefakt.

---

## Aufgabe 1 — Event Storming

**Anweisung an die KI:**
> Themengebiet digitales Intranet / Wissensplattform, branchenneutral. Hilf mir, Domain-Events zu sammeln und zu Domains zu clustern.

**Klärungsfragen der KI:**
> Welche Events fehlen, welche streichen? Passen vier zeitliche Phasen (Zugang & Eintritt / Onboarding / Wissen nutzen / Beitragen & Pflegen)?

**Eigene Vorgabe:**
> Phasen passen. Als Foto soll nur der sortierte + geclusterte Zustand dienen, die rohe Event-Liste kommt als Text ins Repo. Gestaltung in Miro, Abgabe zusätzlich als Reiter auf der GitHub-Page.

**Resultat:**
30 Domain-Events in Vergangenheitsform, entlang vier Phasen sortiert, zu neun Domains geclustert (Identitäts-/Zugriffsverwaltung, Onboarding, Learning & Schulung, Mitarbeiterverzeichnis, Wissenssuche & Retrieval, AI-Assistent, Community & Q&A, Dokumentenmanagement, News & Kommunikation). Umsetzung als Miro-Board, dokumentiert in event-storming.md und auf dem DDD-Reiter.

---

## Aufgabe 2 — Core Domain Chart

**Anweisung an die KI:**
> Ordne die neun Domains in Core / Supporting / Generic ein und begründe.

**Eigene Vorgabe:**
> Kerngeschäft einer Wissensplattform ist die intelligente Suche und der AI-Assistent — die gehören in den Core. Standardfunktionen (Auth, News, LMS) sind Generic und zukaufbar.

**Resultat:**
2 Core (Wissenssuche & Retrieval, AI-Assistent), 4 Supporting (Dokumentenmanagement, Community & Q&A, Mitarbeiterverzeichnis, Onboarding), 3 Generic (Identitäts-/Zugriffsverwaltung, News & Kommunikation, Learning & Schulung). Umsetzung im Core-Domain-Chart-Template in Miro, dokumentiert in core-domain-chart.md.

---

## Aufgabe 3 — Domain Mapping

**Anweisung an die KI:**
> Bestimme die Beziehungen zwischen den neun Domains mit DDD-Context-Mapping-Patterns. Erstelle die Grafik direkt (keine Miro-Vorlage für Mapping vorhanden).

**Eigene Vorgabe:**
> Auswahl soll mehrere verschiedene Pattern-Typen abdecken, damit die Mapping-Begriffe sichtbar angewandt sind. Grafik als SVG im Stil einer klassischen Context Map (Boxen, beschriftete Kanten, U/D-Markierungen).

**Resultat:**
Neun Beziehungen, sieben Pattern-Typen (Open Host Service, Published Language, Conformist, Customer/Supplier, Anticorruption Layer, Shared Kernel, Partnership). Erstellt als SVG-Context-Map (`docs/img/ddd-domain-mapping.svg`), dokumentiert in domain-mapping.md.
