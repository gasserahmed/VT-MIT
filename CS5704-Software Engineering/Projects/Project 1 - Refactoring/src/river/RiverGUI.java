package river;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JFrame;
import javax.swing.JPanel;
import river.GameEngine.Item;

/**
 * Graphical interface for the River application
 * 
 * @author Gregory Kulczycki
 */
public class RiverGUI extends JPanel implements MouseListener {

    // ==========================================================
    // Fields (hotspots)
    // ==========================================================

    private final Rectangle leftFarmerRect = new Rectangle(80, 215, 50, 50);
    private final Rectangle leftWolfRect = new Rectangle(20, 215, 50, 50);
    private final Rectangle leftGooseRect = new Rectangle(20, 275, 50, 50);
    private final Rectangle leftBeansRect = new Rectangle(80, 275, 50, 50);
    private final Rectangle leftBoatRect = new Rectangle(140, 275, 110, 50);
    private final Rectangle leftBoatDriverRect = new Rectangle(140, 215, 50, 50);
    private final Rectangle leftBoatPassengerRect = new Rectangle(200, 215, 50, 50);

    private final Rectangle rightFarmerRect = new Rectangle(730, 215, 50, 50);
    private final Rectangle rightWolfRect = new Rectangle(670, 215, 50, 50);
    private final Rectangle rightGooseRect = new Rectangle(670, 275, 50, 50);
    private final Rectangle rightBeansRect = new Rectangle(730, 275, 50, 50);
    private final Rectangle rightBoatRect = new Rectangle(550, 275, 110, 50);
    private final Rectangle rightBoatDriverRect = new Rectangle(550, 215, 50, 50);
    private final Rectangle rightBoatPassengerRect = new Rectangle(610, 215, 50, 50);

    private final Rectangle restartButtonRect = new Rectangle(350, 120, 100, 30);

    // ==========================================================
    // Private Fields
    // ==========================================================

    private GameEngine engine; // Model
    private boolean restart = false;

    // ==========================================================
    // Constructor
    // ==========================================================

    public RiverGUI() {

        engine = new GameEngine();
        addMouseListener(this);
    }

    // ==========================================================
    // Paint Methods (View)
    // ==========================================================

    @Override
    public void paintComponent(Graphics g) {

        g.setColor(Color.GRAY);
        g.fillRect(0, 0, this.getWidth(), this.getHeight());

        paintObjectsOnLeft(g);
        paintObjectsOnRight(g);
        paintObjectsOnBoat(g);
        String message = "";
        if (engine.gameIsLost()) {
            message = "You Lost!";
            restart = true;
        }
        if (engine.gameIsWon()) {
            message = "You Won!";
            restart = true;
        }
        paintMessage(message, g);
        if (restart) {
            paintRestartButton(g);
        }

    }

    public void paintObjectsOnLeft(Graphics g) {

        if (engine.getItemLocation(Item.FARMER) == Location.START) {
            g.setColor(Color.MAGENTA);
            g.fillRect(80, 215, 50, 50);
        }
        if (engine.getItemLocation(Item.WOLF) == Location.START) {
            g.setColor(Color.CYAN);
            g.fillRect(20, 215, 50, 50);
            paintStringInRectangle("W", 20, 215, 50, 50, g);
        }
        if (engine.getItemLocation(Item.GOOSE) == Location.START) {
            g.setColor(Color.CYAN);
            g.fillRect(20, 275, 50, 50);
            paintStringInRectangle("G", 20, 275, 50, 50, g);
        }
        if (engine.getItemLocation(Item.BEANS) == Location.START) {
            g.setColor(Color.CYAN);
            g.fillRect(80, 275, 50, 50);
            paintStringInRectangle("B", 80, 275, 50, 50, g);
        }
    }

    public void paintObjectsOnRight(Graphics g) {

        if (engine.getItemLocation(Item.FARMER) == Location.FINISH) {
            g.setColor(Color.MAGENTA);
            g.fillRect(730, 215, 50, 50);
        }
        if (engine.getItemLocation(Item.WOLF) == Location.FINISH) {
            g.setColor(Color.CYAN);
            g.fillRect(670, 215, 50, 50);
            paintStringInRectangle("W", 670, 215, 50, 50, g);
        }
        if (engine.getItemLocation(Item.GOOSE) == Location.FINISH) {
            g.setColor(Color.CYAN);
            g.fillRect(670, 275, 50, 50);
            paintStringInRectangle("G", 670, 275, 50, 50, g);
        }
        if (engine.getItemLocation(Item.BEANS) == Location.FINISH) {
            g.setColor(Color.CYAN);
            g.fillRect(730, 275, 50, 50);
            paintStringInRectangle("B", 730, 275, 50, 50, g);
        }
    }

    public void paintObjectsOnBoat(Graphics g) {
        if (engine.getBoatLocation() == Location.START) {
            g.setColor(Color.ORANGE);
            g.fillRect(140, 275, 110, 50);
            if (engine.getItemLocation(Item.FARMER) == Location.BOAT) {
                g.setColor(Color.MAGENTA);
                g.fillRect(140, 215, 50, 50);
            }
            if (engine.getItemLocation(Item.WOLF) == Location.BOAT) {
                g.setColor(Color.CYAN);
                g.fillRect(200, 215, 50, 50);
                paintStringInRectangle("W", 200, 215, 50, 50, g);
            } else if (engine.getItemLocation(Item.GOOSE) == Location.BOAT) {
                g.setColor(Color.CYAN);
                g.fillRect(200, 215, 50, 50);
                paintStringInRectangle("G", 200, 215, 50, 50, g);
            } else if (engine.getItemLocation(Item.BEANS) == Location.BOAT) {
                g.setColor(Color.CYAN);
                g.fillRect(200, 215, 50, 50);
                paintStringInRectangle("B", 200, 215, 50, 50, g);
            }
        }
        if (engine.getBoatLocation() == Location.FINISH) {
            g.setColor(Color.ORANGE);
            g.fillRect(550, 275, 110, 50);
            if (engine.getItemLocation(Item.FARMER) == Location.BOAT) {
                g.setColor(Color.MAGENTA);
                g.fillRect(550, 215, 50, 50);
            }
            if (engine.getItemLocation(Item.WOLF) == Location.BOAT) {
                g.setColor(Color.CYAN);
                g.fillRect(610, 215, 50, 50);
                paintStringInRectangle("W", 610, 215, 50, 50, g);
            } else if (engine.getItemLocation(Item.GOOSE) == Location.BOAT) {
                g.setColor(Color.CYAN);
                g.fillRect(610, 215, 50, 50);
                paintStringInRectangle("G", 610, 215, 50, 50, g);
            } else if (engine.getItemLocation(Item.BEANS) == Location.BOAT) {
                g.setColor(Color.CYAN);
                g.fillRect(610, 215, 50, 50);
                paintStringInRectangle("B", 610, 215, 50, 50, g);
            }
        }
    }

    public void paintStringInRectangle(String str, int x, int y, int width, int height, Graphics g) {
        g.setColor(Color.BLACK);
        int fontSize = (height >= 40) ? 36 : 18;
        g.setFont(new Font("Verdana", Font.BOLD, fontSize));
        FontMetrics fm = g.getFontMetrics();
        int strXCoord = x + width / 2 - fm.stringWidth(str) / 2;
        int strYCoord = y + height / 2 + fontSize / 2 - 4;
        g.drawString(str, strXCoord, strYCoord);
    }

    public void paintMessage(String message, Graphics g) {
        g.setColor(Color.BLACK);
        g.setFont(new Font("Verdana", Font.BOLD, 36));
        FontMetrics fm = g.getFontMetrics();
        int strXCoord = 400 - fm.stringWidth(message) / 2;
        int strYCoord = 100;
        g.drawString(message, strXCoord, strYCoord);
    }

    public void paintRestartButton(Graphics g) {
        g.setColor(Color.BLACK);
        paintBorder(restartButtonRect, 3, g);
        g.setColor(Color.PINK);
        paintRectangle(restartButtonRect, g);
        paintStringInRectangle("Restart", restartButtonRect.x, restartButtonRect.y, restartButtonRect.width,
                restartButtonRect.height, g);
    }

    public void paintBorder(Rectangle r, int thickness, Graphics g) {
        g.fillRect(r.x - thickness, r.y - thickness, r.width + (2 * thickness), r.height + (2 * thickness));
    }

    public void paintRectangle(Rectangle r, Graphics g) {
        g.fillRect(r.x, r.y, r.width, r.height);
    }

    // ==========================================================
    // Startup Methods
    // ==========================================================

    /**
     * Create the GUI and show it. For thread safety, this method should be invoked
     * from the event-dispatching thread.
     */
    private static void createAndShowGUI() {

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

    public static void main(String[] args) {

        // Schedule a job for the event-dispatching thread:
        // creating and showing this application's GUI.
        javax.swing.SwingUtilities.invokeLater(RiverGUI::createAndShowGUI);
    }

    // ==========================================================
    // MouseListener Methods (Controller)
    // ==========================================================

    @Override
    public void mouseClicked(MouseEvent e) {

        if (restart) {
            if (this.restartButtonRect.contains(e.getPoint())) {
                engine.resetGame();
                restart = false;
                repaint();
            }
            return;
        }

        if (leftFarmerRect.contains(e.getPoint())) {
            if (engine.getItemLocation(Item.FARMER) == Location.START) {
                engine.loadBoat(Item.FARMER);
            }
        } else if (leftWolfRect.contains(e.getPoint())) {
            if (engine.getItemLocation(Item.WOLF) == Location.START) {
                engine.loadBoat(Item.WOLF);
            }
        } else if (leftGooseRect.contains(e.getPoint())) {
            if (engine.getItemLocation(Item.GOOSE) == Location.START) {
                engine.loadBoat(Item.GOOSE);
            }
        } else if (leftBeansRect.contains(e.getPoint())) {
            if (engine.getItemLocation(Item.BEANS) == Location.START) {
                engine.loadBoat(Item.BEANS);
            }
        } else if (leftBoatDriverRect.contains(e.getPoint())) {
            if (engine.getBoatLocation() == Location.START && engine.getItemLocation(Item.FARMER) == Location.BOAT) {
                engine.unloadBoat(Item.FARMER);
            }
        } else if (leftBoatPassengerRect.contains(e.getPoint())) {
            if (engine.getBoatLocation() == Location.START) {
                if (engine.getItemLocation(Item.WOLF) == Location.BOAT) {
                    engine.unloadBoat(Item.WOLF);
                } else if (engine.getItemLocation(Item.GOOSE) == Location.BOAT) {
                    engine.unloadBoat(Item.GOOSE);
                } else if (engine.getItemLocation(Item.BEANS) == Location.BOAT) {
                    engine.unloadBoat(Item.BEANS);
                }
            }
        } else if (leftBoatRect.contains(e.getPoint())) {
            if (engine.getBoatLocation() == Location.START && engine.getItemLocation(Item.FARMER) == Location.BOAT) {
                engine.rowBoat();
            }
        } else if (rightFarmerRect.contains(e.getPoint())) {
            if (engine.getItemLocation(Item.FARMER) == Location.FINISH) {
                engine.loadBoat(Item.FARMER);
            }
        } else if (rightWolfRect.contains(e.getPoint())) {
            if (engine.getItemLocation(Item.WOLF) == Location.FINISH) {
                engine.loadBoat(Item.WOLF);
            }
        } else if (rightGooseRect.contains(e.getPoint())) {
            if (engine.getItemLocation(Item.GOOSE) == Location.FINISH) {
                engine.loadBoat(Item.GOOSE);
            }
        } else if (rightBeansRect.contains(e.getPoint())) {
            if (engine.getItemLocation(Item.BEANS) == Location.FINISH) {
                engine.loadBoat(Item.BEANS);
            }
        } else if (rightBoatDriverRect.contains(e.getPoint())) {
            if (engine.getBoatLocation() == Location.FINISH && engine.getItemLocation(Item.FARMER) == Location.BOAT) {
                engine.unloadBoat(Item.FARMER);
            }
        } else if (rightBoatPassengerRect.contains(e.getPoint())) {
            if (engine.getBoatLocation() == Location.FINISH) {
                if (engine.getItemLocation(Item.WOLF) == Location.BOAT) {
                    engine.unloadBoat(Item.WOLF);
                } else if (engine.getItemLocation(Item.GOOSE) == Location.BOAT) {
                    engine.unloadBoat(Item.GOOSE);
                } else if (engine.getItemLocation(Item.BEANS) == Location.BOAT) {
                    engine.unloadBoat(Item.BEANS);
                }
            }
        } else if (rightBoatRect.contains(e.getPoint())) {
            if (engine.getBoatLocation() == Location.FINISH && engine.getItemLocation(Item.FARMER) == Location.BOAT) {
                engine.rowBoat();
            }
        } else {
            return;
        }
        repaint();
    }

    // ----------------------------------------------------------
    // None of these methods will be used
    // ----------------------------------------------------------

    @Override
    public void mousePressed(MouseEvent e) {
        //
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        //
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        //
    }

    @Override
    public void mouseExited(MouseEvent e) {
        //
    }
}
