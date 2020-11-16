package ducksim;

public class FlyWithWings implements FlyBehavior {

    @Override
    public State getState() {
        return State.FLYING;
    }

}
