package de.rdbht.swt.monitor.scheduler;

import de.rdbht.swt.monitor.checker.CheckResult;
import de.rdbht.swt.monitor.store.StatusRecord;

import java.time.Instant;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Taktgeber des Messpfads: führt jeden Check aus und übergibt das Ergebnis an einen
 * {@link ResultSink}.
 *
 * Zusammenhang: läuft im agent-Prozess. Der Scheduler ist bewusst UNABHÄNGIG davon, was mit
 * einem Ergebnis geschieht — diese Entscheidung (lokal speichern vs. per Netzwerk an einen
 * Collector senden) steckt im Sink. Genau das erlaubt es, denselben Scheduler in einem
 * verteilten Agenten zu betreiben. runOnce() ist deterministisch und unit-testbar; start(long)
 * treibt ihn periodisch für den echten Betrieb.
 */
public final class Scheduler {

    private final List<MonitoredService> services;
    private final ResultSink sink;
    private ScheduledExecutorService executor;

    public Scheduler(List<MonitoredService> services, ResultSink sink) {
        this.services = services;
        this.sink = sink;
    }

    /** Ein Messdurchlauf über alle Dienste; jedes Ergebnis geht an den Sink. */
    public void runOnce() {
        for (MonitoredService service : services) {
            CheckResult result = service.check().run(service.target());
            sink.accept(new StatusRecord(service.name(), result.status(), result.responseMs(), Instant.now()));
        }
    }

    /** Startet die periodische Ausführung alle {@code intervalMs} Millisekunden. */
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
