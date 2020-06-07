package adventure.commands;

import adventure.Command;
import adventure.Container;
import adventure.EnhancedRoom;
import adventure.GameObject;
import adventure.Message;
import adventure.Player;

/**
 * 
 * This class handles scenarios for hiding the house-key object
 *
 * @author gasser18
 * @version Nov 16, 2019
 *
 */
public class HideCommand extends Command
{

    @Override
    public String execute(Player player)
    {
        EnhancedRoom room = (EnhancedRoom) player.getRoom();
        
        // if there is not second word, return
        if (!hasSecondWord()) 
        {
            return Message.commandRequiresSecond("hide");
        }
        
        String second = getSecondWord();        

        // if object is house-key and exists in player's inventory, 
        // put it back in flower-pot if player is in the front yard 
        if (second.equals("house-key")) 
        {
            if (room.hasObject("flower-pot"))
            {
                if (player.hasObject("house-key")) 
                {
                    GameObject houseKey = player.getObject("house-key");
                    Container flowerPot = (Container) room.getObject("flower-pot"); 
                    houseKey.moveTo(flowerPot);
                    houseKey.addProperty("concealed");
                    return Message.hideSuccess("house-key");
                }
                
                return Message.hideAlready("house-key");
            }
            
            return Message.hideNeedsFlowerpot(second);
        }
        
        // if the second word is not an in-scope object, return
        GameObject obj = room.getInScopeObject(second);
        
        if (obj == null) {
            return Message.objectNotInScope(second);
        }
        
        return Message.hideCant(second);
    }
}
