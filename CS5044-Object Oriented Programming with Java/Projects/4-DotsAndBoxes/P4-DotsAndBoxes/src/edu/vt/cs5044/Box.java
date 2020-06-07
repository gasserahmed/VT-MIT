package edu.vt.cs5044;

import java.util.Collection;
import java.util.HashSet;

/**
 * 
 * This class has private instances and public methods for the DABGame class
 *
 * @author gasser18
 * @version Oct 24, 2019
 *
 */
public class Box
{
    private Player owner;
    private Collection<Direction> drawnEdges;
    
    /**
     * Create a new Box object.
     *
     */
    public Box() 
    {
        owner = null;
        drawnEdges = new HashSet<>();
    }

    /**
     * Gets the current owner of the box
     *
     * @return the owner
     */
    public Player getOwner()
    {
        return owner;
    }

    /**
     * Gets drawn edges of the current box object
     *
     * @return the drawnEdges
     */
    public Collection<Direction> getDrawnEdges()
    {
        return new HashSet<>(drawnEdges);
    }
    
    /**
     * Add edge to the current box using direction and current player to own the box if completed
     *
     * @param dir the edge direction
     * @param currentPlayer the current player in turn
     * @return true if edge's ownership changed and false otherwise
     */
    public boolean addEdge(Direction dir, Player currentPlayer) 
    {
        drawnEdges.add(dir);
        if (drawnEdges.size() == 4) 
        {
            owner = currentPlayer;
            return true;
        }
        
        return false;
    }
    
    /**
     * Checks if box has the given edge
     *
     * @param dir the given edge/direction
     * @return true if box has the given edge and false otherwise
     */
    public boolean hasEdge(Direction dir) 
    {
        return drawnEdges.contains(dir);
    }
    
    /**
     * Checks if box has an owner
     *
     * @return true if box has an owner and false otherwise
     */
    public boolean hasOwner() 
    {
        return owner != null;
    }
}
