package river;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import river.GameEngine.Item;
import river.GameEngine.Location;

public class GameEngineTest {

    GameEngine engine;
    
    @Before
    public void setUp() throws Exception {
        engine = new GameEngine();
    }

    @Test
    public void testObjectCallThroughs() {
        Assert.assertEquals("Farmer", engine.getName(Item.PLAYER));
        Assert.assertEquals(Location.START, engine.getLocation(Item.PLAYER));
        Assert.assertEquals("", engine.getSound(Item.PLAYER));
        
        Assert.assertEquals("Wolf", engine.getName(Item.TOP));
        Assert.assertEquals(Location.START, engine.getLocation(Item.TOP));
        Assert.assertEquals("Howl", engine.getSound(Item.TOP));
        
        Assert.assertEquals("Goose", engine.getName(Item.MID));
        Assert.assertEquals(Location.START, engine.getLocation(Item.MID));
        Assert.assertEquals("Honk", engine.getSound(Item.MID));
        
        Assert.assertEquals("Beans", engine.getName(Item.BOTTOM));
        Assert.assertEquals(Location.START, engine.getLocation(Item.BOTTOM));
        Assert.assertEquals("", engine.getSound(Item.BOTTOM));
    }

    @Test
    public void testMidTransport() {
        Assert.assertEquals(Location.START, engine.getLocation(Item.MID));
        engine.loadBoat(Item.MID);
        engine.rowBoat();
        engine.unloadBoat(Item.MID);
        Assert.assertEquals(Location.FINISH, engine.getLocation(Item.MID));
    }

    @Test
    public void testWinningGame() {

        // transport the goose        
        engine.loadBoat(Item.PLAYER);
        transport(Item.MID);
        Assert.assertFalse(engine.gameIsLost());
        Assert.assertFalse(engine.gameIsWon());

        // go back alone
        goBackAlone();
        Assert.assertFalse(engine.gameIsLost());
        Assert.assertFalse(engine.gameIsWon());

        // transport the wolf
        transport(Item.TOP);
        Assert.assertFalse(engine.gameIsLost());
        Assert.assertFalse(engine.gameIsWon());  
        
        // transport back the goose
        transport(Item.MID);
        Assert.assertFalse(engine.gameIsLost());
        Assert.assertFalse(engine.gameIsWon());
        
        // transport the beans
        transport(Item.BOTTOM);
        Assert.assertFalse(engine.gameIsLost());
        Assert.assertFalse(engine.gameIsWon());  
        
        // go back alone
        goBackAlone();
        Assert.assertFalse(engine.gameIsLost());
        Assert.assertFalse(engine.gameIsWon());
        
        // transport the goose and farmer
        transport(Item.MID);
        engine.unloadBoat(Item.PLAYER);
        
        // game won
        Assert.assertFalse(engine.gameIsLost());
        Assert.assertTrue(engine.gameIsWon());
    }

    @Test
    public void testLosingGame() {

        // transport the goose
        transport(Item.MID);
        Assert.assertFalse(engine.gameIsLost());
        Assert.assertFalse(engine.gameIsWon());
        
        // go back alone
        goBackAlone();
        Assert.assertFalse(engine.gameIsLost());
        Assert.assertFalse(engine.gameIsWon());
        
        // transport the wolf
        transport(Item.TOP);
        Assert.assertFalse(engine.gameIsLost());
        Assert.assertFalse(engine.gameIsWon());  

        // go back alone
        goBackAlone();
        Assert.assertTrue(engine.gameIsLost());
        Assert.assertFalse(engine.gameIsWon());
    }

    @Test
    public void testError() {

        // transport the goose
        transport(Item.MID);
        Assert.assertFalse(engine.gameIsLost());
        Assert.assertFalse(engine.gameIsWon());

        // save the state
        GameEngine.Location topLoc = engine.getLocation(Item.TOP);
        GameEngine.Location midLoc = engine.getLocation(Item.MID);
        GameEngine.Location bottomLoc = engine.getLocation(Item.BOTTOM);
        GameEngine.Location playerLoc = engine.getLocation(Item.PLAYER);

        // This action should do nothing since the wolf is not on the same shore as the
        // boat
        engine.loadBoat(Item.TOP);

        // check that the state has not changed
        Assert.assertEquals(topLoc, engine.getLocation(Item.TOP));
        Assert.assertEquals(midLoc, engine.getLocation(Item.MID));
        Assert.assertEquals(bottomLoc, engine.getLocation(Item.BOTTOM));
        Assert.assertEquals(playerLoc, engine.getLocation(Item.PLAYER));
    }
    
    private void transport(Item item) {
        engine.loadBoat(item);
        engine.rowBoat();
        engine.unloadBoat(item);
    }
    
    private void goBackAlone() {
        engine.rowBoat();
    }
}
