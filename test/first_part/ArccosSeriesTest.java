package first_part;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

class ArccosSeriesTest {

    private static final double DELTA = 1e-7;
    private static final double BOUNDARY_DELTA = 1e-2;

    @Test
    void testBoundaryValues() {
        assertEquals(0.0, ArccosSeries.arccos(1), BOUNDARY_DELTA);
        assertEquals(Math.PI, ArccosSeries.arccos(-1), BOUNDARY_DELTA);
    }

    @ParameterizedTest
    @CsvSource({
            "0.5, 1.0471975",
            "0.9, 0.4510268",
            "-0.5, 2.0943951",
            "-0.9, 2.6905658",
            "0, 1.5707963"
    })
    void testNormalValues(double x, double expected) {
        assertEquals(expected, ArccosSeries.arccos(x), DELTA);
    }

    @Test
    void testInvalidInput() {
        assertTrue(Double.isNaN(ArccosSeries.arccos(1.5)));
        assertTrue(Double.isNaN(ArccosSeries.arccos(-2)));
    }
}