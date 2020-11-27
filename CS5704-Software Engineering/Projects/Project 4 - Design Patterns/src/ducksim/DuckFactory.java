package ducksim;

import java.util.ArrayList;

public class DuckFactory implements Subject {
    private static final DuckFactory duckFactory = new DuckFactory();
    private ArrayList<Observer> observers = new ArrayList<Observer>();

    public static DuckFactory getInstance() {
        return duckFactory;
    }

    public Duck createDuck(String duckType, int starCount, int moonCount,
            int crossCount) {
        Duck duck;
        switch (duckType) {
        case "Mallard":
            duck = new MallardDuck();
            break;
        case "Redhead":
            duck = new RedheadDuck();
            break;
        case "Rubber":
            duck = new RubberDuck();
            break;
        case "Decoy":
            duck = new DecoyDuck();
            break;
        case "Goose":
            duck = new GooseDuck(new Goose());
            break;
        default:
            duck = null;
        }

        // Apply star bling
        for (int starIndex = 0; starIndex < starCount; starIndex++)
        {
            duck = new StarBling(duck);
        }

        // Apply moon bling
        for (int moonIndex = 0; moonIndex < moonCount; moonIndex++)
        {
            duck = new MoonBling(duck);
        }

        // Apply cross bling
        for (int crossIndex = 0; crossIndex < crossCount; crossIndex++)
        {
            duck = new CrossBling(duck);
        }
        
        notifyObservers();
        return duck;
    }

    @Override
    public void registerObserver(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        int index = observers.indexOf(observer);
        observers.remove(index);
    }

    @Override
    public void notifyObservers() {
        for (Observer observer : observers) {
            observer.update();
        }
    }
}
