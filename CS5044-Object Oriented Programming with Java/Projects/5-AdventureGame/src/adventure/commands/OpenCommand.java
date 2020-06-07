package adventure.commands;

import java.util.Set;

import adventure.Command;
import adventure.Container;
import adventure.Door;
import adventure.EnhancedRoom;
import adventure.GameObject;
import adventure.Message;
import adventure.Player;

/**
 * 
 * This class handles scenarios for opening an object
 *
 * @author gasser18
 * @version Nov 16, 2019
 *
 */
public class OpenCommand extends Command
{

    @Override
    public String execute(Player player)
    {
        EnhancedRoom room = (EnhancedRoom) player.getRoom();
        
        // if there is not second word, return
        if (!hasSecondWord()) {
            return Message.commandRequiresSecond("open");
        }
        
        String second = getSecondWord();

        // if the second word is a door object
        GameObject doorObj = room.getObject(second);
        if (doorObj instanceof Door) 
        {
            if (doorObj.hasProperty("locked")) {
                return Message.openLocked(second);
            }
            
            return Message.openSuccess(second);
        }
        
        // if the second word is not an in-scope object, return
        GameObject obj = room.getInScopeObject(second);
        
        if (obj == null) {
            return Message.objectNotInScope(second);
        }
        
        // treat wall-lantern special
        if (second.equals("wall-lantern")) {
            // is the lantern working? (already open) return
            if (obj.hasProperty("open")) {
                return Message.openLanternAlready();
            }
            
            // is the lantern still broken?
            if (!player.hasObject("screwdriver")) {
                return Message.openLanternNoKey();
            }
            
            // now what?? can it be open??
            obj.addProperty("open");
            return Message.openLanternSuccess();    
        }
        
        // is the object openable? if not, return
        if (!obj.hasProperty("openable")) {
            return Message.openCant(second);
        }
        
        // is the object already open? return
        if (obj.hasProperty("open")) {
            return Message.openAlready(second);
        }          
        
        // if container: set props, open success, and search result (empty/list)
        Container container = (Container) obj;
        container.addProperty("open");
        Set<String> contents = container.getObjectNames();
        if (contents.isEmpty()) {
            return Message.openSuccess(second) + " " + Message.searchEmpty(second);
        }
        
        return Message.openSuccess(second) + " " + 
                Message.searchList(second, contents.toArray(new String[0]));
    }

}
