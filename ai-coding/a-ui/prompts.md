# Prompts — Teil A · UI vibe-coden

Werkzeug: **Claude Design**. Vorgehen: fünf aufeinander aufbauende Iterationen — erst die Design-Sprache, dann je ein Screen, zum Schluss eine Konsistenz-Runde. Bewusst kein "ein Prompt, fertig".

## Iteration 1 — Design-Sprache zuerst, noch kein Screen

> Entwirf das UI für einen IT-Service-Monitor — ein internes Tool, mit dem ein IT-Operations-Team die Verfügbarkeit von Services überwacht und es den ganzen Tag offen hat. Lege zunächst nur die Design-Sprache fest: helles, sachliches Layout, klare Typografie, eine Status-Farbskala (grün = OK, gelb = degraded, rot = down, grau = unbekannt) und die Basis-Bausteine Status-Badge, Service-Karte, Tabelle. Noch keine einzelnen Screens.

Begründung: Das Fundament einmal festlegen, damit alle vier Screens konsistent bleiben und nicht optisch auseinanderdriften.

## Iteration 2 — Dashboard

> Baue auf dieser Design-Sprache den Hauptscreen Dashboard. Er zeigt alle überwachten Services mit Name, Check-Typ (HTTP/TCP/DNS), aktuellem Status, Antwortzeit und Zeitpunkt des letzten Checks. Oben eine Zusammenfassung (Anzahl OK / degraded / down), dazu Filter nach Status und eine sichtbare Aktion "Service hinzufügen".

Begründung: Das Dashboard ist der Einstiegsscreen — der Gesamtstatus muss in einem Blick erfassbar sein.

## Iteration 3 — Service-Detail

> Entwirf den Screen Service-Detail, erreichbar per Klick auf einen Service. Er zeigt die Konfiguration (URL/Host/Port, Check-Typ, Intervall), den aktuellen Status groß hervorgehoben sowie den Verlauf der letzten Checks als Zeitreihen-Diagramm plus eine Liste der letzten Ausfälle. Aktionen: Bearbeiten, "Jetzt prüfen", Löschen.

Begründung: Hier geht es um den Verlauf, nicht nur den Momentanstatus — nötig, um Ausfälle nachvollziehen zu können.

## Iteration 4 — Service-Formular

> Entwirf das Service-Formular zum Anlegen/Bearbeiten eines Service. Felder: Name, Check-Typ; die folgenden Felder ändern sich je nach Typ (HTTP: URL + erwarteter Statuscode; TCP: Host + Port; DNS: Domain), dazu Prüfintervall. Validierung sichtbar darstellen, Buttons Speichern/Abbrechen.

Begründung: Das Formular muss alle drei Check-Typen abbilden — die typabhängigen Felder sind der eigentlich knifflige Teil.

## Iteration 5 — Empty State + Konsistenz-Politur

> Entwirf den Empty State des Dashboards — den Zustand, wenn noch kein Service angelegt ist: kurze Erklärung, was das Tool leistet, und ein klarer Call-to-Action "Ersten Service hinzufügen". Prüfe abschließend alle vier Screens auf einheitliche Status-Farben, Abstände und Buttons.

Begründung: Der Empty State ist der erste Eindruck für neue Nutzer; die Schlussrunde sichert, dass alle Screens zusammenpassen.

---

_(Tatsächlich verwendete Prompt-Wortlaute und Folge-Korrekturen werden während der Bearbeitung ergänzt.)_
