package de.rdbht.swt.monitor.agent;

import de.rdbht.swt.monitor.store.IngestCodec;
import de.rdbht.swt.monitor.store.StatusRecord;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;

/**
 * Sendet jedes gemessene Ergebnis per HTTP an den collector — der Netzwerk-Sprung, der das
 * System verteilt macht.
 *
 * Zusammenhang: Produzenten-Seite, lebt im agent-Prozess und IST der ResultSink, den der
 * Scheduler bekommt (implements ResultSink). Der Agent speichert und bewertet selbst nichts —
 * er serialisiert das Ergebnis (IngestCodec) und leitet es weiter. Der collector kann in einer
 * anderen JVM oder auf einem anderen Rechner laufen; diese Klasse interessiert das nicht. Ein
 * fehlgeschlagener Versand wird geloggt und verworfen (kein Retry).
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
