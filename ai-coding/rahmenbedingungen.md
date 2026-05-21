# Rahmenbedingungen — Vibe & AI Coding (Präsenz 1)

Zusammengetragen aus Aufgabentext, Videoaufzeichnung und Miro-Board des Dozenten.
Dient als verbindliche Referenz für die Bearbeitung.

## Formales

- **Titel:** Vibe & AI Coding — Präsenz 1
- **Punkte:** 24 gesamt (Teil A ≈ 5)
- **Frist:** 12. Juli 2026
- **Aufwand:** Teil A + B einfach, zügig schaffbar, zählen als Exercise; Teil C schwer, Zeit bis Semesterende

## Abgabeform

- **Public** GitHub-Repository (keine privaten Einladungen)
- `README.md` auf Titelseite: einige Absätze Prosa + Pointer in die Ordner / auf Screenshots / auf konkrete Codezeilen
- Prompts zeigen ("Show your prompts")
- Alternativ PDF erlaubt
- Umsetzung hier: Reiter auf der GitHub-Pages-Site + Repo-Ordner `ai-coding/`

## Teil A — UI vibe-coden

- GUI für eine Projektidee erzeugen; Werkzeug ideal Google Stitch (Alternativen erlaubt)
- Überlegte Prompts in mehreren Iterationen — kein spontanes "Reinquatschen"
- Abgabe: Prompts + Screenshots + 1–2 Absätze Prosa

## Teil B — Pet-Project vibe-coden

- Mittelgroßes "Pet-Project" mit echten Vibe-Coding-Tools (Lovable empfohlen)
- Datenhaltung möglichst lokal; Cloud nur wo nötig (Datenschutz bewusst abwägen)

## Teil C — Modulare, verteilte App

- Werkzeug: **eine CLI oder ein VS-Code-Clone** — und nachweisen, dass das **andere** ebenfalls installiert und benutzt wurde
- Größere **verteilte** App, die **vollständig verstanden** ist
- App **muss aus separaten Modulen** bestehen
- Entwicklung **step-by-step über MD-Files** (Specification-Driven Development)
- Planning Mode zuerst, dann Write Mode
- Tests (Unit bis Abnahmetests), Deployment-Fähigkeit
- **Ownership:** echter, eigener, verstandener Code — kein "Prompt rein, Ergebnis raus"

## Übergreifende Prinzipien (aus Video & Board)

- **"PRÜFUNG = jeden Code verstehen!"** — mündliche Prüfung über den eigenen Code
- Mindestens drei Werkzeuge ausprobieren
- Mensch bleibt in der Loop; Schuld lässt sich nicht auf die AI schieben
- One feature at a time

## Umsetzungsentscheidungen für dieses Projekt

- Durchgängiges Projekt: **IT-Service-Monitor** (A = UI, B = Vibe-Version, C = modulare Version)
- Teil C mit **Claude Code (CLI)**; Nachweis des zweiten Werkzeugs über **Cline** (VS-Code-Plugin)
- Werkzeuge insgesamt: Google Stitch, Lovable, Claude Code, Cline (= vier, erfüllt "mindestens drei")

## Offene Punkte — vor Teil C zu klären

1. **CLI vs. VS-Code-Derivat für C:** Der Aufgabentext erlaubt beides. Das Workflow-Bild des Dozenten ordnet "medium distributed project" allerdings dem VS-Code-Derivat zu und "small, lokal lauffähig" der CLI. → Mit der Modulbetreuung abklären, ob Claude Code (CLI) als Hauptwerkzeug für C akzeptiert ist.
2. **Punkteverteilung** über A / B / C im Detail (nur Teil A ≈ 5 bekannt).
3. **Mindestumfang von C** ("ein bisschen verteilt" — konkrete Erwartung an Modulzahl/Größe).

---

## Anforderungs-Check (laufende Prüfung)

Diese Checkliste wird vor jedem Push und bei jedem Meilenstein durchgegangen — abgleichen, ob der Arbeitsstand die Vorgaben erfüllt.

### Übergreifend
- [ ] Public GitHub-Repository
- [ ] README mit Prosa-Absätzen + Pointern auf Ordner / Screenshots / Codezeilen
- [ ] Prompts dokumentiert ("Show your prompts")
- [ ] mindestens drei Werkzeuge ausprobiert

### Teil A — UI vibe-coden
- [ ] GUI mit UI-Designer erzeugt (Google Stitch o. ä.)
- [ ] mehrere überlegte Prompt-Iterationen (kein spontanes "Reinquatschen")
- [ ] Screenshots vorhanden
- [ ] 1–2 Absätze Prosa zur Vorgehensweise

### Teil B — Pet-Project vibe-coden
- [ ] mittelgroßes Pet-Project mit Vibe-Coding-Tool
- [ ] lauffähig, Live-Link vorhanden
- [ ] Prompts dokumentiert
- [ ] Datenhaltung bewusst gewählt (lokal vs. Cloud)

### Teil C — Modulare, verteilte App
- [ ] verteilte App aus separaten Modulen
- [ ] CLI **oder** VS-Code-Clone genutzt; das andere Werkzeug nachweislich installiert/benutzt
- [ ] Entwicklung step-by-step über Spezifikations-MD-Files (SDD)
- [ ] Tests vorhanden (Unit bis Abnahmetests)
- [ ] App ist deploybar
- [ ] Code vollständig verstanden — prüfungsfähig
- [ ] eigener Code, Ownership belegt (Commit-Historie, Prompts, Spezifikationen)
