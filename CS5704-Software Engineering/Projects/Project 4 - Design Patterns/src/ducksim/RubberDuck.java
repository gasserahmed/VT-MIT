
package ducksim;

import java.awt.Color;

public class RubberDuck extends Duck {
    
    public RubberDuck() {
        setColor(Color.YELLOW);
        setFlyBehavior(new FlyNoWay());
        setQuackBehavior(new QuackSqueek());
    }
    
    @Override
    public String display() {
        return "Rubber";
    }
}
