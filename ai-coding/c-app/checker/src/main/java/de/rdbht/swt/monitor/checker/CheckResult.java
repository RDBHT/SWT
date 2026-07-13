package de.rdbht.swt.monitor.checker;

/**
 * Unveränderliches Ergebnis eines einzelnen Checks: Status, gemessene Antwortzeit (ms)
 * und ein kurzer Detail-Text (z. B. "HTTP 200").
 *
 * Zusammenhang: Jede Check-Strategie (HttpCheck/TcpCheck/DnsCheck) liefert ein CheckResult
 * zurück. Der Scheduler überführt es zusammen mit Name und Zeitstempel in einen StatusRecord
 * (store-Modul), der dann verteilt versendet wird.
 */
public record CheckResult(Status status, long responseMs, String detail) {
}
