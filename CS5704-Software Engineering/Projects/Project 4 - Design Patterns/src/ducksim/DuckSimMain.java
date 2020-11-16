
package ducksim;

import javax.swing.JComponent;
import javax.swing.JFrame;

/**
 * Main class for the DuckSim application
 * 
 * @author Gregory Kulczycki
 */
public class DuckSimMain {

    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(DuckSimMain::createAndShowGUI);
    }
    
    private static void createAndShowGUI() {
        DuckSimModel model = new DuckSimModel();
        DuckSimView view = new DuckSimView(model);
        DuckSimController controller = new DuckSimController(view, model);
        view.setEscapeAction(controller.getEscapeAction());
        view.addMouseListener(controller);
        view.addMouseListener(controller.getPopupListener());
        JFrame frame = new JFrame("DuckSim");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JComponent content = view;
        content.setOpaque(true);
        frame.setContentPane(content);
        frame.setSize(800, 600);
        frame.setVisible(true);
    }
    
}
