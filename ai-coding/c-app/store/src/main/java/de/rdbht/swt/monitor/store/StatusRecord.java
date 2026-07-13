package de.rdbht.swt.monitor.store;

import de.rdbht.swt.monitor.checker.Status;

import java.time.Instant;

/**
 * Eine gespeicherte Status-Beobachtung für einen Dienst: Name, Status, Antwortzeit (ms)
 * und Zeitstempel.
 *
 * Zusammenhang: der zentrale Datensatz, der durch das ganze System wandert. Der Scheduler
 * erzeugt ihn, der IngestCodec serialisiert/deserialisiert ihn für die Netzwerk-Übertragung
 * zwischen agent und collector, der store hält ihn, das dashboard-ui zeigt ihn an.
 */
public record StatusRecord(String service, Status status, long responseMs, Instant timestamp) {
}
