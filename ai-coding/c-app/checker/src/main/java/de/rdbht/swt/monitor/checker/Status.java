package de.rdbht.swt.monitor.checker;

/**
 * Zustände, die ein überwachter Dienst haben kann.
 *
 * Zusammenhang: Basistyp des checker-Moduls, auf den sich alle anderen Module stützen.
 * Der StatusEvaluator bildet Messwerte auf diesen enum ab; store speichert ihn,
 * alerting reagiert darauf, dashboard-ui zeigt ihn an.
 * UP = ok, DEGRADED = erreichbar aber langsam, DOWN = Ausfall, UNKNOWN = noch nicht gemessen.
 */
public enum Status {
    UP, DEGRADED, DOWN, UNKNOWN
}
