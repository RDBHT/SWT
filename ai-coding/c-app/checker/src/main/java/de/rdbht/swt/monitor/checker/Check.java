package de.rdbht.swt.monitor.checker;

/**
 * Strategy-Schnittstelle für einen Prüf-Typ (HTTP, TCP, DNS).
 *
 * Zusammenhang: Herzstück des checker-Moduls. Der Scheduler (scheduler-Modul, läuft im
 * agent-Prozess) hält je überwachtem Dienst eine Check-Instanz und ruft run(target) auf.
 * Ein neuer Prüf-Typ wird durch eine neue Implementierung dieser Schnittstelle ergänzt,
 * ohne bestehenden Code zu ändern (Open/Closed-Prinzip).
 */
public interface Check {

    /** Probe type identifier, e.g. "HTTP". */
    String type();

    /** Run the check against the given target and return the result. */
    CheckResult run(String target);
}
