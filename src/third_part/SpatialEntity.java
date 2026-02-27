package third_part;

import third_part.location.Location;

public interface SpatialEntity {
    Location getLocation();
    void setLocation(Location location);
    String getName();
}