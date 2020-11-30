package ducksim;

import java.util.List;

public class NewDuckController {
    private final DuckSimView view;
    private final DuckSimModel model;

    public NewDuckController(DuckSimView view, DuckSimModel model) {
        this.view = view;
        this.model = model;
    }

    public void createNewDuck() {
        
        if (model.noSelectedDucks()) {
            MakeDuckDialog makeDuckDialog = new MakeDuckDialog(model, view);
            makeDuckDialog.setSize(300, 200);
            makeDuckDialog.setVisible(true);
        } else {
            // get the selected ducks from the model
            List<Duck> ducks = model.getSelectedDucks();
            // filter the selected ducks by removing any flocks
            for (int index = 0; index < ducks.size(); index++) {
                if (ducks.get(index) instanceof Flock) {
                    ducks.remove(index);
                    index--;
                }
            }
            
            // if there is more than one duck after removing flocks,
            // create a new flock with the selected ducks
            if (ducks.size() > 1) {
                Flock flock = new Flock(ducks);
                model.addNewDuck(flock);
            }
           
            view.repaint();
        }
    }
}
