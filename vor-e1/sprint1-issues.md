# Sprint-1-Issues — Copy-paste für GitHub

Sieben Issues für den Milestone „Sprint 1". Titel und Beschreibung jeweils in ein
neues GitHub Issue (Repo RDBHT/SWT) kopieren, Labels und Milestone setzen.

**Vorbereitung:** Milestone „Sprint 1" anlegen. Labels: `prio:must`,
`epic:persistenz`, `epic:service`, `epic:checks`, `epic:dashboard`, `epic:test`.

---

## Issue 1

**Titel:** `[US-01] Persistenz-Schicht [5 SP]`

**Beschreibung:**

```
Als System möchte ich Services, Checks und CheckResults dauerhaft speichern,
damit die Daten einen Neustart überstehen.

Akzeptanzkriterien:
- [ ] Service, Check und CheckResult werden persistiert.
- [ ] Nach einem Neustart sind alle Daten wieder verfügbar.
- [ ] Lesen und Schreiben über eine definierte Schnittstelle.
```

**Labels:** `prio:must`, `epic:persistenz` — **Milestone:** Sprint 1

---

## Issue 2

**Titel:** `[US-02] Mock-Service [3 SP]`

**Beschreibung:**

```
Als Entwickler möchte ich einen Mock-Service mit steuerbaren HTTP/TCP/DNS-
Endpunkten, um Checks ohne echte Zielsysteme testen zu können.

Akzeptanzkriterien:
- [ ] HTTP-Endpunkt mit konfigurierbarem Statuscode.
- [ ] TCP-Port lässt sich öffnen und schließen.
- [ ] DNS-Antwort ist konfigurierbar.
```

**Labels:** `prio:must`, `epic:test` — **Milestone:** Sprint 1

---

## Issue 3

**Titel:** `[US-03] Service anlegen [3 SP]`

**Beschreibung:**

```
Als Admin möchte ich einen Service anlegen (Name, Host), um ihn überwachen
zu lassen.

Akzeptanzkriterien:
- [ ] Name und Host sind eingebbar.
- [ ] Pflichtfelder werden validiert.
- [ ] Der Service erscheint anschließend in der Liste.
```

**Labels:** `prio:must`, `epic:service` — **Milestone:** Sprint 1

---

## Issue 4

**Titel:** `[US-04] Service-Liste [2 SP]`

**Beschreibung:**

```
Als Operator möchte ich alle Services als Liste sehen, um einen Überblick
zu haben.

Akzeptanzkriterien:
- [ ] Liste zeigt Name, Host und aktuellen Status.
- [ ] Der leere Zustand wird sinnvoll dargestellt.
```

**Labels:** `prio:must`, `epic:service` — **Milestone:** Sprint 1

---

## Issue 5

**Titel:** `[US-05] HTTP-Check konfigurieren [5 SP]`

**Beschreibung:**

```
Als Admin möchte ich einen HTTP-Check konfigurieren (URL, erwarteter
Statuscode), um Web-Dienste prüfen zu lassen.

Akzeptanzkriterien:
- [ ] URL und erwarteter Statuscode sind eingebbar.
- [ ] Die URL wird validiert.
- [ ] Der Check ist genau einem Service zugeordnet.
```

**Labels:** `prio:must`, `epic:checks` — **Milestone:** Sprint 1

---

## Issue 6

**Titel:** `[US-06] Check ausführen → CheckResult [5 SP]`

**Beschreibung:**

```
Als System möchte ich einen Check ausführen und ein CheckResult erzeugen,
um den Zustand eines Service zu erfassen.

Akzeptanzkriterien:
- [ ] HTTP-Request mit Timeout.
- [ ] Latenz wird gemessen.
- [ ] Status (OK/Degraded/Down) wird abgeleitet und als CheckResult gespeichert.
```

**Labels:** `prio:must`, `epic:checks` — **Milestone:** Sprint 1

---

## Issue 7

**Titel:** `[US-07] Dashboard-Statusübersicht [5 SP]`

**Beschreibung:**

```
Als Operator möchte ich den Status aller Services auf einen Blick sehen,
um Störungen schnell zu erkennen.

Akzeptanzkriterien:
- [ ] Farbcodierte Statusanzeige (OK/Degraded/Down).
- [ ] Der Status je Service stammt aus dem letzten CheckResult.
- [ ] Alle Services sind in der Übersicht sichtbar.
```

**Labels:** `prio:must`, `epic:dashboard` — **Milestone:** Sprint 1
