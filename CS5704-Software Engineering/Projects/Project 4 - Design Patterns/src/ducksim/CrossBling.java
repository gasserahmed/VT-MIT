package ducksim;

public class CrossBling extends Bling {
    public CrossBling(Duck duck) {
        super(duck);
    }
    
    public String display() {
        return super.display() + ":+";
    }
}
