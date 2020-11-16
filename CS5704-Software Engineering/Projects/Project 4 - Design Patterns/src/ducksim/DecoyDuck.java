
package ducksim;

import java.awt.Color;

public class DecoyDuck extends Duck {
    
    public DecoyDuck() {
        setColor(Color.ORANGE);
        setFlyBehavior(new FlyNoWay());
        setQuackBehavior(new QuackNoWay());
    }
    
    @Override
    public String display() {
        return "Decoy";
    }
}
