package de.rdbht.swt.tst.monitor;

/**
 * Core monitoring logic for Teil 3. On each {@link #tick(Target)} it probes the
 * target, evaluates the status and raises an alert via {@link AlertSink} only
 * once a target has stayed {@link Status#DOWN} for longer than the configured
 * window. All three collaborators ({@link HttpProbe}, {@link Clock},
 * {@link AlertSink}) are injected via the constructor so they can be mocked.
 */
public class AlertingService {

  private final HttpProbe probe;
  private final Clock clock;
  private final AlertSink alertSink;
  private final StatusEvaluator evaluator;
  private final long alertAfterMs;

  private Long downSince;   // null = not in a DOWN streak
  private boolean alerted;

  public AlertingService(HttpProbe probe, Clock clock, AlertSink alertSink,
                         StatusEvaluator evaluator, long alertAfterMs) {
    if (alertAfterMs < 0) {
      throw new IllegalArgumentException("alertAfterMs must not be negative");
    }
    this.probe = probe;
    this.clock = clock;
    this.alertSink = alertSink;
    this.evaluator = evaluator;
    this.alertAfterMs = alertAfterMs;
  }

  /**
   * Performs one monitoring cycle for the given target.
   *
   * @return the status observed in this cycle
   */
  public Status tick(Target target) {
    ProbeResult result = probe.probe(target);
    Status status = evaluator.evaluate(result.httpStatus(), result.responseTimeMs());

    if (status == Status.DOWN) {
      long now = clock.nowMillis();
      if (downSince == null) {
        downSince = now;
      } else if (!alerted && now - downSince >= alertAfterMs) {
        alertSink.fire(target,
            "Service " + target + " down for " + (now - downSince) + " ms");
        alerted = true;
      }
    } else {
      downSince = null;
      alerted = false;
    }
    return status;
  }
}
