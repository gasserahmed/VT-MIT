package river;

import river.GameEngine.Location;

public class GameObject {

    protected String name;
    protected Location location;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public Location getLocation() {
        return location;
    }

    public void setLocation(Location loc) {
        this.location = loc;
    }

    public String getSound() {
        switch (name) {
        case "Wolf":
            return "Howl";
        case "Goose":
            return "Honk";
        default:
            return "";
        }
    }

}
