
package ducksim;

import java.awt.Color;

public class RubberDuck extends Duck {
    
    public RubberDuck() {
        setColor(Color.YELLOW);
        originalFlyBehavior = new FlyNoWay();
        currentFlyBehavior = originalFlyBehavior;
        originalQuackBehavior = new QuackSqueek();
        currentQuackBehavior = originalQuackBehavior;
    }
    
    @Override
    public String display() {
        return "Rubber";
    }
}
