package de.rdbht.swt.tst.monitor;

/** Raw outcome of one network probe: HTTP status and measured round-trip time. */
public record ProbeResult(int httpStatus, long responseTimeMs) {}
