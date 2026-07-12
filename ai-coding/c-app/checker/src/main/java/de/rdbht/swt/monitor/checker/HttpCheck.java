package de.rdbht.swt.monitor.checker;

import java.net.HttpURLConnection;
import java.net.URI;

/** HTTP health check: GETs the target URL and evaluates status code plus latency. */
public final class HttpCheck implements Check {

    private final StatusEvaluator evaluator;
    private final int timeoutMs;

    public HttpCheck(StatusEvaluator evaluator, int timeoutMs) {
        this.evaluator = evaluator;
        this.timeoutMs = timeoutMs;
    }

    @Override
    public String type() {
        return "HTTP";
    }

    @Override
    public CheckResult run(String target) {
        long start = System.currentTimeMillis();
        try {
            HttpURLConnection con = (HttpURLConnection) URI.create(target).toURL().openConnection();
            con.setConnectTimeout(timeoutMs);
            con.setReadTimeout(timeoutMs);
            con.setRequestMethod("GET");
            int code = con.getResponseCode();
            long elapsed = System.currentTimeMillis() - start;
            con.disconnect();
            return new CheckResult(evaluator.evaluate(code, elapsed), elapsed, "HTTP " + code);
        } catch (Exception e) {
            long elapsed = System.currentTimeMillis() - start;
            return new CheckResult(Status.DOWN, elapsed, "error: " + e.getClass().getSimpleName());
        }
    }
}
