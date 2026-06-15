package de.rdbht.swt;

/**
 * Minimal demo class for the BUI-E1 build-management assignment.
 *
 * <p>Masks the local part of an e-mail address so only the first character
 * remains visible, e.g. {@code "anna@example.de"} becomes
 * {@code "a***@example.de"}. The example is intentionally tiny &ndash; the
 * point of the assignment is the build pipeline, not the logic.</p>
 */
public final class Anonymizer {

    private Anonymizer() {
        // utility class, no instances
    }

    /**
     * Masks the local part (before the {@code @}) of an e-mail address.
     *
     * @param email the address to mask, must contain exactly one {@code @}
     * @return the masked address, first character of the local part kept
     * @throws IllegalArgumentException if {@code email} is null or has no {@code @}
     */
    public static String maskEmail(String email) {
        if (email == null || email.indexOf('@') < 1) {
            throw new IllegalArgumentException("not a valid e-mail: " + email);
        }
        int at = email.indexOf('@');
        String local = email.substring(0, at);
        String domain = email.substring(at); // includes the '@'
        return local.charAt(0) + "***" + domain;
    }

    public static void main(String[] args) {
        String sample = args.length > 0 ? args[0] : "anna@example.de";
        System.out.println("Input : " + sample);
        System.out.println("Masked: " + maskEmail(sample));
    }
}
