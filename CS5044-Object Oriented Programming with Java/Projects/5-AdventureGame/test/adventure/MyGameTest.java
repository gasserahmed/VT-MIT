package adventure;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import java.io.FileInputStream;
import java.io.InputStream;
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
    public void testMain() {
        MyGame.main(null);                // exercises the main() method
        assertNotNull(new Message());     // exercises the Message constructor 
                                          // (and thus the class itself)
        assertEquals("<p>You are locked out of your house, and you need to get in.</p>" + 
                "<p>Type 'help' if you need help.</p>" 
                + "<p>Hit [return] to continue...</p>", game.welcomeMessage());
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

    @Test
    public void testExamineConcealedDriverLicense() 
    {
        testUnlockFrontDoorSuccess();
        executeMoves("n", "examine driver-license");
        assertEquals(game.player().getRoom().getName(), "your house entrance");
    }
    
    @Test
    public void testExamineConcealedCreditcardInWallet() 
    {
        testUnlockFrontDoorSuccess();
        executeMoves("n", "examine wallet");
        assertEquals(game.player().getRoom().getName(), "your house entrance");
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
    
    @Test
    public void testTakeScrewdriver() {
        executeMoves("east", "north", "take screwdriver");
        assertEquals("your garage", roomName);
        assertEquals(Message.takeSuccess("screwdriver"), message);
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
        assertEquals(game.player().getRoom().getName(), "your house entrance");
    }
    
    /*
     * Testing Search commands
     * */
    @Test
    public void testSearchWithNoSecondWord() 
    {
        executeMoves("search");
        assertEquals(Message.commandRequiresSecond("search"), message);
    }
    
    @Test
    public void testSearchObjectNotInScope() 
    {
        executeMoves("search not_here");
        assertEquals(Message.objectNotInScope("not_here"), message);
    }
    
    @Test
    public void testSearchInDark() 
    {
        executeMoves("search stone-walkway");
        assertEquals(Message.searchDark(), message);
    }
    
    @Test
    public void testSearchFlowerPotSuccess() 
    {
        testReplaceLightBulbSuccess();
        executeMoves("search flower-pot");
        assertEquals(Message.searchPotSuccess(), message);
    }
    
    @Test
    public void testSearchFlowerPotAlready() 
    {
        testSearchFlowerPotSuccess();
        executeMoves("search flower-pot");
        assertEquals(Message.searchPotAlready(), message);
    }
    
    @Test
    public void testSearchWalletSuccess() 
    {
        testUnlockFrontDoorSuccess();
        executeMoves("n", "w", "w", "search wallet");
        assertEquals(Message.searchDriverLicenseSuccess(), message);
    }
    
    @Test
    public void testSearchWalletAlready() 
    {
        testSearchWalletSuccess();
        executeMoves("search wallet");
        assertEquals(Message.searchDriverLicenseAlready(), message);
    }
    
    @Test
    public void testSearchContainerClosed() 
    {
        executeMoves("e", "n", "search shoebox");
        assertEquals(Message.searchClosed("shoebox"), message);
    }
    
    @Test
    public void testSearchContainerOpenAndEmpty() 
    {
        testTakeLightBulbSuccessfull();
        executeMoves("search shoebox");
        assertEquals(Message.searchEmpty("shoebox"), message);
    }
    
    @Test
    public void testSearchContainerOpenAndFull() 
    {
        executeMoves("e", "n", "open shoebox", "search shoebox");
        GameObject obj = game.player().getRoom().getObject("shoebox");
        Container container = (Container) obj;
        Set<String> contents = container.getObjectNames();
        assertEquals(Message.searchList(obj.getName(), contents.toArray(new String[0])), message);
    }
    
    @Test
    public void testSearchUnopenableEmptyContainer() 
    {
        executeMoves("e", "n", "search old-boxes");
        assertEquals(Message.searchEmpty("old-boxes"), message);
    }
    
    @Test
    public void testSearchItem() 
    {
        executeMoves("e", "n", "search screwdriver");
        assertEquals(Message.searchDefault(), message);
    }
    
    /*
     * Test Unlock commands
     * */
    @Test
    public void testUnlockWithNoSecondWord() 
    {
        executeMoves("unlock");
        assertEquals(Message.commandRequiresSecond("unlock"), message);
    }
    
    @Test
    public void testUnlockObjectNotInScope() 
    {
        executeMoves("unlock not_here");
        assertEquals(Message.objectNotInScope("not_here"), message);
    }
    
    @Test
    public void testUnlockObjectNotLockable() 
    {
        executeMoves("e", "n", "unlock shoebox");
        assertEquals(Message.unlockCant("shoebox"), message);
    }
    
    @Test
    public void testUnlockLockedFrontDoorWithNoKey() 
    {
        executeMoves("unlock front-door");
        assertEquals(Message.unlockNoKey("front-door", "house-key"), message);
    }
    
    @Test
    public void testUnlockFrontDoorSuccess() 
    {
        testSearchFlowerPotSuccess();
        executeMoves("unlock front-door");
        assertEquals(Message.unlockSuccess("front-door", "house-key"), message);
    }
    
    @Test
    public void testUnlockLockedFrontDoorAlready() 
    {
        testUnlockFrontDoorSuccess();
        executeMoves("unlock front-door");
        assertEquals(Message.unlockAlready("front-door"), message);
    }
    
    /*
     * Testing Hide commands
     * */
    @Test
    public void testHideWithNoSecondWord() 
    {
        executeMoves("hide");
        assertEquals(Message.commandRequiresSecond("hide"), message);
    }
    
    @Test
    public void testHideObjectNotInScope() 
    {
        executeMoves("hide not_here");
        assertEquals(Message.objectNotInScope("not_here"), message);
    }
    
    @Test
    public void testHideNotHouseKey() 
    {
        executeMoves("hide flower-pot");
        assertEquals(Message.hideCant("flower-pot"), message);
    }
    
    @Test
    public void testHideNeedsFlowerPot() 
    {
        testSearchFlowerPotSuccess();
        executeMoves("e", "hide house-key");
        assertEquals(Message.hideNeedsFlowerpot("house-key"), message);
    }
    
    @Test
    public void testHideSuccess() 
    {
        testSearchFlowerPotSuccess();
        executeMoves("hide house-key");
        assertEquals(Message.hideSuccess("house-key"), message);
    }
    
    @Test
    public void testHideAlready() 
    {
        testHideSuccess();
        executeMoves("hide house-key");
        assertEquals(Message.hideAlready("house-key"), message);
    }
    
    /*
     * Testing Close commands
     * */
    @Test
    public void testCloseWithNoSecondWord() 
    {
        executeMoves("close");
        assertEquals(Message.commandRequiresSecond("close"), message);
    }
    
    @Test
    public void testCloseObjectNotInScope() 
    {
        executeMoves("close not_here");
        assertEquals(Message.objectNotInScope("not_here"), message);
    }
    
    @Test
    public void testCloseUnopenableObject() 
    {
        executeMoves("close stone-walkway");
        assertEquals(Message.closeCant("stone-walkway"), message);
    }
    
    @Test
    public void testCloseSuccess() 
    {
        testOpenWallLanternSuccess();
        executeMoves("close wall-lantern");
        assertEquals(Message.closeSuccess("wall-lantern"), message);
    }
    
    @Test
    public void testCloseAlready() 
    {
        testCloseSuccess();
        executeMoves("close wall-lantern");
        assertEquals(Message.closeAlready("wall-lantern"), message);
    }
    
    /*
     * Testing winning after player took his driver license
     * */
    @Test
    public void testDriverLicenseFound() 
    {
        testSearchWalletSuccess();
        assertEquals(Message.searchDriverLicenseSuccess(), message);
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
