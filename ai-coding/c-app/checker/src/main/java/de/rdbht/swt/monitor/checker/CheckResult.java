package de.rdbht.swt.monitor.checker;

/** Immutable result of a single check run: status, measured latency and a detail note. */
public record CheckResult(Status status, long responseMs, String detail) {
}
