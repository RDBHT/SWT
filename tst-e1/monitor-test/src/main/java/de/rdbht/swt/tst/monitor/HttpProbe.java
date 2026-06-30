package de.rdbht.swt.tst.monitor;

/**
 * Performs the actual network request against a target. This is the
 * "unpleasant" collaborator of Teil 3: a real implementation opens a socket,
 * which is slow, non-deterministic and externally dependent. In tests it is
 * replaced by a Mockito mock so the surrounding logic can be verified in
 * isolation.
 */
public interface HttpProbe {
  ProbeResult probe(Target target);
}
