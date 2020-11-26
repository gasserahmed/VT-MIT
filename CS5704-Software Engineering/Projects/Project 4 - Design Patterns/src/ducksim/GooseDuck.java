package ducksim;

import java.awt.Color;

public class GooseDuck extends Duck {
    Goose goose;

    public GooseDuck(Goose goose) {
        this.goose = goose;
        setColor(Color.BLUE);
        originalFlyBehavior = new FlyWithWings();
        currentFlyBehavior = originalFlyBehavior;
        originalQuackBehavior = new QuackNormal();
        currentQuackBehavior = originalQuackBehavior;
    }

    @Override
    public String getQuack() {
        return goose.getHonk();
    }

    @Override
    public String display() {
        return goose.getName();
    }
}
