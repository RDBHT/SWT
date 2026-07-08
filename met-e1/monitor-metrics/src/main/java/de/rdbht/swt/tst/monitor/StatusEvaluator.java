package de.rdbht.swt.tst.monitor;

/**
 * Pure decision logic that maps a raw probe outcome (HTTP status code plus
 * response time) onto a {@link Status}. Deterministic and free of any I/O, so
 * it is the natural place for plain unit tests (Teil 1).
 */
public class StatusEvaluator {

  private final long degradedThresholdMs;

  /**
   * @param degradedThresholdMs response time above which a healthy answer counts
   *                            as {@link Status#DEGRADED}; must be positive
   * @throws IllegalArgumentException if the threshold is not positive
   */
  public StatusEvaluator(long degradedThresholdMs) {
    if (degradedThresholdMs <= 0) {
      throw new IllegalArgumentException(
          "degradedThresholdMs must be positive, was " + degradedThresholdMs);
    }
    this.degradedThresholdMs = degradedThresholdMs;
  }

  /**
   * @param httpStatus     HTTP status code; 0 means "no response / timeout"
   * @param responseTimeMs measured round-trip time in milliseconds
   * @return the derived {@link Status}
   * @throws IllegalArgumentException if responseTimeMs is negative
   */
  public Status evaluate(int httpStatus, long responseTimeMs) {
    if (responseTimeMs < 0) {
      throw new IllegalArgumentException(
          "responseTimeMs must not be negative, was " + responseTimeMs);
    }
    boolean answeredOk = httpStatus >= 200 && httpStatus < 400;
    if (!answeredOk) {
      return Status.DOWN;
    }
    return responseTimeMs <= degradedThresholdMs ? Status.UP : Status.DEGRADED;
  }
}
