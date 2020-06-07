package adventure.commands;

import adventure.Command;
import adventure.EnhancedRoom;
import adventure.GameObject;
import adventure.Message;
import adventure.Player;

/**
 * 
 * This class handles scenarios for taking an object
 *
 * @author gasser18
 * @version Nov 16, 2019
 *
 */
public class TakeCommand extends Command
{

    @Override
    public String execute(Player player)
    {
        EnhancedRoom room = (EnhancedRoom) player.getRoom();
        
        // if there is not second word, return
        if (!hasSecondWord()) {
            return Message.commandRequiresSecond("take");
        }
        
        String second = getSecondWord();     
        
        GameObject objWithPlayer = player.getObject(second);
        if (objWithPlayer != null) 
        {
            return Message.takeAlready(second);
        }
        
        // if the second word is not an in-scope object, return
        GameObject obj = room.getInScopeObject(second);
        
        if (obj == null) {
            return Message.objectNotInScope(second);
        }
        
        // if object is container and local, return
        if (obj.hasProperty("local")) 
        {
            return Message.takeLocal(second);
        }
        
        // if object is a player, return
        if (obj instanceof Player) 
        {
            return Message.takePlayer();
        }
        
        // if object is not fixed, take it 
        if (!obj.hasProperty("fixed")) 
        {
            obj.moveTo(player);
            return Message.takeSuccess(second);
        }
    
        return Message.takeCant(second);
    }
}
