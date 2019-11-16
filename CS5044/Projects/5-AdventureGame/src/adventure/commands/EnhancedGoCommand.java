package adventure.commands;

import adventure.Door;
import adventure.EnhancedRoom;
import adventure.GoCommand;
import adventure.Message;
import adventure.Player;

public class EnhancedGoCommand extends GoCommand
{
    private String direction;    // holds a pre-populated direction, or null if unknown

    public EnhancedGoCommand()
    {
        direction = null;         // for "go ..." this is unknown at the time of construction
    }

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
            
           player.setHasWon(true);
           return "You solved the mystery!";
        }
        
        // delegate to superclass
        return super.execute(player);
    }
}
