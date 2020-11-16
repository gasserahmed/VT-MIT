package ducksim;

public class QuackNormal implements QuackBehavior {

    @Override
    public State getState() {
        return State.QUACKING;
    }

    @Override
    public String getQuack() {
        return "Quack!";
    }

}
