package de.rdbht.swt.monitor.dashboard;

import de.rdbht.swt.monitor.store.StatusRecord;

import java.util.List;

/**
 * Rendert den jüngsten Status je Dienst als kleines HTML-Fragment (rein, testbar).
 *
 * Zusammenhang: bewusst von der HTTP-Auslieferung getrennt — reine Zeichenketten-Logik,
 * deshalb per StatusRendererTest prüfbar. Wird sowohl vom lokalen DashboardServer als auch
 * vom verteilten CollectorServer genutzt, um das Dashboard-HTML zu erzeugen.
 */
public final class StatusRenderer {

    public String render(List<StatusRecord> latest) {
        StringBuilder sb = new StringBuilder("<table><tr><th>Service</th><th>Status</th><th>ms</th></tr>");
        for (StatusRecord r : latest) {
            sb.append("<tr><td>").append(r.service()).append("</td><td>")
              .append(r.status()).append("</td><td>").append(r.responseMs()).append("</td></tr>");
        }
        return sb.append("</table>").toString();
    }
}
