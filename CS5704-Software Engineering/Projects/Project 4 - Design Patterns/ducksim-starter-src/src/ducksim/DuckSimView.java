package ducksim;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import javax.swing.Action;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.KeyStroke;
import javax.swing.Timer;

/**
 * DuckSimView is a JPanel that will serve as the content pane to the main
 * JFrame window.
 *
 * @author Dr. K
 */
public class DuckSimView extends JPanel {

	// ==========================================================
	// Private fields
	// ==========================================================
	private final DuckSimModel model;

	// Field for escape key action
	private Action escapeAction;

	// Fields for popup menu
	private JPopupMenu popup;

	// Fields for flying animation
	private final Timer flyTimer;
	private int animationX = -50;

	// Fields for quacking animation
	private final Timer quackTimer;
	private int quackCounter;

	public DuckSimView(DuckSimModel model) {

		this.model = model;

		flyTimer = new Timer(25, e -> {
			animationX = animationX + 5;
			repaint();
		});

		quackTimer = new Timer(100, e -> repaint());
		quackCounter = 0;

		getInputMap().put(KeyStroke.getKeyStroke("ESCAPE"), "doEscapeAction");
		getActionMap().put("doEscapeAction", escapeAction);

	}

	public void setEscapeAction(Action action) {
		escapeAction = action;
	}

	public Timer getFlyTimer() {
		return flyTimer;
	}

	public Timer getQuackTimer() {
		return quackTimer;
	}

	public JPopupMenu getPopup() {
		return popup;
	}

	public void initializePopup() {
		popup = new JPopupMenu();
	}

	public boolean clickedNewDuckButton(MouseEvent e) {
		return xyIsWithinRect(e.getX(), e.getY(), 30, 500, 50, 50);
	}

	public int getClickedDuck(MouseEvent e) {
		for (int i = 0; i < Math.min(model.currDuckCount(), 4); i++) {
			if (xyIsWithinRect(e.getX(), e.getY(), 150 + i * 125, 325, 100, 100)) {
				return i;
			}
		}
		for (int i = 4; i < Math.min(model.currDuckCount(), 8); i++) {
			if (xyIsWithinRect(e.getX(), e.getY(), 150 + (i - 4) * 125, 425, 100, 100)) {
				return i;
			}
		}
		return -1;
	}

	public boolean xyIsWithinRect(int x, int y, int rectX, int rectY, int rectW, int rectH) {
		return (rectX <= x && x <= rectX + rectW && rectY <= y && y <= rectY + rectH);
	}

	// ==========================================================
	// Paint methods
	// ==========================================================
	
	// Paint the quacking duck animation.
	public void paintQuack(Graphics g) {
		int currentDuck = model.getCurrentDuck();
		if (currentDuck == -1 || !quackTimer.isRunning()) {
			return;
		}
		g.setColor(Color.BLACK);

		String quack = model.getDuck(currentDuck).getQuack();

		g.setFont(new Font("Verdana", Font.BOLD, 36));
		FontMetrics fm = g.getFontMetrics();
		int totalWidth = fm.stringWidth(quack);
		g.drawString(quack, 400 - totalWidth / 2, 100);

		quackCounter = quackCounter + 100;

		if (quackCounter > 2000) {
			quackTimer.stop();
			model.getDuck(currentDuck).swim();
			quackCounter = 0;
			repaint();
		}
	}

	// Paint the flying duck animation.
	public void paintFlyingDuck(Graphics g) {
		int currentDuck = model.getCurrentDuck();
		if (currentDuck == -1) {
			return;
		}
		g.setColor(model.getDuck(currentDuck).getColor());
		g.fillRect(animationX, 100, 50, 25);
		if (animationX == 800) {
			flyTimer.stop();
			model.getDuck(currentDuck).swim();
			animationX = -50;
			repaint();
		}
	}

	// Paint the duck in position "pos".
	// There are up to 8 ducks with positions 1-8
	// Ducks 1-4 are in the first row and 5-8 are in the second row
	// [1] [2] [3] [4]
	// [5] [6] [7] [8]
	public void paintDuck(Graphics g, int pos) {
		int x = 0;
		int y = 0;

		// If no duck in that position, don't paint anything...
		if (!model.containsDuck(pos))
			return;

		// Otherwise, paint the duck.
		
		// Get the top-left corner of the square that represents the duck
		switch (pos) {
		case 0:
		case 1:
		case 2:
		case 3:
			x = 150 + (pos) * 125;
			y = 325;
			break;
		case 4:
		case 5:
		case 6:
		case 7:
			x = 150 + (pos - 4) * 125;
			y = 450;
			break;
		default:
			assert false;
		}
		
		// Paint the border of the duck square.
		// If the duck is quacking or flying the border is cyan, otherwise it's white.
		if (model.getCurrentDuck() == pos && (flyTimer.isRunning() || quackTimer.isRunning())) {
			g.setColor(Color.CYAN);
		} else {
			g.setColor(Color.WHITE);
		}
		g.fillRect(x, y, 100, 100);
		g.setColor(Color.GRAY);
		g.fillRect(x + 5, y + 5, 90, 90);

		// Paint the body of the square.
		// If the duck is selected, paint it black, otherwise paint it white.
		if (model.isSelected(pos)) {
			g.setColor(Color.BLACK);
		} else {
			g.setColor(Color.WHITE);
		}
		g.fillRect(x + 10, y + 10, 80, 80);

		// Paint the text in the square.
		
		// A duck's display string has the form "name:bling1:bling2:bling3"
		// where the name is required but the bling elements are optional.
		// Spliting the string around the colon character will yield
		// an array with the name and bling in separate cells.
		// For example, a duck with only a name has display string "name"
		// and text array [name];
		// a duck with a name and 3 pieces of bling has display string "name:b1:b2:b3"
		// and text array [name, b1, b2, b3].
		// A duck cannot have more than three pieces of bling
		String[] text = model.getDuck(pos).display().split(":");

		// Draw duck text
		g.setFont(new Font("Verdana", Font.BOLD, 14));
		FontMetrics fm = g.getFontMetrics();
		int totalWidth;

		// If the duck is on the DuckSim welcoming committee and the duck
		// has state "welcoming", the string "Welcome!" will be printed
		// in blue near the top of the square.
		g.setColor(Color.BLUE);
		if (model.getDuck(pos).isOnDSWC()) {
			if (model.getDuck(pos).getState() == State.WELCOMING) {
				totalWidth = fm.stringWidth("Welcome!");
				g.drawString("Welcome!", x + 50 - totalWidth / 2, y + 35);
				model.getDuck(pos).swim();
			}
		}

		// If the duck is selected, make the text white, otherwise make it black.
		if (model.isSelected(pos)) {
			g.setColor(Color.WHITE);
		} else {
			g.setColor(Color.BLACK);
		}
		// Center the name of the duck and paint it in the middle of the square.
		totalWidth = fm.stringWidth(text[0]);
		g.drawString(text[0], x + 50 - totalWidth / 2, y + 55);

		// Draw the bling text (if any) in the lower portion of the square.
		StringBuilder sb = new StringBuilder();
		for (int i = 1; i < text.length; i++) {
			if (i != 1) {
				sb.append(" ");
			}
			sb.append(text[i]);
		}
		String bling = sb.toString();
		totalWidth = fm.stringWidth(bling);
		g.drawString(bling, x + 50 - totalWidth / 2, y + 75);

		// If the duck is captured, put a red X in the lower right corner of the square.
		if (!model.getDuck(pos).isFree()) {
			g.setColor(Color.RED);
			g.drawString("X", x + 75, y + 85);
		}
	}

	// paint the add-duck button (+) in the bottom left
	private void paintAddDuckButton(Graphics g) {
		int x = 30;
		int y = 500;

		g.setColor(Color.BLACK);
		g.fillOval(x, y, 50, 50);
		g.setColor(Color.GRAY);
		g.fillOval(x + 2, y + 2, 46, 46);
		g.setColor(Color.BLACK);
		g.fillOval(x + 5, y + 5, 40, 40);
		g.setColor(Color.GRAY);
		g.fillRect(x + 20, y + 10, 10, 30);
		g.fillRect(x + 10, y + 20, 30, 10);
	}

	@Override
	public void paintComponent(Graphics g) {

		// paint the background
		g.setColor(Color.GRAY);
		g.fillRect(0, 0, this.getWidth(), this.getHeight());

		// paint the ducks (up to 8 of them)
		for (int i = 0; i < model.currDuckCount(); i++) {
			paintDuck(g, i);
		}

		// paint the add-duck button (+) in the bottom left
		paintAddDuckButton(g);

		// if one of the ducks is quacking, paint it
		paintQuack(g);

		// if one of the ducks is flying, paint it
		paintFlyingDuck(g);
	}

}
