package de.rdbht.swt.monitor.checker;

import java.net.InetAddress;

/**
 * DNS-Check: löst den Ziel-Domainnamen in eine oder mehrere IP-Adressen auf.
 *
 * Zusammenhang: dritte Check-Strategie. Liefert die Auflösung mindestens eine Adresse,
 * gilt der Name als erreichbar (UP), sonst DOWN. Zeigt, dass sich das Strategy-Muster
 * (Check-Schnittstelle) auf einen ganz anderen Prüf-Typ erweitern lässt.
 */
public final class DnsCheck implements Check {

    @Override
    public String type() {
        return "DNS";
    }

    @Override
    public CheckResult run(String target) {
        long start = System.currentTimeMillis();
        try {
            InetAddress[] addresses = InetAddress.getAllByName(target);
            long elapsed = System.currentTimeMillis() - start;
            Status status = addresses.length > 0 ? Status.UP : Status.DOWN;
            return new CheckResult(status, elapsed, addresses.length + " address(es)");
        } catch (Exception e) {
            long elapsed = System.currentTimeMillis() - start;
            return new CheckResult(Status.DOWN, elapsed, "unresolved");
        }
    }
}
