package de.rdbht.swt.tst.monitor;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
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
}
