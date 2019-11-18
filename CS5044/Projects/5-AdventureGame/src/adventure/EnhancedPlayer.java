package adventure;

/**
 * 
 * This class is the enhanced version of class Player
 *
 * @author gasser18
 * @version Nov 16, 2019
 *
 */

public class EnhancedPlayer extends Player
{

    /**
     * Create a new EnhancedPlayer object with a default "do-not-list" property.
     *
     * @param name player name
     */
    public EnhancedPlayer(String name)
    {
        super(name);
        this.addProperty("do-not-list");
    }

}
 