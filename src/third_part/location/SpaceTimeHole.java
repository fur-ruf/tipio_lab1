package third_part.location;

import third_part.phrase.Phrase;
import third_part.phrase.PhraseState;
import third_part.SpatialEntity;

import java.util.UUID;

public class SpaceTimeHole implements SpatialEntity {
    private String id;
    private Location location;
    private boolean isActive;

    public SpaceTimeHole(Location location) {
        this.id = UUID.randomUUID().toString();
        this.location = location;
        this.isActive = true;
    }

    @Override
    public String getName() { return "SpaceTimeHole: " + id; }

    @Override
    public Location getLocation() { return location; }

    @Override
    public void setLocation(Location location) {
        this.location = location;
    }

    public boolean teleport(SpatialEntity entity, Location targetLocation) {
        if (!isActive) return false;

        entity.setLocation(targetLocation);
        if (entity instanceof Phrase) {
            ((Phrase) entity).setState(PhraseState.MOVING);
        }
        return true;
    }

    public void setActive(boolean active) { isActive = active; }

    public boolean isActive() {
        return isActive;
    }
}
