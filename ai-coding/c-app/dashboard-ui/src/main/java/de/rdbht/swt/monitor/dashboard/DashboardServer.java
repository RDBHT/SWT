package de.rdbht.swt.monitor.dashboard;

import com.sun.net.httpserver.HttpServer;
import de.rdbht.swt.monitor.store.HistoryStore;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.nio.charset.StandardCharsets;

/**
 * Read-only HTTP-Dashboard: liefert den jüngsten Status je Dienst aus dem Store.
 *
 * Zusammenhang: die lokale (Ein-Prozess-)Variante der Anzeige; im verteilten Betrieb
 * übernimmt der CollectorServer dieselbe Aufgabe über GET /. Der Store ist die einzige
 * Koppelstelle (Client-Server; das Dashboard schreibt nie).
 */
public final class DashboardServer {

    private final HistoryStore store;
    private final StatusRenderer renderer = new StatusRenderer();
    private HttpServer server;

    public DashboardServer(HistoryStore store) {
        this.store = store;
    }

    public void start(int port) throws IOException {
        server = HttpServer.create(new InetSocketAddress(port), 0);
        server.createContext("/", exchange -> {
            String body = "<!doctype html><meta charset=utf-8><title>Monitor</title>"
                    + "<h1>IT-Service-Monitor</h1>" + renderer.render(store.latestPerService());
            byte[] bytes = body.getBytes(StandardCharsets.UTF_8);
            exchange.getResponseHeaders().set("Content-Type", "text/html; charset=utf-8");
            exchange.sendResponseHeaders(200, bytes.length);
            try (OutputStream os = exchange.getResponseBody()) {
                os.write(bytes);
            }
        });
        server.start();
    }

    public void stop() {
        if (server != null) {
            server.stop(0);
        }
    }
}
