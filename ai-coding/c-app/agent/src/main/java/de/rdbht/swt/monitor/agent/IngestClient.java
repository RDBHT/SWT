package de.rdbht.swt.monitor.agent;

import de.rdbht.swt.monitor.store.IngestCodec;
import de.rdbht.swt.monitor.store.StatusRecord;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;

/**
 * Sends each measured result to the collector over HTTP — the network hop that
 * makes the system distributed. This is the producer side: the agent does not
 * store or evaluate anything itself, it only forwards.
 */
public final class IngestClient implements de.rdbht.swt.monitor.scheduler.ResultSink {

    private final HttpClient http = HttpClient.newHttpClient();
    private final URI ingestUri;

    public IngestClient(String ingestUrl) {
        this.ingestUri = URI.create(ingestUrl);
    }

    @Override
    public void accept(StatusRecord record) {
        try {
            HttpRequest request = HttpRequest.newBuilder(ingestUri)
                    .timeout(Duration.ofSeconds(3))
                    .header("Content-Type", "application/x-www-form-urlencoded")
                    .POST(HttpRequest.BodyPublishers.ofString(IngestCodec.encode(record)))
                    .build();
            HttpResponse<Void> response = http.send(request, HttpResponse.BodyHandlers.discarding());
            System.out.println("-> ingest " + record.service() + " " + record.status()
                    + " (HTTP " + response.statusCode() + ")");
        } catch (Exception e) {
            System.out.println("!  ingest failed for " + record.service() + ": " + e.getClass().getSimpleName());
        }
    }
}
