package third_part;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import third_part.being.Creature;
import third_part.being.CreatureState;
import third_part.being.People;
import third_part.location.*;
import third_part.phrase.Phrase;
import third_part.phrase.PhraseState;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ScenarioTest {

    @Test
    void testArthurStory() {
        Scenario scenario = new Scenario();

        assertDoesNotThrow(scenario::executeArthurStory);
    }

    @Test
    @DisplayName("Тест истории")
    void testCompleteStoryFlow() {
        FabricOfSpaceTime fabric = new FabricOfSpaceTime();
        Location earth = new Location("Earth", LocationType.PLANET,
                new Coordinates(0, 0, 0, 2024));
        Location distantGalaxy = new Location("Distant Galaxy", LocationType.GALAXY,
                new Coordinates(1000, 2000, 1500, 1000000));

        fabric.addLocation(earth);
        fabric.addLocation(distantGalaxy);

        People arthur = new People("Arthur", earth);
        fabric.addBeing(arthur);

        Creature warrior1 = new Creature("Warrior1", distantGalaxy, "FactionA");
        Creature warrior2 = new Creature("Warrior2", distantGalaxy, "FactionB");
        fabric.addBeing(warrior1);
        fabric.addBeing(warrior2);

        Phrase arthurPhrase = arthur.speak("А у меня, кажется, большие проблемы с образом жизни");
        fabric.addPhrase(arthurPhrase);

        SpaceTimeHole randomHole = fabric.createRandomHole();

        if (randomHole != null) {
            randomHole.teleport(arthurPhrase, distantGalaxy);
            arthurPhrase.setState(PhraseState.MOVING);
            arthurPhrase.setLocation(distantGalaxy);
            arthurPhrase.setState(PhraseState.LOST);

            warrior1.setState(CreatureState.ON_BALANCE);
            warrior2.setState(CreatureState.ON_BALANCE);
        }

        Phrase testPhrase = arthur.getSpokenPhrases().get(0);
        assertEquals(arthurPhrase, testPhrase);
        assertEquals(distantGalaxy, testPhrase.getLocation());
        assertEquals(PhraseState.LOST, testPhrase.getState());
        assertEquals(CreatureState.ON_BALANCE, warrior1.getState());
        assertEquals(CreatureState.ON_BALANCE, warrior2.getState());
        assertTrue(arthur.getSpokenPhrases().contains(arthurPhrase));
        assertTrue(fabric.getHoles().contains(randomHole));
    }
}
