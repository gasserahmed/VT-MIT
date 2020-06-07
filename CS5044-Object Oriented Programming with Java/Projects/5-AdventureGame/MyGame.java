package adventure;

public class MyGame extends Game {
    public MyGame() {
        super(new Player("Player"), new Parser());
    }

    public static void main(String[] args) {
        Game game = new MyGame();
        game.play();
    }

    @Override
    public void createCommands() {
        CommandWords commands = parser().commandWords();
        commands.addCommand("go",   new GoCommand());
        commands.addCommand("help", new HelpCommand(commands));
        commands.addCommand("quit", new QuitCommand());
    }

    @Override
    public void createRooms() {

    	// ======================================================
    	// create rooms
    	// ======================================================

    	Room yard = new Room("the front yard");
        Room house = new Room("your house");
        house.setDescription("You are inside your house.");
        Room driveway = new Room("the driveway");
        driveway.setDescription("You are standing on your driveway, facing the garage.");
        Room garage = new Room("your garage");

    	// ======================================================
        // set room exits
    	// ======================================================

        yard.setExit("north", house);
        yard.setExit("east", driveway);
        driveway.setExit("north", garage);
        driveway.setExit("west", yard);
        garage.setExit("west", house);
        garage.setExit("south", driveway);
        house.setExit("south", yard);
        house.setExit("east", garage);

        // # Create game objects
        // 1. construct and name object
        // 2. add description
        // 3. add properties
        // 4. move to room/container
        
    	// ======================================================
        // create container objects
    	// ======================================================

        Container shoebox = new Container("shoebox", garage);
        shoebox.addProperty("openable"); // closed by default
        
    	// ======================================================
        // create item (leaf) objects
    	// ======================================================

        // TODO Create class Door that assign common properties to new doors
        Item frontDoor = new Item("front-door", yard);
        frontDoor.addProperty("openable"); // closed by default
        frontDoor.addProperty("lockable"); // unlocked by default
        frontDoor.addProperty("locked");
        frontDoor.addProperty("concealed"); // put door in room description, not in list of contents
        
        Item screwdriver = new Item("screwdriver", garage);

        Item lightBulb = new Item("light-bulb", shoebox);
        
        // the player starts the game outside
        player().moveTo(yard);
    }

    @Override
    public String welcomeMessage() {
        return
            "<p>Welcome to The World of Simplicity!</p>"
            + "<p>Type 'help' if you need help.</p>"
            + "<p>Hit [return] to continue...</p>";
    }
}