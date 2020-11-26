package ducksim;

public class CrossBling extends Bling {
    public CrossBling(Duck duck) {
        super(duck);
    }
    
    @Override
    public String display() {
        return duck.display() + ":+";
    }
}
