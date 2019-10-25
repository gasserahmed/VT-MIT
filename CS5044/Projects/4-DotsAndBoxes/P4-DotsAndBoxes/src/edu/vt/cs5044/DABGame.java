package edu.vt.cs5044;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * 
 * This class has private instances, helpers, and public methods for the DotsAndBoxes interface
 * implementation
 *
 * @author gasser18
 * @version Oct 24, 2019
 *
 */
public class DABGame implements DotsAndBoxes
{
    private Map<Coordinate, Box> boxGrid;
    private Player currentPlayer;
    private int gridSize;

    /**
     * Create a new DABGame object.
     *
     */
    public DABGame()
    {
        gridSize = Integer.MIN_VALUE;
        boxGrid = null;
    }

    @Override
    public boolean drawEdge(Coordinate coord, Direction dir)
    {
        checkInit();
        if (dir == null)
        {
            throw new GameException();
        }

        boolean boxOwnershipChanged = false;
        boolean adjBoxOwnershipChanged = false;

        Box box = findBox(coord);
        if (box.hasEdge(dir))
        {
            return false;
        }

        boxOwnershipChanged = box.addEdge(dir, currentPlayer);
        boxGrid.put(coord, box);

        try
        {
            Box adjBox = null;
            Coordinate adjCoord = null;
            if (dir == Direction.LEFT && coord.getX() > 0)
            {
                adjCoord = coord.getNeighbor(dir);
                adjBox = findBox(adjCoord);
                adjBoxOwnershipChanged = adjBox.addEdge(Direction.RIGHT, currentPlayer);
                boxGrid.put(adjCoord, adjBox);
            }
            else if (dir == Direction.RIGHT)
            {
                adjCoord = coord.getNeighbor(dir);
                adjBox = findBox(adjCoord);
                adjBoxOwnershipChanged = adjBox.addEdge(Direction.LEFT, currentPlayer);
                boxGrid.put(adjCoord, adjBox);
            }
            else if (dir == Direction.TOP && coord.getY() > 0)
            {
                adjCoord = coord.getNeighbor(dir);
                adjBox = findBox(adjCoord);
                adjBoxOwnershipChanged = adjBox.addEdge(Direction.BOTTOM, currentPlayer);
                boxGrid.put(adjCoord, adjBox);
            }
            else if (dir == Direction.BOTTOM)
            {
                adjCoord = coord.getNeighbor(dir);
                adjBox = findBox(adjCoord);
                adjBoxOwnershipChanged = adjBox.addEdge(Direction.TOP, currentPlayer);
                boxGrid.put(adjCoord, adjBox);
            }
        }
        catch (GameException ge)
        {
            // neighbor doesn't exist for that direction
        }

        if (gameIsOver())
        {
            currentPlayer = null;
        }
        else if (!(boxOwnershipChanged || adjBoxOwnershipChanged))
        {
            if (currentPlayer == Player.ONE)
            {
                currentPlayer = Player.TWO;
            }
            else
            {
                currentPlayer = Player.ONE;
            }
        }

        return true;
    }

    @Override
    public Player getCurrentPlayer()
    {
        checkInit();

        return currentPlayer;
    }

    @Override
    public Collection<Direction> getDrawnEdgesAt(Coordinate coord)
    {
        checkInit();
        Box box = findBox(coord);
        return box.getDrawnEdges();
    }

    @Override
    public Player getOwnerAt(Coordinate coord)
    {
        checkInit();
        Box box = findBox(coord);
        return box.getOwner();
    }

    @Override
    public Map<Player, Integer> getScores()
    {
        checkInit();
        Map<Player, Integer> scoreMap = new HashMap<>();
        int playerOneScore = 0;
        int playerTwoScore = 0;

        scoreMap.put(Player.ONE, playerOneScore);
        scoreMap.put(Player.TWO, playerTwoScore);

        for (Box box : boxGrid.values())
        {
            if (box.hasOwner())
            {
                if (box.getOwner().equals(Player.ONE))
                {
                    scoreMap.put(Player.ONE, ++playerOneScore);
                }
                else
                {
                    scoreMap.put(Player.TWO, ++playerTwoScore);
                }
            }
        }

        return scoreMap;
    }

    @Override
    public int getSize()
    {
        checkInit();
        return gridSize;
    }

    @Override
    public void init(int size)
    {
        if (size < 2)
        {
            throw new GameException();
        }

        currentPlayer = Player.ONE;
        gridSize = size;

        boxGrid = new HashMap<>();
        for (int x = 0; x < gridSize; x++)
        {
            for (int y = 0; y < gridSize; y++)
            {
                Coordinate coord = new Coordinate(x, y);
                Box box = new Box();
                boxGrid.put(coord, box);
            }
        }
    }

    /**
     * Search for a specific box using coordinate
     * 
     * @param coord requested box coordinate
     * @return the requested box if existed or null if not
     */
    private Box findBox(Coordinate coord)
    {
        Box box = boxGrid.get(coord);
        if (box == null)
        {
            throw new GameException();
        }
        return box;
    }

    /**
     * Check if grid is initialized
     * 
     */
    private void checkInit()
    {
        if (gridSize == Integer.MIN_VALUE)
        {
            throw new GameException();
        }
    }

    /**
     * Check if game is over if the total score equals the total number of grid boxes
     * 
     * @return true if game is over and false otherwise
     */
    private boolean gameIsOver()
    {
        int totalScore = 0;
        for (int score : getScores().values())
        {
            totalScore += score;
        }

        return totalScore == (gridSize * gridSize);
    }
}
