package de.rdbht.swt.monitor.alerting;

/** Injected time source — enables deterministic tests without {@code Thread.sleep}. */
public interface Clock {
    long nowMillis();
}
