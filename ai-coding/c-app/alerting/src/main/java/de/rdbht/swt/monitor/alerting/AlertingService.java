package de.rdbht.swt.monitor.alerting;

import de.rdbht.swt.monitor.checker.Status;

import java.util.HashMap;
import java.util.Map;

/**
 * Erkennt anhaltende Ausfälle und löst PRO Ausfall genau EINEN Alarm aus — erst nachdem ein
 * Ziel länger als das konfigurierte Fenster ({@code alertAfterMs}) DOWN ist. Erholung setzt
 * den Zustand zurück, sodass ein späterer Ausfall wieder alarmieren kann.
 *
 * Zusammenhang: lebt im collector-Prozess. Bei jedem empfangenen Ergebnis ruft der
 * CollectorServer tick(service, status) auf. Die Zeit kommt über die injizierte Clock, der
 * Alarm geht an den injizierten AlertSink (beide austauschbar → deterministisch testbar).
 * Der Zustand liegt im Speicher (KISS — bei Neustart geht eine laufende DOWN-Strecke verloren,
 * siehe Risiko-Notiz in ARC-E2).
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

    /** Verarbeitet eine einzelne Status-Beobachtung für einen Dienst. */
    public void tick(String service, Status status) {
        long now = clock.nowMillis();
        if (status != Status.DOWN) {
            // Erholung oder jeder Nicht-DOWN-Zustand setzt die Ausfall-Verfolgung zurück
            downSince.remove(service);
            alerted.remove(service);
            return;
        }
        // Ausfallbeginn merken und die Entprellung auf DEMSELBEN Tick prüfen —
        // so kann ein Null- bzw. bereits abgelaufenes Fenster sofort feuern.
        downSince.putIfAbsent(service, now);
        long since = downSince.get(service);
        if (!alerted.getOrDefault(service, false) && (now - since) >= alertAfterMs) {
            sink.fire(service, "DOWN seit " + (now - since) + " ms");
            alerted.put(service, true);
        }
    }
}
