    package adventure;

/**
 * Message file for the adventure game.
 * - do NOT delete any of the messages
 * - do NOT change method names or parameters
 * - DO add messages if you wish
 * - DO modify message is you wish (but keep parameters in message)
 *
 * @author Dr. K
 * @version 2019-11-04
 */
public class Message {
	
	private static Formatter formatter = Formatter.getInstance();

	// ==========================================================
	// Additional messages
	// ==========================================================
	
	public static final String commandRequiresSecond(String command) {
		return "The " + command + " command requires a second word.";
	}
	
	public static final String objectNotInScope(String obj) {
		return "You can't see any " + obj;
	}
	
	// ==========================================================
	// Examine
	// ==========================================================
	
	public static final String examineDefault(String obj) {
		return "You see nothing special about the " + obj + ".";		
	}
	
	public static final String examinePlayer() {
		return "You are an exemplary adventurer!";
	}

	// NOTE: When you examine a game object you should typically get it's description
	
	// NOTE: When you examine a container (like the shoebox), you should typically get:
	// (1) the container's description AND
	// (2) the message you would get if you searched the container (closed/empty/list)
	
	// ==========================================================
	// Search
	// ==========================================================
	
	public static final String searchDefault() {
		return "You find nothing of interest.";		
	}
	
	public static final String searchDark() {
		return "It's too dark around here to search for anything.";		
	}
	
	public static final String searchClosed(String obj) {
		return "The " + obj + " is closed.";
	}
	
	public static final String searchEmpty(String obj) {
		return "The " + obj + " is empty.";
	}
	
	public static final String searchList(String obj, String[] list) {
		return "In the " + obj + " you see " + formatter.commaSeparatedList(list, "a");
	}
	
	// ==========================================================
	// Open
	// ==========================================================
	
	public static final String openCant(String obj) {
		return "The " + obj + " is not something you can open.";
	}
	
	public static final String openLocked(String obj) {
		return "The " + obj + " is locked.";
	}
	
	public static final String openAlready(String obj) {
		return "The " + obj + " is already open.";
	}
	
	public static final String openSuccess(String obj) {
		return "You open the " + obj + ".";
	}
	
	// NOTE: When you successfully open a container (like the shoebox), you should typically get:
	// (1) the openSuccess message above AND
	// (2) the message you would get if you searched the container (empty/list)
	
	// ==========================================================
	// Unlock
	// ==========================================================
	
	public static final String unlockCant(String obj) {
		return "The " + obj + " is not something you can lock and unlock.";
	}
		
	public static final String unlockAlready(String obj) {
		return "The " + obj + " is already unlocked.";
	}
	
	public static final String unlockNoKey(String obj, String key) {
		return "You need the " + key + " to unlock the " + obj + ".";
	}
	
	public static final String unlockSuccess(String obj, String key) {
		return "You unlock the " + obj + " with the " + key + ".";
	}
	
	// ==========================================================
	// Inventory
	// ==========================================================
	
	public static final String invEmpty() {
		return "You are not carrying anything.";
	}

	public static final String invList(String[] contents) {
		return "You are carrying " + formatter.commaSeparatedList(contents, "the");
	}
	
	// ==========================================================
	// Take
	// ==========================================================
	
	public static final String takeCant(String obj) {
		return "You can't take the " + obj + ".";
	}
	
	// good for objects like containers:
	// the player might reasonably want to take them,
	// but you want to leave them in the current room
	public static final String takeLocal(String obj) {
		return "You don't need to take " + obj + ".";
	}
	
	public static final String takeAlready(String obj) {
		return "You aleady have the " + obj + ".";
	}
	
	public static final String takePlayer() {
		return "You are always self-possessed.";
	}
	
	public static final String takeSuccess(String obj) {
		return "You take the " + obj + ".";
	}

	// ==========================================================
	// Replace
	// ==========================================================

	public static final String replaceCant(String obj) {
		return "The " + obj + " is not something you need to replace.";
	}
	
	public static final String replaceAlready(String obj) {
		return "You have already replaced the " + obj + ".";
	}
	
	public static final String replacePlayer() {
		return "I like you just the way your are!";
	}
	
	public static final String replaceSuccess(String obj) {
		return "You carefully remove the old light-bulb and screw in the new one. It works! "
		        + "The wall-lantern lights up the area around your front-door again. "
		        + "You close the lantern, happy that you have at least accomplished something. "
		        + "Now if only you can only find that house-key!";
	}
	
	// ==========================================================
	// Locked-Out Specific
	// ==========================================================
	
	// ----------------------------------------------------------
	// Examine
	// ----------------------------------------------------------
	
	public static final String examineLanternBroken() {
		return "The wall-lantern is broken. The light-bulb is burned out.";
	}
	
	public static final String examineLanternOpen() {
		return "The wall-lantern is open. You need to replace the light-bulb.";
	}
	
	public static final String examineLanternFixed() {
		return "The wall-lantern is providing light.";
	}
	
	// ----------------------------------------------------------
	// Search
	// ----------------------------------------------------------
	
	public static final String searchPotAlready() {
		return "You find nothing else in the flower-pot.";
	}
	
	public static final String searchPotSuccess() {
		return "You sift through the dirt beneath the crysanthemums and come up with nothing. "
		        + "You tilt the flower-pot a bit, moving the lower branches of the plant aside, and"
		        + " you see something like a piece of metal catch the light. That's it -- "
		        + "you've found the house-key! You take the key and brush off the dirt.";
	}
	
	// ----------------------------------------------------------
	// Open
	// ----------------------------------------------------------
	
	public static final String openLanternAlready() {
		return "You no longer need to open the wall-lantern.";
	}
	
	public static final String openLanternNoKey() {
		return "You need the screwdriver to open the wall-lantern.";
	}
	
	public static final String openLanternSuccess() {
		return "You use the screwdriver to open the wall-lantern.";
	}

	// ----------------------------------------------------------
	// Replace
	// ----------------------------------------------------------
	
    public static final String replaceNoLightBulbsHere() {
        return "No light-bulbs around here need to be replaced.";
    }
    
    public static final String replaceOpenWallLanternFirst() {
        return "You need to open the wall-lantern before you can replace the light-bulb.";
    }
	
}
