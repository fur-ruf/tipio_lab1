package third_part;

import third_part.being.Creature;
import third_part.being.CreatureState;
import third_part.being.People;
import third_part.location.*;
import third_part.phrase.Phrase;
import third_part.phrase.PhraseState;


public class Scenario {
    private final FabricOfSpaceTime fabric;

    public Scenario() {
        this.fabric = new FabricOfSpaceTime();
    }

    public void executeArthurStory() {
        Location earth = new Location("Earth", LocationType.PLANET,
                new Coordinates(0, 0, 0, 2026));
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
    }
}