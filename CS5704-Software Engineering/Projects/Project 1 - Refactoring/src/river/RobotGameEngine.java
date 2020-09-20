package river;

import java.awt.Color;
import java.util.HashMap;
import java.util.Map;

public class RobotGameEngine implements GameEngine
{

    private Map<Item, GameObject> gameObjects;
    private Location boatLocation;

    private final static Item SMALLBOT_2 = Item.ITEM_0;
    private final static Item SMALLBOT_1 = Item.ITEM_1;
    private final static Item TALLBOT_1 = Item.ITEM_2;
    private final static Item TALLBOT_2 = Item.ITEM_3;
    private Item passenger1;
    private Item passenger2;
    private final static Color boatColor = Color.ORANGE;
    private int numberOfObjectsInBoat = 0;

    public RobotGameEngine()
    {
        gameObjects = new HashMap<Item, GameObject>()
        {
            {
                put(SMALLBOT_2, new GameObject("S2", Color.CYAN, Location.START));
                put(SMALLBOT_1, new GameObject("S1", Color.CYAN, Location.START));
                put(TALLBOT_1, new GameObject("T1", Color.GREEN, Location.START));
                put(TALLBOT_2, new GameObject("T2", Color.GREEN, Location.START));
            }
        };
        boatLocation = Location.START;
        numberOfObjectsInBoat = 0;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getItemLabel(Item id)
    {
        return gameObjects.get(id).getLabel();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Location getItemLocation(Item id)
    {
        return gameObjects.get(id).getLocation();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Color getItemColor(Item id)
    {
        return gameObjects.get(id).getColor();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Location getBoatLocation()
    {
        return boatLocation;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Color getBoatColor()
    {
        return boatColor;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void loadBoat(Item id)
    {
        if (getItemLocation(id) == boatLocation && !isAnyTallRobotOnBoat()
                && numberOfObjectsInBoat < 2 
                && canLoadTallRobot(id))
        {
            if (passenger1 == null) {
                passenger1 = id;
            }
            else {
                passenger2 = id;
            }
            
            gameObjects.get(id).setLocation(Location.BOAT);
            numberOfObjectsInBoat++;
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void unloadBoat(Item id)
    {
        if (getItemLocation(id) == Location.BOAT)
        {
            if (passenger1 == id) {
                passenger1 = null;
            }
            else {
                passenger2 = null;
            }
            
            gameObjects.get(id).setLocation(boatLocation);
            numberOfObjectsInBoat--;
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void rowBoat()
    {
        if (numberOfObjectsInBoat > 0)
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
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean gameIsWon()
    {
        return getItemLocation(TALLBOT_1) == Location.FINISH
                && getItemLocation(SMALLBOT_1) == Location.FINISH
                && getItemLocation(SMALLBOT_2) == Location.FINISH
                && getItemLocation(TALLBOT_2) == Location.FINISH;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean gameIsLost()
    {
        return false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void resetGame()
    {
        gameObjects.forEach((id, gameObject) -> gameObject.setLocation(Location.START));
        boatLocation = Location.START;
    }

    private boolean isAnyTallRobotOnBoat()
    {
        return getItemLocation(TALLBOT_1) == Location.BOAT
                || getItemLocation(TALLBOT_2) == Location.BOAT;
    }

    private boolean canLoadTallRobot(Item id)
    {
        return (id != TALLBOT_1 && id != TALLBOT_2) || numberOfObjectsInBoat == 0;
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public Item getPassenger1() {
        return passenger1;
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public Item getPassenger2() {
        return passenger2;
    }
}
