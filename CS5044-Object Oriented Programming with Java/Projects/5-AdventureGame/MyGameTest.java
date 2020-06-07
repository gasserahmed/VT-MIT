package adventure;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

//CHECKSTYLE:OFF
@SuppressWarnings("javadoc")
public class MyGameTest {

    private String message;
    private String roomDesc;
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
        roomDesc = game.player().getRoom().getDescription();
        assertEquals("the front yard", roomDesc);
    }

    @Test
    public void testExamineWallLantern() {
        executeMoves("examine wall-lantern");
        assertEquals("the front yard", roomDesc);
        assertEquals(Message.descWallLanternBroken(), message);
        assertTrue(longDesc.contains("lantern"));
    }

    @Test
    public void testTakeScrewdriver() {
        executeMoves("east", "north", "take screwdriver");
        assertEquals("your garage", roomDesc);
        assertEquals(Message.takeSuccess("screwdriver"), message);
    }

    private void executeMoves(String... inputs) {
        for (String input : inputs) {
            Command command = game.parser().getCommand(input);
            message = command.execute(game.player());
        }
        roomDesc = game.player().getRoom().getDescription();
        longDesc = game.player().getRoom().getLongDescription();
    }
}
