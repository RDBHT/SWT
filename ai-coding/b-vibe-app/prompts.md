# Prompts — Teil B · Pet-Project vibe-coden

Werkzeug: **bolt.new**. Vorgehen: wenige, aber gezielte Prompts in mehreren Schritten
— erst Grundgerüst, dann Funktion, dann Politur (kein spontanes „Reinquatschen"). Die
lauffähige App liegt unter [`docs/b-vibe/`](../../docs/b-vibe/index.html).

> **Hinweis:** Diese Prompts spiegeln den tatsächlich gebauten Stand wider. Wenn du die
> App live in bolt.new (nach)baust, trag hier die exakten Wortlaute und Screenshots
> deiner Session ein — die Struktur passt bereits.

## Iteration 1 — Grundgerüst, Datenmodell & Dashboard

> Baue eine Single-Page-Web-App „IT-Service-Monitor" mit reinem HTML/CSS/JS (kein
> Framework, alles in einer Datei). Datenmodell je Service: `name`, `type` (HTTP/TCP/DNS),
> `target`, `interval` (Sekunden), `status` (OK/DEGRADED/DOWN/UNKNOWN), `responseMs`,
> `lastCheck`. Zeig auf dem Dashboard eine Tabelle aller Services mit Name, Typ, Ziel,
> Status als farbige Badge, Antwortzeit und letztem Check. Nutze IBM Plex Sans/Mono und
> eine Status-Farbfamilie: OK grün, Degraded gelb, Down rot, Unknown grau.

Begründung: Erst Datenmodell und Hauptscreen festlegen — alles Weitere hängt daran.

Ergebnis: Einseitige App mit Service-Tabelle, farbigen Status-Badges und Seed-Daten.

## Iteration 2 — KPI-Leiste, Filter, Suche, Sortierung

> Ergänze oben eine KPI-Leiste mit vier Kacheln (OK / Degraded / Down / Unknown), die die
> Anzahl je Status zeigen und beim Klick als Statusfilter wirken. Füge ein Suchfeld
> (Name/Ziel) hinzu und sortiere die Tabelle nach Schweregrad (Down zuerst, dann Degraded,
> Unknown, OK).

Begründung: Der Gesamtstatus muss auf einen Blick erfassbar und filterbar sein.

Ergebnis: Klickbare KPI-Kacheln als Filter, Suche, Sortierung nach Schwere.

## Iteration 3 — Service anlegen (typabhängig) & löschen

> Füge einen „Service hinzufügen"-Dialog hinzu: Felder Name, Check-Typ (HTTP/TCP/DNS) und
> Ziel, wobei der Hinweis zum Ziel je Typ wechselt (HTTP: vollständige URL, TCP: host:port,
> DNS: Domain), plus Intervall. Prüfe Pflichtfelder. Jede Tabellenzeile bekommt einen
> Löschen-Button.

Begründung: Die typabhängigen Felder sind der eigentlich knifflige Teil.

Ergebnis: Formular mit typabhängigem Ziel-Hinweis, einfacher Validierung und Löschen.

## Iteration 4 — Simulierte Live-Checks, lokale Datenhaltung & Empty State

> Simuliere „Live"-Checks: aktualisiere alle 3 Sekunden zufällig Status und Antwortzeit
> der Services (überwiegend OK, gelegentlich degraded/down) samt Zeitstempel. Speichere
> alle Daten lokal im Browser (`localStorage`), kein Backend. Zeig einen Empty State, wenn
> keine Services vorhanden sind, und stelle sicher, dass die Status-Farben über alle
> Elemente konsistent sind.

Begründung: „Live"-Gefühl plus bewusst lokale Datenhaltung (Vibe-Qualität, kein Server).

Ergebnis: Periodische Status-Simulation, `localStorage`-Persistenz, Empty State, konsistente Farben.

---

**Reflexion:** Vibe-Coding lieferte in wenigen Prompts eine lauffähige, ansehnliche App;
Feinheiten (typabhängige Felder, Sortierung nach Schwere) brauchten gezielte
Nachschärfung. Bewusster Kontrast zur strukturierten, getesteten Arbeit in Teil C.
