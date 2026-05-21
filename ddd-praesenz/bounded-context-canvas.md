# Aufgabe 4 — Bounded Context Canvas

Detailbeschreibung einer Domain mit der ddd-crew Bounded Context Canvas (v4).
Gewählte Domain: **AI-Assistent** (Core-Domain).

## Name

AI-Assistent

## Description

Beantwortet Mitarbeiterfragen zur internen Wissensbasis in natürlicher Sprache. Nutzt Retrieval-Augmented Generation (RAG): ruft relevante Dokument-Chunks über die Wissenssuche ab und erzeugt daraus eine belegte Antwort mit Quellenangaben. Nutzen: schnelle, nachvollziehbare Antworten statt manueller Recherche in verstreuten Ablagen.

## Strategic Classification

- **Domain:** core
- **Business Model:** engagement (bindet Nutzer an die Plattform, steigert Produktivität)
- **Evolution:** custom built (RAG-Assistenten sind noch kein Commodity)

## Domain Roles

Execution Context — der AI-Assistent ist der Ort, an dem die eigentliche Wertschöpfung (die generierte, belegte Antwort) entsteht. Mit analytischem Anteil (Synthese aus mehreren Quellen).

## Inbound Communication

| Collaborator | Message | Typ |
|---|---|---|
| Mitarbeiter (via Frontend) | Frage an Assistenten stellen | Command |
| Mitarbeiter (via Frontend) | AI-Antwort als falsch melden | Command |

## Outbound Communication

| Message | Typ | Collaborator |
|---|---|---|
| Relevante Chunks abrufen | Query | Wissenssuche & Retrieval |
| Antwort mit Quellen generiert | Event | Frontend / QS-Logging |

## Ubiquitous Language

Prompt · Chunk (Dokumentfragment) · Embedding · Retrieval · Kontextfenster · Grounding · Quellenangabe (Citation) · Halluzination · Konfidenz · RAG.

## Business Decisions

- Jede Antwort enthält verpflichtend mindestens eine Quellenangabe — ohne Beleg keine Ausgabe.
- Bei niedriger Konfidenz oder fehlender Quelle wird nicht geraten, sondern "keine belegte Antwort gefunden" zurückgegeben.
- Rechtlich relevante Themen erhalten einen Disclaimer und einen Eskalationshinweis an erfahrene Kollegen.
- Kein Cloud-LLM mit Drittstaaten-Transfer; Inferenz nur EU-gehostet oder lokal (DSGVO).
- Zielantwortzeit ≤ 5 Sekunden.
