# ADR-0001: Mocking (Variante 3) statt Mutation Testing (Variante 4)

- **Status:** akzeptiert
- **Datum:** 2026-06-30
- **Kontext:** Einsendeaufgabe TST-E1, Teil 3/4 (alternativ eine Variante)

## Kontext

TST-E1 verlangt zusätzlich zu Unit-Tests und TDD **entweder** das Herausmocken
einer „unangenehmen" Methode (Variante 3) **oder** einen Mutation-Testing-Lauf
mit Kommentierung überlebender Mutanten (Variante 4). Das Testprojekt ist in der
Domäne des **IT-Service-Monitors** angesiedelt, dessen Kern aus Netzwerk-Probes,
Zeitfenster-Logik und Alarm-Versand besteht.

## Entscheidung

Wir wählen **Variante 3 (Mocking)**. Im `AlertingService` werden die
Kollaborateure `HttpProbe` (Netzwerk), `Clock` (Zeit) und `AlertSink`
(Benachrichtigung) per Dependency Injection als Interfaces geschnitten und in den
Tests mit Mockito ersetzt (`mock`, `when…thenReturn`, `verify(...).times(1)`).

## Begründung

- Die Domäne *besteht* aus den klassischen Mock-Kandidaten Netzwerk und Zeit;
  ohne deren Ersatz ist die Alerting-Logik nicht isoliert, schnell und
  wiederholbar testbar.
- Eine „Alarm erst nach 5 Minuten Ausfall"-Regel ist mit echter Uhr nur in
  Echtzeit prüfbar — mit gemockter `Clock` in Mikrosekunden.
- Mutation Testing bewertet die **Güte** bereits vorhandener, deterministischer
  Tests. Es adressiert nicht das Kernproblem der Domäne (externe, nicht-
  deterministische Abhängigkeiten) und setzt mockbaren/deterministischen Code
  ohnehin voraus.

## Konsequenzen

- **Positiv:** deterministische, offline laufende Tests; klare Trennung von Logik
  und Infrastruktur; Vorlage (KrK/CalcSteuer) fachnah übernommen.
- **Negativ / offen:** Die Aussagekraft der Tests selbst wird nicht gemessen.
  Mutation Testing (PIT) auf die deterministischen Teil-1-Klassen
  (`StatusEvaluator`, `UptimeCalculator`) bleibt ein sinnvoller **nächster**
  Schritt und könnte später als ADR-0002 ergänzt werden.
