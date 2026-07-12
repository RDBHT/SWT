package de.rdbht.swt.monitor.scheduler;

import de.rdbht.swt.monitor.checker.Check;

/** A service to monitor: a name, the check strategy to use and its target. */
public record MonitoredService(String name, Check check, String target) {
}
