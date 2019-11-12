package adventure;

public class Door extends Item
{

    public Door(String name, Container parent)
    {
        super(name, parent);
        this.addProperty("openable"); // closed by default
        this.addProperty("lockable"); // unlocked by default
        this.addProperty("concealed"); // put door in room description, not in list of contents
    }

}
