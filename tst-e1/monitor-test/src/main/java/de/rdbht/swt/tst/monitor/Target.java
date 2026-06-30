package de.rdbht.swt.tst.monitor;

import java.util.Objects;

/**
 * A monitored service endpoint, identified by host and port. Parsing a target
 * from a "host:port" string is validation-heavy and therefore a good fit for
 * the required exception unit test (Teil 1).
 */
public final class Target {

  private final String host;
  private final int port;

  public Target(String host, int port) {
    if (host == null || host.isBlank()) {
      throw new IllegalArgumentException("host must not be blank");
    }
    if (port < 1 || port > 65535) {
      throw new IllegalArgumentException("port out of range (1..65535): " + port);
    }
    this.host = host;
    this.port = port;
  }

  /**
   * Parses a {@code "host:port"} specification.
   *
   * @throws IllegalArgumentException if the spec is null, has no single colon,
   *                                  or carries an invalid host/port
   */
  public static Target parse(String spec) {
    if (spec == null) {
      throw new IllegalArgumentException("spec must not be null");
    }
    String[] parts = spec.split(":");
    if (parts.length != 2) {
      throw new IllegalArgumentException("expected 'host:port', was '" + spec + "'");
    }
    int port;
    try {
      port = Integer.parseInt(parts[1].trim());
    } catch (NumberFormatException e) {
      throw new IllegalArgumentException("port is not a number: '" + parts[1] + "'", e);
    }
    return new Target(parts[0].trim(), port);
  }

  public String host() { return host; }

  public int port() { return port; }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof Target other)) return false;
    return port == other.port && host.equals(other.host);
  }

  @Override
  public int hashCode() { return Objects.hash(host, port); }

  @Override
  public String toString() { return host + ":" + port; }
}
