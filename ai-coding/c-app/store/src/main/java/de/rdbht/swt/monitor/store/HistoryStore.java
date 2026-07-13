package de.rdbht.swt.monitor.store;

import java.util.List;

/**
 * Lese-/Schreibzugriff auf die Status-Historie.
 *
 * Zusammenhang: diese Schnittstelle ist die EINZIGE Koppelstelle zwischen dem schreibenden
 * Teil (im collector-Prozess: append bei jedem empfangenen Ergebnis) und dem lesenden Teil
 * (Dashboard: latestPerService). Weil hier nur eine Schnittstelle steht, lässt sich die
 * In-Memory-Umsetzung später ohne Änderung an Collector oder Dashboard gegen z. B. SQLite tauschen.
 */
public interface HistoryStore {

    /** Hängt eine Beobachtung an. */
    void append(StatusRecord record);

    /** Die neuesten {@code limit} Datensätze eines Dienstes, jüngste zuerst. */
    List<StatusRecord> recent(String service, int limit);

    /** Der jeweils jüngste Datensatz je Dienst (für die Dashboard-Übersicht). */
    List<StatusRecord> latestPerService();
}
