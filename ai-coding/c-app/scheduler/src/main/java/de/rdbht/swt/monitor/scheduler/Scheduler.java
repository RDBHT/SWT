package de.rdbht.swt.monitor.scheduler;

import de.rdbht.swt.monitor.alerting.AlertingService;
import de.rdbht.swt.monitor.checker.CheckResult;
import de.rdbht.swt.monitor.store.HistoryStore;
import de.rdbht.swt.monitor.store.StatusRecord;

import java.time.Instant;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Orchestrates one measurement pass: run each check, persist the result and feed
 * the alerting pipeline. {@link #runOnce()} is deterministic and unit-testable;
 * {@link #start(long)} drives it periodically for real operation.
 */
public final class Scheduler {

    private final List<MonitoredService> services;
    private final HistoryStore store;
    private final AlertingService alerting;
    private ScheduledExecutorService executor;

    public Scheduler(List<MonitoredService> services, HistoryStore store, AlertingService alerting) {
        this.services = services;
        this.store = store;
        this.alerting = alerting;
    }

    /** One measurement pass over all services. */
    public void runOnce() {
        for (MonitoredService service : services) {
            CheckResult result = service.check().run(service.target());
            store.append(new StatusRecord(service.name(), result.status(), result.responseMs(), Instant.now()));
            alerting.tick(service.name(), result.status());
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
