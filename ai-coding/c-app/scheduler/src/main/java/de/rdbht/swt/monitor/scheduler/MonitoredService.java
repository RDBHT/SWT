package de.rdbht.swt.monitor.scheduler;

import de.rdbht.swt.monitor.checker.Check;

/**
 * Ein zu überwachender Dienst: Name, die zu verwendende Check-Strategie und das Ziel.
 *
 * Zusammenhang: der agent legt je überwachtem Dienst ein MonitoredService an und gibt die
 * Liste an den Scheduler. Beispiel: ("mock-http", HttpCheck, "http://localhost:8081/health").
 */
public record MonitoredService(String name, Check check, String target) {
}
