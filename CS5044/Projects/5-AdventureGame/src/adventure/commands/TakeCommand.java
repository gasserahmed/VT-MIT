package adventure.commands;

import adventure.Command;
import adventure.Container;
import adventure.Door;
import adventure.EnhancedRoom;
import adventure.GameObject;
import adventure.Item;
import adventure.Message;
import adventure.Player;

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
        // if the second word is a door object
        Object doorObj = room.getObject(second);
        if (doorObj instanceof Door) 
        {
            return Message.takeCant(second);
        }        
        
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
        
        // if object is item/container and not fixed, take it 
        if ((obj instanceof Container || obj instanceof Item) && !obj.hasProperty("fixed")) 
        {
            obj.moveTo(player);
            return Message.takeSuccess(second);
        }
    
        return Message.takeCant(second);
    }
}
