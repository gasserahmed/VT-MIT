package adventure.commands;

import adventure.Command;
import adventure.EnhancedRoom;
import adventure.GameObject;
import adventure.Message;
import adventure.Player;

/**
 * 
 * This class handles scenarios for closing an object
 *
 * @author gasser18
 * @version Nov 16, 2019
 *
 */
public class CloseCommand extends Command
{

    @Override
    public String execute(Player player)
    {
        EnhancedRoom room = (EnhancedRoom) player.getRoom();
        
        // if there is not second word, return
        if (!hasSecondWord()) {
            return Message.commandRequiresSecond("close");
        }
        
        String second = getSecondWord();        
        
        // if the second word is not an in-scope object, return
        GameObject obj = room.getInScopeObject(second);
        
        if (obj == null) 
        {
            return Message.objectNotInScope(second);
        }
        
        if (!obj.hasProperty("openable")) 
        {
            return Message.closeCant(second);
        }
        
        if (obj.hasProperty("open")) 
        {
            obj.removeProperty("open");
            return Message.closeSuccess(second);
        }
        
        return Message.closeAlready(second);
    }

}
