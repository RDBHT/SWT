package de.rdbht.swt.monitor.store;

import java.util.List;

/**
 * Read/write access to the status history. This interface is the single
 * coupling point between the check agent (writes) and the dashboard (reads).
 */
public interface HistoryStore {

    /** Append one observation. */
    void append(StatusRecord record);

    /** The most recent {@code limit} records for one service, newest first. */
    List<StatusRecord> recent(String service, int limit);

    /** The latest record per service (for the dashboard overview). */
    List<StatusRecord> latestPerService();
}
