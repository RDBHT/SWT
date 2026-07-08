package de.rdbht.swt.tst.monitor;

/**
 * Abstraction over the system clock. The second "unpleasant" collaborator of
 * Teil 3: real time only moves forward and cannot be controlled, which makes
 * time-window logic untestable. A mock lets the test fast-forward at will.
 */
public interface Clock {
  long nowMillis();
}
