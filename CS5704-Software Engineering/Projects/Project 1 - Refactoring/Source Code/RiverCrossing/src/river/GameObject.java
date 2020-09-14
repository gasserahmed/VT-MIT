package river;

import java.awt.Color;

public class GameObject {

    private String label;
    private Color color;
    private Location location;
    
    public GameObject(String label, Color color, Location location) {
        super();
        this.label = label;
        this.color = color;
        this.location = location;
    }

    public String getLabel() {
        return label;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location loc) {
        this.location = loc;
    }

    public Color getColor() {
        return color;
    }
}
