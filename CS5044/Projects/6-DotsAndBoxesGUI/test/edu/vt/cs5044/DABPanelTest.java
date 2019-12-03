package edu.vt.cs5044;

import static edu.vt.cs5044.DABGuiName.*;
import java.awt.Component;
import java.awt.Container;
import java.awt.Frame;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.stream.IntStream;
import javax.swing.JButton;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.SwingUtilities;
import org.junit.After;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

//
// PLEASE NOTE: You don't need to alter this file in any way; please just use it as-is.
//

//CHECKSTYLE:OFF
@SuppressWarnings("javadoc")
public class DABPanelTest {

    private JFrame dummyFrame;
    private DABPanel dabPanel;
    private DABGrid dabGrid;

    private JComboBox<Integer> xCombo;
    private JComboBox<Integer> yCombo;
    private JComboBox<Direction> dirCombo;

    private JLabel p1ScoreLabel;
    private JLabel p2ScoreLabel;
    private JLabel turnLabel;

    private JButton drawButton;

    private JMenuItem init2MenuItem;
    private JMenuItem init3MenuItem;
    private JMenuItem init4MenuItem;
    private JMenuItem interactiveMenuItem;

    //
    // ----- HELPER METHODS -----
    //
    
    /**
     * Wrapper method just calls SwingUtilities.invokeAndWait and handles exceptions.
     * 
     * Any checked exceptions are thrown as an unchecked GameException
     * 
     * @param r Runnable to invoke (or a compatible lambda expression or method reference)
     */
    private void SwingInvokeAndWait(Runnable r) {
        try {
            SwingUtilities.invokeAndWait(r);
        } catch (InterruptedException | InvocationTargetException e) {
            throw new GameException("Swing invocation exception during testing");
        }
    }
    
    
    /**
     * Ensure the score labels contain only the expected number, with no other digits.
     *
     * @param expectP1 expected score of Player.ONE
     * @param expectP2 expected score of Player.TWO
     */
    private void checkScores(int expectP1, int expectP2) {
        assertTrue(p1ScoreLabel.getText().matches("[^\\d]*" + expectP1 + "[^\\d]*"));
        assertTrue(p2ScoreLabel.getText().matches("[^\\d]*" + expectP2 + "[^\\d]*"));
    }

    /**
     * Ensure the turn label contains only the correct player.
     *
     * @param expectPlayer expected current player
     */
    private void checkTurn(Player expectPlayer) {
        String turnString = turnLabel.getText();
        assertTrue(turnString.contains(expectPlayer.toString()));
        assertFalse(turnString.contains(expectPlayer.getOpponent().toString()));
    }

    /**
     * Draw an edge by setting the combo boxes and simulating a click of the draw button.
     *
     * This must all be done within the context of Swing, so we must use a utility to ask Swing to
     * schedule the task, then wait until it has been completed to continue testing.
     *
     * @param x coordinate x
     * @param y coordinate y
     * @param dir direction
     */
    private void drawEdge(int x, int y, Direction dir) {
        SwingInvokeAndWait(() -> {
            xCombo.setSelectedItem(x);
            yCombo.setSelectedItem(y);
            dirCombo.setSelectedItem(dir);
            drawButton.doClick();
        });
    }

    /**
     * Simulate a click of one of the menu items.
     *
     * This must all be done within the context of Swing, so we must use a utility to ask Swing to
     * schedule the task, then wait until it has been completed to continue testing.
     *
     * @param item menu item to click
     */
    private void clickMenuItem(JMenuItem item) {
        SwingInvokeAndWait(item::doClick);
    }

    /**
     * Find a component by name within the hierarchy of a Swing container.
     * 
     * Recursively searches any sub-containers. Note that we can't check the actual type of the 
     * component found at runtime, so we may return an incompatible subclass of component,
     * but we've suppressed the warning. This is intentional, because we prefer to "fail fast" 
     * if a component is found with the right name but the wrong type.
     *
     * @param <T> Type of component expected to be returned, which can be inferred by compiler
     * @param root top-level container to search
     * @param name name of component to find
     * @return component with specified name, or null if no component with this name is found
     */
    @SuppressWarnings("unchecked")
    private <T extends Component> T findComponent(Container root, String name) {
        synchronized(root.getTreeLock()) {
            for (Component child : root.getComponents()) {
                if (name.equals(child.getName())) {
                    return (T) child; // Unchecked cast warning, due to type erasure, is suppressd
                }
                if (child instanceof Container) {
                    T subChild = findComponent((Container) child, name);
                    if (subChild != null) {
                        return subChild;
                    }
                }
            }
            return null;
        }
    }

    
    
    //
    // -----  TEST METHODS  -----
    //
    
    /**
     * Create a new frame and panel, then find all the relevant components by name.
     */
    @Before
    public void setUp() {
        dummyFrame = new JFrame();
        dabPanel = new DABPanel(dummyFrame);
        dummyFrame.setContentPane(dabPanel);

        xCombo = findComponent(dabPanel, X_COMBO);
        yCombo = findComponent(dabPanel, Y_COMBO);
        dirCombo = findComponent(dabPanel, DIR_COMBO);
        drawButton = findComponent(dabPanel, DRAW_BUTTON);

        p1ScoreLabel = findComponent(dabPanel, P1_SCORE_LABEL);
        p2ScoreLabel = findComponent(dabPanel, P2_SCORE_LABEL);
        turnLabel = findComponent(dabPanel, TURN_LABEL);

        dabGrid = findComponent(dabPanel, DAB_GRID);
    }

    /**
     * Explicitly dispose the frame after each test to release its resources.
     */
    @After
    public void tearDown() {
        if (dummyFrame != null) {
            dummyFrame.dispose();
        }
    }

    @Test
    public void testFoundAllComponents() {
        assertNotNull(xCombo);
        assertNotNull(yCombo);
        assertNotNull(dirCombo);
        assertNotNull(p1ScoreLabel);
        assertNotNull(p2ScoreLabel);
        assertNotNull(turnLabel);
        assertNotNull(drawButton);
        assertNotNull(dabGrid);
    }

    @Test
    public void testInitialCombos() {
        assertEquals(3, xCombo.getItemCount());
        assertEquals(3, yCombo.getItemCount());

        IntStream.range(0, 3)
            .forEach(i -> {
                assertEquals(i, (int) xCombo.getItemAt(i));
                assertEquals(i, (int) yCombo.getItemAt(i));
            });
                
        assertEquals(4, dirCombo.getItemCount());
        
        Arrays.stream(Direction.values())
            .forEach(dir -> assertEquals(dir, dirCombo.getItemAt(dir.ordinal())));
    }

    @Test
    public void testInitialLabels() {
        assertTrue(p1ScoreLabel.getText().contains("ONE"));
        assertTrue(p2ScoreLabel.getText().contains("TWO"));

        checkScores(0, 0);
        checkTurn(Player.ONE);
    }

    @Test
    public void testInitialDrawEnabled() {
        assertTrue(drawButton.isEnabled());
    }

    @Test
    public void testMenuBarAndSetMenuFields() {
        JMenuBar jmb = dummyFrame.getJMenuBar();

        JMenu gameMenu = (JMenu) jmb.getComponent(0);
        assertEquals("Game", gameMenu.getText());
        assertEquals(2, gameMenu.getItemCount());

        assertTrue(gameMenu.getItem(1) instanceof JCheckBoxMenuItem);
        JMenuItem interactive = (JCheckBoxMenuItem) gameMenu.getItem(1);
        assertTrue(interactive.getText().toLowerCase().contains("interact"));
        interactiveMenuItem = interactive;

        assertTrue(gameMenu.getItem(0) instanceof JMenu);
        JMenu newGameMenu = (JMenu) gameMenu.getItem(0);
        assertEquals("New", newGameMenu.getText());
        assertEquals(3, newGameMenu.getItemCount());

        init2MenuItem = newGameMenu.getItem(0);
        init3MenuItem = newGameMenu.getItem(1);
        init4MenuItem = newGameMenu.getItem(2);

        assertTrue(init2MenuItem.getText().matches("[^\\d]*2[^\\d]*2?[^\\d]*"));
        assertTrue(init3MenuItem.getText().matches("[^\\d]*3[^\\d]*3?[^\\d]*"));
        assertTrue(init4MenuItem.getText().matches("[^\\d]*4[^\\d]*4?[^\\d]*"));
    }

    @Test
    public void testInitialInteractiveModeDisabled() {
        testMenuBarAndSetMenuFields();

        assertFalse(interactiveMenuItem.isSelected());
        assertFalse(dabGrid.isInteractive());
    }

    @Test
    public void testFirstMove() {
        drawEdge(1, 1, Direction.TOP);

        checkScores(0, 0);
        checkTurn(Player.TWO);
    }

    @Test
    public void testRedrawEdge() {
        testFirstMove();

        drawEdge(1, 1, Direction.TOP);

        checkScores(0, 0);
        checkTurn(Player.TWO);
    }

    @Test
    public void testRedrawEdgeFromAdjacent() {
        testFirstMove();

        drawEdge(1, 0, Direction.BOTTOM);

        checkScores(0, 0);
        checkTurn(Player.TWO);
    }

    @Test
    public void testInitAfterFirstMove() {
        testFirstMove();
        testMenuBarAndSetMenuFields();

        clickMenuItem(init2MenuItem);

        checkScores(0, 0);
        checkTurn(Player.ONE);
    }

    @Test
    public void testCompleteBox() {
        testFirstMove();
        drawEdge(1, 1, Direction.BOTTOM);
        drawEdge(1, 1, Direction.RIGHT);

        drawEdge(1, 1, Direction.LEFT);

        checkScores(0, 1);
        checkTurn(Player.TWO);
    }

    @Test
    public void testInitAfterCompleteBox() {
        testCompleteBox();
        testMenuBarAndSetMenuFields();

        clickMenuItem(init3MenuItem);

        checkScores(0, 0);
        checkTurn(Player.ONE);
        assertTrue(drawButton.isEnabled());

    }

    @Test
    public void testDrawAllHorizontalEdgesThenCompleteBox() {
        drawEdge(0, 0, Direction.TOP);
        drawEdge(1, 0, Direction.TOP);
        drawEdge(2, 0, Direction.TOP);

        drawEdge(0, 1, Direction.TOP);
        drawEdge(1, 1, Direction.TOP);
        drawEdge(2, 1, Direction.TOP);

        drawEdge(0, 1, Direction.BOTTOM);
        drawEdge(1, 1, Direction.BOTTOM);
        drawEdge(2, 1, Direction.BOTTOM);

        drawEdge(0, 2, Direction.BOTTOM);
        drawEdge(1, 2, Direction.BOTTOM);
        drawEdge(2, 2, Direction.BOTTOM);

        drawEdge(0, 0, Direction.LEFT);
        drawEdge(0, 0, Direction.RIGHT);

        checkScores(0, 1);
        checkTurn(Player.TWO);
    }

    @Test
    public void testTwoBoxesTopRowAfterHorizontalEdges() {
        testDrawAllHorizontalEdgesThenCompleteBox();

        drawEdge(2, 0, Direction.RIGHT);
        drawEdge(2, 0, Direction.LEFT);

        checkScores(2, 1);
        checkTurn(Player.ONE);
    }

    @Test
    public void testTwoBoxesMiddleRowAfterHorizontalEdges() {
        testTwoBoxesTopRowAfterHorizontalEdges();

        drawEdge(1, 1, Direction.RIGHT);
        drawEdge(1, 1, Direction.LEFT);
        drawEdge(0, 1, Direction.LEFT);

        checkScores(2, 3);
        checkTurn(Player.TWO);
    }

    @Test
    public void testTwoBoxesMiddleBottomRowAfterHorizontalEdges() {
        testTwoBoxesMiddleRowAfterHorizontalEdges();

        drawEdge(2, 2, Direction.LEFT);
        drawEdge(2, 2, Direction.RIGHT);
        drawEdge(2, 1, Direction.RIGHT);

        checkScores(4, 3);
        checkTurn(Player.ONE);
    }

    @Test
    public void testCompleteGameAfterHorizontalEdges() {
        testTwoBoxesMiddleBottomRowAfterHorizontalEdges();

        drawEdge(0, 2, Direction.LEFT);
        drawEdge(0, 2, Direction.RIGHT);

        checkScores(4, 5);

        String turnString = turnLabel.getText().toUpperCase();
        assertTrue(turnString.contains("OVER"));
        assertFalse(turnString.contains("ONE"));
        assertFalse(turnString.contains("TWO"));

        assertFalse(drawButton.isEnabled());
    }

    @Test
    public void testInitAfterCompleteGame() {
        testCompleteGameAfterHorizontalEdges();
        testMenuBarAndSetMenuFields();

        clickMenuItem(init4MenuItem);

        checkScores(0, 0);
        checkTurn(Player.ONE);
        assertTrue(drawButton.isEnabled());
    }

    @Test
    public void testEnableInteractiveMode() {
        testInitialInteractiveModeDisabled();

        clickMenuItem(interactiveMenuItem);

        assertTrue(interactiveMenuItem.isSelected());
        assertTrue(dabGrid.isInteractive());
    }

    @Test
    public void testDisableInteractiveMode() {
        testEnableInteractiveMode();

        clickMenuItem(interactiveMenuItem);

        assertFalse(interactiveMenuItem.isSelected());
        assertFalse(dabGrid.isInteractive());
    }
    
    /**
     * Invoke the application via DABPanel.main().
     * 
     * NOTE: This method will very briefly display the entire interface at its normal size.
     * 
     * ACADEMIC NOTE: This is only to achieve for 100% coverage; it serves no other useful purpose.
     */
    @Test
    public void testCallMain() {
        DABPanel.main(new String[0]);
        SwingUtilities.invokeLater(() -> {
            Arrays.stream(Frame.getFrames()).forEach(Frame::dispose);
        });
    }

}
