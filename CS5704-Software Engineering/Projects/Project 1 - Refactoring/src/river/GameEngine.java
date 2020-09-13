package river;

import java.awt.Color;
import java.util.HashMap;
import java.util.Map;

public class GameEngine
{

    private Map<Item, GameObject> gameObjects;
    private Location boatLocation;
    
    private final static Item BEANS = Item.ITEM_0;
    private final static Item GOOSE = Item.ITEM_1;
    private final static Item WOLF  = Item.ITEM_2;
    private final static Item FARMER  = Item.ITEM_3;

    public GameEngine()
    {
        gameObjects = new HashMap<Item, GameObject>()
        {
            {
                put(BEANS, new GameObject("Beans", Color.CYAN, Location.START));
                put(GOOSE, new GameObject("Goose", Color.CYAN, Location.START));
                put(WOLF, new GameObject("Wolf", Color.CYAN, Location.START));
                put(FARMER, new GameObject("Farmer", Color.MAGENTA, Location.START));
            }
        };
        boatLocation = Location.START;
    }

    public String getItemLabel(Item id)
    {
        return gameObjects.get(id).getLabel();
    }

    public Location getItemLocation(Item id)
    {
        return gameObjects.get(id).getLocation();
    }

    public Color getItemColor(Item id)
    {
        return gameObjects.get(id).getColor();
    }

    public Location getBoatLocation()
    {
        return boatLocation;
    }

    public void loadBoat(Item id)
    {
        if (getItemLocation(id) == boatLocation && getItemLocation(id) != Location.BOAT)
        {
            gameObjects.get(id).setLocation(Location.BOAT);
        }
    }

    public void unloadBoat(Item id)
    {
        if (getItemLocation(id) == Location.BOAT)
        {
            gameObjects.get(id).setLocation(boatLocation);
        }
    }

    public void rowBoat()
    {
        if (boatLocation == Location.START)
        {
            boatLocation = Location.FINISH;
        }
        else
        {
            boatLocation = Location.START;
        }
    }

    public boolean gameIsWon()
    {

        return getItemLocation(WOLF) == Location.FINISH && getItemLocation(GOOSE) == Location.FINISH
                && getItemLocation(BEANS) == Location.FINISH && getItemLocation(FARMER) == Location.FINISH;
    }

    public boolean gameIsLost()
    {
        if (getItemLocation(GOOSE) == Location.BOAT)
        {
            return false;
        }
        if (getItemLocation(GOOSE) == getItemLocation(FARMER))
        {
            return false;
        }
        if (getItemLocation(GOOSE) == boatLocation)
        {
            return false;
        }
        if (getItemLocation(GOOSE) == getItemLocation(WOLF))
        {
            return true;
        }
        if (getItemLocation(GOOSE) == getItemLocation(BEANS))
        {
            return true;
        }
        return false;
    }

    public void resetGame()
    {
        gameObjects.forEach((id, gameObject) -> gameObject.setLocation(Location.START));
        boatLocation = Location.START;
    }

}
