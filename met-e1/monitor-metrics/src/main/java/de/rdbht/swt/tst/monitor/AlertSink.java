package de.rdbht.swt.tst.monitor;

/**
 * Outbound notification channel (e-mail, Slack, ...). Mocked in tests so we can
 * verify *that* and *how often* an alert was raised, without sending anything.
 */
public interface AlertSink {
  void fire(Target target, String message);
}
