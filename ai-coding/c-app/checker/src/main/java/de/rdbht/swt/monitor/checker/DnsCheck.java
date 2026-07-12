package de.rdbht.swt.monitor.checker;

import java.net.InetAddress;

/** DNS health check: resolves the target domain to one or more addresses. */
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
