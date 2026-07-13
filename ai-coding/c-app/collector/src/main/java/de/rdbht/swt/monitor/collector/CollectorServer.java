package de.rdbht.swt.monitor.collector;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpServer;
import de.rdbht.swt.monitor.alerting.AlertingService;
import de.rdbht.swt.monitor.dashboard.StatusRenderer;
import de.rdbht.swt.monitor.store.HistoryStore;
import de.rdbht.swt.monitor.store.IngestCodec;
import de.rdbht.swt.monitor.store.StatusRecord;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.nio.charset.StandardCharsets;

/**
 * Collector-Prozess (Consumer/Verarbeiter). Empfängt Check-Ergebnisse vom Agenten per
 * {@code POST /ingest}, verarbeitet sie (in den store schreiben, die Alerting-Pipeline speisen)
 * und liefert das Dashboard unter {@code GET /}.
 *
 * Zusammenhang: die Verarbeiter-Hälfte des verteilten Systems. Kennt den Agenten NICHT und
 * reagiert nur auf eingehende Anfragen — mehrere Agenten könnten in denselben Collector schreiben.
 * Führt selbst keine Checks aus.
 */
public final class CollectorServer {

    private final HistoryStore store;
    private final AlertingService alerting;
    private final StatusRenderer renderer = new StatusRenderer();
    private HttpServer server;

    public CollectorServer(HistoryStore store, AlertingService alerting) {
        this.store = store;
        this.alerting = alerting;
    }

    public void start(int port) throws IOException {
        server = HttpServer.create(new InetSocketAddress(port), 0);
        server.createContext("/ingest", this::handleIngest);
        server.createContext("/", this::handleDashboard);
        server.start();
    }

    private void handleIngest(HttpExchange exchange) throws IOException {
        if (!"POST".equals(exchange.getRequestMethod())) {
            exchange.sendResponseHeaders(405, -1);
            exchange.close();
            return;
        }
        String body = new String(exchange.getRequestBody().readAllBytes(), StandardCharsets.UTF_8);
        StatusRecord record = IngestCodec.decode(body);
        store.append(record);
        alerting.tick(record.service(), record.status());
        System.out.println("<- ingest " + record.service() + " " + record.status() + " " + record.responseMs() + "ms");
        exchange.sendResponseHeaders(200, -1);
        exchange.close();
    }

    private void handleDashboard(HttpExchange exchange) throws IOException {
        String html = "<!doctype html><meta charset=utf-8><title>Collector</title>"
                + "<h1>IT-Service-Monitor &mdash; Collector</h1>"
                + "<p>Empfangen vom Agenten über POST /ingest:</p>"
                + renderer.render(store.latestPerService());
        byte[] bytes = html.getBytes(StandardCharsets.UTF_8);
        exchange.getResponseHeaders().set("Content-Type", "text/html; charset=utf-8");
        exchange.sendResponseHeaders(200, bytes.length);
        try (OutputStream os = exchange.getResponseBody()) {
            os.write(bytes);
        }
    }

    public void stop() {
        if (server != null) {
            server.stop(0);
        }
    }
}
