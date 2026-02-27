package third_part.phrase;

import third_part.SpatialEntity;
import third_part.location.Location;

import java.time.Instant;
import java.util.UUID;

public class Phrase implements SpatialEntity {
    private final String id;
    private final String content;
    private Location location;
    private PhraseState state;
    private final Instant createdAt;

    public Phrase(String content, Location location, PhraseState state) {
        this.id = UUID.randomUUID().toString();
        this.content = content;
        this.location = location;
        this.state = state;
        this.createdAt = Instant.now();
    }

    @Override
    public String getName() { return "Phrase: " + content.substring(0, Math.min(20, content.length())); }

    @Override
    public Location getLocation() { return location; }

    @Override
    public void setLocation(Location location) {
        this.location = location;
    }

    public void setState(PhraseState state) { this.state = state; }
    public PhraseState getState() { return state; }
    public String getContent() { return content; }
}