package adventure.commands;

import java.util.Set;

import adventure.Command;
import adventure.Container;
import adventure.EnhancedRoom;
import adventure.GameObject;
import adventure.Item;
import adventure.Message;
import adventure.Player;

public class SearchCommand extends Command
{

    @Override
    public String execute(Player player)
    {
        EnhancedRoom room = (EnhancedRoom) player.getRoom();
        
        // if there is not second word, return
        if (!hasSecondWord()) {
            return Message.commandRequiresSecond("search");
        }
        
        String second = getSecondWord();        
        GameObject objInRoom = room.getObject(second);
        if (objInRoom instanceof Item) 
        {
            return Message.searchDefault();
        }
        
        // if the second word is not an in-scope object, return
        GameObject obj = room.getInScopeObject(second);
        
        if (obj == null) 
        {
            return Message.objectNotInScope(second);
        }
        
        // if room is dark
        if (room.hasProperty("dark")) 
        {
            return Message.searchDark();
        }
        
        // if the second word the flower pot
        if (second.equals("flower-pot")) {
            if (player.hasObject("house-key")) 
            {
                return Message.searchPotAlready();
            }
            
            Container flowerPot = (Container) room.getObject("flower-pot");
            GameObject gameObj = flowerPot.getObject("house-key");
            gameObj.moveTo(player);
            return Message.searchPotSuccess();
        }
        
        if (obj instanceof Container) 
        {
            // If the second word is a container
            // search it (tell if its closed / empty / or list the items)
            
            if (obj.hasProperty("openable") && !obj.hasProperty("open")) {
                return Message.searchClosed(second);
            }
            
            Set<String> contents = ((Container) obj).getObjectNames();
            if (contents.isEmpty()) {
                return Message.searchEmpty(second);
            }
            
            return Message.searchList(second, contents.toArray(new String[0]));
        }
        
        return Message.searchDefault();
    }

}
