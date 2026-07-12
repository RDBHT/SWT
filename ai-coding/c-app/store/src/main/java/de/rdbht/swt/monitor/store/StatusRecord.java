package de.rdbht.swt.monitor.store;

import de.rdbht.swt.monitor.checker.Status;

import java.time.Instant;

/** One persisted status observation for a service. */
public record StatusRecord(String service, Status status, long responseMs, Instant timestamp) {
}
