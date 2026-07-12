package de.rdbht.swt.monitor.alerting;

import de.rdbht.swt.monitor.checker.Status;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Deterministic tests using hand-written fakes (controllable clock, capturing sink)
 * instead of a mocking framework — the collaborators are simple enough.
 */
class AlertingServiceTest {

    /** Controllable time source. */
    static final class FakeClock implements Clock {
        long t;
        @Override public long nowMillis() { return t; }
    }

    /** Records which services were alerted. */
    static final class CapturingSink implements AlertSink {
        final List<String> fired = new ArrayList<>();
        @Override public void fire(String service, String message) { fired.add(service); }
    }

    @Test
    void fires_exactly_once_after_window() {
        FakeClock clock = new FakeClock();
        CapturingSink sink = new CapturingSink();
        AlertingService svc = new AlertingService(300_000, clock, sink); // 5-minute window

        clock.t = 0;        svc.tick("api", Status.DOWN); // outage starts
        clock.t = 60_000;   svc.tick("api", Status.DOWN); // 1 min — too early
        clock.t = 300_000;  svc.tick("api", Status.DOWN); // 5 min — fire
        clock.t = 360_000;  svc.tick("api", Status.DOWN); // still down — no second alert

        assertEquals(1, sink.fired.size());
        assertEquals("api", sink.fired.get(0));
    }

    @Test
    void recovery_allows_next_alert() {
        FakeClock clock = new FakeClock();
        CapturingSink sink = new CapturingSink();
        AlertingService svc = new AlertingService(0, clock, sink); // fire immediately

        clock.t = 0;  svc.tick("api", Status.DOWN); // fires
        svc.tick("api", Status.UP);                 // recovery resets
        clock.t = 10; svc.tick("api", Status.DOWN); // fires again

        assertEquals(2, sink.fired.size());
    }
}
