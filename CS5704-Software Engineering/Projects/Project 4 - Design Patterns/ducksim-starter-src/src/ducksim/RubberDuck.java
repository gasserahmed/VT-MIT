
package ducksim;

import java.awt.Color;

public class RubberDuck extends Duck {
    
    public RubberDuck() {
        setColor(Color.YELLOW);
    }
    
    @Override
    public void fly() {
        //
    }
    
    @Override
    public String getQuack() {
        return "Squeek!";
    }
    
    @Override
    public String display() {
        return "Rubber";
    }
}
