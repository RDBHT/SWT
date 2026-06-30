package de.rdbht.swt.tst.monitor;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;
import org.junit.jupiter.api.Test;

class UptimeCalculatorTest {

  private final UptimeCalculator calc = new UptimeCalculator();

  @Test
  void allUpIsHundredPercent() {
    assertEquals(100.0, calc.uptimePercent(List.of(Status.UP, Status.UP)));
  }

  @Test
  void degradedCountsAsAvailable() {
    assertEquals(100.0, calc.uptimePercent(List.of(Status.UP, Status.DEGRADED)));
  }

  @Test
  void oneDownInFourIs75Percent() {
    assertEquals(75.0,
        calc.uptimePercent(List.of(Status.UP, Status.UP, Status.DOWN, Status.UP)));
  }

  @Test
  void emptyWindowIsRejected() {
    assertThrows(IllegalArgumentException.class, () -> calc.uptimePercent(List.of()));
  }
}
