package ducksim;

public abstract class Bling extends Duck {
    protected Duck duck;

    public Bling(Duck duck) {
        this.duck = duck;
        originalFlyBehavior = duck.originalFlyBehavior;
        currentFlyBehavior = originalFlyBehavior;
        originalQuackBehavior = duck.originalQuackBehavior;
        currentQuackBehavior = originalQuackBehavior;
        color = duck.color;
    }

    @Override
    public String getQuack() {
        return duck.getQuack();
    }
}
