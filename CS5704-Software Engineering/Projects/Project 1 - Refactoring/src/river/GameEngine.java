package river;

import java.awt.Color;

public interface GameEngine
{

    /**
     * Returns the label of the specified item. This method may be used by a GUI (for example) to
     * put the label string inside of a rectangle. A label is typically one or two characters long.
     * 
     * @param item the item with the desired label
     * @return the label of the specified item
     */
    String getItemLabel(Item item);

    /**
     * Returns the color of the specified item. This method may be used by a GUI (for example) to
     * color a rectangle that represents the item.
     * 
     * @param item the item with the desired color
     * @return the color of the specified item
     */
    Color getItemColor(Item item);

    /**
     * Returns the location of the specified item. The location may be START, FINISH, or BOAT.
     * 
     * @param item the item with the desired location
     * @return the location of the specified item
     */
    Location getItemLocation(Item item);

    /**
     * Returns the color of the boat
     * 
     * @return the color of the boat
     */
    Color getBoatColor();

    /**
     * Returns the location of the boat.
     * 
     * @return the location of the boat
     */
    Location getBoatLocation();

    /**
     * Loads the specified item onto the boat. Assuming that all the required conditions are met,
     * this method will change the location of the specified item to BOAT. Typically, the following
     * conditions must be met: (1) the item's location and the boat's location must be the same, and
     * (2) there must be room on the boat for the item. If any condition is not met, this method
     * does nothing.
     * 
     * @param item the item to load onto the boat
     */
    void loadBoat(Item item);

    /**
     * Unloads the specified item from the boat. If the item is on the boat (the item's location is
     * BOAT), then the item's location is changed to the boat's location. If the item is not on the
     * boat, then this method does nothing.
     * 
     * @param item the item to be unloaded
     */
    void unloadBoat(Item item);

    /**
     * Rows the boat to the other shore. This method will only change the location of the boat if
     * the boat has a passenger that can drive the boat.
     */
    void rowBoat();

    /**
     * True when the location of all the game items is FINISH.
     * 
     * @return true if all game items of a location of FINISH, false otherwise
     */
    boolean gameIsWon();

    /**
     * True when one or more implementation-specific conditions are met. The conditions have to do
     * with which items are on which side of the river. If an item is in the boat, it is typically
     * still considered to be on the same side of the river as the boat.
     * 
     * @return true when one or more game-specific conditions are met, false otherwise
     */
    boolean gameIsLost();

    /**
     * Resets the game.
     */
    void resetGame();
}