package ducksim;

import java.util.List;

public class Flock extends Duck {
    private List<Duck> ducks;

    public Flock(List<Duck> ducks) {
        this.ducks = ducks;
        originalFlyBehavior = new FlyWithWings();
        currentFlyBehavior = originalFlyBehavior;
        originalQuackBehavior = new QuackNormal();
        currentQuackBehavior = originalQuackBehavior;
    }

    @Override
    public String getQuack() {
        return "Noise!";
    }

    @Override
    public void joinDSWC() {
        super.joinDSWC();
        for (Duck duck : ducks)
        {
            duck.joinDSWC();
        }
    }

    @Override
    public void quitDSWC() {
        super.quitDSWC();
        for (Duck duck : ducks)
        {
            duck.quitDSWC();
        }
    }

    @Override
    public void capture() {
        super.capture();
        for (Duck duck : ducks)
        {
            duck.capture();
        }
    }

    @Override
    public void release() {
        super.release();
        for (Duck duck : ducks)
        {
            duck.release();
        }
    }

    @Override
    public String display() {
        String displayText = "Flock";
        for (Duck duck : ducks)
        {
            displayText += ":" + duck.display().charAt(0);
        }

        return displayText;
    }
}
