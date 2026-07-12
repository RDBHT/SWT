package de.rdbht.swt.monitor.mock;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MockServiceTest {

    @Test
    void health_code_is_settable() {
        MockService mock = new MockService();
        assertEquals(200, mock.healthCode());
        mock.setHealthCode(503);
        assertEquals(503, mock.healthCode());
    }
}
