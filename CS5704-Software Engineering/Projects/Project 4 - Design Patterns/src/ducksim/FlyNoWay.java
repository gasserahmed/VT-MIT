package ducksim;

public class FlyNoWay implements FlyBehavior {

    @Override
    public State getState() {
        return State.SWIMMING;
    }

}
