# ARC-E2 — Architecture Communication Canvas

Abgabe zur Einsendeaufgabe **ARC-E2** (Softwaretechnik). Ein **Architecture
Communication Canvas** (nach [canvas.arc42.org](https://canvas.arc42.org))
für einen kleinen, abgeschlossenen Teil der ARC-E1-Architektur: die
**Alerting-Pipeline** des IT-Service-Monitors.

Repository: https://github.com/RDBHT/SWT
Pages-Reiter (Canvas visuell): https://rdbht.github.io/SWT/arc-e2.html

## Canvas: Alerting-Pipeline

### Value Proposition

Alarmiert Betreiber **zuverlässig und ohne Fehlalarm-Flut**, wenn ein
überwachter Dienst ausfällt: erst wenn ein Ziel länger als das konfigurierte
Fenster DOWN ist, wird **genau ein** Alert ausgelöst — kein Alarm-Spam bei
Flattern, keine Doppel-Alerts während eines laufenden Ausfalls.

### Key Stakeholder

- **Operator/Admin** (Empfänger): will schnelle, verlässliche Alarme.
- **Entwicklerteam**: braucht mockbare Schnittstellen für Tests.
- **Kursbetreuung/Reviewer**: bewertet Architektur- und Testqualität.

### Core Functions

- Statusereignisse aus dem Messpfad konsumieren (`tick` je Ziel)
- DOWN-Strecken erkennen und **entprellen** (`alertAfterMs`-Fenster)
- Bei bestätigtem Ausfall genau einen Alert an den `AlertSink` feuern
- Erholung erkennen und Alarmzustand zurücksetzen (erneut alarmierfähig)

### Quality Requirements

- **Zuverlässigkeit:** kein verlorener und kein doppelter Alert je Ausfall
- **Testbarkeit:** Kernlogik ohne echte Uhr/Netz/Mail testbar (TST-E1:
  Mockito-Tests mit `Clock`-, `HttpProbe`-, `AlertSink`-Mocks)
- **Erweiterbarkeit:** neue Kanäle (Slack, SMS) ohne Änderung der Pipeline
- **Wartbarkeit:** Zustandslogik unter Metrik-Beobachtung (MET-E1:
  `tick` = McCabe 5, Cognitive Complexity 6 — Hotspot der Codebasis)

### Business Context

- **Input:** Statusereignisse (UP/DEGRADED/DOWN) vom `StatusEvaluator`,
  Zeit von der injizierten `Clock`
- **Output:** Alert-Nachrichten an Notifier-Adapter (`MailSink`,
  `WebhookSink`) → E-Mail/Webhook an den Operator
- **Nachbarn:** `scheduler` (Taktgeber), `store` (parallele Persistenz,
  von Alerting unabhängig)

### Core Decisions — Good or Bad

- ✅ **Entprellung über Zeitfenster statt Fehlversuchszähler** — robust bei
  variablen Check-Intervallen; eine Konfigurationsgröße (`alertAfterMs`).
- ✅ **`AlertSink` als Interface (Strategy)** — Kanäle austauschbar, Pipeline
  bleibt unberührt; im Test durch Mock ersetzt.
- ✅ **Uhr als injizierte `Clock`** — deterministische Zeitreise-Tests statt
  `Thread.sleep`.
- ⚠️ **Alarmzustand im Speicher** (`downSince`, `alerted`) — bei
  Agent-Neustart geht eine laufende DOWN-Strecke verloren; bewusst
  akzeptiert (KISS), Persistenz des Zustands wäre der nächste Schritt.

### Components / Modules

`AlertingService` (Zustandsmaschine, `tick`) · `StatusEvaluator`
(liefert Ereignisse) · `AlertSink`-Interface + Adapter (`MailSink`,
`WebhookSink`) · injizierte `Clock`

### Core Technologies

Java 17 · Maven · JUnit 5 + Mockito 5 (Verhaltenstests der Pipeline) ·
PMD/Checkstyle im CI (Metrik-Gate aus MET-E1)

### Risks and Missing Information

- Zustandsverlust bei Neustart (s. o.) — Entscheidung zur Persistenz offen
- Eskalation/Deduplizierung bei **vielen gleichzeitig** ausfallenden Zielen
  (Sammel-Alert?) noch nicht spezifiziert
- Zustellgarantie des Sinks ungeklärt: Retry-Strategie, wenn der
  Mail-/Webhook-Versand selbst fehlschlägt

## Abgabeform

Link auf den ARC-E2-Reiter der GitHub-Pages-Site:
**`https://rdbht.github.io/SWT/arc-e2.html`**
