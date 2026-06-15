package de.rdbht.swt;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class AnonymizerTest {

    @Test
    @DisplayName("masks the local part but keeps the first character and domain")
    void masksLocalPart() {
        assertEquals("a***@example.de", Anonymizer.maskEmail("anna@example.de"));
    }

    @Test
    @DisplayName("rejects input without an @ sign")
    void rejectsInvalidInput() {
        assertThrows(IllegalArgumentException.class, () -> Anonymizer.maskEmail("no-at-sign"));
    }
}
