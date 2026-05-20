# Aufgabe 1 — Event Storming

Themengebiet: **Digitales Intranet / Wissensplattform** für eine mittelständische Organisation.

## Domain-Events (chaotische Sammlung)

30 Events in Vergangenheitsform, zunächst ungeordnet erhoben (Chaotic Exploration):

Mitarbeiter hat sich erstmals angemeldet · Zugriffsrecht wurde vergeben · Mitarbeiter hat Passwort zurückgesetzt · Konto wurde deaktiviert · Neuer Mitarbeiter wurde angelegt · Onboarding-Checkliste wurde gestartet · Onboarding-Schritt wurde abgehakt · Onboarding wurde abgeschlossen · Dokument wurde hochgeladen · Dokument wurde aktualisiert · Dokument wurde als veraltet markiert · Dokument wurde freigegeben · Vertrauliches Dokument wurde versehentlich öffentlich geteilt · Suchanfrage wurde gestellt · Suchergebnis wurde angeklickt · Suchindex wurde neu aufgebaut · Suche lieferte kein Ergebnis · Frage wurde an den AI-Assistenten gestellt · AI-Assistent hat Antwort mit Quellen generiert · AI-Antwort wurde als falsch markiert · Frage wurde im Forum gestellt · Frage wurde beantwortet · Antwort wurde als hilfreich markiert · Kommentar wurde geschrieben · News-Beitrag wurde veröffentlicht · Benachrichtigung wurde versendet · Schulung wurde zugewiesen · Schulung wurde abgeschlossen · Mitarbeiterprofil wurde aktualisiert · Experte wurde im Verzeichnis gefunden

## Zeitlich sortiert — vier Phasen (Pivotal-Event-Bereiche)

**Phase 1 — Zugang & Eintritt:** Neuer Mitarbeiter wurde angelegt · Zugriffsrecht wurde vergeben · Mitarbeiter hat sich erstmals angemeldet · Mitarbeiter hat Passwort zurückgesetzt · Konto wurde deaktiviert

**Phase 2 — Onboarding:** Onboarding-Checkliste wurde gestartet · Onboarding-Schritt wurde abgehakt · Onboarding wurde abgeschlossen · Schulung wurde zugewiesen · Mitarbeiterprofil wurde aktualisiert

**Phase 3 — Wissen nutzen:** Suchanfrage wurde gestellt · Suchergebnis wurde angeklickt · Suche lieferte kein Ergebnis · Suchindex wurde neu aufgebaut · Frage wurde an den AI-Assistenten gestellt · AI-Assistent hat Antwort mit Quellen generiert · AI-Antwort wurde als falsch markiert · Experte wurde im Verzeichnis gefunden · Frage wurde im Forum gestellt · Schulung wurde abgeschlossen

**Phase 4 — Beitragen & Pflegen:** Dokument wurde hochgeladen · Dokument wurde freigegeben · Dokument wurde aktualisiert · Dokument wurde als veraltet markiert · Vertrauliches Dokument wurde versehentlich öffentlich geteilt · Frage wurde beantwortet · Antwort wurde als hilfreich markiert · Kommentar wurde geschrieben · News-Beitrag wurde veröffentlicht · Benachrichtigung wurde versendet

## Geclusterte Domains (Ergebnis)

Durch Clustern der Events ergeben sich **neun Domains**:

| Domain | gebildet aus Events |
|---|---|
| Identitäts- & Zugriffsverwaltung | Mitarbeiter angelegt, Recht vergeben, angemeldet, Passwort zurückgesetzt, Konto deaktiviert |
| Onboarding | Checkliste gestartet, Schritt abgehakt, Onboarding abgeschlossen |
| Dokumentenmanagement | Dokument hochgeladen, aktualisiert, freigegeben, veraltet markiert, vertraulich geteilt |
| Wissenssuche & Retrieval | Suchanfrage, Suchergebnis angeklickt, kein Ergebnis, Suchindex neu aufgebaut |
| AI-Assistent | AI-Frage gestellt, Antwort generiert, als falsch markiert |
| Community & Q&A | Forum-Frage gestellt, beantwortet, als hilfreich markiert, Kommentar geschrieben |
| News & Kommunikation | News veröffentlicht, Benachrichtigung versendet |
| Learning & Schulung | Schulung zugewiesen, Schulung abgeschlossen |
| Mitarbeiterverzeichnis | Mitarbeiterprofil aktualisiert, Experte gefunden |
