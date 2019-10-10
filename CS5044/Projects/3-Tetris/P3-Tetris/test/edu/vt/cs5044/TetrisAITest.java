package edu.vt.cs5044;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import edu.vt.cs5044.tetris.AI;
import edu.vt.cs5044.tetris.Board;
import edu.vt.cs5044.tetris.Placement;
import edu.vt.cs5044.tetris.Rotation;
import edu.vt.cs5044.tetris.Shape;

/**
 * 
 * JUnit test class for the TetrisAI.
 *
 * @author gasser18
 * @version Oct 7, 2019
 *
 */
public class TetrisAITest
{
    private AI brain;
    private Board emptyBoard;
    private Board largeApIBoard;
    private Board noneOApiBoard;
    private Board ninetyJApiBoard;
    private Board oneEightyTApiBoard;
    private Board twoSeventyLApiBoard;

    /**
     * Create default instance of TetrisAI and different instances of board.
     *
     */
    @Before
    public void setUp()
    {
        brain = new TetrisAI();
        emptyBoard = new Board();
        largeApIBoard = new Board(
                "## ##    #",
                "# ##### ##",
                "#### #####",
                "# ##### ##",
                "## #######",
                "######### ",
                " #########",
                " #########",
                "###  #####",
                "####### ##",
                "######## #",
                " #### ####");
        
        noneOApiBoard = new Board(
                "###  #####",
                "###  #####");
        
        ninetyJApiBoard = new Board(
                "##   #####",
                "#### #####");
        
        oneEightyTApiBoard = new Board(
                "#######   ",
                "######## #");
        
        twoSeventyLApiBoard = new Board(
                "   #######",
                " #########",
                "##########");
    }

    /**
     * Testing an empty board.
     *
     */
    @Test
    public void testEmptyBoard()
    {
        assertEquals(0, brain.getAverageColumnHeight(emptyBoard));
        assertEquals(0, brain.getColumnHeightRange(emptyBoard));
        assertEquals(0, brain.getColumnHeightVariance(emptyBoard));
        assertEquals(0, brain.getTotalGapCount(emptyBoard));
    }

    /**
     * Testing a large API board.
     *
     */
    @Test
    public void testLargeApiBoard()
    {
        assertEquals(11, brain.getAverageColumnHeight(largeApIBoard));
        assertEquals(2, brain.getColumnHeightRange(largeApIBoard));
        assertEquals(6, brain.getColumnHeightVariance(largeApIBoard));
        assertEquals(14, brain.getTotalGapCount(largeApIBoard));
        assertEquals(new Placement(Rotation.CCW_90, 5),
                brain.findBestPlacement(largeApIBoard, Shape.J));
    }

    /**
     * Test getting a best placement by adding a none rotated O into 3rd column.
     *
     */
    @Test
    public void testNoneOThirdColApiBoard()
    {
        assertEquals(1, brain.getAverageColumnHeight(noneOApiBoard));
        assertEquals(2, brain.getColumnHeightRange(noneOApiBoard));
        assertEquals(4, brain.getColumnHeightVariance(noneOApiBoard));
        assertEquals(0, brain.getTotalGapCount(noneOApiBoard));
        assertEquals(new Placement(Rotation.NONE, 3),
                brain.findBestPlacement(noneOApiBoard, Shape.O));
    }

    /**
     * Test getting a best placement by adding a 90 rotated J into 2nd column.
     *
     */
    @Test
    public void testNinetyJSecondColApiBoard()
    {
        assertEquals(1, brain.getAverageColumnHeight(ninetyJApiBoard));
        assertEquals(2, brain.getColumnHeightRange(ninetyJApiBoard));
        assertEquals(4, brain.getColumnHeightVariance(ninetyJApiBoard));
        assertEquals(0, brain.getTotalGapCount(ninetyJApiBoard));
        assertEquals(new Placement(Rotation.CCW_90, 2),
                brain.findBestPlacement(ninetyJApiBoard, Shape.J));
    }

    /**
     * Test getting a best placement by adding a 180 rotated T into 7th column
     *
     */
    @Test
    public void testOneEightyTSeventhColApiBoard()
    {
        assertEquals(1, brain.getAverageColumnHeight(oneEightyTApiBoard));
        assertEquals(2, brain.getColumnHeightRange(oneEightyTApiBoard));
        assertEquals(3, brain.getColumnHeightVariance(oneEightyTApiBoard));
        assertEquals(0, brain.getTotalGapCount(oneEightyTApiBoard));
        assertEquals(new Placement(Rotation.CCW_180, 7),
                brain.findBestPlacement(oneEightyTApiBoard, Shape.T));
    }

    /**
     * Test getting a best placement by adding a 270 rotated L into 0th column
     *
     */
    @Test
    public void testTwoSeventyLZerothColApiBoard()
    {
        assertEquals(2, brain.getAverageColumnHeight(twoSeventyLApiBoard));
        assertEquals(2, brain.getColumnHeightRange(twoSeventyLApiBoard));
        assertEquals(2, brain.getColumnHeightVariance(twoSeventyLApiBoard));
        assertEquals(0, brain.getTotalGapCount(twoSeventyLApiBoard));
        assertEquals(new Placement(Rotation.CCW_270, 0),
                brain.findBestPlacement(twoSeventyLApiBoard, Shape.L));
    }
}
