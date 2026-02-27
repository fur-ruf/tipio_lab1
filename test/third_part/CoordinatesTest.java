package third_part;

import org.junit.jupiter.api.Test;
import third_part.location.Coordinates;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CoordinatesTest {

    @Test
    void testDistanceCalculation() {
        Coordinates coords1 = new Coordinates(0, 0, 0, 0);
        Coordinates coords2 = new Coordinates(3, 4, 0, 100);

        double distance = coords1.distanceTo(coords2);
        assertEquals(105.0, distance, 0.001);
    }

    @Test
    void testZeroDistance() {
        Coordinates coords1 = new Coordinates(1, 1, 1, 50);
        Coordinates coords2 = new Coordinates(1, 1, 1, 50);

        assertEquals(0, coords1.distanceTo(coords2));
    }
}
