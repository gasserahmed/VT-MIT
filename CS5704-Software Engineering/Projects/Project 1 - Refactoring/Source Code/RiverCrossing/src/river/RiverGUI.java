package river;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * Graphical interface for the River application
 * 
 * @author Gregory Kulczycki
 */
public class RiverGUI extends JPanel implements MouseListener
{

    // ==========================================================
    // Fields (hotspots)
    // ==========================================================

    private Map<Item, Rectangle> itemRectangleMap;
    private Rectangle boatRectangle = new Rectangle(140, 275, 110, 50);
    private int numberOfObjectsInBoat = 0;
    private final static int leftBaseX = 80;
    private final static int leftBaseY = 275;
    private final static int leftBoatX = 140;
    private final static int leftBoatY = 275;
    private final static int rightBaseX = 730;
    private final static int rightBaseY = 275;
    private final static int rightBoatX = 550;
    private final static int rightBoatY = 275;
    private final static int[] dx =
    { 0, -60, -60, 0 };
    private final static int[] dy =
    { 0, 0, -60, -60 };
    private boolean isFirstSeatAvailable = true;
    
    private final Rectangle restartButtonRect = new Rectangle(350, 120, 100, 30);

    // ==========================================================
    // Private Fields
    // ==========================================================

    private GameEngine engine; // Model
    private boolean restart = false;

    // ==========================================================
    // Constructor
    // ==========================================================

    public RiverGUI()
    {

        engine = new FarmerGameEngine();
        resetItemRectangleMap();
        addMouseListener(this);
    }

    // ==========================================================
    // Paint Methods (View)
    // ==========================================================

    @Override
    public void paintComponent(Graphics g)
    {
        g.setColor(Color.GRAY);
        g.fillRect(0, 0, this.getWidth(), this.getHeight());
        isFirstSeatAvailable = true;
        paintItem(g, Item.ITEM_0);
        paintItem(g, Item.ITEM_1);
        paintItem(g, Item.ITEM_2);
        paintItem(g, Item.ITEM_3);
        paintBoat(g);
        
        String message = "";
        if (engine.gameIsLost())
        {
            message = "You Lost!";
            restart = true;
        }
        if (engine.gameIsWon())
        {
            message = "You Won!";
            restart = true;
        }
        paintMessage(message, g);
        if (restart)
        {
            paintRestartButton(g);
        }
    }

    private void paintItem(Graphics g, Item item)
    {
        Rectangle rect;
        // Item at start
        if (engine.getItemLocation(item) == Location.START)
        {
            rect = new Rectangle(leftBaseX + dx[item.ordinal()], leftBaseY + dy[item.ordinal()], 50,
                    50);
        }
        // Item at finish
        else if (engine.getItemLocation(item) == Location.FINISH)
        {
            rect = new Rectangle(rightBaseX + dx[item.ordinal()], rightBaseY + dy[item.ordinal()],
                    50, 50);
        }
        // Item in boat
        else
        {
            if (engine.getBoatLocation() == Location.START)
            {
                // Put item on first seat if available, otherwise, put on second seat
                
                if (isFirstSeatAvailable == true)
                {
                    rect = new Rectangle(leftBoatX, leftBoatY - 60, 50, 50);
                    isFirstSeatAvailable = false;
                }
                else
                {
                    rect = new Rectangle(leftBoatX + 60, leftBoatY - 60, 50, 50);
                }
            }
            // Boat at finish
            else
            {
                if (isFirstSeatAvailable == true)
                {
                    rect = new Rectangle(rightBoatX, rightBoatY - 60, 50, 50);
                    isFirstSeatAvailable = false;
                }
                else
                {
                    rect = new Rectangle(rightBoatX + 60, rightBoatY - 60, 50, 50);
                }
            }

        }

        itemRectangleMap.put(item, rect);
        paintRectangle(g, engine.getItemColor(item), rect);
        if (item != Item.ITEM_3)
        {
            paintStringInRectangle(engine.getItemLabel(item), rect.x, rect.y,
                    rect.width, rect.height, g);
        }
    }

    private void paintBoat(Graphics g)
    {
        if (engine.getBoatLocation() == Location.START)
        {
            boatRectangle = new Rectangle(leftBoatX, leftBoatY, 110, 50);
        }
        else
        {
            boatRectangle = new Rectangle(rightBoatX, rightBoatY, 110, 50);
        }

        paintRectangle(g, engine.getBoatColor(), boatRectangle);
    }

    private void paintRectangle(Graphics g, Color color, Rectangle rect)
    {
        g.setColor(color);
        g.fillRect(rect.x, rect.y, rect.width, rect.height);
    }

    private void resetItemRectangleMap() {
        itemRectangleMap = new HashMap<Item, Rectangle>()
        {
            {
                put(Item.ITEM_0, new Rectangle(80, 275, 50, 50));
                put(Item.ITEM_1, new Rectangle(20, 275, 50, 50));
                put(Item.ITEM_2, new Rectangle(20, 215, 50, 50));
                put(Item.ITEM_3, new Rectangle(80, 215, 50, 50));
            }
        };
    }
    
    public void paintStringInRectangle(String str, int x, int y, int width, int height, Graphics g)
    {
        g.setColor(Color.BLACK);
        int fontSize = (height >= 40) ? 36 : 18;
        g.setFont(new Font("Verdana", Font.BOLD, fontSize));
        FontMetrics fm = g.getFontMetrics();
        int strXCoord = x + width / 2 - fm.stringWidth(str) / 2;
        int strYCoord = y + height / 2 + fontSize / 2 - 4;
        g.drawString(str, strXCoord, strYCoord);
    }

    public void paintMessage(String message, Graphics g)
    {
        g.setColor(Color.BLACK);
        g.setFont(new Font("Verdana", Font.BOLD, 36));
        FontMetrics fm = g.getFontMetrics();
        int strXCoord = 400 - fm.stringWidth(message) / 2;
        int strYCoord = 100;
        g.drawString(message, strXCoord, strYCoord);
    }

    public void paintRestartButton(Graphics g)
    {
        g.setColor(Color.BLACK);
        paintBorder(restartButtonRect, 3, g);
        g.setColor(Color.PINK);
        paintRectangle(restartButtonRect, g);
        paintStringInRectangle("Restart", restartButtonRect.x, restartButtonRect.y,
                restartButtonRect.width, restartButtonRect.height, g);
    }

    /**
     * Remove this comment: this method is for restart button
     */
    public void paintBorder(Rectangle r, int thickness, Graphics g)
    {
        g.fillRect(r.x - thickness, r.y - thickness, r.width + (2 * thickness),
                r.height + (2 * thickness));
    }

    public void paintRectangle(Rectangle r, Graphics g)
    {
        g.fillRect(r.x, r.y, r.width, r.height);
    }

    // ==========================================================
    // Startup Methods
    // ==========================================================

    /**
     * Create the GUI and show it. For thread safety, this method should be invoked from the
     * event-dispatching thread.
     */
    private static void createAndShowGUI()
    {

        // Create and set up the window
        JFrame frame = new JFrame("RiverCrossing");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create and set up the content pane
        RiverGUI newContentPane = new RiverGUI();
        newContentPane.setOpaque(true);
        frame.setContentPane(newContentPane);

        // Display the window
        frame.setSize(800, 600);
        frame.setVisible(true);
    }

    public static void main(String[] args)
    {

        // Schedule a job for the event-dispatching thread:
        // creating and showing this application's GUI.
        javax.swing.SwingUtilities.invokeLater(RiverGUI::createAndShowGUI);
    }

    // ==========================================================
    // MouseListener Methods (Controller)
    // ==========================================================

    @Override
    public void mouseClicked(MouseEvent e)
    {

        if (restart)
        {
            if (this.restartButtonRect.contains(e.getPoint()))
            {
                engine.resetGame();
                restart = false;
                numberOfObjectsInBoat = 0;
                resetItemRectangleMap();
                repaint();
            }
            return;
        }

        for (Map.Entry<Item, Rectangle> itemRectangle : itemRectangleMap.entrySet())
        {
            if ((itemRectangle.getValue()).contains(e.getPoint()))
            {
                if (engine.getItemLocation(itemRectangle.getKey()) == Location.BOAT)
                {
                    engine.unloadBoat(itemRectangle.getKey());
                    numberOfObjectsInBoat--;
                }
                else
                {
                    if (engine.getBoatLocation() == engine.getItemLocation(itemRectangle.getKey())
                            && numberOfObjectsInBoat < 2)
                    {
                        engine.loadBoat(itemRectangle.getKey());
                        numberOfObjectsInBoat++;                       
                    }
                }
            }
        }

        if (boatRectangle.contains(e.getPoint())
                && engine.getItemLocation(Item.ITEM_3) == Location.BOAT)
        {
            engine.rowBoat();
        }

        repaint();
    }

    // ----------------------------------------------------------
    // None of these methods will be used
    // ----------------------------------------------------------

    @Override
    public void mousePressed(MouseEvent e)
    {
        //
    }

    @Override
    public void mouseReleased(MouseEvent e)
    {
        //
    }

    @Override
    public void mouseEntered(MouseEvent e)
    {
        //
    }

    @Override
    public void mouseExited(MouseEvent e)
    {
        //
    }
}
