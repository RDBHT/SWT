package de.rdbht.swt.monitor.checker;

/**
 * Strategy for one probe type (HTTP, TCP, DNS). A new check type is added by
 * implementing this interface — existing code stays untouched (open/closed).
 */
public interface Check {

    /** Probe type identifier, e.g. "HTTP". */
    String type();

    /** Run the check against the given target and return the result. */
    CheckResult run(String target);
}
