package de.rdbht.swt.monitor.agent;

import de.rdbht.swt.monitor.checker.HttpCheck;
import de.rdbht.swt.monitor.checker.StatusEvaluator;
import de.rdbht.swt.monitor.checker.TcpCheck;
import de.rdbht.swt.monitor.scheduler.MonitoredService;
import de.rdbht.swt.monitor.scheduler.Scheduler;

import java.util.List;

/**
 * Check-Agent process — the producer half of the distributed system. Periodically
 * probes the configured targets and hands every result to the {@link IngestClient},
 * which forwards it over HTTP to the collector. Runs in its own JVM.
 *
 * <p>Usage: {@code AgentMain [collectorIngestUrl] [httpTarget] [tcpTarget]}
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
