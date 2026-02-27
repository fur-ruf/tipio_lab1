package third_part;

import org.junit.jupiter.api.Test;
import third_part.being.People;
import third_part.location.Coordinates;
import third_part.location.Location;
import third_part.location.LocationType;
import third_part.phrase.Phrase;
import third_part.phrase.PhraseState;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PeopleTest {

    @Test
    void testPeopleCreation() {
        Location location = new Location("Earth", LocationType.PLANET,
                new Coordinates(0, 0, 0, 2024));
        People arthur = new People("Arthur", location);

        assertEquals("Arthur", arthur.getName());
        assertEquals(location, arthur.getLocation());
    }

    @Test
    void testPeopleSpeak() {
        Location location = new Location("Earth", LocationType.PLANET,
                new Coordinates(0, 0, 0, 2024));
        People arthur = new People("Arthur", location);

        String speech = "Hello, world!";
        Phrase phrase = arthur.speak(speech);

        assertEquals(speech, phrase.getContent());
        assertEquals(location, phrase.getLocation());
        assertEquals(PhraseState.AT_LOCATION, phrase.getState());
    }
}