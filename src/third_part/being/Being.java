package third_part.being;

import third_part.SpatialEntity;
import third_part.location.Location;

public abstract class Being implements SpatialEntity {
    protected String name;
    protected Location location;
    boolean isAlive;

    public Being(String name, Location location, boolean isAlive) {
        this.name = name;
        this.location = location;
        this.isAlive = isAlive;
    }

    @Override
    public String getName() { return name; }

    @Override
    public Location getLocation() { return location; }

    @Override
    public void setLocation(Location location) {
        this.location = location;
    }

    public void die() {
        this.isAlive = false;
    }
}
