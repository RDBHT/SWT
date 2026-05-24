# Product Backlog & Sprint-1-Planung — IT-Service-Monitor

## Produktvision

> Der IT-Service-Monitor überwacht IT-Dienste automatisiert per HTTP-, TCP- und
> DNS-Checks, erkennt Ausfälle früh, alarmiert Verantwortliche und macht den
> Zustand aller Dienste auf einen Blick sichtbar.

**Product Goal (MVP):** Ein lauffähiger Monitor, der Services verwaltet,
periodisch prüft, bei Störungen alarmiert und den Status in einem Dashboard
darstellt.

## Epics

| Epic | Inhalt |
|---|---|
| Persistenz | dauerhaftes Speichern aller Daten |
| Service-Verwaltung | Services anlegen, bearbeiten, auflisten |
| Health-Checks | HTTP-, TCP-, DNS-Checks definieren und ausführen |
| Scheduling | Checks automatisch im Intervall ausführen |
| Alerting | Regeln definieren, benachrichtigen, quittieren |
| Dashboard | Statusübersicht und Detailansicht |
| Test | Mock-Service für reproduzierbare Tests |

## Product Backlog

Geordnete Liste, Priorität nach MoSCoW, Schätzung in Story Points (Fibonacci).

| ID | Epic | User Story | Akzeptanzkriterium (Kurzform) | Prio | SP |
|---|---|---|---|---|---|
| US-01 | Persistenz | Als System möchte ich Services, Checks und Ergebnisse dauerhaft speichern | Daten überstehen einen Neustart | Must | 5 |
| US-02 | Test | Als Entwickler möchte ich einen Mock-Service mit steuerbaren HTTP/TCP/DNS-Endpunkten | Endpunkt liefert konfigurierbares Verhalten | Must | 3 |
| US-03 | Service-Verwaltung | Als Admin möchte ich einen Service anlegen (Name, Host) | Service erscheint danach in der Liste | Must | 3 |
| US-04 | Service-Verwaltung | Als Operator möchte ich alle Services als Liste sehen | Liste zeigt Name, Host und Status | Must | 2 |
| US-05 | Health-Checks | Als Admin möchte ich einen HTTP-Check konfigurieren (URL, erwarteter Status) | Check ist einem Service zugeordnet | Must | 5 |
| US-06 | Health-Checks | Als System möchte ich einen Check ausführen und ein CheckResult erzeugen | Status und Latenz werden erfasst | Must | 5 |
| US-07 | Dashboard | Als Operator möchte ich den Status aller Services auf einen Blick sehen | Farbcodiert: OK / Degraded / Down | Must | 5 |
| US-08 | Scheduling | Als System möchte ich Checks im konfigurierten Intervall automatisch ausführen | Check läuft ohne manuelles Auslösen | Must | 5 |
| US-09 | Service-Verwaltung | Als Admin möchte ich einen Service bearbeiten und löschen | Änderungen werden persistiert | Should | 2 |
| US-10 | Health-Checks | Als Admin möchte ich einen TCP-Port-Check konfigurieren | Port-Erreichbarkeit wird geprüft | Should | 3 |
| US-11 | Scheduling | Als Admin möchte ich das Prüfintervall je Check festlegen | Intervall wird übernommen | Should | 2 |
| US-12 | Alerting | Als Admin möchte ich eine Alert-Regel definieren (z. B. 3× Fehlschlag) | Regel je Service speicherbar | Should | 5 |
| US-13 | Alerting | Als Operator möchte ich bei ausgelöster Regel benachrichtigt werden (E-Mail) | Benachrichtigung wird versendet | Should | 5 |
| US-14 | Dashboard | Als Operator möchte ich die Detailansicht eines Service mit Check-Verlauf | Verlauf der letzten Checks sichtbar | Should | 5 |
| US-15 | Health-Checks | Als Admin möchte ich einen DNS-Check konfigurieren | DNS-Auflösung wird geprüft | Could | 3 |
| US-16 | Alerting | Als Operator möchte ich einen Alert quittieren | Alert wird als bearbeitet markiert | Could | 2 |
| US-17 | Dashboard | Als Operator möchte ich die Service-Liste nach Status filtern | Filter zeigt nur passende Services | Could | 2 |

**Summe:** 62 SP — Must 33, Should 22, Could 7.

## Sprint-1-Planung

**Sprint-Länge:** 2 Wochen. **Sprint Goal:**

> Ein Service kann angelegt, mit einem HTTP-Check versehen und manuell geprüft
> werden; das Ergebnis wird gespeichert und sein Status im Dashboard angezeigt.

Sprint 1 liefert damit einen vollständigen vertikalen Durchstich („Walking
Skeleton") durch alle Schichten — von der Persistenz bis zur Anzeige. Das
automatische Scheduling (US-08) folgt bewusst erst in Sprint 2; Sprint 1 prüft
manuell ausgelöst.

**Kapazität:** Da noch keine Velocity vorliegt, ist die Auswahl ein *Forecast*.
Veranschlagt werden **28 SP**; das Sprint Review kalibriert die reale Velocity
für Sprint 2.

### Sprint Backlog

| ID | User Story | SP |
|---|---|---|
| US-01 | Persistenz-Schicht | 5 |
| US-02 | Mock-Service | 3 |
| US-03 | Service anlegen | 3 |
| US-04 | Service-Liste | 2 |
| US-05 | HTTP-Check konfigurieren | 5 |
| US-06 | Check ausführen → CheckResult | 5 |
| US-07 | Dashboard-Statusübersicht | 5 |

### Task-Aufschlüsselung (Auszug)

- **US-01 Persistenz:** Datenmodell festlegen · Speicher-/Ladelogik · Schema für
  Service, Check, CheckResult.
- **US-02 Mock-Service:** HTTP-Endpunkt mit konfigurierbarem Statuscode ·
  TCP-Port öffnen/schließen · DNS-Stub.
- **US-05 HTTP-Check:** Konfigurationsformular (URL, erwarteter Status) ·
  Validierung · Zuordnung zum Service.
- **US-06 Check ausführen:** HTTP-Request mit Timeout · Latenzmessung ·
  Status-Ableitung (OK/Degraded/Down) · CheckResult speichern.
- **US-07 Dashboard:** Service-Kacheln mit Statusfarbe · Aggregation des
  Service-Status aus letztem CheckResult.

### Definition of Done

Funktion implementiert und lauffähig · Code committet (Conventional Commits) ·
kurzer Selbsttest gegen den Mock-Service bestanden · Dokumentation bei Bedarf
aktualisiert.

### Scrum-Events im Sprint

- **Sprint Planning** — Tag 1: Sprint Goal bestätigen, Sprint Backlog festlegen.
- **Daily Scrum** — täglich: kurze Selbst-Synchronisation am Board.
- **Sprint Review** — Tag 10: Increment prüfen, Velocity ermitteln, Product
  Backlog anpassen.
- **Retrospektive** — Tag 10: Prozess reflektieren, eine Verbesserung ableiten.

## Ausblick

- **Sprint 2:** automatisches Scheduling (US-08, US-11), Service bearbeiten/löschen
  (US-09), TCP-Check (US-10).
- **Sprint 3:** Alerting (US-12, US-13), Detailansicht (US-14).
- **Sprint 4:** DNS-Check (US-15), Alert quittieren (US-16), Statusfilter (US-17).
