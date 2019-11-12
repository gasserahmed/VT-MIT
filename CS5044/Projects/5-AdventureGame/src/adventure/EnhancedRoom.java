package adventure;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class EnhancedRoom extends Room
{
    private Map<String, Door> doorMap;
    private Formatter formatter = Formatter.getInstance();
    
    public EnhancedRoom(String name)
    {
        super(name);
        doorMap = new HashMap<>();
    }
    
    // door-map getter and setter
    public Door getDoor(String direction) {
        return doorMap.get(direction);
    }
    
    public void setDoor(String direction, Door door) {
        doorMap.put(direction, door);
    }
    
    public boolean hasDoor(String doorName) {
        boolean doorExists = false;
        for (Door door : doorMap.values()) {
            if (door.getName().equals(doorName)) {
                doorExists = true;
            }
        }
        return doorExists;
    }
    
    @Override
    public String getExitString() {
        // create a fancier exit string
        return super.getExitString();
    }
    
    @Override
    public String getRoomContents() {
        //Set<String> roomObjNames = this.getObjectNames();
        String[] contentArr = getRoomContentsArray();
        if (contentArr.length == 0) {
            return "";
        }
        
        return "You can see " + formatter.commaSeparatedList(contentArr, "a") + " here.";
    }
    
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
    
    public GameObject getInScopeObject(String objectName) {
        return getInScopeObjectMap().get(objectName);
    }
    
    private Map<String, GameObject> getInScopeObjectMap() {
        Map<String, GameObject> inScopeMap = new HashMap<>();        
        for (String objName : this.getObjectNames()) {
            GameObject obj = this.getObject(objName);
            if (!obj.hasProperty("concealed")) {
                inScopeMap.put(objName, obj);
                // if obj instanceof non-closed container, add the non-concealed objs to map also
            }
        }
        return inScopeMap;
    }

}
