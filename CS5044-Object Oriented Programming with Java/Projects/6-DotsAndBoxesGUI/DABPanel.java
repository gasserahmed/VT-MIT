package edu.vt.cs5044;

import static edu.vt.cs5044.DABGuiName.*;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
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
        // TODO: don't forget to call updateStatus(), but ONLY if the draw was successful
    }

    private void updateStatus() {
        // TODO: read the game status via accessors; set each label's text accordingly
        // TODO: don't forget to disable the draw button, if the game is over
        // TODO: be sure to call repaint() at the end of this method to render any changes
    }

    private void updateCombos() {
        // TODO: update the coordinate combo box options, based on the current size of the grid
    }

    private void startGame(int size) {
        // TODO: start a new game of the specified size
        // TODO: call updateCombos() and updateStatus()
        // TODO: don't forget to enable the draw button
    }

    private void setupLayout() {
        // TODO: layout this panel and all its components
        // TODO: The layout must reasonably handle resizing of the frame
    }

    private JMenuBar setupMenuBar() {
        // TODO: create a new JMenuBar and populate it with the required items
        // TODO: use lambda expressions so the new game items call startGame() when clicked
        // TODO: use a lambda expression to handle activating/deactivating interactive mode
        // (Note that a method reference must be nested within the lambda expression)
        return null; // TODO: replace this placeholder so it returns the JMenuBar
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
