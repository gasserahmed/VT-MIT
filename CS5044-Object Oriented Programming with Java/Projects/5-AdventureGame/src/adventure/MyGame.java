package adventure;

import adventure.commands.*;

/**
 * 
 * This class has public methods that overrides those in Game class
 *
 * @author gasser18
 * @version Nov 16, 2019
 *
 */
public class MyGame extends Game {
    /**
     * Create a new MyGame object.
     *
     */
    public MyGame() {
        super(new EnhancedPlayer("Player"), new Parser());
    }

    /**
     * The main method where the game starts
     *
     * @param args arguments/parameters
     */
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
        commands.addCommand("close", new CloseCommand());
        commands.addCommand("hide", new HideCommand());
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
        EnhancedRoom houseEntrance = new EnhancedRoom("your house entrance");
        houseEntrance.setDescription("You are inside your house entrance.");
        EnhancedRoom driveway = new EnhancedRoom("the driveway");
        driveway.addProperty("dark");
        driveway.setDescription("You are standing on your driveway, facing the garage. "
                + "Also, there's a <em>water-fountain.</em>");
        EnhancedRoom garage = new EnhancedRoom("your garage");
        garage.setDescription("You are in your garage. There are a few <em>old-boxes</em> on the "
                + "floor that don't look important, and there is a <em>shoebox</em> "
                + "on some shelves in the corner. A <em>side-door</em> leads to the house.");
        EnhancedRoom livingRoom = new EnhancedRoom("the living room");
        livingRoom.setDescription("You are inside your living room.");
        EnhancedRoom bedroom = new EnhancedRoom("your bedroom");
        bedroom.setDescription("You are inside your bedroom. There is a <em>bed</em> with a "
                + "<em>mattress</em> on it and a <em>closet beside it.</em>");
        EnhancedRoom kitchen = new EnhancedRoom("the kitchen");
        kitchen.setDescription("You are inside kitchen.");
        EnhancedRoom bathroom = new EnhancedRoom("the bathroom");
        bathroom.setDescription("You are inside your bathroom.");
        EnhancedRoom storageRoom = new EnhancedRoom("the storage room");
        storageRoom.setDescription("You are inside your storage room.");
        Door frontDoor = new Door("front-door", yard);
        frontDoor.addProperty("locked");
        frontDoor.setKey("house-key");
        Door sideDoor = new Door("side-door", garage);
        sideDoor.addProperty("locked");
        sideDoor.setKey("house-key");
        Door bathRoomDoor = new Door("bath-door", bathroom);
        Door bedroomDoor = new Door("bed-door", bedroom);
        
        // ======================================================
        // set room doors and exits
        // ======================================================

        yard.setExit("north", houseEntrance);
        yard.setExit("east", driveway);
        
        driveway.setExit("north", garage);
        driveway.setExit("west", yard);
        
        garage.setExit("west", houseEntrance);
        garage.setExit("south", driveway);
        
        houseEntrance.setExit("south", yard);
        houseEntrance.setExit("east", garage);
        houseEntrance.setExit("west", livingRoom);
        
        livingRoom.setExit("east", houseEntrance);
        livingRoom.setExit("west", bedroom);
        livingRoom.setExit("south", kitchen);
        livingRoom.setExit("north", bathroom);
        
        bedroom.setExit("east", livingRoom);
        kitchen.setExit("north", livingRoom);
        bathroom.setExit("south", livingRoom);
        
        yard.setDoor("north", frontDoor);
        garage.setDoor("west", sideDoor);
        livingRoom.setDoor("west", bedroomDoor);
        livingRoom.setDoor("north", bathRoomDoor);

        // # Create game objects
        // 1. construct and name object
        // 2. add description
        // 3. add properties
        // 4. move to room/container
        
        // ======================================================
        // create container objects
        // ======================================================

        Container wallLantern = new Container("wall-lantern", yard);
        wallLantern.setDescription(Message.examineLanternBroken());
        wallLantern.addProperty("do-not-list");
        wallLantern.addProperty("fixed");
        wallLantern.addProperty("openable");
        
        Container flowerPot = new Container("flower-pot", yard);
        flowerPot.addProperty("local");
        flowerPot.setDescription("The flower pot is full of deep-red chrysanthemums.");
        
        Container shoebox = new Container("shoebox", garage);
        shoebox.addProperty("do-not-list");
        shoebox.addProperty("openable"); // closed by default
        shoebox.addProperty("local");
        
        Container oldBoxes = new Container("old-boxes", garage);
        oldBoxes.setDescription("You don't keep anything valuable in these old boxes.");
        oldBoxes.addProperty("do-not-list");
        oldBoxes.addProperty("open");
        
        Container waterFountain = new Container("water-fountain", driveway);
        waterFountain.addProperty("do-not-list");
        waterFountain.addProperty("fixed");
        waterFountain.setDescription("The water-fountain is not working.");
        
        Container bed = new Container("bed", bedroom);
        bed.addProperty("do-not-list");
        bed.addProperty("local");
        bed.addProperty("fixed");
        bed.addProperty("openable");
        bed.addProperty("open");
        
        Container tvTable = new Container("television-table", livingRoom);
        tvTable.addProperty("do-not-list");
        tvTable.addProperty("local");
        tvTable.addProperty("fixed");
        tvTable.addProperty("openable");
        tvTable.addProperty("open");
        
        Container closet = new Container("closet", bedroom);
        closet.addProperty("do-not-list");
        closet.addProperty("local");
        closet.addProperty("fixed");
        closet.addProperty("openable");
        closet.addProperty("open");
        
        Container wallet = new Container("wallet", bedroom);
        wallet.setDescription("The wallet is almost empty and for some reason it is open.");
        wallet.addProperty("openable");
        wallet.addProperty("open");

        Container diningTable = new Container("dining-table", kitchen);
        diningTable.addProperty("local");
        diningTable.addProperty("openable");
        diningTable.addProperty("open");

        
        // ======================================================
        // create item (leaf) objects
        // ======================================================

        Item screwdriver = new Item("screwdriver", garage);
        screwdriver.setDescription("Your handy-dandy screwdriver.");
        
        Item stoneWalkway = new Item("stone-walkway", yard);
        stoneWalkway.setDescription("The stone-walkway leads up to the front-door of your house.");
        stoneWalkway.addProperty("do-not-list");
        stoneWalkway.addProperty("fixed");
        
        Item lightBulb = new Item("light-bulb", shoebox);
        lightBulb.setDescription("The light-bulb will enlighten your path.");
        
        Item houseKey = new Item("house-key", flowerPot);
        houseKey.addProperty("concealed");
        
        Item creditCard = new Item("credit-card", houseEntrance);
        creditCard.addProperty("concealed");
        
        Item driverLicense = new Item("driver-license", wallet);
        driverLicense.addProperty("concealed");
        
        Item wallClock = new Item("wall-clock", livingRoom);
        wallClock.setDescription("The wall clock is off by one hour.");

        Item tv = new Item("television", tvTable);
        tv.addProperty("do-not-list");
        tv.addProperty("fixed");
        
        Item mattress = new Item("mattress", bed);
        mattress.addProperty("do-not-list");
        mattress.addProperty("fixed");
        
        Item jacket = new Item("jacket", closet);
        jacket.setDescription("The jacket is medium sized and has three pockets.");
        
        Item microwave = new Item("microwave", kitchen);
        microwave.addProperty("local");
        
        Item spoon = new Item("spoon", diningTable);
        spoon.setDescription("The spoon has some rice leftovers");
        
        Item shower = new Item("shower", bathroom);
        shower.addProperty("fixed");
        
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