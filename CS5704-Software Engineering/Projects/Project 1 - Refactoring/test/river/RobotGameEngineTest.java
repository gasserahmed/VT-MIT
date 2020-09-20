package river;

import java.awt.Color;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class RobotGameEngineTest {

    GameEngine engine;
    
    private final static Item SMALLBOT_2 = Item.ITEM_0;
    private final static Item SMALLBOT_1 = Item.ITEM_1;
    private final static Item TALLBOT_1  = Item.ITEM_2;
    private final static Item TALLBOT_2  = Item.ITEM_3;
    
    @Before
    public void setUp() throws Exception {
        engine = new RobotGameEngine();
    }

    @Test
    public void testObjectCallThroughs() {
        Assert.assertEquals("T2", engine.getItemLabel(TALLBOT_2));
        Assert.assertEquals(Location.START, engine.getItemLocation(TALLBOT_2));
        Assert.assertEquals(Color.GREEN, engine.getItemColor(TALLBOT_2));
        
        Assert.assertEquals("T1", engine.getItemLabel(TALLBOT_1));
        Assert.assertEquals(Location.START, engine.getItemLocation(TALLBOT_1));
        Assert.assertEquals(Color.GREEN, engine.getItemColor(TALLBOT_1));
        
        Assert.assertEquals("S1", engine.getItemLabel(SMALLBOT_1));
        Assert.assertEquals(Location.START, engine.getItemLocation(SMALLBOT_1));
        Assert.assertEquals(Color.CYAN, engine.getItemColor(SMALLBOT_1));
        
        Assert.assertEquals("S2", engine.getItemLabel(SMALLBOT_2));
        Assert.assertEquals(Location.START, engine.getItemLocation(SMALLBOT_2));
        Assert.assertEquals(Color.CYAN, engine.getItemColor(SMALLBOT_2));
        
        Assert.assertEquals(Location.START, engine.getBoatLocation());
        Assert.assertEquals(Color.ORANGE, engine.getBoatColor());
    }
    
    @Test
    public void testPassengerSeatsAreEmptyAtGameStart() {
        Assert.assertEquals(null, engine.getPassenger1());
        Assert.assertEquals(null, engine.getPassenger2());
    }

    @Test
    public void testMidTransport() {
        Assert.assertEquals(Location.START, engine.getItemLocation(SMALLBOT_1));
        engine.loadBoat(SMALLBOT_1);
        engine.rowBoat();
        engine.unloadBoat(SMALLBOT_1);
        Assert.assertEquals(Location.FINISH, engine.getItemLocation(SMALLBOT_1));
    }

    @Test
    public void testWinningGame() {

        // transport SMALLBOT_1 and SMALLBOT_2 but keep SMALLBOT_1 on the boat   
        engine.loadBoat(SMALLBOT_1);
        engine.loadBoat(SMALLBOT_2);
        engine.rowBoat();
        engine.unloadBoat(SMALLBOT_2);
        Assert.assertFalse(engine.gameIsLost());
        Assert.assertFalse(engine.gameIsWon());

        // transport the SMALLBOT_1
        transport(SMALLBOT_1);
        Assert.assertFalse(engine.gameIsLost());
        Assert.assertFalse(engine.gameIsWon());  
        
        // transport TALLBOT_1 and SMALL_BOT2
        transportTallBot(TALLBOT_1);
        
        // transport SMALLBOT_1
        transport(SMALLBOT_1);
        Assert.assertFalse(engine.gameIsLost());
        Assert.assertFalse(engine.gameIsWon());  
        
        // transport TALLBOT_2 and SMALL_BOT2
        transportTallBot(TALLBOT_2);
        Assert.assertFalse(engine.gameIsLost());
        Assert.assertFalse(engine.gameIsWon());        

        // Unload SMALLBOT_1
        engine.unloadBoat(SMALLBOT_1);
                
        // game won
        Assert.assertFalse(engine.gameIsLost());
        Assert.assertTrue(engine.gameIsWon());
    }

    @Test
    public void testWinningGameWhenSmallBot2NotOnFinish() {
        testWinningGame();
        engine.loadBoat(SMALLBOT_2);
        Assert.assertFalse(engine.gameIsWon());
    }
    
    @Test
    public void testWinningGameWhenTallBot2NotOnFinish() {
        testWinningGame();
        engine.loadBoat(TALLBOT_2);
        Assert.assertFalse(engine.gameIsWon());
    }
    
    @Test
    public void testError() {

        // transport the SMALLBOT_1
        engine.loadBoat(TALLBOT_2);
        transport(SMALLBOT_1);
        Assert.assertFalse(engine.gameIsLost());
        Assert.assertFalse(engine.gameIsWon());

        // save the state
        Location topLoc = engine.getItemLocation(TALLBOT_1);
        Location midLoc = engine.getItemLocation(SMALLBOT_1);
        Location bottomLoc = engine.getItemLocation(SMALLBOT_2);
        Location playerLoc = engine.getItemLocation(TALLBOT_2);

        // This action should do nothing since the TALLBOT_1 is not on the same shore as the
        // boat
        engine.loadBoat(TALLBOT_1);
        
        // check that the state has not changed
        Assert.assertEquals(topLoc, engine.getItemLocation(TALLBOT_1));
        Assert.assertEquals(midLoc, engine.getItemLocation(SMALLBOT_1));
        Assert.assertEquals(bottomLoc, engine.getItemLocation(SMALLBOT_2));
        Assert.assertEquals(playerLoc, engine.getItemLocation(TALLBOT_2));
    }
    
    @Test
    public void testRowBoatWithNoObjects() {
        engine.rowBoat();
        Assert.assertEquals(Location.START, engine.getBoatLocation());
    }
    
    @Test
    public void testLoadBoatWithMoreThanTwoObjects() {
        engine.loadBoat(SMALLBOT_1);
        engine.loadBoat(SMALLBOT_2);
        engine.loadBoat(TALLBOT_1);
        
        Assert.assertEquals(Location.START, engine.getItemLocation(TALLBOT_1));
    }
    
    @Test
    public void testLoadBoatWithTallBotsCantLoad() {
        engine.loadBoat(SMALLBOT_1);
        engine.loadBoat(TALLBOT_1);
        
        Assert.assertEquals(Location.START, engine.getItemLocation(TALLBOT_1));
    }
    
    @Test
    public void testLoadBoatWithSMALLBOT1WhileTallBot1OnBoat() {
        engine.loadBoat(TALLBOT_1);
        engine.loadBoat(SMALLBOT_1);
        
        Assert.assertEquals(Location.START, engine.getItemLocation(SMALLBOT_1));
    }
    
    @Test
    public void testResettingGame() {
        testWinningGame();
        engine.resetGame();
        Assert.assertEquals(Location.START, engine.getItemLocation(TALLBOT_1));
    }
    
    private void transport(Item item) {
        engine.loadBoat(item);
        engine.rowBoat();
        engine.unloadBoat(item);
    }
    
    private void transportTallBot(Item item) {
        // transport the TALLBOT
        transport(item);
        Assert.assertFalse(engine.gameIsLost());
        Assert.assertFalse(engine.gameIsWon()); 
        
        // Go back with SMALLBOT_2
        engine.loadBoat(SMALLBOT_2);
        engine.rowBoat();
        Assert.assertFalse(engine.gameIsLost());
        Assert.assertFalse(engine.gameIsWon());  
        
        // Pickup SMALLBOT_1 and Transport SMALLBOT_2
        engine.loadBoat(SMALLBOT_1);
        engine.rowBoat();
        engine.unloadBoat(SMALLBOT_2);
        Assert.assertFalse(engine.gameIsLost());
        Assert.assertFalse(engine.gameIsWon());
    }
}
