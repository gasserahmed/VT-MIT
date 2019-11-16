package adventure;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import java.util.Set;

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
    public void testTakeScrewdriver() {
        executeMoves("east", "north", "take screwdriver");
        assertEquals("your garage", roomName);
        assertEquals(Message.takeSuccess("screwdriver"), message);
    }


    /*
     * Testing Examine Commands
     * */
    @Test
    public void testExamineWithNoSecondWord() 
    {
        executeMoves("examine");
        assertEquals(Message.commandRequiresSecond("examine"), message);
    }
    
    @Test
    public void testExamineItemNotInScope() 
    {
        executeMoves("examine not_here");
        assertEquals(Message.objectNotInScope("not_here"), message);
    }

    @Test
    public void testExamineFrontDoor() {
        executeMoves("examine front-door");
        assertEquals("the front yard", roomName);
        assertEquals(Message.examineDefault("front-door"), message);
        assertTrue(longDesc.contains("front-door"));
    }

    @Test
    public void testExamineWallLanternWorking() 
    {
        game.player().getRoom().removeProperty("dark");
        executeMoves("examine wall-lantern");
        assertEquals(Message.examineLanternFixed(), message);
    }
    
    @Test
    public void testExamineWallLanternOpen() 
    {
        game.player().getRoom().getObject("wall-lantern").addProperty("open");
        executeMoves("examine wall-lantern");
        assertEquals(Message.examineLanternOpen(), message);
    }

    @Test
    public void testExamineWallLanternBroken() 
    {
        executeMoves("examine wall-lantern");
        assertEquals(Message.examineLanternBroken(), message);
    }

    @Test
    public void testExaminePlayer() 
    {
        executeMoves("examine Player");
        assertEquals(Message.examinePlayer(), message);
    }
    
    @Test
    public void testExamineFlowerPot() 
    {
        executeMoves("examine flower-pot");
        assertEquals(game.player().getRoom().getObject("flower-pot").getDescription(), message);
    }
    
    @Test
    public void testExamineItem() 
    {
        executeMoves("examine stone-walkway");
        assertEquals(game.player().getRoom().getObject("stone-walkway").getDescription(), message);
    }
    
    @Test
    public void testExamineUnopenableEmptyContainer() 
    {
        executeMoves("e", "n", "examine old-boxes");
        assertEquals(game.player().getRoom().getObject("old-boxes").getDescription()
                + " " +Message.searchEmpty("old-boxes"), message);
    }
    
    @Test
    public void testExamineClosedContainer() 
    {
        executeMoves("e", "n", "examine shoebox");
        assertEquals(game.player().getRoom().getObject("shoebox").getDescription()
                + " " +Message.searchClosed("shoebox"), message);
    }
    
    @Test
    public void testExamineOpenEmptyContainer() 
    {
        executeMoves("e", "n", "open shoebox", "take light-bulb", "examine shoebox");
        assertEquals(game.player().getRoom().getObject("shoebox").getDescription()
                + " " + Message.searchEmpty("shoebox"), message);
    }
    
    @Test
    public void testExamineOpenFullContainer() 
    {
        executeMoves("e", "n", "open shoebox", "examine shoebox");
        GameObject obj = game.player().getRoom().getObject("shoebox");
        Container container = (Container) obj;
        Set<String> contents = container.getObjectNames();
        assertEquals(obj.getDescription()
                + " " + Message.searchList("shoebox", contents.toArray(new String[0])), message);
    }


    /*
     * Testing Inventory Commands
     * */
    @Test
    public void testEmptyInventory() 
    {
        executeMoves("i");
        assertEquals(Message.invEmpty(), message);
    }
    
    @Test
    public void testInventoryHavingLightBulb() 
    {
        testTakeLightBulbSuccessfull();
        executeMoves("i");
        String[] playerContents = game.player().getObjectNames().toArray(new String[0]);
        assertEquals(Message.invList(playerContents), message);
    }
    
    /*
     * Testing Open Commands
     * */
    @Test
    public void testOpenWithNoSecondWord() 
    {
        executeMoves("open");
        assertEquals(Message.commandRequiresSecond("open"), message);
    }
    
    @Test
    public void testOpenObjectNotInScope() 
    {
        executeMoves("open not_here");
        assertEquals(Message.objectNotInScope("not_here"), message);
    }
    
    @Test
    public void testOpenLockedDoor() 
    {
        executeMoves("open front-door");
        assertEquals(Message.openLocked("front-door"), message);
    }
    
    @Test
    public void testOpenUnLockedDoor() 
    {
        game.player().getRoom().getObject("front-door").removeProperty("locked");
        executeMoves("open front-door");
        assertEquals(Message.openSuccess("front-door"), message);
    }
    
    @Test
    public void testOpenWallLanternAlreadyOpen() 
    {
        game.player().getRoom().getObject("wall-lantern").addProperty("open");
        executeMoves("open wall-lantern");
        assertEquals(Message.openLanternAlready(), message);
    }
    
    @Test
    public void testOpenWallLanternNoKey() 
    {
        executeMoves("open wall-lantern");
        assertEquals(Message.openLanternNoKey(), message);
    }
    
    @Test
    public void testOpenWallLanternSuccess() 
    {
        executeMoves("e", "n", "take screwdriver", "s", "w", "open wall-lantern");
        GameObject obj = game.player().getRoom().getObject("wall-lantern");
        assertEquals(true, obj.hasProperty("open"));
        assertEquals(Message.openLanternSuccess(), message);
    }
    
    @Test
    public void testOpenObjectAlreadyOpen() 
    {
        executeMoves("e", "n", "open shoebox", "open shoebox");
        assertEquals(Message.openAlready("shoebox"), message);
    }

    @Test
    public void testOpenObjectNotOpenable() 
    {
        executeMoves("open flower-pot");
        assertEquals(Message.openCant("flower-pot"), message);
    }
    
    @Test
    public void testOpenItem() 
    {
        game.player().getRoom().getObject("front-door").removeProperty("locked");
        executeMoves("open front-door");
        assertEquals(Message.openSuccess("front-door"), message);
    }
    
    @Test
    public void testOpenEmptyContainer() 
    {
        testTakeLightBulbSuccessfull();
        game.player().getRoom().getObject("shoebox").removeProperty("open");
        executeMoves("open shoebox");
        assertEquals(Message.openSuccess("shoebox") + " " + Message.searchEmpty("shoebox"),
                message);
    }
    
    /*
     * Testing Replace command
     */
    @Test
    public void testReplaceWithNoSecondWord() 
    {
        executeMoves("replace");
        assertEquals(Message.commandRequiresSecond("replace"), message);
    }
    
    @Test
    public void testReplaceObjectNotInScope() 
    {
        executeMoves("replace not_here");
        assertEquals(Message.objectNotInScope("not_here"), message);
    }
    
    @Test
    public void testReplacePlayer() 
    {
        executeMoves("replace Player");
        assertEquals(Message.replacePlayer(), message);
    }
    
    @Test
    public void testReplaceObjectCantReplaced() 
    {
        executeMoves("replace front-door");
        assertEquals(Message.replaceCant("front-door"), message);
    }
    
    
    @Test
    public void testReplaceLightBulbAlreadyReplaced() 
    {
        testReplaceLightBulbSuccess();
        executeMoves("replace light-bulb");
        assertEquals(Message.replaceAlready("light-bulb"), message);
    }
    
    @Test
    public void testReplaceLightBulbWithoutLightBulb() 
    {
        executeMoves("replace light-bulb");
        assertEquals(Message.objectNotInScope("light-bulb"), message);
    }
    
    @Test
    public void testReplaceLightBulbWithWallLanternClosed() 
    {
        testTakeLightBulbSuccessfull();
        executeMoves("s", "w", "replace light-bulb");
        assertEquals(Message.replaceOpenWallLanternFirst(), message);
    }
    
    @Test
    public void testReplaceLightBulbInWrongRoom() 
    {
        testTakeLightBulbSuccessfull();
        executeMoves("replace light-bulb");
        assertEquals(Message.replaceNoLightBulbsHere(), message);
    }  
    
    @Test
    public void testReplaceLightBulbSuccess() 
    {
        testTakeLightBulbSuccessfull();
        executeMoves("s", "w");
        testOpenWallLanternSuccess();
        executeMoves("replace light-bulb");
        assertEquals(Message.replaceSuccess("light-bulb"), message);
    }    
    
    /*
     * Testing Take commands
     */
    @Test
    public void testTakeWithNoSecondWord() 
    {
        executeMoves("take");
        assertEquals(Message.commandRequiresSecond("take"), message);
    }
    
    @Test
    public void testTakeObjectNotInScope() 
    {
        executeMoves("take not_here");
        assertEquals(Message.objectNotInScope("not_here"), message);
    }
    
    @Test
    public void testTakePlayer() 
    {
        executeMoves("take Player");
        assertEquals(Message.takePlayer(), message);
    }
    
    @Test
    public void testTakeCant() 
    {
        executeMoves("take front-door");
        assertEquals(Message.takeCant("front-door"), message);
    }
    
    @Test
    public void testTakeAlready() 
    {
        testTakeLightBulbSuccessfull();
        executeMoves("take light-bulb");
        assertEquals(Message.takeAlready("light-bulb"), message);
    }
    
    @Test
    public void testTakeLocal() 
    {
        executeMoves("take flower-pot");
        assertEquals(Message.takeLocal("flower-pot"), message);
    }

    @Test
    public void testTakeFixedItem() 
    {
        executeMoves("take wall-lantern");
        assertEquals(Message.takeCant("wall-lantern"), message);
    }
    
    @Test
    public void testTakeLightBulbSuccessfull() 
    {
        executeMoves("e", "n", "open shoebox", "take light-bulb");
        assertEquals(Message.takeSuccess("light-bulb"), message);
    }
    
    /*
     * Testing EnhancedGo commands
     * */
    @Test
    public void testEnhancedGoDirectionNull()
    {
        executeMoves("go");
        assertEquals("Go where?", message);
    }
    
    @Test
    public void testEnhancedGoDoorLocked()
    {
        executeMoves("n");
        assertEquals(Message.openLocked("front-door"), message);
    }
    
    @Test
    public void testEnhancedGoDoorUnLocked()
    {
        game.player().getRoom().getObject("front-door").removeProperty("locked");
        executeMoves("n");
        assertEquals("You solved the mystery!", message);
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
