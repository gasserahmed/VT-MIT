package adventure.commands;

import java.util.Set;

import adventure.Command;
import adventure.Container;
import adventure.EnhancedRoom;
import adventure.GameObject;
import adventure.Message;
import adventure.Player;

/**
 * 
 * This class handles scenarios for searching an object
 *
 * @author gasser18
 * @version Nov 16, 2019
 *
 */
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
        

        if (second.equals("wallet")) 
        {
            if (player.hasObject("driver-license")) 
            {
                return Message.searchDriverLicenseAlready();
            }
            
            Container wallet = (Container) room.getObject("wallet");
            GameObject driverLicense = wallet.getObject("driver-license");
            driverLicense.removeProperty("concealed");
            driverLicense.moveTo(player);
            player.setHasWon(true);
            return Message.searchDriverLicenseSuccess();
        }
        
        // if the second word the flower pot
        if (second.equals("flower-pot")) {
            if (player.hasObject("house-key")) 
            {
                return Message.searchPotAlready();
            }
            
            Container flowerPot = (Container) room.getObject("flower-pot");
            GameObject houseKey = flowerPot.getObject("house-key");
            houseKey.removeProperty("concealed");
            houseKey.moveTo(player);
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
