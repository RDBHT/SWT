# Prompts — Teil A · UI vibe-coden

Werkzeug: **Claude Design**. Vorgehen: fünf aufeinander aufbauende Prompts — erst die Design-Sprache, dann je ein Screen, zum Schluss eine Konsistenz-Runde. Bewusst kein "ein Prompt, fertig". Die folgenden Wortlaute sind die tatsächlich in Claude Design eingegebenen Prompts; je Iteration ist das Ergebnis kurz notiert.

## Iteration 1 — Design-Sprache zuerst, noch kein Screen

> Entwirf das UI für einen IT-Service-Monitor — ein internes Tool, mit dem ein IT-Operations-Team die Verfügbarkeit von Services überwacht und es den ganzen Tag offen hat. Lege zunächst nur die Design-Sprache fest: helles, sachliches Layout, klare Typografie, eine Status-Farbskala (grün = OK, gelb = degraded, rot = down, grau = unbekannt) und die Basis-Bausteine Status-Badge, Service-Karte, Tabelle. Noch keine einzelnen Screens.

Begründung: Das Fundament einmal festlegen, damit alle vier Screens konsistent bleiben und nicht optisch auseinanderdriften.

Ergebnis: Einseitiges Design-Language-Spec — Typografie IBM Plex Sans / Plex Mono, Status-Farben in OKLCH (gleiche Helligkeit/Sättigung, damit sie als Familie wirken), Status-Badge in fünf Varianten, Service-Karte mit allen vier Zuständen, Tabelle als dichte Default-Ansicht, Tokens für Spacing/Radien/Schatten.

## Iteration 2 — Dashboard

> Baue auf dieser Design-Sprache den Hauptscreen Dashboard. Er zeigt alle überwachten Services mit Name, Check-Typ (HTTP/TCP/DNS), aktuellem Status, Antwortzeit und Zeitpunkt des letzten Checks. Oben eine Zusammenfassung (Anzahl OK / degraded / down), dazu Filter nach Status und eine sichtbare Aktion "Service hinzufügen".

Begründung: Das Dashboard ist der Einstiegsscreen — der Gesamtstatus muss in einem Blick erfassbar sein.

Ergebnis: Interaktiver Prototyp mit Top-Bar und Live-Indikator, KPI-Strip (klickbare Status-Kacheln doppeln als Filter), Toolbar mit Status- und Check-Typ-Chips plus Suche, sortierbarer Tabelle und "Service hinzufügen"-Modal. Default-Sortierung nach Schwere (Down → Degraded → Unknown → OK).

## Iteration 3 — Service-Detail

> Entwirf den Screen Service-Detail, erreichbar per Klick auf einen Service. Er zeigt die Konfiguration (URL/Host/Port, Check-Typ, Intervall), den aktuellen Status groß hervorgehoben sowie den Verlauf der letzten Checks als Zeitreihen-Diagramm plus eine Liste der letzten Ausfälle. Aktionen: Bearbeiten, "Jetzt prüfen", Löschen.

Begründung: Hier geht es um den Verlauf, nicht nur den Momentanstatus — nötig, um Ausfälle nachvollziehen zu können.

Ergebnis: Detailseite eines Service mit groß hervorgehobenem Status-Block, Konfiguration und Verlauf der letzten Checks.

## Iteration 4 — Service-Formular

> Entwirf das Service-Formular zum Anlegen/Bearbeiten eines Service. Felder: Name, Check-Typ; die folgenden Felder ändern sich je nach Typ (HTTP: URL + erwarteter Statuscode; TCP: Host + Port; DNS: Domain), dazu Prüfintervall. Validierung sichtbar darstellen, Buttons Speichern/Abbrechen.

Begründung: Das Formular muss alle drei Check-Typen abbilden — die typabhängigen Felder sind der eigentlich knifflige Teil.

Ergebnis: Formularscreen mit Modus-Umschaltung Anlegen/Bearbeiten, dynamischen Feldern je Check-Typ (HTTP/TCP/DNS), sichtbarer Validierung (rote/grüne Border, Inline-Fehlertext, Fehler-Zähler-Banner) und Live-Zusammenfassung. Der Verifier von Claude Design behob dabei einen Markup-Fehler — nicht maskierte Anführungszeichen in einem `placeholder`-Attribut, die das Tag zerrissen.

## Iteration 5 — Empty State + Konsistenz-Politur

> Entwirf den Empty State des Dashboards — den Zustand, wenn noch kein Service angelegt ist: kurze Erklärung, was das Tool leistet, und ein klarer Call-to-Action "Ersten Service hinzufügen". Prüfe abschließend alle vier Screens auf einheitliche Status-Farben, Abstände und Buttons.

Begründung: Der Empty State ist der erste Eindruck für neue Nutzer; die Schlussrunde sichert, dass alle Screens zusammenpassen.

Ergebnis: Empty-State-Screen mit Erklärung, primärem Call-to-Action und Beispielkarten (HTTP/TCP/DNS). Der mitbeauftragte Konsistenz-Check bestätigte byte-identische Status-Tokens und Abstände über alle Screens und fand eine reale Abweichung: in Dashboard fehlte `white-space: nowrap` an den Buttons (in Service-Detail und Service-Formular bereits vorhanden) — ergänzt, verhindert ein Umbrechen der Button-Labels auf schmalen Viewports.

---

Jede Iteration wurde aus Claude Design als eigenständige HTML-Datei exportiert (`docs/a-ui/iter-1.html` … `iter-5.html`) und ist auf dem AI-Coding-Reiter live eingebettet.
