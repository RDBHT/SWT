# KI-Prompts zur Analyse

Dokumentation der gezielten Mini-Prompts (Claude, Anthropic) und der jeweiligen Nutzer-Antworten gemäß Skript-Vorgabe: *"Lassen sie sich gezielt helfen! Notieren Sie die Prompts die ihnen geholfen haben etwas zu verbessern oder zu strukturieren und geben Sie diese mit ab."*

Vorgehen: pro Checklistenpunkt 1–2 fokussierte KI-Fragen → Nutzer antwortet kurz → KI schreibt den Absatz → nächster Punkt.

---

## Punkt 1 — 3.5 Vision und Systemidee

**Prompt (KI):**
> a) Welcher konkrete Schmerzpunkt in der WBG soll gelöst werden? (Onboarding-Dauer, verstreute Dokumente, ...)
> b) Wer ist die Primärnutzergruppe? (Sachbearbeitung, Hausmeister, neue Mitarbeiter, ...)

**Antwort (Nutzer):**
> Standardfragen (Mietverträge, Hausordnung) liegen schon im ERP. Schmerzpunkt ist eher Onboarding sowie ein Konzept für die Prozesslandschaft und die SharePoint-Stammdaten-Listen.

**Rückfrage (KI):**
> Ist die SharePoint-Stammdaten-Konzeption gleichwertiges zweites Feature oder Nebeneffekt?

**Antwort (Nutzer):**
> Eher Nebeneffekt; erstmal nur Onboarding-Q&A, später ggf. Erweiterung um Konzept-Workbench.

**KI-Synthese:**
3.5 mit zwei Absätzen: (1) Schmerzpunkt-Beschreibung (Onboarding-Dauer + fehlendes SharePoint-Konzept), (2) Vision = AI-Onboarding-Assistent mit RAG, Stammdatenmodell als Nebeneffekt, Roadmap-Ausblick auf Konzept-Workbench.

---

## Punkt 2 — 3.6 Vorstudie und Marktanalyse

**Prompt (KI):**
> Welche Marktalternativen habt ihr evaluiert? Kategorien: M365-Copilot, spezialisierte Knowledge-Tools (Glean, Notion AI), klassische Onboarding-Systeme, Self-built. Wie ist die Ist-Lage (M365 vorhanden, Budget, vorhandene Tools)?

**Antwort (Nutzer):**
> Nichts vorhanden, nur Ordner — Wissen verstreut in Abteilungen, wie man es kennt.

**KI-Synthese:**
3.6 mit zwei Absätzen: (1) Greenfield-Ist-Zustand ohne dediziertes Wissensmanagement, (2) Marktüberblick (M365-Copilot, Enterprise-Tools wie Glean, klassische Onboarding-Systeme, Self-built) mit Empfehlung Self-built als pragmatischer Einstieg.

---

## Punkt 3 — 3.9 Durchführbarkeitsstudie und Risikoanalyse

**Prompt (KI):**
> Welche 3–4 Risiken aus folgender Liste sind für die WBG am relevantesten? (DSGVO, Halluzinationen, Dokumentenqualität, Akzeptanz, Wirtschaftlichkeit, Anbieter-Abhängigkeit, plus WBG-spezifisch wie Betriebsrat oder rechtlich bindende WEG-Beschlüsse)

**Antwort (Nutzer):**
> Bewerte selbst.

**KI-Synthese:**
3.9 mit zwei Absätzen: (1) Technische Machbarkeit kurz bestätigt, Hauptrisiko liegt in Datenbasis und Organisation, (2) vier priorisierte Risiken mit jeweils einer Zeile Mitigation: Halluzinationen mit rechtlicher Wirkung, Dokumentenqualität, Datenschutz/Datenhoheit, organisatorische Akzeptanz inkl. Betriebsrat.

---

## Punkt 4 — 3.10 Konzept der Qualitätssicherung

**Prompt (KI):**
> Welcher QS-Aufwand ist realistisch für eine mittelständische WBG? Drei Stufen: minimal (Stichproben + Feedback), solide (Eval-Set 30–50 Goldfragen + Drift), anspruchsvoll (LLM-as-a-Judge, CI-Eval).

**Antwort (Nutzer):**
> Stufe 2 — nicht zu viel, soll minimalistisch bleiben, aber Aufgabe erfüllen.

**KI-Synthese:**
3.10 mit zwei Absätzen: (1) Eval-Set von 30–50 Goldstandard-Fragen mit drei Metriken (Korrektheit, Quellenzitate, Halluzinationsfreiheit), (2) ergänzend Feedback-Button + Anfragen-Logging; LLM-as-a-Judge bewusst out of scope für den Prototyp.

---

## Punkt 5 — 3.11 Technischer Prototyp / Durchstich (Spike)

**Prompt (KI):**
> Vorschlag Stack für Spike: 10–20 PDFs/MD, multilinguales Embedding (e5-large), lokaler Vector-Store (Chroma/Qdrant), lokales LLM auf RunPod (Llama 3.x oder Mistral), Streamlit-UI. Ziel: Frage → Antwort mit Quelle in <5 Sek. Passt das?

**Antwort (Nutzer):**
> OK.

**KI-Synthese:**
3.11 mit zwei Absätzen: (1) Stack-Komponenten und Begründung Open-Source-Stack ohne Cloud-LLM, (2) Erfolgskriterium und explizite Abgrenzung zu produktiven Themen (Auth, Multi-User, SharePoint, Skalierung).

---

## Punkt 6 — 3.13 Vorgehens- und Prozessmodell

**Prompt (KI):**
> Vorschlag iteratives 3-Phasen-Modell (Spike → Pilot → Rollout). Passt das, oder anderes Modell (Scrum, Kanban, V-Modell)?

**Antwort (Nutzer):**
> OK, aber orientiere dich am Skript von Edlich.

**KI-Synthese:**
3.13 explizit an den im Skript genannten "heute relevanten" Modellen (RUP, XP, Scrum, Crystal Clear) und den drei Skript-Auswahlkriterien (Größe/Art, Kultur/Bereitschaft, Wissen/Bereitschaft) orientiert. Auswahl Scrum-Light/Kanban mit dreiphasiger Iteration (Spike → Pilot → Rollout), Querverweise auf 3.5, 3.10, 3.11.

---

## Punkt 7 — 3.15 Stakeholder und deren Interessen

**Prompt (KI):**
> Lass mich die Stakeholder selbst identifizieren entlang der Skript-Fragen (Wer trägt zu Requirements bei? Endkunden? Fachabteilungen? Politische/egoistische Interessen?). OK für WBG-Onboarding-Tool?

**Antwort (Nutzer):**
> (Implizit OK durch vorherige "bewerte selbst"-Anweisung; Skript-Bezug explizit gewünscht.)

**KI-Synthese:**
3.15 mit zwei Absätzen: (1) sechs zentrale Stakeholder-Gruppen mit jeweiligen Interessen (Geschäftsleitung, IT-Leitung, Abteilungsleiter/Fachexperten, neue Mitarbeitende, Betriebsrat, Datenschutzbeauftragter), (2) Skript-Aufgriff "politische und egoistische Interessen" mit konkreter Diskussion und Mitigations-Ansätzen.

---

## Punkt 8 — 3.17 Requirements und Use Cases

**Prompt (KI):**
> Vorschlag drei Use Cases (Frage stellen, Korpus pflegen, Antwort melden) plus vier Requirements (Quellenangaben, Eigeninfrastruktur, ≤5s, Authentifizierung). Passt das?

**Antwort (Nutzer):**
> OK.

**KI-Synthese:**
3.17 mit zwei Absätzen, am Skript-Vokabular orientiert: (1) drei Use Cases UC-1 bis UC-3 als funktionale Sicht, (2) nicht-funktionale Anforderungen entlang der Skript-Kategorien Performance, Reliability, Supportability, Benutzbarkeit.

---

## Punkt 9 — 3.20 Systemschnittstellen

**Prompt (KI):**
> Vorschlag Schnittstellen: Spike minimal (Web-UI, RunPod-API), Pilot Microsoft Graph (SharePoint), AD/Entra ID, Logging. Passt?

**Antwort (Nutzer):**
> OK.

**KI-Synthese:**
3.20 explizit entlang der vier Skript-Kategorien (Dialog, Ausgabe, Daten, funktional): (1) Spike-Minimum mit Web-UI + RunPod-API + Vektor-Store, (2) Roadmap-Schnittstellen Microsoft Graph, AD/Entra ID, Logging-Schnittstelle; Querverweise auf 3.5, 3.10, 3.17, 3.21.

---

## Punkt 10 — 3.21 Explorativer Schnittstellenprototyp / GUI

**Prompt (KI):**
> Grobentwurf: schlankes Chat-Interface mit Pico.css-Look, Frage-Feld + Antwort + Quellen-Block + Feedback-Button + Korpus-Sidebar, mobile-tauglich. Passt?

**Antwort (Nutzer):**
> OK.

**KI-Synthese:**
3.21 mit zwei Absätzen, Skript-konform: (1) Grobentwurf der Komponenten und Verweis auf den bewährten Pico.css-Look des SWT-Portfolios, (2) operative Umsetzung der Skript-Empfehlung "frühzeitiges Feedback" via Pilotgruppe in Phase 2, Designdetails bewusst auf die Designphase verschoben.

---

## Teil 2 — 5 AI-Punkte (Erweiterung)

**Prompt (KI):**
> Detailtiefe für Teil 2: gleich detailliert wie Teil 1, etwas kürzer (je ein Absatz), oder selbst bewerten?

**Antwort (Nutzer):**
> Kürzer.

**KI-Synthese:**
ai-erweiterung.md mit je einem prägnanten Absatz pro Punkt: (1) Datenanforderungen (Format/Sprache/Kuratierung/Datenminimierung), (2) Modellrisiken (Halluzinationen, Bias, Prompt-Injection) mit jeweiliger Mitigation, (3) Akzeptanzkriterien mit konkreten Schwellen (≥80% Korrektheit, ≥90% Citation, ≤5% Halluzination, ≥70% Daumen-hoch), (4) AI-Validierung (Eval-Set, A/B, Citation-Accuracy, LLM-Judge als Roadmap), (5) Regulatorik (DSGVO, EU AI Act, BetrVG, Transparenzpflicht).

---

## Refactor — analyse.md kompakter

**Prompt (KI):**
> analyse.md ist zu textlastig — wo möglich Tabellen oder Grafiken nutzen, sonst kürzen.

**Antwort (Nutzer):**
> Kürzer, mit Tabelle/Grafik wo nötig.

**KI-Synthese:**
analyse.md neu strukturiert. Mehrheit der Punkte in Tabellenform (3.6 Marktalternativen, 3.9 Risiken+Mitigation, 3.13 Phasen, 3.15 Stakeholder, 3.17 UCs + nicht-funktionale Anforderungen, 3.20 Schnittstellen). 3.11 mit Mermaid-Architekturdiagramm (RunPod-Pods sichtbar). 3.21 mit ASCII-Mockup des Chat-Layouts. 3.5 und 3.10 narrativ aber gekürzt. Skript-Bezug bleibt in jedem Punkt erhalten.

---

## Refactor — ai-erweiterung.md kompakter

**Prompt (KI):**
> ai-erweiterung.md ebenfalls kürzer, mit Tabellen/Diagramm wo angebracht.

**Antwort (Nutzer):**
> Bitte überarbeiten, kürzer + Grafik/Tabelle wo passt.

**KI-Synthese:**
ai-erweiterung.md auf Tabellen-First umgestellt. (1) Datenanforderungen als Aspekt/Anforderungs-Tabelle, (2) Modellrisiken als Risiko/Charakteristik/Mitigation-Tabelle, (3) Akzeptanzkriterien als Metrik/Schwelle/Bezug-Tabelle, (4) AI-Validierung mit Mermaid-Loop-Diagramm (Release → Eval → Drift-Gate → Live → Feedback → zurück) und einem ergänzenden Absatz, (5) Regulatorik als Norm/Anker/Maßnahme-Tabelle.
