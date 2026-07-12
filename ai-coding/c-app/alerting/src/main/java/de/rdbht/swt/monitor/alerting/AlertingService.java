package de.rdbht.swt.monitor.alerting;

import de.rdbht.swt.monitor.checker.Status;

import java.util.HashMap;
import java.util.Map;

/**
 * Detects sustained outages and fires exactly one alert per outage, after the
 * configured debounce window ({@code alertAfterMs}). Recovery resets the state
 * so a later outage can alert again. State is kept in memory (KISS — see the
 * ARC-E2 risk note on state loss at restart).
 */
public final class AlertingService {

    private final long alertAfterMs;
    private final Clock clock;
    private final AlertSink sink;
    private final Map<String, Long> downSince = new HashMap<>();
    private final Map<String, Boolean> alerted = new HashMap<>();

    public AlertingService(long alertAfterMs, Clock clock, AlertSink sink) {
        if (alertAfterMs < 0) {
            throw new IllegalArgumentException("alertAfterMs must not be negative");
        }
        this.alertAfterMs = alertAfterMs;
        this.clock = clock;
        this.sink = sink;
    }

    /** Feed one status observation for a service. */
    public void tick(String service, Status status) {
        long now = clock.nowMillis();
        if (status == Status.DOWN) {
            Long since = downSince.get(service);
            if (since == null) {
                downSince.put(service, now);
                alerted.put(service, false);
            } else if (!alerted.getOrDefault(service, false) && (now - since) >= alertAfterMs) {
                sink.fire(service, "DOWN seit " + (now - since) + " ms");
                alerted.put(service, true);
            }
        } else {
            // recovery or any non-down state resets the outage tracking
            downSince.remove(service);
            alerted.remove(service);
        }
    }
}
