package adventure.commands;

import java.util.Set;

import adventure.Command;
import adventure.Container;
import adventure.EnhancedRoom;
import adventure.GameObject;
import adventure.Item;
import adventure.Message;
import adventure.Player;

public class ExamineCommand extends Command
{

    @Override
    public String execute(Player player)
    {
        EnhancedRoom room = (EnhancedRoom) player.getRoom();
        
        // if there is not second word, return
        if (!hasSecondWord()) {
            return Message.commandRequiresSecond("examine");
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
        
        // is the second word player? return x player message
        if (obj == player) {
            return Message.examinePlayer();
        }
        
        // is the second word the flower pot? return the flower-pot (but not contents)
        if (second.equals("flower-pot")) {
            return obj.getDescription();
        }
        
        // is the second word an item (not a container)? return its description
        if (obj instanceof Item) {
            return obj.getDescription();
        }
        
        // is the second word a container? return its description and
        // search it (tell if its closed / empty / or list the items)
        
        Container container = (Container) obj;
        if (container.hasProperty("openable") && !container.hasProperty("open")) {
            return container.getDescription() + " " + Message.searchClosed(second);
        }
        
        Set<String> contents = container.getObjectNames();
        if (contents.isEmpty()) {
            return container.getDescription() + " " + Message.searchEmpty(second);
        } else {
            return container.getDescription() + " " + 
                    Message.searchList(second, contents.toArray(new String[0]));
        }
    }

}
