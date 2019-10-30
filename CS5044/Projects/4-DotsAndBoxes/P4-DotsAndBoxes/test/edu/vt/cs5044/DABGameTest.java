package edu.vt.cs5044;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.Collection;
import java.util.HashSet;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

@SuppressWarnings("javadoc")
public class DABGameTest
{
    private DotsAndBoxes game;

    @Before
    public void setup()
    {
        game = new DABGame();
    }

    @Test(expected = GameException.class)
    public void testGetSizeNotInit()
    {
        game.getSize();
    }

    @Test
    public void testGetSizeNotInitTryCatch()
    {
        try
        {
            game.getSize();
            fail("No exception thrown; expected GameException");
        }
        catch (GameException ge)
        {
            // PASS!
        }
    }

    @Test(expected = GameException.class)
    public void testGetScoreNotInit()
    {
        game.getScores();
    }

    @Test(expected = GameException.class)
    public void testNullDirection()
    {
        game.init(2);
        game.drawEdge(new Coordinate(0, 0), null);
    }

    @Test(expected = GameException.class)
    public void testInitBadSize()
    {
        game.init(1);
    }

    @Test
    public void testInitGoodSize()
    {
        game.init(2);
        assertEquals(2, game.getSize());
    }

    @Test
    public void testEmptyBoxGetEdges()
    {
        game.init(3);

        assertTrue(game.getDrawnEdgesAt(new Coordinate(0, 0)).isEmpty());
    }

    @Test
    public void testBoxOneEdgeGetEdges()
    {
        game.init(3);

        game.drawEdge(new Coordinate(0, 0), Direction.LEFT);

        assertEquals(1, game.getDrawnEdgesAt(new Coordinate(0, 0)).size());
        assertTrue(game.getDrawnEdgesAt(new Coordinate(0, 0)).contains(Direction.LEFT));
    }

    @Test
    public void testBoxOneEdgeGetEdgesAlt()
    {
        game.init(3);

        game.drawEdge(new Coordinate(0, 0), Direction.LEFT);

        Collection<Direction> expectedEdges = new HashSet<>();
        expectedEdges.add(Direction.LEFT);

        assertEquals(expectedEdges, game.getDrawnEdgesAt(new Coordinate(0, 0)));
    }

    @Test
    public void testBoxGotCompleted()
    {
        Coordinate coord = new Coordinate(0, 0);
        game.init(2);

        game.drawEdge(coord, Direction.LEFT);
        game.drawEdge(coord, Direction.TOP);
        game.drawEdge(coord, Direction.RIGHT);
        game.drawEdge(coord, Direction.BOTTOM);

        Collection<Direction> expectedEdges = new HashSet<>();
        expectedEdges.add(Direction.LEFT);
        expectedEdges.add(Direction.RIGHT);
        expectedEdges.add(Direction.TOP);
        expectedEdges.add(Direction.BOTTOM);
        assertEquals(Player.TWO, game.getOwnerAt(coord));
        assertEquals(expectedEdges, game.getDrawnEdgesAt(new Coordinate(0, 0)));
    }

    @Test
    public void testBoxTwoEdgeGetEdgesAlt()
    {
        game.init(3);

        game.drawEdge(new Coordinate(0, 0), Direction.LEFT);
        game.drawEdge(new Coordinate(0, 0), Direction.RIGHT);

        Collection<Direction> expectedEdges = new HashSet<>();
        expectedEdges.add(Direction.LEFT);
        expectedEdges.add(Direction.RIGHT);

        assertEquals(expectedEdges, game.getDrawnEdgesAt(new Coordinate(0, 0)));
    }

    @Test(expected = GameException.class)
    public void testBadCoordGetEdges()
    {
        game.init(2);

        game.getDrawnEdgesAt(new Coordinate(-1, 2));
    }

    @Test
    public void testInitScores()
    {
        game.init(2);

        Map<Player, Integer> actualScoreMap = game.getScores();

        assertEquals(0, (int) actualScoreMap.get(Player.ONE));
        assertEquals(0, (int) actualScoreMap.get(Player.TWO));

        Map<Player, Integer> expectedScoreMap = game.getScores();
        expectedScoreMap.put(Player.ONE, 0);
        expectedScoreMap.put(Player.TWO, 0);
        assertEquals(expectedScoreMap, actualScoreMap);
    }

    @Test
    public void testGameScores()
    {
        game.init(2);
        Coordinate box01 = new Coordinate(0, 1);
        Coordinate box10 = new Coordinate(1, 0);
        game.drawEdge(box01, Direction.RIGHT);
        game.drawEdge(box01, Direction.TOP);
        game.drawEdge(box01, Direction.LEFT);
        game.drawEdge(box10, Direction.BOTTOM);
        game.drawEdge(box01, Direction.BOTTOM);

        Map<Player, Integer> actualScoreMap = game.getScores();
        assertEquals(1, (int) actualScoreMap.get(Player.ONE));
        assertEquals(0, (int) actualScoreMap.get(Player.TWO));

        Map<Player, Integer> expectedScoreMap = game.getScores();
        expectedScoreMap.put(Player.ONE, 1);
        expectedScoreMap.put(Player.TWO, 0);

        assertEquals(expectedScoreMap, actualScoreMap);
    }

    @Test
    public void testCurrentPlayer()
    {
        testBoxGotCompleted();
        assertEquals(Player.TWO, game.getCurrentPlayer());
    }

    @Test
    public void testGameIsOver()
    {
        game.init(2);
        Coordinate box10 = new Coordinate(1, 0);
        game.drawEdge(box10, Direction.LEFT);
        game.drawEdge(box10, Direction.RIGHT);
        game.drawEdge(box10, Direction.TOP);
        game.drawEdge(box10, Direction.BOTTOM);

        Coordinate box00 = new Coordinate(0, 0);
        game.drawEdge(box00, Direction.LEFT);
        game.drawEdge(box00, Direction.TOP);
        game.drawEdge(box00, Direction.BOTTOM);

        Coordinate box01 = new Coordinate(0, 1);
        game.drawEdge(box01, Direction.LEFT);
        game.drawEdge(box01, Direction.BOTTOM);

        Coordinate box11 = new Coordinate(1, 1);
        game.drawEdge(box11, Direction.LEFT);
        game.drawEdge(box11, Direction.RIGHT);
        game.drawEdge(box11, Direction.BOTTOM);

        game.drawEdge(box10, Direction.BOTTOM);

        assertEquals(null, game.getCurrentPlayer());
    }
}
