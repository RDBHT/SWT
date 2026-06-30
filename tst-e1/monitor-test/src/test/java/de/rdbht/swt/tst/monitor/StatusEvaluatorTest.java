package de.rdbht.swt.tst.monitor;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

class StatusEvaluatorTest {

  private final StatusEvaluator evaluator = new StatusEvaluator(1000);

  @Test
  void okAndFastIsUp() {
    assertEquals(Status.UP, evaluator.evaluate(200, 200));
  }

  @Test
  void okButSlowIsDegraded() {
    assertEquals(Status.DEGRADED, evaluator.evaluate(200, 1500));
  }

  @Test
  void exactlyAtThresholdIsStillUp() {
    assertEquals(Status.UP, evaluator.evaluate(200, 1000));
  }

  @Test
  void serverErrorIsDown() {
    assertEquals(Status.DOWN, evaluator.evaluate(500, 50));
  }

  @Test
  void noResponseIsDown() {
    assertEquals(Status.DOWN, evaluator.evaluate(0, 0));
  }

  // --- Exception tests (Pflicht der Aufgabe) ---

  @Test
  void nonPositiveThresholdIsRejected() {
    assertThrows(IllegalArgumentException.class, () -> new StatusEvaluator(0));
  }

  @Test
  void negativeResponseTimeIsRejected() {
    assertThrows(IllegalArgumentException.class, () -> evaluator.evaluate(200, -1));
  }
}
