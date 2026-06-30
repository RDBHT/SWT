package de.rdbht.swt.tst.monitor;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

class TargetTest {

  @Test
  void parsesHostAndPort() {
    Target t = Target.parse("example.com:443");
    assertEquals("example.com", t.host());
    assertEquals(443, t.port());
  }

  @Test
  void rejectsMissingPort() {
    assertThrows(IllegalArgumentException.class, () -> Target.parse("example.com"));
  }

  @Test
  void rejectsNonNumericPort() {
    assertThrows(IllegalArgumentException.class, () -> Target.parse("example.com:https"));
  }

  @Test
  void rejectsPortOutOfRange() {
    assertThrows(IllegalArgumentException.class, () -> new Target("h", 70000));
  }

  @Test
  void rejectsBlankHost() {
    assertThrows(IllegalArgumentException.class, () -> new Target("  ", 80));
  }
}
