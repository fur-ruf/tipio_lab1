package third_part.being;

import third_part.location.Location;
import third_part.phrase.Phrase;
import third_part.phrase.PhraseState;

import java.util.ArrayList;
import java.util.List;

public class People extends Being {
    private List<Phrase> spokenPhrases = new ArrayList<>();

    public People(String name, Location location) {
        super(name, location, true);
    }

    public Phrase speak(String content) {
        Phrase phrase = new Phrase(content, this.location, PhraseState.AT_LOCATION);
        spokenPhrases.add(phrase);
        return phrase;
    }
}