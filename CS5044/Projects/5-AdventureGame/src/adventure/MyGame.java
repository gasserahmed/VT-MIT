package adventure;

import adventure.commands.*;

public class MyGame extends Game {
    public MyGame() {
        super(new EnhancedPlayer("Player"), new Parser());
    }

    public static void main(String[] args) {
        Game game = new MyGame();
        game.play();
    }

    @Override
    public void createCommands() {
        CommandWords commands = parser().commandWords();
        
        // no argument constructor; we need a second word
        commands.addCommand("go", new EnhancedGoCommand());
        // pre-populate the direction; no second word involved
        commands.addCommand("east", new EnhancedGoCommand("east"));
        // pre-populate the direction; no second word involved
        commands.addCommand("e", new EnhancedGoCommand("east"));
        
        commands.addCommand("west", new EnhancedGoCommand("west"));
        commands.addCommand("w", new EnhancedGoCommand("west"));
        commands.addCommand("north", new EnhancedGoCommand("north"));
        commands.addCommand("n", new EnhancedGoCommand("north"));
        commands.addCommand("south", new EnhancedGoCommand("south"));
        commands.addCommand("s", new EnhancedGoCommand("south"));
        
        commands.addCommand("help", new HelpCommand(commands));
        commands.addCommand("quit", new QuitCommand());
        commands.addCommand("examine", new ExamineCommand());
        commands.addCommand("x", new ExamineCommand());
        commands.addCommand("search", new SearchCommand());
        commands.addCommand("take", new TakeCommand());
        commands.addCommand("inventory", new InventoryCommand());
        commands.addCommand("i", new InventoryCommand());
        commands.addCommand("open", new OpenCommand());
        commands.addCommand("unlock", new UnlockCommand());
        commands.addCommand("replace", new ReplaceCommand());
    }

    @Override
    public void createRooms() {

    	// ======================================================
    	// create rooms
    	// ======================================================

    	EnhancedRoom yard = new EnhancedRoom("the front yard");
    	yard.setDescription("You are in the front yard of your house. "
    	        + "A <em>stone-walkway</em> leads up to the <em>front-door</em>. "
    	        + "A <em>wall-lantern</em> hangs next to the door. "
    	        + "It seems to be broken.");
    	yard.addProperty("dark");
        EnhancedRoom house = new EnhancedRoom("your house");
        house.setDescription("You are inside your house.");
        EnhancedRoom driveway = new EnhancedRoom("the driveway");
        driveway.addProperty("dark");
        driveway.setDescription("You are standing on your driveway, facing the garage. "
                + "Also, there's a <em>water-fountain.</em>");
        EnhancedRoom garage = new EnhancedRoom("your garage");
        garage.setDescription("You are in your garage. There are a few <em>old-boxes</em> on the "
                + "floor that don't look important, and there is a <em>shoebox</em> "
                + "on some shelves in the corner. A <em>side-door</em> leads to the house.");

        Door frontDoor = new Door("front-door", yard);
        frontDoor.addProperty("locked");
        frontDoor.setKey("house-key");
        Door sideDoor = new Door("side-door", garage);
        sideDoor.addProperty("locked");
        sideDoor.setKey("house-key");
        
    	// ======================================================
        // set room doors and exits
    	// ======================================================

        yard.setExit("north", house);
        yard.setExit("east", driveway);
        driveway.setExit("north", garage);
        driveway.setExit("west", yard);
        garage.setExit("west", house);
        garage.setExit("south", driveway);
        house.setExit("south", yard);
        house.setExit("east", garage);
        
        yard.setDoor("north", frontDoor);
        garage.setDoor("west", sideDoor);

        // # Create game objects
        // 1. construct and name object
        // 2. add description
        // 3. add properties
        // 4. move to room/container
        
    	// ======================================================
        // create container objects
    	// ======================================================

        Container shoebox = new Container("shoebox", garage);
        shoebox.addProperty("do-not-list");
        shoebox.addProperty("openable"); // closed by default
        shoebox.addProperty("local");
        Container flowerPot = new Container("flower-pot", yard);
        flowerPot.addProperty("local");
        flowerPot.setDescription("The flower pot is full of deep-red chrysanthemums.");
        Container waterFountain = new Container("water-fountain", driveway);
        waterFountain.addProperty("do-not-list");
        waterFountain.addProperty("fixed");
        waterFountain.setDescription("The water-fountain is not working.");
        
    	// ======================================================
        // create item (leaf) objects
    	// ======================================================

        Item wallLantern = new Item("wall-lantern", yard);
        wallLantern.setDescription(Message.examineLanternBroken());
        wallLantern.addProperty("do-not-list");
        wallLantern.addProperty("fixed");
        Item screwdriver = new Item("screwdriver", garage);
        screwdriver.setDescription("Your handy-dandy screwdriver.");
        Container oldBoxes = new Container("old-boxes", garage);
        oldBoxes.setDescription("You don't keep anything valuable in these old boxes.");
        oldBoxes.addProperty("do-not-list");
        oldBoxes.addProperty("open");
        
        Item stoneWalkway = new Item("stone-walkway", yard);
        stoneWalkway.setDescription("The stone-walkway leads up to the front-door of your house.");
        stoneWalkway.addProperty("do-not-list");
        stoneWalkway.addProperty("fixed");
        
        Item lightBulb = new Item("light-bulb", shoebox);
        
        Item houseKey = new Item("house-key", flowerPot);
        houseKey.addProperty("concealed");
        
        // the player starts the game outside
        player().moveTo(yard);
    }

    @Override
    public String welcomeMessage() {
        return
            "<p>You are locked out of your house, and you need to get in.</p>"
            + "<p>Type 'help' if you need help.</p>"
            + "<p>Hit [return] to continue...</p>";
    }
}