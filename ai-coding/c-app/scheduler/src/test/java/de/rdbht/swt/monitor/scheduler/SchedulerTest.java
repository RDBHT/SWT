package de.rdbht.swt.monitor.scheduler;

import de.rdbht.swt.monitor.alerting.AlertingService;
import de.rdbht.swt.monitor.checker.Check;
import de.rdbht.swt.monitor.checker.CheckResult;
import de.rdbht.swt.monitor.checker.Status;
import de.rdbht.swt.monitor.store.HistoryStore;
import de.rdbht.swt.monitor.store.InMemoryHistoryStore;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SchedulerTest {

    @Test
    void runOnce_persists_a_record_per_service() {
        // Stub check: always UP, no real network.
        Check stub = new Check() {
            @Override public String type() { return "STUB"; }
            @Override public CheckResult run(String target) { return new CheckResult(Status.UP, 42, "ok"); }
        };
        HistoryStore store = new InMemoryHistoryStore();
        AlertingService alerting = new AlertingService(0, () -> 0L, (service, message) -> { });
        Scheduler scheduler = new Scheduler(
                List.of(new MonitoredService("api", stub, "x")), store, alerting);

        scheduler.runOnce();

        assertEquals(1, store.recent("api", 10).size());
        assertEquals(Status.UP, store.recent("api", 10).get(0).status());
    }
}
