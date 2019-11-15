package adventure.commands;

import adventure.Command;
import adventure.Door;
import adventure.EnhancedRoom;
import adventure.GameObject;
import adventure.Message;
import adventure.Player;

public class UnlockCommand extends Command
{

    @Override
    public String execute(Player player)
    {
        EnhancedRoom room = (EnhancedRoom) player.getRoom();
        
        // if there is not second word, return
        if (!hasSecondWord()) {
            return Message.commandRequiresSecond("unlock");
        }
        
        String second = getSecondWord();
        
        // if the second word is not an in-scope object, return
        GameObject doorObj = room.getObject(second);
        
        if (doorObj == null) 
        {
            return Message.objectNotInScope(second);
        }
        
        if (doorObj.hasProperty("lockable") ) 
        {
            if (doorObj.hasProperty("locked")) 
            {
                Door door = (Door) doorObj;
                String doorKey = door.getKey();
                if (player.hasObject(doorKey)) 
                {
                    door.removeProperty("locked");
                    return Message.unlockSuccess(second, doorKey);    
                }
                
                return Message.unlockNoKey(second, doorKey);                
            }
            
            return Message.unlockAlready(second);
        }
        
        return Message.unlockCant(second);
    }

}

