package third_part;

import org.junit.jupiter.api.Test;
import third_part.being.People;
import third_part.location.Coordinates;
import third_part.location.Location;
import third_part.location.LocationType;
import third_part.location.SpaceTimeHole;
import third_part.phrase.Phrase;
import third_part.phrase.PhraseState;

import static org.junit.jupiter.api.Assertions.*;

class SpaceTimeHoleTest {

    @Test
    void testHoleCreation() {
        Location location = new Location("Space", LocationType.SPACE,
                new Coordinates(50, 50, 50, 5000));
        SpaceTimeHole hole = new SpaceTimeHole(location);

        assertEquals(location, hole.getLocation());
        assertTrue(hole.isActive());
    }

    @Test
    void testTeleportPeople() {
        Location startLocation = new Location("Earth", LocationType.PLANET,
                new Coordinates(0, 0, 0, 2024));
        Location targetLocation = new Location("Mars", LocationType.PLANET,
                new Coordinates(100, 0, 0, 2024));
        SpaceTimeHole hole = new SpaceTimeHole(startLocation);
        People arthur = new People("Arthur", startLocation);

        boolean result = hole.teleport(arthur, targetLocation);

        assertTrue(result);
        assertEquals(targetLocation, arthur.getLocation());
    }

    @Test
    void testTeleportPhrase() {
        Location startLocation = new Location("Earth", LocationType.PLANET,
                new Coordinates(0, 0, 0, 2024));
        Location targetLocation = new Location("Mars", LocationType.PLANET,
                new Coordinates(100, 0, 0, 2024));
        SpaceTimeHole hole = new SpaceTimeHole(startLocation);
        Phrase phrase = new Phrase("Test", startLocation, PhraseState.AT_LOCATION);

        hole.teleport(phrase, targetLocation);

        assertEquals(targetLocation, phrase.getLocation());
        assertEquals(PhraseState.MOVING, phrase.getState());
    }

    @Test
    void testInactiveHoleCannotTeleport() {
        Location location = new Location("Earth", LocationType.PLANET,
                new Coordinates(0, 0, 0, 2024));
        SpaceTimeHole hole = new SpaceTimeHole(location);
        hole.setActive(false);
        People arthur = new People("Arthur", location);

        boolean result = hole.teleport(arthur, location);

        assertFalse(result);
        assertEquals(location, arthur.getLocation());
    }
}