
package ducksim;

import java.awt.Color;

public class RedheadDuck extends Duck {
    
    public RedheadDuck() {
        setColor(Color.RED);
        setFlyBehavior(new FlyWithWings());
        setQuackBehavior(new QuackNormal());
    }
    
    @Override
    public String display() {
        return "Redhead";
    }
}
