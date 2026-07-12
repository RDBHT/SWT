# Prompts — Teil C · Modulare App

Werkzeuge: **Claude Code (CLI)** für fünf Module; **Cline** (VS-Code-Plugin) für das
Modul `mock-service`. Vorgehen je Modul **spezifikationsgetrieben**: erst die
Spezifikations-MD (`specs/<modul>.md`), dann im **Planning Mode** den Plan prüfen und
bestätigen, dann Implementierung, dann Tests.

> **Hinweis:** Trage hier je Modul die **tatsächlich eingegebenen Prompts** ein und
> notiere kurz den Planning-Mode-Schritt. Die Specs liegen bereits unter
> [`specs/`](./specs), der Code in den Modul-Ordnern.

## Ablauf je Modul (Schema)

1. **Spec** schreiben/prüfen (`specs/<modul>.md`)
2. **Plan** anfordern (Planning Mode) → prüfen → bestätigen
3. **Write** → Implementierung
4. **Tests** grün → Commit (Conventional Commits)

## checker (Claude Code)
> _[Prompt-Wortlaut — z. B.: „Lies specs/checker.md. Plane die Umsetzung von Check,
> CheckResult, Status, StatusEvaluator und den drei Check-Typen. Zeig mir erst den Plan."]_

Ergebnis / Planning-Notiz: _[kurz]_

## store (Claude Code)
> _[Prompt-Wortlaut — HistoryStore + InMemoryHistoryStore nach specs/store.md]_

Ergebnis / Planning-Notiz: _[kurz]_

## alerting (Claude Code)
> _[Prompt-Wortlaut — AlertingService mit Entprellung nach specs/alerting.md]_

Ergebnis / Planning-Notiz: _[kurz]_

## scheduler (Claude Code)
> _[Prompt-Wortlaut — Scheduler.runOnce/start nach specs/scheduler.md]_

Ergebnis / Planning-Notiz: _[kurz]_

## dashboard-ui (Claude Code)
> _[Prompt-Wortlaut — StatusRenderer + DashboardServer nach specs/dashboard-ui.md]_

Ergebnis / Planning-Notiz: _[kurz]_

## mock-service (Cline — VS-Code-Plugin, Werkzeug-Nachweis)
> _[Prompt-Wortlaut aus Cline — MockService mit settable /health-Code nach specs/mock-service.md]_

Ergebnis / Planning-Notiz: _[kurz]_
**[SCREENSHOT]** Cline im Einsatz → `docs/img/c-app-cline.png`

---

**Reflexion (2–3 Sätze):** _[Wo hat der Agent gut geplant, wo musstest du eingreifen?
Was hast du bewusst selbst entschieden (Modulschnitt, Schnittstellen, Fachregeln)?]_
