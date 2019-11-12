package adventure.commands;

import java.util.Set;

import adventure.Command;
import adventure.Container;
import adventure.EnhancedRoom;
import adventure.GameObject;
import adventure.Item;
import adventure.Message;
import adventure.Player;

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
        // if the second word is a door
        if (room.hasDoor(second)) {
            return Message.examineDefault(second);
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
            if (!player.getParent().hasObject(second)) {
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
        
        // is the object locked? return
        if (obj.hasProperty("locked")) {
            return Message.openLocked(second);
        }
        
        // can you successfully open the object
        // if so, is the object an item or a container?
        // if item: set props and return open success
        if (obj instanceof Item) {
            obj.addProperty("open");
            return Message.openSuccess(second);
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
