package edu.vt.cs5044;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

//CHECKSTYLE:OFF
/**
 * A starter JUnit test class for the MinivanDoor.
 *
 * NOTE: Javadocs are NOT required in this file.
 * Comments are provided here to help document the example test cases.
 *
 * Please be sure name all of your test methods very clearly,
 * with very descriptive method names, using in-line comments as necessary.
 * Also be sure to make it clear what single "action" is in each method.
 *
 * NOTE: Web-CAT will completely ignore style rules on this file.
 * Leave the below "@SuppressWarnings" annotation intact, to avoid
 * Eclipse warnings about Javadocs, and keep the CHECKSTYLE:OFF comment above.
 */
@SuppressWarnings("javadoc")
public class MinivanDoorTest
{

    private MinivanDoor vanDoor;

    /*
     * Called by JUnit each time it calls any test method.
     * Note the "@Before" annotation, immediately above the method signature.
     * You'll need to use the "@Test" annotation for all your test methods.
     * We'll use this to simply create a new default instance of MinivanDoor.
     */
    @Before
    public void setUp()
    {
        vanDoor = new MinivanDoor();
    }

    /*
     * Tests all the initial values of the constructor.
     * This also requires most accessor methods to be implemented properly.
     *
     * Don't forget the "@Test" annotation.
     */
    @Test
    public void testConstructorDefaults()
    {
        // Default setup

        assertFalse(vanDoor.isLocked());
        assertFalse(vanDoor.isOpen());
        assertFalse(vanDoor.isChildSafe());
        assertEquals(Gear.PARK, vanDoor.getGear());
        assertEquals(0, vanDoor.getLogEntryCount());
        assertNull(vanDoor.getLastLogEntry());
    }

    /*
     * Test activation of the lock button with default setup.
     * We expect the door to lock successfully,
     * with an event log entry that reflects this.
     *
     * Don't forget the "@Test" annotation.
     */
    @Test
    public void testLockDoorWithDefaults()
    {
        // Default setup

        vanDoor.pushLockButton(); // ACTION

        assertTrue(vanDoor.isLocked());
        assertEquals(LogEntry.DOOR_LOCKED, vanDoor.getLastLogEntry());
    }

    /*
     * Test opening the door with the open button, after locking the door.
     * We expect the door to remain closed and locked in this case,
     * with an event log entry that reflects this.
     *
     * Note how we use another test case as the setup here, so we can be sure
     * that the setup itself actually worked as expected before continuing.
     *
     * Don't forget the "@Test" annotation.
     */
    @Test
    public void testOpenButtonAfterLockedWithDefaults()
    {
        testLockDoorWithDefaults();

        vanDoor.pushOpenButton(); // ACTION

        assertTrue(vanDoor.isLocked());
        assertFalse(vanDoor.isOpen());
        assertEquals(LogEntry.OPEN_REFUSED_LOCK, vanDoor.getLastLogEntry());
    }

}
