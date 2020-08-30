package message;

/**
 * 
 * Message class.
 *
 * @author Gasser Ahmed
 * @version Aug 28, 2020
 *
 */
public class Message
{
    private String text;
    
    /**
     * Create a new Message object.
     *
     * @param text message text
     */
    public Message(String text) {
        this.text = text;
    }

    @Override
    public String toString()
    {
        return text;
    }    
}
