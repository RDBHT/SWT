# KI-Prompts zur Analyse

Dokumentation der gezielten Mini-Prompts (Claude, Anthropic) und der jeweiligen Eingaben gemäß Skript-Vorgabe: *"Lassen sie sich gezielt helfen! Notieren Sie die Prompts die ihnen geholfen haben etwas zu verbessern oder zu strukturieren und geben Sie diese mit ab."*

**Vorgehen:** pro Checklistenpunkt eine fokussierte Klärungsfrage an die KI, eigene Entscheidung mit Kontext zur WBG-Realität als Antwort, danach Ausarbeitung in Tabelle/Mermaid/Text durch die KI auf dieser Basis. Keine "ein Prompt, viele Seiten"-Generierung. Refactor-Schritte am Ende für Lesbarkeit (Tabellen, Diagramme).

---

## Schritt 1 — 3.5 Vision und Systemidee

**Klärungsfrage der KI:**
> a) Welcher konkrete Schmerzpunkt in der WBG soll gelöst werden?
> b) Wer ist die Primärnutzergruppe?

**Eigene Vorgabe:**
> Standardfragen wie Mietverträge oder Hausordnung sind bei uns im ERP gut hinterlegt — dafür braucht es kein zusätzliches Tool. Der eigentliche Schmerzpunkt ist die Einarbeitung neuer Mitarbeiter: das Wissen über die Prozesslandschaft liegt verteilt in Abteilungsordnern und in den Köpfen erfahrener Kollegen. Mittelfristig steht die Frage im Raum, wie wir das in einer SharePoint-Stammdatenstruktur konsolidieren. Primärnutzergruppe sind neue Mitarbeitende in den ersten Wochen.

**Rückfrage der KI:**
> Soll die SharePoint-Stammdaten-Konzeption ein gleichwertiges zweites Feature sein oder eher Nebeneffekt?

**Eigene Entscheidung:**
> Eher Nebeneffekt. Im ersten Prototyp ausschließlich Onboarding-Q&A, das hinterlegte Prozessmodell dient gleichzeitig als Entwurfsgrundlage für später. Eine Konzept-Workbench für IT/Organisation ist denkbar, kommt aber frühestens in einer zweiten Ausbaustufe.

---

## Schritt 2 — 3.6 Vorstudie und Marktanalyse

**Klärungsfrage der KI:**
> Was habt ihr evaluiert? Wie ist die Ist-Lage (M365, Budget, vorhandene Tools)?

**Eigene Vorgabe:**
> Nichts vorhanden — typische mittelständische Situation. Kein M365-Stack, keine AI-Tools lizenziert, Inhalte verteilt auf Abteilungsordner. Marktüberblick darf knapp bleiben, Fokus auf vier Alternativen mit Empfehlung Self-built (volle Datenhoheit, niedrige Initialkosten).

---

## Schritt 3 — 3.9 Durchführbarkeitsstudie und Risikoanalyse

**Klärungsfrage der KI:**
> Welche Risiken sind aus WBG-Sicht prioritär? (DSGVO, Halluzinationen, Dokumentenqualität, Akzeptanz, Wirtschaftlichkeit, Betriebsrat …)

**Eigene Vorgabe:**
> Aus WBG-Sicht klar prioritär: Datenschutz wegen personenbezogener Daten in Vorlagen, BR-Einbindung wegen Mitarbeiter-Tool, Halluzinationen besonders bei rechtlich relevanten Themen (WEG-Beschlüsse, Mietrecht). Die technische Bewertung (Modellrisiken etc.) kannst du selbst priorisieren. Bitte 3–4 Top-Risiken mit jeweils einer Zeile Mitigation, nicht mehr.

---

## Schritt 4 — 3.10 Konzept der Qualitätssicherung

**Klärungsfrage der KI:**
> Welche QS-Stufe ist realistisch? (1) minimal, (2) solide mit Eval-Set, (3) anspruchsvoll mit LLM-as-a-Judge?

**Eigene Vorgabe:**
> Stufe 2 — wir haben kein dediziertes ML-Team, aber QS auf reines User-Feedback zu reduzieren wäre zu wenig. Goldfragen-Eval-Set ist realistisch, wenn jemand das einmal sauber aufsetzt. LLM-as-a-Judge ist Roadmap-Thema, nicht Pilot. Insgesamt soll die Doku zu diesem Punkt knapp bleiben.

---

## Schritt 5 — 3.11 Technischer Prototyp / Durchstich (Spike)

**Vorschlag der KI:**
> Stack-Skizze: 10–20 PDFs/MD, multilinguales Embedding (e5-large) auf RunPod, lokaler Vector-Store (Chroma/Qdrant), Open-Source-LLM (Llama/Mistral) auf RunPod, schlankes Streamlit-UI.

**Eigene Bewertung:**
> Stack passt. Wichtig: RunPod EU-Region wegen Datenschutz, bewusst kein OpenAI. Erfolgskriterium des Spikes: fachlich korrekte Antwort mit Quellenangabe in unter fünf Sekunden. Authentifizierung, Multi-User und SharePoint-Anbindung sind ausdrücklich nicht Teil des Durchstichs.

---

## Schritt 6 — 3.13 Vorgehens- und Prozessmodell

**Klärungsfrage der KI:**
> Welches Vorgehensmodell? Iteratives 3-Phasen-Modell vorgeschlagen — passt das oder anderes (Scrum, V-Modell, Kanban)?

**Eigene Vorgabe:**
> Drei Phasen (Spike → Pilot → Rollout) passen, aber bitte explizit am Skript orientieren. In unserer Größe geht klassisches Wasserfall oder RUP nicht — Scrum-Light bzw. Kanban mit kurzen Reviews ist passender, auch weil Fachexperten bei uns keine ausgebildeten Product Owner sind. Begründung der Wahl an den drei Skript-Auswahlkriterien (Größe, Kultur, Bereitschaft) festmachen.

---

## Schritt 7 — 3.15 Stakeholder und deren Interessen

**Klärungsfrage der KI:**
> Stakeholder-Identifikation entlang der Skript-Fragen (Wer trägt zu Requirements bei? Politische und egoistische Interessen?). Bewerte selbst?

**Eigene Vorgabe:**
> Selbst bewerten, mit Skript-Bezug. Aus WBG-Sicht zentrale Akteure: Geschäftsleitung will ROI sehen, IT-Leitung sorgt sich um Wartungslast, Abteilungsleiter sehen Wissen teilweise als Macht. Der Betriebsrat ist bei uns aktiv und muss früh eingebunden werden, sonst entstehen Blockaden im Rollout. Erfahrene Kollegen sollten in der Kommunikation als Profiteure (Entlastung) und nicht als Bedrohte (Ersetzung) gerahmt werden — das ist erfahrungsgemäß heikel.

---

## Schritt 8 — 3.17 Requirements und Use Cases

**Vorschlag der KI:**
> Drei Use Cases (Frage stellen, Korpus pflegen, Antwort melden) plus nicht-funktionale Anforderungen entlang der Skript-Kategorien (Performance, Reliability, Supportability, Benutzbarkeit).

**Eigene Bewertung:**
> OK, drei Use Cases reichen für den Pilot. Die Authentifizierung (Reliability) muss als REQ rein, auch wenn sie im Spike noch nicht implementiert ist — das ist eine Bedingung für jeden produktiven Schritt. Antwortzeit ≤ 5 s als Performance-Schwelle nehmen, das deckt sich mit dem Erfolgskriterium des Spikes.

---

## Schritt 9 — 3.20 Systemschnittstellen

**Vorschlag der KI:**
> Spike minimal (Web-UI, RunPod-API, Vector-Store), Pilot/Rollout Microsoft Graph (SharePoint) und AD/Entra ID, plus Logging-Schnittstelle.

**Eigene Bewertung:**
> Stimmt. Wichtig: die Roadmap-Schnittstellen (SharePoint, Entra ID) klar erst für Pilot/Rollout markieren, nicht den Spike damit überfrachten. Die Skript-Kategorien (Dialog/Ausgabe/Daten/funktional) bitte explizit nennen, das schärft die Darstellung. Klassische Ausgabeerzeugnisse (Briefe, Reports) entfallen bei uns.

---

## Schritt 10 — 3.21 Explorativer Schnittstellenprototyp / GUI

**Vorschlag der KI:**
> Grobentwurf: schlankes Chat-Interface mit Pico.css-Look (analog zum RE-Tool dieses Portfolios), Frage-Feld + Antwort mit Quellen-Block + Feedback-Button, Korpus-Sidebar für Power-User, mobile-tauglich.

**Eigene Bewertung:**
> OK. Pico.css ist hier konsistent zu den anderen Reitern dieser Site, das spart Designarbeit und der Auftritt bleibt einheitlich. Mobile-Tauglichkeit ist nicht optional — unsere Hausmeister sind im Außendienst und sollen das Tool unterwegs nutzen können. Detailliertes GUI explizit auf die Designphase verschieben (Skript-Logik).

---

## Teil 2 — 5 AI-Punkte (Erweiterung)

**Klärungsfrage der KI:**
> Detailtiefe für Teil 2 — gleich detailliert wie Teil 1, etwas kürzer (je ein Absatz) oder selbst bewerten?

**Eigene Vorgabe:**
> Kürzer. Der AI-Anteil ist in Teil 1 ja schon präsent — Teil 2 soll die zusätzlichen AI-spezifischen Punkte hervorheben, nicht die ganze Analyse wiederholen. Je ein knapper Absatz pro Punkt, harte Schwellen wo möglich (z. B. Korrektheit ≥ 80 %, Citation-Accuracy ≥ 90 %). Regulatorik bitte konkret an Normen festmachen (DSGVO-Artikel, BetrVG-Paragraph, EU AI Act), keine vagen Andeutungen.

---

## Refactor 1 — analyse.md kompakter

**Anweisung an die KI:**
> analyse.md ist im ersten Wurf zu textlastig. Bitte überarbeiten: wo möglich Tabellen oder Grafiken nutzen, sonst kürzen. Skript-Bezug muss in jedem Punkt erhalten bleiben.

**Konkrete Vorgaben:**
> Marktanalyse, Risiken, Phasen, Stakeholder, Use Cases, Schnittstellen — das sind alles Tabellen-Kandidaten. Für 3.11 wäre ein Architekturdiagramm hilfreich, das die RunPod-Pods sichtbar macht. Für 3.21 wäre ein Mockup statt langer Beschreibung besser. 3.5 und 3.10 dürfen narrativ bleiben, müssen aber kürzer als bisher werden.

---

## Refactor 2 — ai-erweiterung.md kompakter

**Anweisung an die KI:**
> Gleicher Ansatz für ai-erweiterung.md. Tabellen wo möglich, ein kleines Validierungsfluss-Diagramm wäre für Punkt 4 (AI-spezifische Validierung) angebracht — der Loop aus Eval-Set, Drift-Gate und Live-Feedback wird grafisch deutlich verständlicher als in Prosa.

**Eigene Vorgabe:**
> Mermaid-Loop genau für die Drift-/Feedback-Schleife einsetzen, der Rest als Tabellen mit klaren Spalten (Aspekt/Anforderung, Risiko/Mitigation, Metrik/Schwelle, Norm/Maßnahme). Keine zusätzlichen narrativen Absätze, wenn die Tabelle den Punkt vollständig abdeckt.
