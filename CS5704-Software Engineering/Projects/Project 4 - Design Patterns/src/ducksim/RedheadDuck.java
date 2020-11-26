
package ducksim;

import java.awt.Color;

public class RedheadDuck extends Duck {
    
    public RedheadDuck() {
        setColor(Color.RED);
        originalFlyBehavior = new FlyWithWings();
        currentFlyBehavior = originalFlyBehavior;
        originalQuackBehavior = new QuackNormal();
        currentQuackBehavior = originalQuackBehavior;
    }
    
    @Override
    public String display() {
        return "Redhead";
    }
}
