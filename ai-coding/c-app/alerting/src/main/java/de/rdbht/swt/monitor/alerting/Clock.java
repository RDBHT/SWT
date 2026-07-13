package de.rdbht.swt.monitor.alerting;

/**
 * Injizierte Zeitquelle.
 *
 * Zusammenhang: der AlertingService fragt die Zeit über diese Schnittstelle ab, statt direkt
 * System.currentTimeMillis() zu nutzen. Dadurch kann der Test eine FakeClock einsetzen und
 * "Zeitreisen" simulieren — deterministisch, ohne echtes Warten.
 */
public interface Clock {
    long nowMillis();
}
