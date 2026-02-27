package third_part;

import org.junit.jupiter.api.Test;
import third_part.location.Coordinates;
import third_part.location.Location;
import third_part.location.LocationType;

import static org.junit.jupiter.api.Assertions.assertEquals;

class LocationTest {

    @Test
    void testLocationCreation() {
        Coordinates coords = new Coordinates(1.0, 2.0, 3.0, 1000);
        Location location = new Location("Test", LocationType.PLANET, coords);

        assertEquals("Test", location.getName());
        assertEquals(LocationType.PLANET, location.getType());
        assertEquals(coords, location.getCoordinates());
    }
}