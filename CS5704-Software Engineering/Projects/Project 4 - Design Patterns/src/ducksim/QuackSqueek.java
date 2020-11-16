package ducksim;

public class QuackSqueek implements QuackBehavior {

    @Override
    public State getState() {
        return State.QUACKING;
    }

    @Override
    public String getQuack() {
        return "Squeek!";
    }

}
