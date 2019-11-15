package adventure.commands;

import adventure.Command;
import adventure.Message;
import adventure.Player;

public class InventoryCommand extends Command
{

    @Override
    public String execute(Player player)
    {
        if (player.getObjectNames().isEmpty()) 
        {
            return Message.invEmpty();    
        }
        
        String[] playerContents = player.getObjectNames().toArray(new String[0]);
        return Message.invList(playerContents);
    }

}
