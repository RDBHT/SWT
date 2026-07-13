# Testlauf-Protokoll — Teil C

Nachweis der lokalen Testläufe (`mvn -B test`, JDK 17 + Maven). Bemerkenswert:
**ein Test hat einen echten Fehler in der Alerting-Logik gefunden** — genau dafür
sind Tests da. Nach dem Fix ist der komplette Reactor grün. Unten die **originalen
Konsolen-Logs** beider Läufe.

## Lauf 1 — ein echter Fehler gefunden (BUILD FAILURE)

`mvn -B test`, 2026-07-13 20:11 — `alerting` schlägt fehl, Folge-Module werden übersprungen.

Kernstelle:

```
Running de.rdbht.swt.monitor.alerting.AlertingServiceTest
[ERROR] Tests run: 2, Failures: 1, Errors: 0, Skipped: 0 <<< FAILURE!
[ERROR] de.rdbht.swt.monitor.alerting.AlertingServiceTest.recovery_allows_next_alert <<< FAILURE!
org.opentest4j.AssertionFailedError: expected: <2> but was: <0>
        at de.rdbht.swt.monitor.alerting.AlertingServiceTest.recovery_allows_next_alert(AlertingServiceTest.java:54)
```

<details><summary>Vollständiges Log — Lauf 1 (BUILD FAILURE)</summary>

```
[INFO] Scanning for projects...
[INFO] ------------------------------------------------------------------------
[INFO] Reactor Build Order:
[INFO]
[INFO] IT-Service-Monitor (Teil C)                                        [pom]
[INFO] checker                                                            [jar]
[INFO] store                                                              [jar]
[INFO] alerting                                                           [jar]
[INFO] scheduler                                                          [jar]
[INFO] dashboard-ui                                                       [jar]
[INFO] mock-service                                                       [jar]
[INFO] agent                                                              [jar]
[INFO] collector                                                          [jar]
[INFO]
[INFO] Building checker 1.0.0-SNAPSHOT                                    [2/9]
[INFO] --- compiler:3.15.0:compile (default-compile) @ checker ---
[INFO] Compiling 7 source files with javac [debug release 17] to target/classes
[INFO] --- surefire:3.2.5:test (default-test) @ checker ---
[INFO] Running de.rdbht.swt.monitor.checker.StatusEvaluatorTest
[INFO] Tests run: 5, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 0.028 s
[INFO]
[INFO] Building store 1.0.0-SNAPSHOT                                      [3/9]
[INFO] --- compiler:3.15.0:compile (default-compile) @ store ---
[INFO] Compiling 4 source files with javac [debug release 17] to target/classes
[INFO] --- surefire:3.2.5:test (default-test) @ store ---
[INFO] Running de.rdbht.swt.monitor.store.StoreTest
[INFO] Tests run: 2, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 0.037 s
[INFO] Running de.rdbht.swt.monitor.store.IngestCodecTest
[INFO] Tests run: 1, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 0.014 s
[INFO]
[INFO] Building alerting 1.0.0-SNAPSHOT                                   [4/9]
[INFO] --- compiler:3.15.0:compile (default-compile) @ alerting ---
[INFO] Compiling 3 source files with javac [debug release 17] to target/classes
[INFO] --- surefire:3.2.5:test (default-test) @ alerting ---
[INFO] Running de.rdbht.swt.monitor.alerting.AlertingServiceTest
[ERROR] Tests run: 2, Failures: 1, Errors: 0, Skipped: 0, Time elapsed: 0.027 s <<< FAILURE!
[ERROR] de.rdbht.swt.monitor.alerting.AlertingServiceTest.recovery_allows_next_alert <<< FAILURE!
org.opentest4j.AssertionFailedError: expected: <2> but was: <0>
        at org.junit.jupiter.api.AssertionFailureBuilder.build(AssertionFailureBuilder.java:151)
        at org.junit.jupiter.api.Assertions.assertEquals(Assertions.java:531)
        at de.rdbht.swt.monitor.alerting.AlertingServiceTest.recovery_allows_next_alert(AlertingServiceTest.java:54)
[INFO] Results:
[ERROR] Failures:
[ERROR]   AlertingServiceTest.recovery_allows_next_alert:54 expected: <2> but was: <0>
[ERROR] Tests run: 2, Failures: 1, Errors: 0, Skipped: 0
[INFO] ------------------------------------------------------------------------
[INFO] Reactor Summary for IT-Service-Monitor (Teil C) 1.0.0-SNAPSHOT:
[INFO]
[INFO] IT-Service-Monitor (Teil C) ........................ SUCCESS [  0.002 s]
[INFO] checker ............................................ SUCCESS [  1.156 s]
[INFO] store .............................................. SUCCESS [  0.394 s]
[INFO] alerting ........................................... FAILURE [  0.339 s]
[INFO] scheduler .......................................... SKIPPED
[INFO] dashboard-ui ....................................... SKIPPED
[INFO] mock-service ....................................... SKIPPED
[INFO] agent .............................................. SKIPPED
[INFO] collector .......................................... SKIPPED
[INFO] ------------------------------------------------------------------------
[INFO] BUILD FAILURE
[INFO] ------------------------------------------------------------------------
[INFO] Total time:  1.959 s
[INFO] Finished at: 2026-07-13T20:11:05+02:00
[ERROR] Failed to execute goal ...maven-surefire-plugin:3.2.5:test (default-test) on project alerting: There are test failures.
```

</details>

## Diagnose & Fix

`AlertingService.tick` setzte beim **ersten** `DOWN`-Tick nur `downSince`, prüfte die
Fenster-Bedingung aber erst bei einem **Folge**-Tick. Beim Test mit Fenster = 0
(`recovery_allows_next_alert`) hat jede DOWN-Strecke nur einen Tick → es feuerte nie
(0 statt 2 Alarme).

**Fix:** Fenster-Prüfung auf denselben Tick ziehen, der `downSince` setzt
(`putIfAbsent`, dann sofort auswerten). Ein Null- bzw. bereits abgelaufenes Fenster
feuert damit sofort; das 5-Minuten-Fenster-Verhalten bleibt unverändert.

```java
downSince.putIfAbsent(service, now);
long since = downSince.get(service);
if (!alerted.getOrDefault(service, false) && (now - since) >= alertAfterMs) {
    sink.fire(service, "DOWN seit " + (now - since) + " ms");
    alerted.put(service, true);
}
```

## Lauf 2 — nach dem Fix (BUILD SUCCESS)

`mvn -B test`, 2026-07-13 20:13 — alle 9 Reactor-Einträge grün.

<details><summary>Vollständiges Log — Lauf 2 (BUILD SUCCESS)</summary>

```
[INFO] Building checker 1.0.0-SNAPSHOT                                    [2/9]
[INFO] Running de.rdbht.swt.monitor.checker.StatusEvaluatorTest
[INFO] Tests run: 5, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 0.027 s
[INFO]
[INFO] Building store 1.0.0-SNAPSHOT                                      [3/9]
[INFO] Running de.rdbht.swt.monitor.store.StoreTest
[INFO] Tests run: 2, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 0.026 s
[INFO] Running de.rdbht.swt.monitor.store.IngestCodecTest
[INFO] Tests run: 1, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 0.010 s
[INFO]
[INFO] Building alerting 1.0.0-SNAPSHOT                                   [4/9]
[INFO] --- compiler:3.15.0:compile (default-compile) @ alerting ---
[INFO] Recompiling the module because of changed source code.
[INFO] Compiling 3 source files with javac [debug release 17] to target/classes
[INFO] Running de.rdbht.swt.monitor.alerting.AlertingServiceTest
[INFO] Tests run: 2, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 0.023 s
[INFO]
[INFO] Building scheduler 1.0.0-SNAPSHOT                                  [5/9]
[INFO] Running de.rdbht.swt.monitor.scheduler.SchedulerTest
[INFO] Tests run: 1, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 0.022 s
[INFO]
[INFO] Building dashboard-ui 1.0.0-SNAPSHOT                               [6/9]
[INFO] Running de.rdbht.swt.monitor.dashboard.StatusRendererTest
[INFO] Tests run: 1, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 0.022 s
[INFO]
[INFO] Building mock-service 1.0.0-SNAPSHOT                               [7/9]
[INFO] Running de.rdbht.swt.monitor.mock.MockServiceTest
[INFO] Tests run: 1, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 0.021 s
[INFO]
[INFO] Building agent 1.0.0-SNAPSHOT                                      [8/9]
[INFO] --- compiler:3.15.0:compile (default-compile) @ agent ---
[INFO] Compiling 2 source files with javac [debug release 17] to target/classes
[INFO] --- surefire:3.2.5:test (default-test) @ agent ---
[INFO] No tests to run.
[INFO]
[INFO] Building collector 1.0.0-SNAPSHOT                                  [9/9]
[INFO] --- compiler:3.15.0:compile (default-compile) @ collector ---
[INFO] Compiling 2 source files with javac [debug release 17] to target/classes
[INFO] --- surefire:3.2.5:test (default-test) @ collector ---
[INFO] No tests to run.
[INFO] ------------------------------------------------------------------------
[INFO] Reactor Summary for IT-Service-Monitor (Teil C) 1.0.0-SNAPSHOT:
[INFO]
[INFO] IT-Service-Monitor (Teil C) ........................ SUCCESS [  0.000 s]
[INFO] checker ............................................ SUCCESS [  0.732 s]
[INFO] store .............................................. SUCCESS [  0.264 s]
[INFO] alerting ........................................... SUCCESS [  0.567 s]
[INFO] scheduler .......................................... SUCCESS [  0.342 s]
[INFO] dashboard-ui ....................................... SUCCESS [  0.362 s]
[INFO] mock-service ....................................... SUCCESS [  0.306 s]
[INFO] agent .............................................. SUCCESS [  0.045 s]
[INFO] collector .......................................... SUCCESS [  0.054 s]
[INFO] ------------------------------------------------------------------------
[INFO] BUILD SUCCESS
[INFO] ------------------------------------------------------------------------
[INFO] Total time:  2.742 s
[INFO] Finished at: 2026-07-13T20:13:35+02:00
[INFO] ------------------------------------------------------------------------
```

</details>

## Testübersicht

| Modul | Test | Anzahl |
|---|---|---|
| checker | StatusEvaluatorTest | 5 |
| store | StoreTest, IngestCodecTest | 3 |
| alerting | AlertingServiceTest | 2 |
| scheduler | SchedulerTest | 1 |
| dashboard-ui | StatusRendererTest | 1 |
| mock-service | MockServiceTest | 1 |
| agent, collector | (kompilieren, keine Unit-Tests) | — |
| **Summe** | | **13 grün** |

## Bewertung

Der Ablauf zeigt zwei prüfungsrelevante Dinge: Die Tests sind keine Deko — sie haben
einen realen Grenzfall-Fehler aufgedeckt (Alarm bei Null-Fenster). Und der Zyklus
**rot → Fix → grün** ist über die Git-Historie nachvollziehbar.

> **Optionaler Screenshot:** der grüne `mvn -B test`-Lauf → `docs/img/c-app-tests.png`.
