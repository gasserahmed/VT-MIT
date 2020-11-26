package ducksim;

public class DuckFactory {
    private static final DuckFactory duckFactory = new DuckFactory();

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
        return duck;
    }
}
