package adventure.commands;

import adventure.Door;
import adventure.EnhancedRoom;
import adventure.GoCommand;
import adventure.Message;
import adventure.Player;

/**
 * 
 * This class is an enhanced version of GoCommand class.
 * It handles scenarios for player movement  
 *
 * @author gasser18
 * @version Nov 16, 2019
 *
 */
public class EnhancedGoCommand extends GoCommand
{
    private String direction;    // holds a pre-populated direction, or null if unknown
    
    /**
     * Create a new EnhancedGoCommand object.
     *
     */
    public EnhancedGoCommand()
    {
        direction = null;         // for "go ..." this is unknown at the time of construction
    }

    /**
     * Create a new EnhancedGoCommand object.
     *
     * @param dir direction name
     */
    public EnhancedGoCommand(String dir)
    {
        direction = dir;          // for all other variants, we'll know ahead of time
    }

    @Override
    public String execute(Player player)
    {
        EnhancedRoom enhancedRoom = (EnhancedRoom) player.getRoom();
        
        // handle setting the direction
        if (direction != null) 
        {
            setSecondWord(direction); // set the second word to the pre-populated direction
        }
        
        String effectiveDir = getSecondWord(); // fetch direction, regardless of how it got there
        
        // handle obstacles (specifically Door objects)
        if (enhancedRoom.hasDoor(effectiveDir)) 
        {
            Door dirDoor = (Door) enhancedRoom.getDoor(effectiveDir);
            if (dirDoor.hasProperty("locked")) 
            {
                return Message.openLocked(dirDoor.getName());
            }            
        }
        
        // delegate to superclass
        return super.execute(player);
    }
}
