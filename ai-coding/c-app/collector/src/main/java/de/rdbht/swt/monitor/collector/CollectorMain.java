package de.rdbht.swt.monitor.collector;

import de.rdbht.swt.monitor.alerting.AlertingService;
import de.rdbht.swt.monitor.store.HistoryStore;
import de.rdbht.swt.monitor.store.InMemoryHistoryStore;

import java.io.IOException;

/**
 * Einstiegspunkt des Collector-Prozesses. Hält den Store und die Alerting-Pipeline und stellt
 * den Ingest-Endpunkt plus das Dashboard bereit. Läuft in einer eigenen JVM — die
 * Consumer-Hälfte des verteilten Systems.
 *
 * Zusammenhang: erzeugt die konkreten Umsetzungen (InMemoryHistoryStore, AlertingService mit
 * 15-s-Fenster, Konsolen-AlertSink) und startet den CollectorServer auf dem Port.
 *
 * <p>Aufruf: {@code CollectorMain [port]} (Default 8080)
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
