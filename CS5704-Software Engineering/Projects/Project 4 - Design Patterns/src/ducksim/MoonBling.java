package ducksim;

public class MoonBling extends Bling {
    public MoonBling(Duck duck) {
        super(duck);
    }
    
    public String display() {
        return super.display() + ":)";
    }
}
