package adventure;

/**
 * 
 * This class has a private instance and public methods for the Door class
 *
 *
 * @author gasser18
 * @version Nov 16, 2019
 *
 */
public class Door extends Item
{

    private String key;
    
    /**
     * Create a new Door object.
     *
     * @param name door name
     * @param parent room name
     */
    public Door(String name, Container parent)
    {
        super(name, parent);
        this.addProperty("openable"); // closed by default
        this.addProperty("lockable"); // unlocked by default
        this.addProperty("do-not-list"); // put door in room description, not in list of contents
        this.addProperty("fixed");
    }

    /**
     * Set the key name for door
     *
     * @param doorKey door key
     */
    public void setKey(String doorKey) 
    {
        key = doorKey;
    }
    
    /**
     * Get door's key name
     *
     * @return key
     */
    public String getKey() 
    {
        return key;
    }
}
