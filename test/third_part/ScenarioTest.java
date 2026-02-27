package third_part;

import org.junit.jupiter.api.Test;
import third_part.being.Creature;
import third_part.being.CreatureState;
import third_part.being.People;
import third_part.location.*;
import third_part.phrase.Phrase;
import third_part.phrase.PhraseState;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

class ScenarioTest {

    @Test
    void testArthurStory() {
        Scenario scenario = new Scenario();

        assertDoesNotThrow(scenario::executeArthurStory);
    }

    @Test
    void testCompleteStoryFlow() {
        FabricOfSpaceTime fabric = new FabricOfSpaceTime();

        Location earth = new Location("Earth", LocationType.PLANET,
                new Coordinates(0, 0, 0, 2024));
        Location distantGalaxy = new Location("Distant Galaxy", LocationType.GALAXY,
                new Coordinates(1000, 2000, 1500, 1000000));

        fabric.addLocation(earth);
        fabric.addLocation(distantGalaxy);

        People arthur = new People("Arthur", earth);
        Creature warrior = new Creature("Warrior", distantGalaxy, "FactionA");

        fabric.addBeing(arthur);
        fabric.addBeing(warrior);

        Phrase phrase = arthur.speak("Test phrase");
        fabric.addPhrase(phrase);

        assertEquals(earth, phrase.getLocation());

        SpaceTimeHole hole = new SpaceTimeHole(earth);
        fabric.addHole(hole);

        hole.teleport(phrase, distantGalaxy);

        assertEquals(distantGalaxy, phrase.getLocation());
        assertEquals(PhraseState.MOVING, phrase.getState());

        warrior.setState(CreatureState.ON_BALANCE);
        assertEquals(CreatureState.ON_BALANCE, warrior.getState());
    }
}
