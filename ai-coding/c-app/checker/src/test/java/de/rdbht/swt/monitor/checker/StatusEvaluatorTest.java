package de.rdbht.swt.monitor.checker;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/** Unit tests for the pure decision logic — no network involved. */
class StatusEvaluatorTest {

    private final StatusEvaluator eval = new StatusEvaluator(500);

    @Test
    void ok_and_fast_is_up() {
        assertEquals(Status.UP, eval.evaluate(200, 120));
    }

    @Test
    void ok_but_slow_is_degraded() {
        assertEquals(Status.DEGRADED, eval.evaluate(200, 800));
    }

    @Test
    void error_code_is_down() {
        assertEquals(Status.DOWN, eval.evaluate(503, 50));
    }

    @Test
    void non_positive_threshold_rejected() {
        assertThrows(IllegalArgumentException.class, () -> new StatusEvaluator(0));
    }

    @Test
    void negative_response_time_rejected() {
        assertThrows(IllegalArgumentException.class, () -> eval.evaluate(200, -1));
    }
}
