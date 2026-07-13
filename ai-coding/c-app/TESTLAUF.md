# Testlauf-Protokoll — Teil C

Nachweis der lokalen Testläufe (`mvn -B test`, JDK 17 + Maven). Bemerkenswert:
**ein Test hat einen echten Fehler in der Alerting-Logik gefunden** — genau dafür
sind die Tests da. Nach dem Fix ist der komplette Reactor grün.

## Lauf 1 — ein echter Fehler gefunden (BUILD FAILURE)

`mvn -B test`, 2026-07-13 20:11

```
Running de.rdbht.swt.monitor.checker.StatusEvaluatorTest
Tests run: 5, Failures: 0, Errors: 0, Skipped: 0
Running de.rdbht.swt.monitor.store.StoreTest
Tests run: 2, Failures: 0, Errors: 0, Skipped: 0
Running de.rdbht.swt.monitor.store.IngestCodecTest
Tests run: 1, Failures: 0, Errors: 0, Skipped: 0
Running de.rdbht.swt.monitor.alerting.AlertingServiceTest
[ERROR] Tests run: 2, Failures: 1  <<< FAILURE!
[ERROR] AlertingServiceTest.recovery_allows_next_alert:54  expected: <2> but was: <0>

Reactor Summary:
  checker ......... SUCCESS
  store ........... SUCCESS
  alerting ........ FAILURE
  scheduler ....... SKIPPED
  dashboard-ui .... SKIPPED
  mock-service .... SKIPPED
  agent ........... SKIPPED
  collector ....... SKIPPED
BUILD FAILURE
```

## Diagnose & Fix

`AlertingService.tick` setzte beim **ersten** `DOWN`-Tick nur `downSince`, prüfte
die Fenster-Bedingung aber erst bei einem **Folge**-Tick. Beim Test mit Fenster = 0
(`recovery_allows_next_alert`) hat jede DOWN-Strecke nur einen Tick → es feuerte nie
(0 statt 2 Alarme).

**Fix:** Fenster-Prüfung auf denselben Tick ziehen, der `downSince` setzt
(`putIfAbsent`, dann sofort auswerten). Damit feuert ein bereits abgelaufenes bzw.
Null-Fenster sofort; das 5-Minuten-Fenster-Verhalten bleibt unverändert.

```java
downSince.putIfAbsent(service, now);
long since = downSince.get(service);
if (!alerted.getOrDefault(service, false) && (now - since) >= alertAfterMs) {
    sink.fire(service, "DOWN seit " + (now - since) + " ms");
    alerted.put(service, true);
}
```

## Lauf 2 — nach dem Fix (BUILD SUCCESS)

`mvn -B test`, 2026-07-13 20:13

```
checker      · StatusEvaluatorTest ....... Tests run: 5   OK
store        · StoreTest ................. Tests run: 2   OK
store        · IngestCodecTest ........... Tests run: 1   OK
alerting     · AlertingServiceTest ....... Tests run: 2   OK
scheduler    · SchedulerTest ............. Tests run: 1   OK
dashboard-ui · StatusRendererTest ........ Tests run: 1   OK
mock-service · MockServiceTest ........... Tests run: 1   OK
agent        · (kompiliert, keine Tests)
collector    · (kompiliert, keine Tests)

Reactor Summary:
  checker ......... SUCCESS
  store ........... SUCCESS
  alerting ........ SUCCESS
  scheduler ....... SUCCESS
  dashboard-ui .... SUCCESS
  mock-service .... SUCCESS
  agent ........... SUCCESS
  collector ....... SUCCESS
BUILD SUCCESS   Total time: 2.742 s   Finished: 2026-07-13T20:13:35
```

## Testübersicht

| Modul | Test | Anzahl |
|---|---|---|
| checker | StatusEvaluatorTest | 5 |
| store | StoreTest, IngestCodecTest | 3 |
| alerting | AlertingServiceTest | 2 |
| scheduler | SchedulerTest | 1 |
| dashboard-ui | StatusRendererTest | 1 |
| mock-service | MockServiceTest | 1 |
| **Summe** | | **13 grün** |

## Bewertung

Der Ablauf zeigt zwei Dinge, die für die Aufgabe zählen: Die Tests sind keine
Deko — sie haben einen realen Grenzfall-Fehler aufgedeckt (Alarm bei Null-Fenster).
Und der Zyklus **rot → Fix → grün** ist über die Git-Historie nachvollziehbar.

> **Optionaler Screenshot:** der grüne `mvn -B test`-Lauf → `docs/img/c-app-tests.png`.
