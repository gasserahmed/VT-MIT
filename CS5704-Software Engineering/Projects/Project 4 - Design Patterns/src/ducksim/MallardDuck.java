
package ducksim;

import java.awt.Color;

public class MallardDuck extends Duck {

    public MallardDuck() {
        setColor(Color.GREEN);
        setFlyBehavior(new FlyWithWings());
        setQuackBehavior(new QuackNormal());
    }
    
    @Override
    public String display() {
        return "Mallard";
    }
    
}
