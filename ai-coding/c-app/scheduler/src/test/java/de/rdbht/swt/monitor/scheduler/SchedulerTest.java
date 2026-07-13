package de.rdbht.swt.monitor.scheduler;

import de.rdbht.swt.monitor.checker.Check;
import de.rdbht.swt.monitor.checker.CheckResult;
import de.rdbht.swt.monitor.checker.Status;
import de.rdbht.swt.monitor.store.StatusRecord;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SchedulerTest {

    @Test
    void runOnce_emits_one_result_per_service_to_the_sink() {
        // Stub check: always UP, no real network.
        Check stub = new Check() {
            @Override public String type() { return "STUB"; }
            @Override public CheckResult run(String target) { return new CheckResult(Status.UP, 42, "ok"); }
        };
        List<StatusRecord> received = new ArrayList<>();
        Scheduler scheduler = new Scheduler(
                List.of(new MonitoredService("api", stub, "x")), received::add);

        scheduler.runOnce();

        assertEquals(1, received.size());
        assertEquals(Status.UP, received.get(0).status());
        assertEquals("api", received.get(0).service());
    }
}
