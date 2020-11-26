
package ducksim;

import java.awt.Color;

public class DecoyDuck extends Duck {
    
    public DecoyDuck() {
        setColor(Color.ORANGE);
        originalFlyBehavior = new FlyNoWay();
        currentFlyBehavior = originalFlyBehavior;
        originalQuackBehavior = new QuackNoWay();
        currentQuackBehavior = originalQuackBehavior;
    }
    
    @Override
    public String display() {
        return "Decoy";
    }
}
