package adventure;

public class EnhancedPlayer extends Player
{

    public EnhancedPlayer(String name)
    {
        super(name);
        this.addProperty("do-not-list");
    }

}
