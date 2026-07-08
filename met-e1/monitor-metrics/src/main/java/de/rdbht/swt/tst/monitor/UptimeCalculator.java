package de.rdbht.swt.tst.monitor;

import java.util.List;

/**
 * Aggregates a window of {@link Status} samples into an availability (SLA)
 * percentage. {@link Status#DEGRADED} counts as available (service reachable),
 * only {@link Status#DOWN} reduces uptime.
 */
public class UptimeCalculator {

  /**
   * @param samples ordered status samples of one window; must be non-empty
   * @return availability in percent, 0.0 .. 100.0
   * @throws IllegalArgumentException if samples is null or empty
   */
  public double uptimePercent(List<Status> samples) {
    if (samples == null || samples.isEmpty()) {
      throw new IllegalArgumentException("samples must not be empty");
    }
    long available = samples.stream().filter(s -> s != Status.DOWN).count();
    return available * 100.0 / samples.size();
  }
}
