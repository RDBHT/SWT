package de.rdbht.swt.monitor.dashboard;

import de.rdbht.swt.monitor.store.StatusRecord;

import java.util.List;

/** Renders the latest status per service as a small HTML fragment (pure, testable). */
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
