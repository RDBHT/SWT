package de.rdbht.swt.tst.monitor;

/** Result of a single health check of a monitored service. */
public enum Status {
  /** Service answered correctly and fast enough. */
  UP,
  /** Service answered correctly but slower than the configured threshold. */
  DEGRADED,
  /** Service did not answer or returned an error status. */
  DOWN
}
