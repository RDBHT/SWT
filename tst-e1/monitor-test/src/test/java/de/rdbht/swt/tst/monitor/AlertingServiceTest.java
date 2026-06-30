package de.rdbht.swt.tst.monitor;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;

/**
 * Teil 3 — Mocking. The {@link AlertingService} talks to a network probe, the
 * system clock and a notification channel. All three are replaced by Mockito
 * mocks so the alerting logic can be tested deterministically and offline.
 */
class AlertingServiceTest {

  private final Target target = new Target("api.example.com", 443);
  private final StatusEvaluator evaluator = new StatusEvaluator(1000);

  @Test
  void reportsDownWhenProbeFailsButDoesNotAlertImmediately() {
    HttpProbe probe = mock(HttpProbe.class);
    Clock clock = mock(Clock.class);
    AlertSink sink = mock(AlertSink.class);

    // Stub the unpleasant network call: pretend the service returns HTTP 503.
    when(probe.probe(target)).thenReturn(new ProbeResult(503, 20));
    when(clock.nowMillis()).thenReturn(0L);

    AlertingService service =
        new AlertingService(probe, clock, sink, evaluator, 300_000); // 5 min window

    assertEquals(Status.DOWN, service.tick(target));
    // Outage streak only just started -> no alert yet.
    verify(sink, never()).fire(any(), any());
  }

  @Test
  void recoveryClearsTheOutageStreak() {
    HttpProbe probe = mock(HttpProbe.class);
    Clock clock = mock(Clock.class);
    AlertSink sink = mock(AlertSink.class);

    // First DOWN, then UP again.
    when(probe.probe(target))
        .thenReturn(new ProbeResult(500, 10))
        .thenReturn(new ProbeResult(200, 50));
    when(clock.nowMillis()).thenReturn(0L, 10L);

    AlertingService service =
        new AlertingService(probe, clock, sink, evaluator, 300_000);

    service.tick(target); // DOWN, streak starts
    assertEquals(Status.UP, service.tick(target)); // recovered
    verify(sink, never()).fire(any(), any());
  }

  @Test
  void alertsExactlyOnceAfterTheOutageWindowIsExceeded() {
    HttpProbe probe = mock(HttpProbe.class);
    Clock clock = mock(Clock.class);
    AlertSink sink = mock(AlertSink.class);

    // Service is permanently unreachable (HTTP 0 = no response -> DOWN).
    when(probe.probe(target)).thenReturn(new ProbeResult(0, 0));
    // Fast-forward the clock across four ticks: 0s, 60s, 300s, 360s.
    when(clock.nowMillis()).thenReturn(0L, 60_000L, 300_000L, 360_000L);

    AlertingService service =
        new AlertingService(probe, clock, sink, evaluator, 300_000); // 5 min

    service.tick(target); // t=0:   streak starts
    service.tick(target); // t=60s: within window, still silent
    service.tick(target); // t=300s: window reached -> ALERT
    service.tick(target); // t=360s: already alerted -> no duplicate

    // Mocking the clock makes a 5-minute rule testable in microseconds,
    // and verify(times(1)) proves the de-duplication of alerts.
    verify(sink, times(1)).fire(eq(target), anyString());
  }
}
