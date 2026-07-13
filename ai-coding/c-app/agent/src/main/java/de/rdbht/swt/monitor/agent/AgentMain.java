package de.rdbht.swt.monitor.agent;

import de.rdbht.swt.monitor.checker.HttpCheck;
import de.rdbht.swt.monitor.checker.StatusEvaluator;
import de.rdbht.swt.monitor.checker.TcpCheck;
import de.rdbht.swt.monitor.scheduler.MonitoredService;
import de.rdbht.swt.monitor.scheduler.Scheduler;

import java.util.List;

/**
 * Check-Agent-Prozess — die Produzenten-Hälfte des verteilten Systems. Prüft periodisch die
 * konfigurierten Ziele und übergibt jedes Ergebnis dem {@link IngestClient}, der es per HTTP an
 * den collector weiterleitet. Läuft in einer eigenen JVM.
 *
 * Zusammenhang: verdrahtet checker + scheduler + IngestClient. Standardmäßig prüft er den
 * mock-service (mock-http per HTTP, mock-tcp per Port) alle 5 s.
 *
 * <p>Aufruf: {@code AgentMain [collectorIngestUrl] [httpTarget] [tcpTarget]}
 */
public final class AgentMain {

    public static void main(String[] args) {
        String ingestUrl = args.length > 0 ? args[0] : "http://localhost:8080/ingest";
        String httpTarget = args.length > 1 ? args[1] : "http://localhost:8081/health";
        String tcpTarget = args.length > 2 ? args[2] : "localhost:8081";

        IngestClient client = new IngestClient(ingestUrl);
        Scheduler scheduler = new Scheduler(List.of(
                new MonitoredService("mock-http", new HttpCheck(new StatusEvaluator(500), 2000), httpTarget),
                new MonitoredService("mock-tcp", new TcpCheck(2000), tcpTarget)),
                client);

        System.out.println("agent -> collector " + ingestUrl + "  | Checks alle 5 s (Strg+C zum Beenden)");
        scheduler.start(5000);
    }
}
