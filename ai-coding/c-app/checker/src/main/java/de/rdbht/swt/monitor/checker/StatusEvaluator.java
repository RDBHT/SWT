package de.rdbht.swt.monitor.checker;

/**
 * Bildet einen HTTP-Antwortcode und eine Antwortzeit auf einen {@link Status} ab.
 *
 * Zusammenhang: die einzige "Urteils"-Logik des checker-Moduls und bewusst REIN gehalten
 * (kein Netz, keine Zeit-Abhängigkeit) — dadurch vollständig unit-testbar (StatusEvaluatorTest).
 * Der HttpCheck ruft evaluate(...) mit dem tatsächlich gemessenen Code und der Zeit auf.
 */
public final class StatusEvaluator {

    private final long degradedThresholdMs;

    public StatusEvaluator(long degradedThresholdMs) {
        if (degradedThresholdMs <= 0) {
            throw new IllegalArgumentException("degradedThresholdMs must be positive");
        }
        this.degradedThresholdMs = degradedThresholdMs;
    }

    /** 2xx/3xx innerhalb des Schwellwerts = UP, langsamer = DEGRADED, alles andere = DOWN. */
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
