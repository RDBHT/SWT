package de.rdbht.swt.monitor.collector;

import de.rdbht.swt.monitor.alerting.AlertingService;
import de.rdbht.swt.monitor.store.HistoryStore;
import de.rdbht.swt.monitor.store.InMemoryHistoryStore;

import java.io.IOException;

/**
 * Collector process entry point. Owns the store and the alerting pipeline and
 * exposes the ingest endpoint plus the dashboard. Runs in its own JVM — the
 * consumer half of the distributed system.
 *
 * <p>Usage: {@code CollectorMain [port]} (default 8080)
 */
public final class CollectorMain {

    public static void main(String[] args) throws IOException {
        int port = args.length > 0 ? Integer.parseInt(args[0]) : 8080;

        HistoryStore store = new InMemoryHistoryStore();
        AlertingService alerting = new AlertingService(
                15_000,                                  // 15 s Ausfallfenster (Demo)
                System::currentTimeMillis,
                (service, message) -> System.out.println("ALERT  " + service + ": " + message));

        new CollectorServer(store, alerting).start(port);
        System.out.println("collector on :" + port + "   (POST /ingest, GET /)");
    }
}
