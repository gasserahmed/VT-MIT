package river;

import java.util.HashMap;
import java.util.Map;

import river.GameEngine.Item;

public class GameEngine {

    public enum Item {
        WOLF, GOOSE, BEANS, FARMER;
    }

    private Map<Item, GameObject> gameObjects;
    private Location boatLocation;

    public GameEngine() {
        gameObjects = new HashMap<Item, GameObject>() {
            {
                put(Item.GOOSE, new GameObject("Goose", "Honk", Location.START));
                put(Item.WOLF, new GameObject("Wolf", "Howl", Location.START));
                put(Item.BEANS, new GameObject("Beans", "", Location.START));
                put(Item.FARMER, new GameObject("Farmer", "", Location.START));
            }
        };        
        boatLocation = Location.START;
    }

    public String getItemName(Item id) {
        return gameObjects.get(id).getName();
    }

    public Location getItemLocation(Item id) {
        return gameObjects.get(id).getLocation();
    }

    public String getItemSound(Item id) {
        return gameObjects.get(id).getSound();
    }

    public Location getBoatLocation() {
        return boatLocation;
    }

    public void loadBoat(Item id) {
        if (getItemLocation(id) == boatLocation
                && gameObjects.values().stream().anyMatch(
                        gameObject -> !gameObject.getName().equals(getItemName(id)) 
                        && gameObject.getLocation() != Location.BOAT
                        )
                ) {
            gameObjects.get(id).setLocation(Location.BOAT);
        }        
    }

    public void unloadBoat(Item id) {
        if (getItemLocation(id) == Location.BOAT) {
            gameObjects.get(id).setLocation(boatLocation);
        }        
    }

    public void rowBoat() {
        assert (boatLocation != Location.BOAT);
        if (boatLocation == Location.START) {
            boatLocation = Location.FINISH;
        } else {
            boatLocation = Location.START;
        }
    }

    public boolean gameIsWon() {
        
        return gameObjects.values().stream().allMatch(gameObject -> gameObject.getLocation() == Location.FINISH);
    }

    public boolean gameIsLost() {
        if (gameObjects.get(Item.GOOSE).getLocation() == Location.BOAT) {
            return false;
        }
        if (gameObjects.get(Item.GOOSE).getLocation() == gameObjects.get(Item.FARMER).getLocation()) {
            return false;
        }
        if (gameObjects.get(Item.GOOSE).getLocation() == boatLocation) {
            return false;
        }
        if (gameObjects.get(Item.GOOSE).getLocation() == gameObjects.get(Item.WOLF).getLocation()) {
            return true;
        }
        if (gameObjects.get(Item.GOOSE).getLocation() == gameObjects.get(Item.BEANS).getLocation()) {
            return true;
        }
        return false;
    }

    public void resetGame() {
        gameObjects.forEach((id, gameObject) -> gameObject.setLocation(Location.START));
        boatLocation = Location.START;
    }

}
