package de.rdbht.swt.monitor.alerting;

/** Destination for alerts (Strategy): mail, webhook, ... — swappable without touching the pipeline. */
public interface AlertSink {
    void fire(String service, String message);
}
