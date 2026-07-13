package de.rdbht.swt.monitor.scheduler;

import de.rdbht.swt.monitor.store.StatusRecord;

/**
 * Destination for each measured result. This seam is what makes the check agent
 * distributable: a local sink writes to the in-JVM store, while the distributed
 * agent's sink sends the record over HTTP to a separate collector process.
 */
@FunctionalInterface
public interface ResultSink {
    void accept(StatusRecord record);
}
