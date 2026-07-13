package de.rdbht.swt.monitor.store;

import de.rdbht.swt.monitor.checker.Status;

import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.time.Instant;

/**
 * Wire format for a {@link StatusRecord}: a simple form-urlencoded line. This lets
 * the agent and the collector exchange results over HTTP without pulling in a JSON
 * library — the whole protocol stays small enough to explain line by line.
 */
public final class IngestCodec {

    private IngestCodec() {
    }

    /** Serialise a record to {@code service=...&status=...&responseMs=...&timestamp=...}. */
    public static String encode(StatusRecord r) {
        return "service=" + enc(r.service())
                + "&status=" + r.status()
                + "&responseMs=" + r.responseMs()
                + "&timestamp=" + enc(r.timestamp().toString());
    }

    /** Parse a form-urlencoded body back into a record. */
    public static StatusRecord decode(String body) {
        String service = null;
        String status = null;
        String timestamp = null;
        long responseMs = 0;
        for (String pair : body.split("&")) {
            int eq = pair.indexOf('=');
            if (eq < 0) {
                continue;
            }
            String key = pair.substring(0, eq);
            String value = dec(pair.substring(eq + 1));
            switch (key) {
                case "service" -> service = value;
                case "status" -> status = value;
                case "responseMs" -> responseMs = Long.parseLong(value);
                case "timestamp" -> timestamp = value;
                default -> { /* ignore unknown keys */ }
            }
        }
        return new StatusRecord(service, Status.valueOf(status), responseMs, Instant.parse(timestamp));
    }

    private static String enc(String s) {
        return URLEncoder.encode(s, StandardCharsets.UTF_8);
    }

    private static String dec(String s) {
        return URLDecoder.decode(s, StandardCharsets.UTF_8);
    }
}
