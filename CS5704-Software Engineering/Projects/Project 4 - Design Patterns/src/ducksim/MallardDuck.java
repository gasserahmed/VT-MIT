
package ducksim;

import java.awt.Color;

public class MallardDuck extends Duck {

    public MallardDuck() {
        setColor(Color.GREEN);
        originalFlyBehavior = new FlyWithWings();
        currentFlyBehavior = originalFlyBehavior;
        originalQuackBehavior = new QuackNormal();
        currentQuackBehavior = originalQuackBehavior;
    }

    @Override
    public String display() {
        return "Mallard";
    }

}
