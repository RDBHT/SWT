package de.rdbht.swt.monitor.scheduler;

import de.rdbht.swt.monitor.store.StatusRecord;

/**
 * Ziel für jedes gemessene Ergebnis.
 *
 * Zusammenhang: DIE Naht, die den Check-Agenten verteilbar macht. Der Scheduler übergibt jedes
 * Ergebnis an einen ResultSink, ohne zu wissen, was damit passiert. Ein lokaler Sink würde in
 * den In-JVM-Store schreiben; im verteilten Betrieb ist der Sink der IngestClient, der den
 * Datensatz per HTTP an den separaten collector-Prozess sendet. Dadurch wird Verteilung zur
 * Konfigurations- statt zur Umbau-Entscheidung.
 */
@FunctionalInterface
public interface ResultSink {
    void accept(StatusRecord record);
}
