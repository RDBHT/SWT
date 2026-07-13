package de.rdbht.swt.monitor.store;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Einfache, thread-sichere In-Memory-Umsetzung von {@link HistoryStore}.
 *
 * Zusammenhang: im collector-Prozess schreibt jeder eingehende POST /ingest hier hinein,
 * während das Dashboard gleichzeitig liest — deshalb thread-sicher (synchronisierte Liste).
 * Bewusst simpel (KISS); eine SQLite-Variante wäre der nächste Schritt und bräuchte keine
 * Änderung außerhalb dieses Moduls. Nachteil: bei Neustart sind die Daten weg.
 */
public final class InMemoryHistoryStore implements HistoryStore {

    private final List<StatusRecord> records = Collections.synchronizedList(new ArrayList<>());

    @Override
    public void append(StatusRecord record) {
        records.add(record);
    }

    @Override
    public List<StatusRecord> recent(String service, int limit) {
        synchronized (records) {
            return records.stream()
                    .filter(r -> r.service().equals(service))
                    .sorted(Comparator.comparing(StatusRecord::timestamp).reversed())
                    .limit(limit)
                    .collect(Collectors.toList());
        }
    }

    @Override
    public List<StatusRecord> latestPerService() {
        synchronized (records) {
            Map<String, StatusRecord> latest = new LinkedHashMap<>();
            for (StatusRecord r : records) {
                StatusRecord current = latest.get(r.service());
                if (current == null || r.timestamp().isAfter(current.timestamp())) {
                    latest.put(r.service(), r);
                }
            }
            return new ArrayList<>(latest.values());
        }
    }
}
