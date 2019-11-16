package adventure.commands;

import adventure.Command;
import adventure.EnhancedRoom;
import adventure.GameObject;
import adventure.Message;
import adventure.Player;

public class ReplaceCommand extends Command
{

    @Override
    public String execute(Player player)
    {
        EnhancedRoom room = (EnhancedRoom) player.getRoom();
        
        // if there is not second word, return
        if (!hasSecondWord()) {
            return Message.commandRequiresSecond("replace");
        }
        
        String second = getSecondWord();
        
        if (second.equals("light-bulb")) 
        {
            if (player.hasObject("light-bulb")) 
            {
                if (!room.hasObject("wall-lantern")) 
                {
                    return Message.replaceNoLightBulbsHere();
                }
                
                if (room.getObject("wall-lantern").hasProperty("open")) 
                {
                    room.removeProperty("dark");
                    player.removeObject("light-bulb");
                    return Message.replaceSuccess(second);       
                }
                
                return Message.replaceOpenWallLanternFirst();
            }
            
            if (!room.hasProperty("dark")) 
            {
                return Message.replaceAlready(second);
            }
            
            return Message.objectNotInScope("light-bulb");
        }
        
        // if the second word is not an in-scope object, return
        GameObject obj = room.getObject(second);
        
        if (obj == null) 
        {
            return Message.objectNotInScope(second);
        }
        
        if (obj instanceof Player) 
        {
            return Message.replacePlayer();
        }
    
        return Message.replaceCant(second);
    }

}
