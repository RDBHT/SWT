# Rahmenbedingungen & Anforderungen — Vibe & AI Coding

Verbindliche Referenz, zusammengetragen aus Aufgabentext, Videoaufzeichnung und Miro-Board des Dozenten. Wird vor jedem Push und bei jedem Meilenstein gegen den Arbeitsstand abgeglichen.

## Formales

- Vibe & AI Coding — Präsenz 1
- Teil A + B einfach und zügig schaffbar; Teil C ist der umfangreiche, anspruchsvolle Teil
- Durchgängiges Projekt: **IT-Service-Monitor** (A = UI, B = Vibe-Version, C = modulare Version)

---

## Werkzeuge (festgelegt)

| Teil | Werkzeug | Art | Hosting / Ablage |
|---|---|---|---|
| A | Claude Design | KI-UI-Tool | UI-Mockup, Screenshots |
| B | bolt.new | Vibe-Coding-Plattform | App-Code → GitHub Pages |
| C | Claude Code | CLI (Agentic Coding) | lokaler Betrieb / kleiner Server |
| C — Werkzeug-Nachweis | Cline | VS-Code-Plugin (Anthropic-API) | baut das Modul `mock-service` |

Vier verschiedene Werkzeuge — erfüllt die Vorgabe "mindestens drei ausprobieren". Claude Code und Cline decken die geforderten zwei Werkzeug-Arten (CLI + VS-Code-Plugin) ab. Der Cline-Nachweis erfolgt nicht über ein Wegwerf-Projekt, sondern über ein echtes Modul von Teil C (`mock-service`) — beide Werkzeuge sind damit im selben Projekt belegt.

---

## Übergreifende Anforderungen (alle Teile)

- [ ] Public GitHub-Repository
- [ ] README mit Prosa-Absätzen + Pointern auf Ordner / Screenshots / Codezeilen
- [ ] Prompts dokumentiert ("Show your prompts")
- [ ] mindestens drei Werkzeuge ausprobiert
- [ ] Eigenleistung erkennbar, Mensch in der Loop

---

## Teil A — UI vibe-coden

**Definition:** Nur das **UI/GUI** — Aussehen, Layout, Screens. Kein funktionierender Code, keine Logik.

**Konkrete Anforderungen:**
- [ ] GUI für den IT-Service-Monitor mit einem KI-UI-Tool erzeugt (hier: Claude Design)
- [ ] überlegte Prompts in **mehreren Iterationen** (kein spontanes Reinquatschen)
- [ ] Screens abgedeckt: Dashboard, Service-Detail, Service-Formular, Empty State
- [ ] Screenshots der Ergebnisse
- [ ] 1–2 Absätze Prosa zur Vorgehensweise

**Abgrenzung:** kein lauffähiger Code, kein Datenmodell, keine Module — das gehört zu C.

**Abgabe-Artefakte:** Prompts (in `prompts.md`), Screenshots, Prosa — dargestellt im Reiter.

---

## Teil B — Pet-Project vibe-coden

**Definition:** Eine **funktionierende**, mittelgroße App, vibe-coded. Läuft, ist aber bewusst "Vibe-Qualität" — nicht sauber modular.

**Konkrete Anforderungen:**
- [ ] lauffähige Vibe-Version des IT-Service-Monitors mit einem Vibe-Coding-Tool (hier: bolt.new), gehostet über GitHub Pages
- [ ] überlegte Prompts dokumentiert
- [ ] Live-Link zur App
- [ ] Screenshots
- [ ] Datenhaltung bewusst gewählt (lokal vs. Cloud)
- [ ] 1–2 Absätze Prosa

**Abgrenzung:** muss **nicht** modular oder sauber strukturiert sein — der Kontrast zu C ist gewollt.

**Abgabe-Artefakte:** Live-Link, Prompts, Screenshots, Prosa.

---

## Teil C — Modulare, verteilte App

**Definition:** Dasselbe Projekt **richtig** gebaut — modular, verteilt, schrittweise spezifiziert, vollständig verstanden.

**Konkrete Anforderungen:**
- [ ] verteilte App aus **separaten Modulen** (`checker`, `scheduler`, `store`, `alerting`, `dashboard-ui`, `mock-service`)
- [ ] Werkzeug: Claude Code (CLI) als Hauptwerkzeug für fünf Module; Cline (VS-Code-Plugin) nachweislich installiert/benutzt — baut das Modul `mock-service` (belegt mit Screenshots + Prompts)
- [ ] Entwicklung **step-by-step über Spezifikations-MD-Files** (Specification-Driven Development)
- [ ] Planning Mode vor Write Mode
- [ ] Tests vorhanden (Unit bis Abnahmetests)
- [ ] App ist deploybar
- [ ] Code **vollständig verstanden** — prüfungsfähig (mündliche Prüfung)
- [ ] eigener Code, Ownership belegt (Commit-Historie, Specs, Prompts)

**Abgrenzung:** kein Vibe-Wegwerf-Code; jeder Modul-Schnitt und jede Codezeile muss erklärbar sein.

**Abgabe-Artefakte:** modulares Repo (`ai-coding/c-app/`), Spezifikations-MD-Files, Tests, README mit Pointern.

---

## Offene Punkte — vor Teil C zu klären

1. **CLI vs. VS-Code-Derivat für C:** Aufgabentext erlaubt beides; das Workflow-Bild ordnet "medium distributed project" dem VS-Code-Derivat zu. → mit Modulbetreuung klären, ob Claude Code (CLI) als Hauptwerkzeug akzeptiert ist.
2. **Punkteverteilung** über A / B / C (nur Teil A ≈ 5 bekannt).
3. **Mindestumfang von C** (konkrete Erwartung an Modulzahl/Größe).
