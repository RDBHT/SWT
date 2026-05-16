# Teil 2 — AI-Erweiterung (5 Punkte aus Requirements/Analyse)

Erweiterung der Analyse um die AI-spezifischen Aspekte des in Teil 1 beschriebenen Onboarding-Assistenten. Diese ergänzen die klassische Checkliste an den Stellen, die bei reiner Software-Analyse nicht abgedeckt sind. Bezugsprojekt: derselbe Prototyp eines internen AI-Wissensportals für die WBG.

---

## 1. Datenanforderungen

Eingangsformate sind primär PDF, DOCX, Markdown und einfache HTML-Dateien aus Abteilungsordnern. Die Inhalte sind nahezu vollständig in deutscher Sprache verfasst, was die Wahl eines multilingualen Embedding-Modells erzwingt (siehe 3.11). Der Korpus wird kuratiert (vgl. 3.9, Risiko Dokumentenqualität) und im Pilot auf 50–200 Dokumente begrenzt; Chunking erfolgt semantisch (etwa 500–1.000 Token pro Chunk). Personenbezogene Daten in Vorlagen (Mustermieter, Beispielnamen) werden vor Aufnahme anonymisiert oder durch Platzhalter ersetzt — die Datenminimierung nach DSGVO Art. 5 wirkt damit bereits am Korpus-Eingang.

## 2. Modellrisiken

Drei Risikoklassen stehen im Vordergrund. **Halluzinationen** treten besonders bei kleinen Open-Source-Modellen in deutscher Sprache auf — Mitigation durch verpflichtende Quellenangabe (REQ-R1 aus 3.17) und niedrige Decoding-Temperatur. **Bias**: viele Modelle sind primär auf englischen Korpora trainiert, deutsches Spezialvokabular der Wohnungswirtschaft (WEG-Beschluss, Betriebskostenabrechnung, Hausordnung) wird teils ungenau verstanden — Gegenmittel sind sorgfältig formulierte System-Prompts und ggf. Domain-Fine-Tuning in späterer Ausbaustufe. **Prompt-Injection**: Nutzer könnten versuchen, durch geschickte Eingaben den Systemprompt zu umgehen — Schutz durch Input-Sanitization, eindeutige Rollen-Trennung im Prompt-Template und Output-Filterung.

## 3. Akzeptanzkriterien für AI-Output

Was "gut genug" heißt, wird quantitativ messbar gemacht. Mindest-Schwellen für den produktiven Übergang aus dem Pilot: Korrektheits-Score gegen das in 3.10 definierte Eval-Set ≥ 80 %, Zitations-Genauigkeit (richtige Quelle pro Antwort) ≥ 90 %, Halluzinationsrate ≤ 5 %, sowie eine Daumen-hoch-Quote im Live-Feedback der Pilotgruppe ≥ 70 %. Ergänzt werden diese harten Metriken durch qualitative Review-Runden zu Tonfall und Verständlichkeit, da reine Zahlen die Akzeptanz nicht vollständig erfassen.

## 4. AI-spezifische Validierung

Die Validierung baut auf dem Eval-Set aus 3.10 auf und ergänzt es um AI-typische Verfahren. Vor jedem Release wird das Set erneut durchlaufen (Drift-Erkennung), bei Modell- oder Prompt-Wechsel zusätzlich ein einfacher A/B-Vergleich zwischen alter und neuer Konfiguration. Die Citation-Accuracy wird automatisiert per Stichwortabgleich zwischen genannten Quellen und tatsächlich abgerufenen Chunks geprüft. Eine LLM-as-a-Judge-Komponente ist bewusst nicht im Pilot enthalten, kann jedoch in einer Ausbaustufe mit europäisch gehostetem Modell ergänzt werden.

## 5. Regulatorische Aspekte

Vier regulatorische Anker sind zu beachten. **DSGVO**: Rechtsgrundlage für die Datenverarbeitung (Art. 6 Abs. 1 lit. f – berechtigtes Interesse) ist mit dem Datenschutzbeauftragten abzustimmen; Auftragsverarbeitung mit dem GPU-Anbieter (z. B. RunPod EU) erfordert einen ADV-Vertrag nach Art. 28. **EU AI Act**: das Tool fällt nach derzeitiger Lesart in eine niedrige Risikoklasse, da keine automatisierten Entscheidungen über Personen getroffen werden; ein interner Compliance-Check vor Rollout ist gleichwohl angeraten. **BetrVG § 87 Abs. 1 Nr. 6**: Mitbestimmung des Betriebsrats bei Einführung technischer Einrichtungen, die das Verhalten oder die Leistung der Beschäftigten überwachen können — durch reine Frage-Antwort-Funktion und Logging-Verzicht auf personenbezogene Auswertungen handhabbar. **Transparenzpflicht**: Nutzer müssen erkennen, dass sie mit einem AI-System interagieren — dies wird im UI sichtbar gemacht.
