package de.rdbht.swt.monitor.checker;

import java.net.InetSocketAddress;
import java.net.Socket;

/**
 * TCP-Check: versucht, eine Socket-Verbindung zu {@code host:port} zu öffnen.
 *
 * Zusammenhang: zweite Check-Strategie. Gelingt der Verbindungsaufbau innerhalb des
 * Timeouts, gilt der Port als offen (UP), sonst DOWN. Kein StatusEvaluator nötig, weil es
 * hier nur "verbunden / nicht verbunden" gibt.
 */
public final class TcpCheck implements Check {

    private final int timeoutMs;

    public TcpCheck(int timeoutMs) {
        this.timeoutMs = timeoutMs;
    }

    @Override
    public String type() {
        return "TCP";
    }

    @Override
    public CheckResult run(String target) {
        String[] parts = target.split(":");
        if (parts.length != 2) {
            throw new IllegalArgumentException("target must be host:port");
        }
        String host = parts[0];
        int port = Integer.parseInt(parts[1]);
        long start = System.currentTimeMillis();
        try (Socket socket = new Socket()) {
            socket.connect(new InetSocketAddress(host, port), timeoutMs);
            long elapsed = System.currentTimeMillis() - start;
            return new CheckResult(Status.UP, elapsed, "connected");
        } catch (Exception e) {
            long elapsed = System.currentTimeMillis() - start;
            return new CheckResult(Status.DOWN, elapsed, "refused/timeout");
        }
    }
}
