package third_part;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import third_part.being.People;
import third_part.location.*;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class FabricOfSpaceTimeTest {

    private FabricOfSpaceTime fabric;
    private Location testLocation;

    @BeforeEach
    void setUp() {
        fabric = new FabricOfSpaceTime();
        testLocation = new Location("Earth", LocationType.PLANET,
                new Coordinates(0, 0, 0, 0));
    }

    @Test
    void testAddLocation() {
        fabric.addLocation(testLocation);
        SpaceTimeHole hole = new SpaceTimeHole(testLocation);
        fabric.addHole(hole);

        Optional<SpaceTimeHole> found = fabric.findNearestHole(testLocation);
        assertTrue(found.isPresent());
        assertEquals(hole, found.get());
    }

    @Test
    void testAddBeing() {
        People arthur = new People("Arthur", testLocation);
        fabric.addBeing(arthur);

        assertDoesNotThrow(() -> fabric.createRandomHole());
    }

    @Test
    void testFindNearestHole() {
        Location farLocation = new Location("Far", LocationType.PLANET,
                new Coordinates(1000, 1000, 1000, 1000));
        Location nearLocation = new Location("Near", LocationType.PLANET,
                new Coordinates(10, 10, 10, 10));

        SpaceTimeHole farHole = new SpaceTimeHole(farLocation);
        SpaceTimeHole nearHole = new SpaceTimeHole(nearLocation);

        fabric.addHole(farHole);
        fabric.addHole(nearHole);

        Location searchLocation = new Location("Search", LocationType.PLANET,
                new Coordinates(0, 0, 0, 0));

        Optional<SpaceTimeHole> nearest = fabric.findNearestHole(searchLocation);
        assertTrue(nearest.isPresent());
        assertEquals(nearHole, nearest.get());
    }

    @Test
    void testCreateRandomHole() {
        fabric.addLocation(testLocation);
        SpaceTimeHole hole = fabric.createRandomHole();

        assertNotNull(hole);
        assertTrue(fabric.findNearestHole(testLocation).isPresent());
    }
}
