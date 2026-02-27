package third_part;

import org.junit.jupiter.api.Test;
import third_part.being.Creature;
import third_part.being.CreatureState;
import third_part.location.Coordinates;
import third_part.location.Location;
import third_part.location.LocationType;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CreatureTest {

    @Test
    void testCreatureStateTransitions() {
        Location location = new Location("Raspberry", LocationType.PLANET,
                new Coordinates(100, 100, 100, 1000));
        Creature creature = new Creature("Palych", location, "Proletariat");

        assertEquals(CreatureState.PEACE, creature.getState());

        creature.setState(CreatureState.ON_BALANCE);
        assertEquals(CreatureState.ON_BALANCE, creature.getState());

        creature.setState(CreatureState.WAR);
        assertEquals(CreatureState.WAR, creature.getState());
    }

    @Test
    void testCreatureFaction() {
        Location location = new Location("Raspberry", LocationType.PLANET,
                new Coordinates(100, 100, 100, 1000));
        Creature creature = new Creature("Palych", location, "Proletariat");

        assertEquals("Proletariat", creature.getFaction());
    }
}
