package adventure;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 
 * This class is the enhanced version of Room class.
 * It has private instances and public methods.
 * 
 *
 * @author gasser18
 * @version Nov 16, 2019
 *
 */
public class EnhancedRoom extends Room
{
    private Map<String, Door> doorMap;
    private Formatter formatter = Formatter.getInstance();
    
    /**
     * Create a new EnhancedRoom object.
     *
     * @param name room name
     */
    public EnhancedRoom(String name)
    {
        super(name);
        doorMap = new HashMap<>();
    }
    
    /**
     * Place a get door at specific direction of room.
     *
     * @param direction direction name
     * @return door
     */
    // door-map getter and setter
    public Door getDoor(String direction) {
        return doorMap.get(direction);
    }
    
    /**
     * Set door with its direction in the room.
     *
     * @param direction direction name
     * @param door door name
     */
    public void setDoor(String direction, Door door) {
        doorMap.put(direction, door);
    }
    
    /**
     * Check if door exists in a certain direction.
     *
     * @param dir room direction
     * @return true if door exists in the given direction, otherwise false
     */
    public boolean hasDoor(String dir) {
        return doorMap.containsKey(dir);
    }
    
    @Override
    public String getExitString() {
        String exitString = "You can go to ";
        Set<String> exitSet = new HashSet<String>();
        for (String dir: getExitDirections()) 
        {
            Room destRoom = getExit(dir);
            exitSet.add(dir + " to " + destRoom.getName());
        }
        
        Formatter f = Formatter.getInstance();
        return exitString + f.commaSeparatedList(exitSet) + '.';
                
    }
    
    @Override
    public String getRoomContents() {
        String[] contentArr = getRoomContentsArray();
        if (contentArr.length == 0) {
            return "";
        }
        
        return "You can see " + formatter.commaSeparatedList(contentArr, "a") + " here.";
    }
    
    /**
     * 
     * Get room contents.
     *
     * @return an array of room contents
     */
    private String[] getRoomContentsArray() {
        List<String> objList = new ArrayList<>();        
        for (String objName : this.getObjectNames()) {
            GameObject obj = this.getObject(objName);
            if (!obj.hasProperty("concealed") && !obj.hasProperty("do-not-list")) {
                objList.add(objName);
            }
        }
        
        return objList.toArray(new String[0]);
    }
    
    /**
     * Get a specific object that is in scope.
     *
     * @param objectName name of the object
     * @return game object
     */
    public GameObject getInScopeObject(String objectName) {
        return getInScopeObjectMap().get(objectName);
    }
    
    /**
     * 
     * Get a map for objects in player's scope.
     *
     * @return a map of object's name with the object
     */
    private Map<String, GameObject> getInScopeObjectMap() {
        Map<String, GameObject> inScopeMap = new HashMap<>();        
        for (String objName : this.getObjectNames()) {
            GameObject obj = this.getObject(objName);
            if (!obj.hasProperty("concealed")) {
                inScopeMap.put(objName, obj);
                
                // if obj instanceof non-closed container, add the non-concealed objs to map also
                if (obj instanceof Container && obj.hasProperty("openable") 
                        && obj.hasProperty("open")) 
                {
                    Container container = (Container) obj;
                    for (String childObjName : container.getObjectNames()) 
                    {
                        GameObject childObj = container.getObject(childObjName);
                        if (!childObj.hasProperty("concealed")) 
                        {
                            inScopeMap.put(childObjName, childObj);
                        }
                    }
                }
            }
        }
        return inScopeMap;
    }

}
