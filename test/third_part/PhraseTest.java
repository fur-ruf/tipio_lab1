package third_part;

import org.junit.jupiter.api.Test;
import third_part.location.Coordinates;
import third_part.location.Location;
import third_part.location.LocationType;
import third_part.phrase.Phrase;
import third_part.phrase.PhraseState;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class PhraseTest {

    @Test
    void testPhraseStateTransitions() {
        Location location = new Location("Earth", LocationType.PLANET,
                new Coordinates(0, 0, 0, 0));
        Phrase phrase = new Phrase("Test", location, PhraseState.AT_LOCATION);

        assertEquals(PhraseState.AT_LOCATION, phrase.getState());

        phrase.setState(PhraseState.MOVING);
        assertEquals(PhraseState.MOVING, phrase.getState());

        phrase.setState(PhraseState.LOST);
        assertEquals(PhraseState.LOST, phrase.getState());
    }

    @Test
    void testPhraseUniqueId() {
        Location location = new Location("Earth", LocationType.PLANET,
                new Coordinates(0, 0, 0, 0));
        Phrase phrase1 = new Phrase("Test1", location, PhraseState.AT_LOCATION);
        Phrase phrase2 = new Phrase("Test2", location, PhraseState.AT_LOCATION);

        assertNotEquals(phrase1.getName(), phrase2.getName());
    }
}