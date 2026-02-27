package third_part.location;

public class Location {
    private String name;
    private LocationType type;
    private Coordinates coordinates;

    public Location(String name, LocationType type, Coordinates coordinates) {
        this.name = name;
        this.type = type;
        this.coordinates = coordinates;
    }

    public String getName() { return name; }
    public LocationType getType() { return type; }
    public Coordinates getCoordinates() { return coordinates; }
}
