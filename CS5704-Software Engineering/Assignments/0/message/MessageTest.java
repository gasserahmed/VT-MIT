package message;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * 
 * JUnit test file for Message class
 *
 * @author Gasser Ahmed
 * @version Aug 28, 2020
 *
 */

@SuppressWarnings("javadoc")
public class MessageTest
{

    @Test
    public void testMessageWithOneWordText()
    {
        Message oneWordMessage = new Message("5704");
        assertEquals(oneWordMessage.toString(), "5704");
    }
    
    @Test
    public void testMessageWithTwoWordsText()
    {
        Message twoWordsMessage = new Message("CS 5704");
        assertEquals(twoWordsMessage.toString(), "CS 5704");
    }

}
