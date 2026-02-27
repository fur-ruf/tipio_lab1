package third_part.being;

import third_part.location.Location;

public class Creature extends Being {
    private CreatureState state;
    private final String faction;

    public Creature(String name, Location location, String faction) {
        super(name, location, true);
        this.faction = faction; // фракция
        this.state = CreatureState.PEACE;
    }

    public void setState(CreatureState state) {
        this.state = state;
    }

    public CreatureState getState() { return state; }
    public String getFaction() { return faction; }
}