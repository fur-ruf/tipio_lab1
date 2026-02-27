package third_part.location;

import third_part.phrase.Phrase;
import third_part.being.Being;

import java.util.*;

public class FabricOfSpaceTime {
    private Map<String, Location> locations;
    private Map<String, Being> beings;
    private List<SpaceTimeHole> holes;
    private List<Phrase> phrases;

    public FabricOfSpaceTime() {
        this.locations = new HashMap<>();
        this.beings = new HashMap<>();
        this.holes = new ArrayList<>();
        this.phrases = new ArrayList<>();
    }

    public void addLocation(Location location) {
        locations.put(location.getName(), location);
    }

    public void addBeing(Being being) {
        beings.put(being.getName(), being);
    }

    public void addHole(SpaceTimeHole hole) {
        holes.add(hole);
    }

    public void addPhrase(Phrase phrase) {
        phrases.add(phrase);
    }

    public Optional<SpaceTimeHole> findNearestHole(Location location) {
        return holes.stream()
                .filter(SpaceTimeHole::isActive)
                .min(Comparator.comparingDouble(h ->
                        h.getLocation().getCoordinates().distanceTo(location.getCoordinates())));
    }

    public SpaceTimeHole createRandomHole() {
        if (locations.isEmpty()) return null;

        Location randomLocation = new ArrayList<>(locations.values())
                .get(new Random().nextInt(locations.size()));
        SpaceTimeHole hole = new SpaceTimeHole(randomLocation);
        addHole(hole);
        return hole;
    }

    public List<SpaceTimeHole> getHoles() {
        return this.holes;
    }
}