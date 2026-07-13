package de.rdbht.swt.monitor.scheduler;

import de.rdbht.swt.monitor.checker.CheckResult;
import de.rdbht.swt.monitor.store.StatusRecord;

import java.time.Instant;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Taktgeber des Messpfads: runs each check and hands the result to a {@link ResultSink}.
 * The scheduler is deliberately agnostic to <em>what</em> happens with a result — that
 * decision (store locally vs. send to a collector over the network) lives in the sink,
 * which is what lets the same scheduler run inside a distributed agent.
 * {@link #runOnce()} is deterministic and unit-testable; {@link #start(long)} drives it
 * periodically for real operation.
 */
public final class Scheduler {

    private final List<MonitoredService> services;
    private final ResultSink sink;
    private ScheduledExecutorService executor;

    public Scheduler(List<MonitoredService> services, ResultSink sink) {
        this.services = services;
        this.sink = sink;
    }

    /** One measurement pass over all services; each result goes to the sink. */
    public void runOnce() {
        for (MonitoredService service : services) {
            CheckResult result = service.check().run(service.target());
            sink.accept(new StatusRecord(service.name(), result.status(), result.responseMs(), Instant.now()));
        }
    }

    /** Start periodic execution every {@code intervalMs} milliseconds. */
    public void start(long intervalMs) {
        executor = Executors.newSingleThreadScheduledExecutor();
        executor.scheduleAtFixedRate(this::runOnce, 0, intervalMs, TimeUnit.MILLISECONDS);
    }

    public void stop() {
        if (executor != null) {
            executor.shutdownNow();
        }
    }
}
