package adventure;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

//CHECKSTYLE:OFF
@SuppressWarnings("javadoc")
public class MyGameTest {

    private String message;
    private String roomName;
    private String longDesc;
    private Game game;

    public MyGameTest() {
    }

    @Before
    public void setUp() {
        game = new MyGame();
        game.createCommands();
        game.createRooms();
    }

    @Test
    public void testInit() {
        roomName = game.player().getRoom().getName();
        assertEquals("the front yard", roomName);
    }

    @Test
    public void testExamineFrontDoor() {
        executeMoves("examine front-door");
        assertEquals("the front yard", roomName);
        assertEquals(Message.examineDefault("front-door"), message);
        assertTrue(longDesc.contains("front-door"));
    }

    @Test
    public void testTakeScrewdriver() {
        executeMoves("east", "north", "take screwdriver");
        assertEquals("your garage", roomName);
        assertEquals(Message.takeSuccess("screwdriver"), message);
    }

    private void executeMoves(String... inputs) {
        for (String input : inputs) {
            Command command = game.parser().getCommand(input);
            message = command.execute(game.player());
        }
        roomName = game.player().getRoom().getName();
        longDesc = game.player().getRoom().getLongDescription();
    }
}
