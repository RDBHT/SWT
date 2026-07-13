package de.rdbht.swt.monitor.store;

import de.rdbht.swt.monitor.checker.Status;

import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.time.Instant;

/**
 * Wire-Format (Übertragungsformat) für einen {@link StatusRecord}: eine einfache
 * form-urlencoded Zeile.
 *
 * Zusammenhang: DAS gemeinsame Protokoll der Verteilung. Der agent (IngestClient) ruft
 * encode(...) und schickt das Ergebnis per HTTP; der collector ruft decode(...) auf den
 * empfangenen Body. Bewusst OHNE JSON-Bibliothek gehalten, damit das Protokoll klein und
 * Zeile für Zeile erklärbar bleibt. Round-Trip (encode→decode) abgesichert durch IngestCodecTest.
 */
public final class IngestCodec {

    private IngestCodec() {
    }

    /** Serialisiert einen Datensatz zu {@code service=...&status=...&responseMs=...&timestamp=...}. */
    public static String encode(StatusRecord r) {
        return "service=" + enc(r.service())
                + "&status=" + r.status()
                + "&responseMs=" + r.responseMs()
                + "&timestamp=" + enc(r.timestamp().toString());
    }

    /** Parst einen form-urlencoded Body zurück in einen Datensatz. */
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
                default -> { /* unbekannte Schlüssel ignorieren */ }
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
