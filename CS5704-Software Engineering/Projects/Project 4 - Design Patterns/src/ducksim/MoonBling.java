package ducksim;

public class MoonBling extends Bling {
    public MoonBling(Duck duck) {
        super(duck);
    }
    
    @Override
    public String display() {
        return duck.display() + ":)";
    }
}
