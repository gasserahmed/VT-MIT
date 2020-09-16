package river;

import java.awt.Color;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class FarmerGameEngineTest {

    GameEngine engine;
    
    private final static Item BEANS = Item.ITEM_0;
    private final static Item GOOSE = Item.ITEM_1;
    private final static Item WOLF  = Item.ITEM_2;
    private final static Item FARMER  = Item.ITEM_3;
    
    @Before
    public void setUp() throws Exception {
        engine = new FarmerGameEngine();
    }

    @Test
    public void testObjectCallThroughs() {
        Assert.assertEquals("", engine.getItemLabel(FARMER));
        Assert.assertEquals(Location.START, engine.getItemLocation(FARMER));
        Assert.assertEquals(Color.MAGENTA, engine.getItemColor(FARMER));
        
        Assert.assertEquals("W", engine.getItemLabel(WOLF));
        Assert.assertEquals(Location.START, engine.getItemLocation(WOLF));
        Assert.assertEquals(Color.CYAN, engine.getItemColor(WOLF));
        
        Assert.assertEquals("G", engine.getItemLabel(GOOSE));
        Assert.assertEquals(Location.START, engine.getItemLocation(GOOSE));
        Assert.assertEquals(Color.CYAN, engine.getItemColor(GOOSE));
        
        Assert.assertEquals("B", engine.getItemLabel(BEANS));
        Assert.assertEquals(Location.START, engine.getItemLocation(BEANS));
        Assert.assertEquals(Color.CYAN, engine.getItemColor(BEANS));
        
        Assert.assertEquals(Location.START, engine.getBoatLocation());
        Assert.assertEquals(Color.ORANGE, engine.getBoatColor());
    }

    @Test
    public void testMidTransport() {
        Assert.assertEquals(Location.START, engine.getItemLocation(GOOSE));
        engine.loadBoat(FARMER);
        engine.loadBoat(GOOSE);
        engine.rowBoat();
        engine.unloadBoat(GOOSE);
        Assert.assertEquals(Location.FINISH, engine.getItemLocation(GOOSE));
    }

    @Test
    public void testWinningGame() {

        // transport the goose        
        engine.loadBoat(FARMER);
        transport(GOOSE);
        Assert.assertFalse(engine.gameIsLost());
        Assert.assertFalse(engine.gameIsWon());

        // go back alone
        goBackAlone();
        Assert.assertFalse(engine.gameIsLost());
        Assert.assertFalse(engine.gameIsWon());

        // transport the wolf
        transport(WOLF);
        Assert.assertFalse(engine.gameIsLost());
        Assert.assertFalse(engine.gameIsWon());  
        
        // transport back the goose
        transport(GOOSE);
        Assert.assertFalse(engine.gameIsLost());
        Assert.assertFalse(engine.gameIsWon());
        
        // transport the beans
        transport(BEANS);
        Assert.assertFalse(engine.gameIsLost());
        Assert.assertFalse(engine.gameIsWon());  
        
        // go back alone
        goBackAlone();
        Assert.assertFalse(engine.gameIsLost());
        Assert.assertFalse(engine.gameIsWon());
        
        // transport the goose and farmer
        transport(GOOSE);
        engine.unloadBoat(FARMER);
        
        // game won
        Assert.assertFalse(engine.gameIsLost());
        Assert.assertTrue(engine.gameIsWon());
    }
    
    @Test
    public void testLoadingObjectAfterWinningGame() {
        testWinningGame();
        engine.loadBoat(FARMER);
        Assert.assertFalse(engine.gameIsWon());
    }

    @Test
    public void testLosingGame() {
        engine.loadBoat(FARMER);
        // transport the goose
        transport(GOOSE);
        Assert.assertFalse(engine.gameIsLost());
        Assert.assertFalse(engine.gameIsWon());
        
        // go back alone
        goBackAlone();
        Assert.assertFalse(engine.gameIsLost());
        Assert.assertFalse(engine.gameIsWon());
        
        // transport the wolf
        transport(WOLF);
        Assert.assertFalse(engine.gameIsLost());
        Assert.assertFalse(engine.gameIsWon());  

        // go back alone
        goBackAlone();
        Assert.assertTrue(engine.gameIsLost());
        Assert.assertFalse(engine.gameIsWon());
    }
    
    @Test
    public void testLosingGameWhenGooseOnBoat() {
        engine.loadBoat(GOOSE);
        Assert.assertFalse(engine.gameIsLost());
    }
    
    @Test
    public void testLosingGameWhenGooseWithBeansAlone() {
        engine.loadBoat(FARMER);
        engine.loadBoat(WOLF);
        engine.rowBoat();
        Assert.assertTrue(engine.gameIsLost());
    }

    @Test
    public void testError() {

        // transport the goose
        engine.loadBoat(FARMER);
        transport(GOOSE);
        Assert.assertFalse(engine.gameIsLost());
        Assert.assertFalse(engine.gameIsWon());

        // save the state
        Location topLoc = engine.getItemLocation(WOLF);
        Location midLoc = engine.getItemLocation(GOOSE);
        Location bottomLoc = engine.getItemLocation(BEANS);
        Location playerLoc = engine.getItemLocation(FARMER);

        // This action should do nothing since the wolf is not on the same shore as the
        // boat
        engine.loadBoat(WOLF);
        
        // check that the state has not changed
        Assert.assertEquals(topLoc, engine.getItemLocation(WOLF));
        Assert.assertEquals(midLoc, engine.getItemLocation(GOOSE));
        Assert.assertEquals(bottomLoc, engine.getItemLocation(BEANS));
        Assert.assertEquals(playerLoc, engine.getItemLocation(FARMER));
    }
    
    @Test
    public void testRowBoatWithNoObjects() {
        engine.rowBoat();
        Assert.assertEquals(Location.START, engine.getBoatLocation());
    }
    
    @Test
    public void testLoadBoatWithMoreThanTwoObjects() {
        engine.loadBoat(FARMER);
        engine.loadBoat(GOOSE);
        engine.loadBoat(BEANS);
        
        Assert.assertEquals(Location.START, engine.getItemLocation(BEANS));
    }
    
    @Test
    public void testUnLoadBoatWithObjectAlreadyNotOnBoat() {
        engine.unloadBoat(FARMER);
        Assert.assertEquals(Location.START, engine.getItemLocation(FARMER));
    }
    
    @Test
    public void testResettingGame() {
        testWinningGame();
        engine.resetGame();
        Assert.assertEquals(Location.START, engine.getItemLocation(FARMER));
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
