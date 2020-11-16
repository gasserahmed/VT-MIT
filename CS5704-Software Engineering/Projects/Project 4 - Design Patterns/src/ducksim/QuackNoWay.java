package ducksim;

public class QuackNoWay implements QuackBehavior {

    @Override
    public State getState() {
        return State.SWIMMING;
    }

    @Override
    public String getQuack() {
        return null;
    }

}
