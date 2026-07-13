package de.rdbht.swt.monitor.store;

import de.rdbht.swt.monitor.checker.Status;
import org.junit.jupiter.api.Test;

import java.time.Instant;

import static org.junit.jupiter.api.Assertions.assertEquals;

class IngestCodecTest {

    @Test
    void round_trip_preserves_all_fields() {
        // Servicename enthält ein Leerzeichen, um auch die URL-Kodierung zu prüfen
        StatusRecord original = new StatusRecord("api gateway", Status.DEGRADED, 512,
                Instant.parse("2026-07-13T10:15:30Z"));

        StatusRecord back = IngestCodec.decode(IngestCodec.encode(original));

        assertEquals(original, back);
    }
}
