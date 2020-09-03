package river;

import river.GameEngine.Location;

public class GameObject {

    private String name;
    private String sound;
    private Location location;
    
    public GameObject(String name, String sound, Location location) {
        super();
        this.name = name;
        this.sound = sound;
        this.location = location;
    }

    public String getName() {
        return name;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location loc) {
        this.location = loc;
    }

    public String getSound() {
        return sound;
    }
}
