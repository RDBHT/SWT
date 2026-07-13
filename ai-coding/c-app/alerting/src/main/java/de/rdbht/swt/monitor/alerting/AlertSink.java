package de.rdbht.swt.monitor.alerting;

/**
 * Ziel für ausgelöste Alarme (Strategy): Mail, Webhook, Konsole, ...
 *
 * Zusammenhang: der AlertingService ruft fire(...) auf, ohne zu wissen, WOHIN der Alarm geht.
 * So lässt sich der Kanal austauschen, ohne die Alarmierungs-Logik anzufassen. Im collector
 * ist der Sink aktuell eine einfache Konsolen-Ausgabe.
 */
public interface AlertSink {
    void fire(String service, String message);
}
