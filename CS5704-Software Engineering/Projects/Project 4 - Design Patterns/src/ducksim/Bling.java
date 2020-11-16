package ducksim;

import java.awt.Color;

public abstract class Bling extends Duck {
    private Duck duck;
    
    public Bling(Duck duck) {
        this.duck = duck;
    }
    
    @Override
    public void swim() {
        duck.swim();
    }
    
    @Override
    public void quack() {
        duck.quack();
    }
        
    @Override
    public String getQuack() {
        return duck.getQuack();
    }
    
    @Override
    public void fly() {
        duck.fly();
    }
    
    @Override
    public State getState() {
        return duck.getState();
    }
    
    @Override
    public Color getColor() {
        return duck.getColor();
    }
    
    @Override
    public void joinDSWC() {
        duck.joinDSWC();
    }
    
    @Override
    public void quitDSWC() {
        duck.quitDSWC();
    }
    
    @Override
    public boolean isOnDSWC() {
        return duck.isOnDSWC();
    }
    
    @Override
    public void capture() {
        duck.capture();
    }
    
    @Override
    public void release() {
        duck.release();
    }
    
    @Override
    public boolean isFree() {
        return duck.isFree();
    }
        
    @Override
    public String display() {
        return duck.display();
    }
}
