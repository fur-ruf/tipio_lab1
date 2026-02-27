package third_part.location;

public class Coordinates {
    private final double spaceX;
    private final double spaceY;
    private final double spaceZ;
    private final long time;

    public Coordinates(double spaceX, double spaceY, double spaceZ, long time) {
        this.spaceX = spaceX;
        this.spaceY = spaceY;
        this.spaceZ = spaceZ;
        this.time = time;
    }

    public double distanceTo(Coordinates other) {
        double spaceDist = Math.sqrt(
                Math.pow(this.spaceX - other.spaceX, 2) +
                        Math.pow(this.spaceY - other.spaceY, 2) +
                        Math.pow(this.spaceZ - other.spaceZ, 2)
        );
        long timeDist = Math.abs(this.time - other.time);
        return spaceDist + timeDist;
    }
}