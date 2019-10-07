package edu.vt.cs5044;

import edu.vt.cs5044.tetris.AI;
import edu.vt.cs5044.tetris.Board;
import edu.vt.cs5044.tetris.Placement;
import edu.vt.cs5044.tetris.Rotation;
import edu.vt.cs5044.tetris.Shape;

/**
 * 
 * This class has the constructor, private instances, helpers, and public methods
 * that for AI interface implementation
 *
 * @author gasser18
 * @version Oct 7, 2019
 *
 */
public class TetrisAI implements AI
{

    @Override
    public Placement findBestPlacement(Board currentBoard, Shape shape)
    {
        int overallCost = Integer.MAX_VALUE;
        int tempCost = 0;
        Placement tempPlacement;
        Placement bestPlacement = new Placement(Rotation.NONE, 0);
        for (int col = 0; col < currentBoard.WIDTH; col++)
        {
            for (Rotation rotation : shape.getRotationSet())
            {
                int availableWidth = currentBoard.WIDTH - col;
                if (shape.getWidth(rotation) <= availableWidth)
                {
                    tempPlacement = new Placement(rotation, col);
                    Board tempBoard = currentBoard.getResultBoard(shape, tempPlacement);
                    tempCost = 6 * getAverageColumnHeight(tempBoard)
                            + 3 * getColumnHeightRange(tempBoard)
                            + 0 * getColumnHeightVariance(tempBoard)
                            + 9 * getTotalGapCount(tempBoard);
                    if (tempCost < overallCost)
                    {
                        overallCost = tempCost;
                        bestPlacement = tempPlacement;
                    }
                }
            }
        }
        return bestPlacement;
    }

    @Override
    public int getAverageColumnHeight(Board board)
    {
        int heightSum = 0;
        for (int col = 0; col < board.WIDTH; col++)
        {
            heightSum += getColumnHeight(board, col);
        }

        return heightSum / board.WIDTH;
    }

    @Override
    public int getColumnHeightRange(Board board)
    {
        int tallestHeight = Integer.MIN_VALUE;
        int shortestHeight = Integer.MAX_VALUE;
        for (int col = 0; col < board.WIDTH; col++)
        {
            tallestHeight = Math.max(tallestHeight, getColumnHeight(board, col));
            shortestHeight = Math.min(shortestHeight, getColumnHeight(board, col));
        }

        return tallestHeight - shortestHeight;
    }

    @Override
    public int getColumnHeightVariance(Board board)
    {
        int heightVariance = 0;
        for (int col = 0; col < board.WIDTH; col++)
        {
            if (col + 1 < board.WIDTH)
            {
                int currentColHeight = getColumnHeight(board, col);
                int nextColHeight = getColumnHeight(board, col + 1);
                heightVariance += Math.abs(currentColHeight - nextColHeight);
            }
        }

        return heightVariance;
    }

    @Override
    public int getTotalGapCount(Board board)
    {
        int totalGaps = 0;
        for (int col = 0; col < board.WIDTH; col++)
        {
            totalGaps += getColumnHeight(board, col) - getColumnBlockCount(board, col);
        }

        return totalGaps;
    }

    /**
     * Get the height of the current column.
     *
     * @param board current game board
     * @param col requested column
     * @return the requested column's height
     */
    private int getColumnHeight(Board board, int col)
    {
        boolean[] colVals = board.getColumn(col);
        for (int row = board.HEIGHT - 1; row >= 0; row--)
        {
            if (colVals[row])
            {
                return row + 1;
            }
        }

        return 0;
    }

    /**
     * Get the block count of the current column.
     *
     * @param board current game board
     * @param col requested column
     * @return the requested columns's block count
     */
    private int getColumnBlockCount(Board board, int col)
    {
        int blockCount = 0;
        boolean[] colVals = board.getColumn(col);
        for (int row = 0; row < board.HEIGHT; row++)
        {
            if (colVals[row])
            {
                blockCount++;
            }
        }

        return blockCount;
    }

}
