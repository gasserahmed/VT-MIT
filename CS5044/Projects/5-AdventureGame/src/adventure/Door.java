package adventure;

public class Door extends Item
{

    private String key;
    
    public Door(String name, Container parent)
    {
        super(name, parent);
        this.addProperty("openable"); // closed by default
        this.addProperty("lockable"); // unlocked by default
        this.addProperty("concealed"); // put door in room description, not in list of contents
        this.addProperty("do-not-list");
    }

    public void setKey(String doorKey) 
    {
        key = doorKey;
    }
    
    public String getKey() 
    {
        return key;
    }
}
