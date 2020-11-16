package ducksim;

public class StarBling extends Bling {
    public StarBling(Duck duck) {
        super(duck);
    }
    
    public String display() {
        return super.display() + ":*";
    }
}
