package de.rdbht.swt.monitor.alerting;

import de.rdbht.swt.monitor.checker.Status;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Deterministische Tests mit handgeschriebenen Fakes (steuerbare Uhr, aufzeichnender Sink)
 * statt eines Mocking-Frameworks — die Kollaborateure sind einfach genug.
 */
class AlertingServiceTest {

    /** Steuerbare Zeitquelle. */
    static final class FakeClock implements Clock {
        long t;
        @Override public long nowMillis() { return t; }
    }

    /** Zeichnet auf, welche Dienste alarmiert wurden. */
    static final class CapturingSink implements AlertSink {
        final List<String> fired = new ArrayList<>();
        @Override public void fire(String service, String message) { fired.add(service); }
    }

    @Test
    void fires_exactly_once_after_window() {
        FakeClock clock = new FakeClock();
        CapturingSink sink = new CapturingSink();
        AlertingService svc = new AlertingService(300_000, clock, sink); // 5-minute window

        clock.t = 0;        svc.tick("api", Status.DOWN); // Ausfall beginnt
        clock.t = 60_000;   svc.tick("api", Status.DOWN); // 1 min — zu früh
        clock.t = 300_000;  svc.tick("api", Status.DOWN); // 5 min — feuert
        clock.t = 360_000;  svc.tick("api", Status.DOWN); // weiterhin DOWN — kein zweiter Alarm

        assertEquals(1, sink.fired.size());
        assertEquals("api", sink.fired.get(0));
    }

    @Test
    void recovery_allows_next_alert() {
        FakeClock clock = new FakeClock();
        CapturingSink sink = new CapturingSink();
        AlertingService svc = new AlertingService(0, clock, sink); // fire immediately

        clock.t = 0;  svc.tick("api", Status.DOWN); // feuert
        svc.tick("api", Status.UP);                 // Erholung setzt zurück
        clock.t = 10; svc.tick("api", Status.DOWN); // feuert erneut

        assertEquals(2, sink.fired.size());
    }
}
