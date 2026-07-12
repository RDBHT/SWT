package de.rdbht.swt.monitor.store;

import de.rdbht.swt.monitor.checker.Status;
import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class StoreTest {

    @Test
    void recent_returns_newest_first() {
        HistoryStore store = new InMemoryHistoryStore();
        Instant t0 = Instant.parse("2026-01-01T10:00:00Z");
        store.append(new StatusRecord("api", Status.UP, 100, t0));
        store.append(new StatusRecord("api", Status.DOWN, 0, t0.plusSeconds(60)));

        List<StatusRecord> recent = store.recent("api", 10);
        assertEquals(2, recent.size());
        assertEquals(Status.DOWN, recent.get(0).status());
    }

    @Test
    void latest_per_service_deduplicates() {
        HistoryStore store = new InMemoryHistoryStore();
        Instant t0 = Instant.parse("2026-01-01T10:00:00Z");
        store.append(new StatusRecord("api", Status.UP, 100, t0));
        store.append(new StatusRecord("db", Status.UP, 20, t0));
        store.append(new StatusRecord("api", Status.DEGRADED, 700, t0.plusSeconds(30)));

        assertEquals(2, store.latestPerService().size());
    }
}
