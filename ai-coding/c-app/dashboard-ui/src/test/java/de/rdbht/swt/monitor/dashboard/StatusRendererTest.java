package de.rdbht.swt.monitor.dashboard;

import de.rdbht.swt.monitor.checker.Status;
import de.rdbht.swt.monitor.store.StatusRecord;
import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

class StatusRendererTest {

    @Test
    void renders_a_row_per_service() {
        String html = new StatusRenderer().render(List.of(
                new StatusRecord("api", Status.UP, 100, Instant.now()),
                new StatusRecord("db", Status.DOWN, 0, Instant.now())));
        assertTrue(html.contains("api"));
        assertTrue(html.contains("DOWN"));
    }
}
