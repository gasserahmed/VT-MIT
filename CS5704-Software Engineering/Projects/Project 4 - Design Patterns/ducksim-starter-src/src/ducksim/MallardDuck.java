
package ducksim;

import java.awt.Color;

public class MallardDuck extends Duck {

    public MallardDuck() {
        setColor(Color.GREEN);
    }
    
    @Override
    public String display() {
        return "Mallard";
    }
    
}
