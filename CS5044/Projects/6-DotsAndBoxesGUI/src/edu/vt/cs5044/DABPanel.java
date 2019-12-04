package edu.vt.cs5044;

import static edu.vt.cs5044.DABGuiName.*;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

/**
 * 
 * This class has private instances, helpers, and public methods for the DotsAndBoxes GUI
 *
 * @author gasser18
 * @version Dec 4, 2019
 *
 */
public class DABPanel extends JPanel {

    private final DotsAndBoxes game;

    private final JLabel p1ScoreLabel;
    private final JLabel p2ScoreLabel;
    private final JLabel turnLabel;
    private final JComboBox<Integer> xCombo;
    private final JComboBox<Integer> yCombo;
    private final JComboBox<Direction> dirCombo;
    private final JButton drawButton;
    private final DABGrid dabGrid;

    /**
     * Create a new DABPanel object.
     *
     * @param frame DABPanel main frame
     */
    public DABPanel(JFrame frame) {

        // Adds a menu bar to the frame that will contain this panel:
        frame.setJMenuBar(setupMenuBar());

        // Creates a new DotAndBoxes instance that will act as the game engine:
        game = new DABGame();

        // Constructs and name each user interface component
        // (Each needs a unique name for testing purposes)
        xCombo = new JComboBox<>();
        xCombo.setName(X_COMBO);

        yCombo = new JComboBox<>();
        yCombo.setName(Y_COMBO);

        dirCombo = new JComboBox<>(Direction.values());
        dirCombo.setName(DIR_COMBO);

        drawButton = new JButton();
        drawButton.setName(DRAW_BUTTON);
        
        drawButton.setText("Draw!");
        drawButton.addActionListener(e -> handleDrawButton(e));
        
        turnLabel = new JLabel();
        turnLabel.setName(TURN_LABEL);
        
        p1ScoreLabel = new JLabel();
        p1ScoreLabel.setName(P1_SCORE_LABEL);

        p2ScoreLabel = new JLabel();
        p2ScoreLabel.setName(P2_SCORE_LABEL);

        dabGrid = new DABGrid(game);
        dabGrid.setName(DAB_GRID);

        // Performs layout of all the user interface components:
        setupLayout();

        // Begins a new 3x3 game by default:
        startGame(3);
    }

    /**
     * 
     * The handler code for the draw button.
     *
     * @param ae action event
     */
    private void handleDrawButton(ActionEvent ae) {
        int row = xCombo.getSelectedIndex();
        int col = yCombo.getSelectedIndex();
        Direction dir = (Direction) dirCombo.getSelectedItem();
        boolean drawIsSuccess = game.drawEdge(new Coordinate(row, col), dir);
        if (drawIsSuccess) 
        {
            updateStatus(); 
        }        
    }

    /**
     * 
     * Read the game status via accessors; then set each label's text accordingly.
     *
     */
    private void updateStatus() {
        Player currentPlayer = game.getCurrentPlayer();
        if (game.getCurrentPlayer() == null) 
        {
            drawButton.setEnabled(false);
            turnLabel.setText("Game Over!");            
        }
        else
        {
            turnLabel.setText("Player " + currentPlayer.toString() + " Go!");
        }
        
        p1ScoreLabel.setText(Player.ONE + ": " + game.getScores().get(Player.ONE));
        p2ScoreLabel.setText(Player.TWO + ": " + game.getScores().get(Player.TWO));
        repaint();
    }

    /**
     * 
     * Update the coordinate combo box options, based on the current size of the grid.
     *
     */
    private void updateCombos() {
        xCombo.removeAllItems();
        yCombo.removeAllItems();
        int gridSize = game.getSize();
        for (int item = 0; item < gridSize ; item++) 
        {
            xCombo.addItem(item);
            yCombo.addItem(item);
        }
    }

    /**
     * 
     * Start a new game of the specified size.
     *
     * @param size grid size
     */
    private void startGame(int size) {
        game.init(size);
        updateCombos();
        updateStatus();
        drawButton.setEnabled(true);
    }

    /**
     * 
     * Layout setup for the panel and all its components.
     *
     */
    private void setupLayout() {
        setLayout(new BorderLayout());
        
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new BorderLayout());
        add(topPanel, BorderLayout.PAGE_START);
        
        JPanel labelLayout = new JPanel();
        labelLayout.setLayout(new FlowLayout());
        labelLayout.add(turnLabel);
        
        JPanel combosLayout = new JPanel();
        combosLayout.setLayout(new BoxLayout(combosLayout, BoxLayout.LINE_AXIS));
        combosLayout.add(xCombo);
        combosLayout.add(yCombo);
        combosLayout.add(dirCombo);
        combosLayout.add(drawButton);

        JPanel playerPanel = new JPanel();
        playerPanel.setLayout(new BoxLayout(playerPanel, BoxLayout.PAGE_AXIS));
        playerPanel.add(labelLayout);
        playerPanel.add(combosLayout);
        
        topPanel.add(playerPanel);        
        
        add(dabGrid, BorderLayout.CENTER);        
        
        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new BorderLayout());
        add(bottomPanel, BorderLayout.PAGE_END);

        JPanel scorePanel = new JPanel();
        scorePanel.setLayout(new BorderLayout());
        scorePanel.add(p1ScoreLabel, BorderLayout.LINE_START);
        scorePanel.add(p2ScoreLabel, BorderLayout.LINE_END);
        
        bottomPanel.add(scorePanel);
    }

    /**
     * 
     * Create a new JMenuBar and populate it with game menu items like New and Interactive Grid.
     *
     * @return the created JMenuBar
     */
    private JMenuBar setupMenuBar() {
        JMenuBar gameMenuBar = new JMenuBar();
        JMenu gameMenu = new JMenu("Game");
        JMenu gridSizeSubMenu = new JMenu("New");
        JMenuItem twoByTwo = new JMenuItem("Size 2x2");
        JMenuItem threeByThree = new JMenuItem("Size 3x3");
        JMenuItem fourByFour = new JMenuItem("Size 4x4");
        gridSizeSubMenu.add(twoByTwo).addActionListener(evt -> {
            startGame(2);
        });
        gridSizeSubMenu.add(threeByThree).addActionListener(evt -> {
            startGame(3);
        });
        gridSizeSubMenu.add(fourByFour).addActionListener(evt -> {
            startGame(4);
        });
        
        JCheckBoxMenuItem gridIsInteractive = new JCheckBoxMenuItem("Interactive grid");
        
        gameMenu.add(gridSizeSubMenu);
        gameMenu.add(gridIsInteractive).addActionListener(evt -> {
            if (gridIsInteractive.isSelected()) 
            {
                dabGrid.setCallback(new Runnable() 
                {
                    @Override
                    public void run()
                    {
                        updateStatus();                        
                    }                    
                });
            }
            else 
            {
                dabGrid.setCallback(null);
            }
        });
        
        gameMenuBar.add(gameMenu);        
        return gameMenuBar; 
    }

    /**
     * 
     * This is boilerplate code for creating and showing GUI.
     * (This shouldn't be touched) 
     *
     */
    private static void createAndShowGUI() {
        JFrame frame = new JFrame("Dots And Boxes");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        JComponent newContentPane = new DABPanel(frame);
        newContentPane.setOpaque(true);
        frame.setContentPane(newContentPane);
        frame.pack();
        frame.setVisible(true);
    }

    /**
     * 
     * This is boilerplate code for running the game.
     * (This shouldn't be touched)
     * 
     * @param args main method arguments
     */
    public static void main(String[] args) {
        // we used the method reference to simplify the code
        SwingUtilities.invokeLater(DABPanel::createAndShowGUI);
    }

}
