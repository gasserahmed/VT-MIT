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
        // TODO: set the text to be displayed on the draw button
        drawButton.setText("Draw!");
        drawButton.addActionListener(e -> handleDrawButton(e));
        // TODO: use a method reference to add handleDrawButton() as a listener to the draw button

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

    private void handleDrawButton(ActionEvent ae) {
        // TODO: the handler code for the draw button goes here
        int row = xCombo.getSelectedIndex();
        int col = yCombo.getSelectedIndex();
        Direction dir = (Direction) dirCombo.getSelectedItem();
        boolean drawIsSuccess = game.drawEdge(new Coordinate(row, col), dir);
        if (drawIsSuccess) 
        {
           updateStatus(); 
        }
        // TODO: don't forget to call updateStatus(), but ONLY if the draw was successful
    }

    private void updateStatus() {
        // TODO: read the game status via accessors; set each label's text accordingly
        Player currentPlayer = game.getCurrentPlayer();
        // TODO: don't forget to disable the draw button, if the game is over
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
        // TODO: be sure to call repaint() at the end of this method to render any changes
        repaint();
    }

    private void updateCombos() {
        // TODO: update the coordinate combo box options, based on the current size of the grid
        xCombo.removeAllItems();
        yCombo.removeAllItems();
        int gridSize = game.getSize();
        for (int i = 0; i < gridSize ; i++) 
        {
            xCombo.addItem(i);
            yCombo.addItem(i);
        }
    }

    private void startGame(int size) {
        // TODO: start a new game of the specified size
        game.init(size);
        // TODO: call updateCombos() and updateStatus()
        updateCombos();
        updateStatus();
        // TODO: don't forget to enable the draw button
        drawButton.setEnabled(true);
    }

    private void setupLayout() {
        // TODO: layout this panel and all its components
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
        // TODO: The layout must reasonably handle resizing of the frame
    }

    private JMenuBar setupMenuBar() {
        // TODO: create a new JMenuBar and populate it with the required items
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
        // TODO: use lambda expressions so the new game items call startGame() when clicked
        // TODO: use a lambda expression to handle activating/deactivating interactive mode
        // (Note that a method reference must be nested within the lambda expression)
        return gameMenuBar; // TODO: replace this placeholder so it returns the JMenuBar
    }

    private static void createAndShowGUI() {
        // This is boilerplate code
        // You should leave this exactly as-is
        JFrame frame = new JFrame("Dots And Boxes");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        JComponent newContentPane = new DABPanel(frame);
        newContentPane.setOpaque(true);
        frame.setContentPane(newContentPane);
        frame.pack();
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        // This is boilerplate code
        // You should leave this exactly as-is
        // Notice the use of a method reference to simplify this code
        SwingUtilities.invokeLater(DABPanel::createAndShowGUI);
    }

}
