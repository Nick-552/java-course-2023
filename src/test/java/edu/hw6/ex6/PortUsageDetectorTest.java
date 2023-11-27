package edu.hw6.ex6;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class PortUsageDetectorTest {

    @Test
    void getOccupiedPorts() {

        assertNotNull(PortUsageDetector.getOccupiedPorts());
        assertFalse(PortUsageDetector.getOccupiedPorts().isEmpty());
    }
}
