
package ducksim;

import java.awt.Color;

public abstract class Duck {
    protected FlyBehavior originalFlyBehavior;
    protected FlyBehavior currentFlyBehavior;
    protected QuackBehavior originalQuackBehavior;
    protected QuackBehavior currentQuackBehavior = originalQuackBehavior;
    protected Color color = Color.BLACK;
    private State state = State.SWIMMING;
    private boolean isFree = true;
    private boolean isOnDSWC = false;
    
    // typical duck commands
    
    public void swim() {
        state = State.SWIMMING;
    }
    
    public void quack() {
        state = currentQuackBehavior.getState();
    }
    
    public String getQuack() {
        return currentQuackBehavior.getQuack();
    }
    
    public void fly() {
        state = currentFlyBehavior.getState();
    }
    
    public State getState() {
        return state;
    }
    
    public void setState(State state) {
        this.state = state;
    }
    
    public void setColor(Color c) {
        color = c;
    }
    
    public Color getColor() {
        return color;
    }
    
    // join / quit and capture / release commands    

    public void joinDSWC() {
        isOnDSWC = true;
    }
    
    public void quitDSWC() {
        isOnDSWC = false;
    }
    
    public boolean isOnDSWC() {
        return isOnDSWC;
    }
    
    public void capture() {
        currentFlyBehavior = new FlyNoWay();
        currentQuackBehavior = new QuackNoWay();
        isFree = false;
    }
    
    public void release() {
        currentFlyBehavior = originalFlyBehavior;
        currentQuackBehavior = originalQuackBehavior;
        isFree = true;
    }
    
    public boolean isFree() {
        return isFree;
    }
    
    public abstract String display();
    
}
