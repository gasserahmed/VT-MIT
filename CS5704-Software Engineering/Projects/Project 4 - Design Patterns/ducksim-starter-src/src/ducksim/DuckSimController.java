package ducksim;

import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.SwingUtilities;

public class DuckSimController implements MouseListener {

    // ==========================================================
    // Private fields
    // ==========================================================
    
    private final DuckSimView view;
    private final DuckSimModel model;

    private final MouseListener popupListener;
    private final Action escapeAction;

    // ==========================================================
    // Inner class for creating pop-up menu
    // ==========================================================
    
    class EscapeAction extends AbstractAction {

        @Override
        public void actionPerformed(ActionEvent e) {
            for (int i = 0; i < model.currDuckCount(); i++) {
                model.deselectDuck(i);
            }
            view.repaint();
        }
    }

    class PopupListener extends MouseAdapter {

        @Override
        public void mousePressed(MouseEvent e) {
            maybeShowPopup(e);
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            maybeShowPopup(e);
        }

        private void maybeShowPopup(MouseEvent e) {
            if (e.isPopupTrigger()) {

                // Do not show popup if *any* of the ducks are selected
                if (!model.noSelectedDucks()) {
                    return;
                }

                // Decide if the mouse click is over a duck
                int d = view.getClickedDuck(e);
                model.setCurrentDuck(d);

                // If the mouse click is over a duck AND the duck is SWIMMING
                // (not FLYING and not QUACKING), construct the popup menu
                if (d != -1 && model.getDuck(d).getState() == State.SWIMMING) {

                    // Create all the menu items for the popup menus
                    JMenuItem menuItem;
                    view.initializePopup();

                    menuItem = new JMenuItem("Fly");
                    menuItem.addActionListener(e1 -> {
                        model.getDuck(d).fly();
                        if (model.getDuck(d).getState() == State.FLYING) {
                            view.getFlyTimer().start();
                        }
                    });
                    view.getPopup().add(menuItem);

                    menuItem = new JMenuItem("Quack");
                    menuItem.addActionListener(e1 -> {
                        model.getDuck(d).quack();
                        if (model.getDuck(d).getState() == State.QUACKING) {
                            view.getQuackTimer().start();
                        }
                    });
                    view.getPopup().add(menuItem);

                    menuItem = new JMenuItem("Join DSWC");
                    menuItem.addActionListener(e1 -> {
                        model.getDuck(d).joinDSWC();
                        view.repaint();
                    });
                    if (!model.getDuck(d).isOnDSWC()) {
                        view.getPopup().add(menuItem);
                    }

                    menuItem = new JMenuItem("Quit DSWC");
                    menuItem.addActionListener(e1 -> {
                        model.getDuck(d).quitDSWC();
                        view.repaint();
                    });
                    if (model.getDuck(d).isOnDSWC()) {
                        view.getPopup().add(menuItem);
                    }

                    menuItem = new JMenuItem("Capture");
                    menuItem.addActionListener(e1 -> {
                        model.getDuck(d).capture();
                        view.repaint();
                    });
                    if (model.getDuck(d).isFree()) {
                        view.getPopup().add(menuItem);
                    }

                    menuItem = new JMenuItem("Release");
                    menuItem.addActionListener(e1 -> {
                        model.getDuck(d).release();
                        view.repaint();
                    });
                    if (!model.getDuck(d).isFree()) {
                        view.getPopup().add(menuItem);
                    }

                    menuItem = new JMenuItem("Delete");
                    menuItem.addActionListener(e1 -> {
                        model.deleteDuck(d);
                        model.setCurrentDuck(-1);
                        view.repaint();
                    });
                    view.getPopup().add(menuItem);

                    view.getPopup().show(view, e.getX(), e.getY());
                }
            }
        }
    }

    // ==========================================================
    // Constructor
    // ==========================================================
    
    public DuckSimController(DuckSimView view, DuckSimModel model) {

        this.view = view;
        this.model = model;

        popupListener = new PopupListener();
        escapeAction = new EscapeAction();

    }
    
    // ==========================================================
    // Public methods
    // ==========================================================

    @Override
    public void mouseClicked(MouseEvent e) {
        if (SwingUtilities.isLeftMouseButton(e)) {
            int idx = view.getClickedDuck(e);
            if (idx == -1) { // no duck was clicked
                if (view.clickedNewDuckButton(e)) {
                    Duck duck;
                    MakeDuckDialog makeDuckDialog = new MakeDuckDialog(model, view);
                    makeDuckDialog.setSize(300, 200);
                    makeDuckDialog.setVisible(true);
                }
            } else {
                if (model.isSelected(idx)) {
                    model.deselectDuck(idx);
                } else {
                    model.selectDuck(idx);
                }
                view.repaint();
            }
        }
    }

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

    public Action getEscapeAction() {
        return escapeAction;
    }

    public MouseListener getPopupListener() {
        return popupListener;
    }

}
