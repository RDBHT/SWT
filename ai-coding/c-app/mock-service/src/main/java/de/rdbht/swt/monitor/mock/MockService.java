package de.rdbht.swt.monitor.mock;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Controllable test target for the monitor. Serves {@code /health} with a settable
 * HTTP status code so checks can be driven deterministically without real services;
 * {@code /admin?code=NNN} changes that code at runtime.
 *
 * <p>Built with Cline (VS-Code-Plugin) — the second required tool type for Teil C.
 */
public final class MockService {

    private final AtomicInteger healthCode = new AtomicInteger(200);
    private HttpServer server;

    /** Set the HTTP code that {@code /health} returns (e.g. 500 to simulate an outage). */
    public void setHealthCode(int code) {
        healthCode.set(code);
    }

    public int healthCode() {
        return healthCode.get();
    }

    public void start(int port) throws IOException {
        server = HttpServer.create(new InetSocketAddress(port), 0);
        server.createContext("/health", exchange -> respond(exchange, healthCode.get(), "health"));
        server.createContext("/admin", exchange -> {
            String query = exchange.getRequestURI().getQuery();
            if (query != null && query.startsWith("code=")) {
                setHealthCode(Integer.parseInt(query.substring("code=".length())));
            }
            respond(exchange, 200, "code=" + healthCode.get());
        });
        server.start();
    }

    private void respond(HttpExchange exchange, int code, String body) throws IOException {
        byte[] bytes = body.getBytes(StandardCharsets.UTF_8);
        exchange.sendResponseHeaders(code, bytes.length);
        try (OutputStream os = exchange.getResponseBody()) {
            os.write(bytes);
        }
    }

    public void stop() {
        if (server != null) {
            server.stop(0);
        }
    }

    public static void main(String[] args) throws IOException {
        int port = args.length > 0 ? Integer.parseInt(args[0]) : 8081;
        new MockService().start(port);
        System.out.println("mock-service on :" + port + " (GET /health, /admin?code=NNN)");
    }
}
