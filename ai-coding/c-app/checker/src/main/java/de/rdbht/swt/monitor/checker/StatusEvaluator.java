package de.rdbht.swt.monitor.checker;

/**
 * Maps an HTTP response code and response time to a {@link Status}.
 * Pure decision logic — no I/O, therefore fully unit-testable (see StatusEvaluatorTest).
 */
public final class StatusEvaluator {

    private final long degradedThresholdMs;

    public StatusEvaluator(long degradedThresholdMs) {
        if (degradedThresholdMs <= 0) {
            throw new IllegalArgumentException("degradedThresholdMs must be positive");
        }
        this.degradedThresholdMs = degradedThresholdMs;
    }

    /** 2xx/3xx within the threshold is UP, slower is DEGRADED, everything else DOWN. */
    public Status evaluate(int httpCode, long responseMs) {
        if (responseMs < 0) {
            throw new IllegalArgumentException("responseMs must not be negative");
        }
        boolean answeredOk = httpCode >= 200 && httpCode < 400;
        if (!answeredOk) {
            return Status.DOWN;
        }
        return responseMs > degradedThresholdMs ? Status.DEGRADED : Status.UP;
    }
}
