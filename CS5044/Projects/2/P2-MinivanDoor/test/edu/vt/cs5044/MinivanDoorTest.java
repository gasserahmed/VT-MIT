package edu.vt.cs5044;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

//CHECKSTYLE:OFF
/**
 * A starter JUnit test class for the MinivanDoor.
 *
 * NOTE: Javadocs are NOT required in this file. Comments are provided here to help document the
 * example test cases.
 *
 * Please be sure name all of your test methods very clearly, with very descriptive method names,
 * using in-line comments as necessary. Also be sure to make it clear what single "action" is in
 * each method.
 *
 * NOTE: Web-CAT will completely ignore style rules on this file. Leave the below
 * "@SuppressWarnings" annotation intact, to avoid Eclipse warnings about Javadocs, and keep the
 * CHECKSTYLE:OFF comment above.
 */
@SuppressWarnings("javadoc")
public class MinivanDoorTest
{

    private MinivanDoor vanDoor;

    /*
     * Called by JUnit each time it calls any test method. Note the "@Before" annotation,
     * immediately above the method signature. You'll need to use the "@Test" annotation for all
     * your test methods. We'll use this to simply create a new default instance of MinivanDoor.
     */
    @Before
    public void setUp()
    {
        vanDoor = new MinivanDoor();
    }

    /*
     * Tests all the initial values of the constructor. This also requires most accessor methods to
     * be implemented properly.
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
     * Test activation of the lock button with default setup. We expect the door to lock
     * successfully, with an event log entry that reflects this.
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
     * Test activation of the lock button when the door is already locked. We expect no action to be
     * taken and door remains locked, with an event log entry that reflects this.
     */
    @Test
    public void testLockDoorWhenDoorLocked()
    {
        testLockDoorWithDefaults(); // ACTION
        vanDoor.pushLockButton();

        assertTrue(vanDoor.isLocked());
        assertEquals(LogEntry.NO_ACTION_TAKEN, vanDoor.getLastLogEntry());
    }

    /*
     * Test activation of the unlock button with default setup. We expect the door to remain
     * unlocked with no actions taken, with an event log entry that reflects this.
     */
    @Test
    public void testUnLockDoorWithDefaults()
    {
        // Default setup

        vanDoor.pushUnlockButton(); // ACTION

        assertFalse(vanDoor.isLocked());
        assertEquals(LogEntry.NO_ACTION_TAKEN, vanDoor.getLastLogEntry());
    }

    /*
     * Test activation of the unlock button with default setup. We expect the door to unlock
     * successfully, with an event log entry that reflects this.
     */
    @Test
    public void testUnLockDoorWhenDoorLocked()
    {
        testLockDoorWithDefaults(); // ACTION
        vanDoor.pushUnlockButton();

        assertFalse(vanDoor.isLocked());
        assertEquals(LogEntry.DOOR_UNLOCKED, vanDoor.getLastLogEntry());
    }

    /*
     * Test opening the door with the open button, after locking the door. We expect the door to
     * remain closed and locked in this case, with an event log entry that reflects this.
     *
     * Note how we use another test case as the setup here, so we can be sure that the setup itself
     * actually worked as expected before continuing.
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

    /*
     * Test closing the door with the close button We expect the door to close in this case, with an
     * event log entry that reflects this.
     */
    @Test
    public void testCloseButtonWithDefaults()
    {
        vanDoor.closeDoor(); // ACTION

        assertFalse(vanDoor.isOpen());
        assertEquals(LogEntry.DOOR_CLOSED, vanDoor.getLastLogEntry());
    }

    /*
     * Test getting the 1st log entry when the event logs is empty We expect the log entry to be
     * null as there isn't any logs
     */
    @Test
    public void testGetLogEntryWhenEmptyWithDefaults()
    {
        assertEquals(null, vanDoor.getLogEntryAt(0));
    }

    /*
     * Test getting a log entry with index -1 We expect the log entry to be null
     */
    @Test
    public void testGetNegativeLogEntryWithDefaults()
    {
        assertEquals(null, vanDoor.getLogEntryAt(-1));
    }

    /*
     * Test getting the 1st log entry after performing a lock door action We expect the 1st log
     * entry to be returned with DOOR_LOCKED
     */
    @Test
    public void testGetLogEntryThatExists()
    {
        vanDoor.pushLockButton();

        assertEquals(LogEntry.DOOR_LOCKED, vanDoor.getLogEntryAt(0));
    }

    /*
     * Test getting the 2nd log entry after performing only a lock door action We expect the 2nd log
     * entry to be null as we only performed one action with a single log entry
     */
    @Test
    public void testGetNonExistentLogEntry()
    {
        vanDoor.pushLockButton();

        assertEquals(null, vanDoor.getLogEntryAt(1));
    }

    /*
     * Test setting gear to PARK when it's already in PARK We expect the gear to remain the same
     * with no action taken in this case, with an event log entry that reflects this.
     */
    @Test
    public void testSetGearParkWithDefaults()
    {
        vanDoor.setGear(Gear.PARK); // ACTION

        assertEquals(Gear.PARK, vanDoor.getGear());
        assertEquals(LogEntry.NO_ACTION_TAKEN, vanDoor.getLastLogEntry());
    }

    /*
     * Test setting gear to NEUTRAL We expect the gear to be changed in this case, with an event log
     * entry that reflects this.
     */
    @Test
    public void testSetGearNeutralWithDefaults()
    {
        vanDoor.setGear(Gear.NEUTRAL); // ACTION

        assertEquals(Gear.NEUTRAL, vanDoor.getGear());
        assertEquals(LogEntry.GEAR_RELEASED, vanDoor.getLastLogEntry());
    }

    /*
     * Test setting gear to PARK after NEUTRAL We expect the gear to be changed in this case, with
     * an event log entry that reflects this.
     */
    @Test
    public void testSetGearParkAfterNeutral()
    {
        testSetGearNeutralWithDefaults(); // ACTION
        vanDoor.setGear(Gear.PARK);

        assertEquals(Gear.PARK, vanDoor.getGear());
        assertEquals(LogEntry.GEAR_PARKED, vanDoor.getLastLogEntry());
    }

    /*
     * Test setting gear to DRIVE after NEUTRAL We expect the gear to be changed in this case but
     * with NO_ACTION_TAKEN in logs, with an event log entry that reflects this.
     */
    @Test
    public void testSetGearDriveAfterNeutral()
    {
        testSetGearNeutralWithDefaults(); // ACTION
        vanDoor.setGear(Gear.DRIVE);

        assertEquals(Gear.DRIVE, vanDoor.getGear());
        assertEquals(LogEntry.NO_ACTION_TAKEN, vanDoor.getLastLogEntry());
    }

    /*
     * Test setting a new gear that is not PARK We expect the gear to be changed in this case, with
     * a GEAR_RELEASED event log entry that reflects this.
     */
    @Test
    public void testSetGearNotPark()
    {
        vanDoor.setGear(Gear.DRIVE); // ACTION

        assertEquals(Gear.DRIVE, vanDoor.getGear());
        assertEquals(LogEntry.GEAR_RELEASED, vanDoor.getLastLogEntry());
    }

    /*
     * Test setting a child-safety to true when door is open We expect the child-safety to be
     * changed to be true in this case, with an event log entry that reflects this.
     */
    @Test
    public void testSetChildSafeOnWhenDoorOpen()
    {
        vanDoor.pushOpenButton(); // ACTION
        vanDoor.setChildSafe(true);

        assertTrue(vanDoor.isChildSafe());
        assertEquals(LogEntry.CHILD_SAFE_ENGAGED, vanDoor.getLastLogEntry());
    }

    /*
     * Test setting a child-safety to false when door is open and child-safety is on We expect the
     * child-safety to be changed to be false in this case, with an event log entry that reflects
     * this.
     */
    @Test
    public void testSetChildSafeOffWhenDoorOpenAndChildSafeOn()
    {
        testSetChildSafeOnWhenDoorOpen(); // ACTION
        vanDoor.setChildSafe(false);

        assertFalse(vanDoor.isChildSafe());
        assertEquals(LogEntry.CHILD_SAFE_DISENGAGED, vanDoor.getLastLogEntry());
    }

    /*
     * Test setting a child-safety to true when door is open and child-safety is on We expect the
     * child-safety to remain true with no actions taken in this case, with an event log entry that
     * reflects this.
     */
    @Test
    public void testSetChildSafeOnWhenDoorOpenAndChildSafeOn()
    {
        testSetChildSafeOnWhenDoorOpen(); // ACTION
        vanDoor.setChildSafe(true);

        assertTrue(vanDoor.isChildSafe());
        assertEquals(LogEntry.NO_ACTION_TAKEN, vanDoor.getLastLogEntry());
    }

    /*
     * Test setting a child-safety to false when door is open but child-safety is already off We
     * expect the child-safety to remain the same with no action taken in this case, with an event
     * log entry that reflects this.
     */
    @Test
    public void testSetChildSafeOffWhenDoorOpenAndChildSafeOff()
    {
        vanDoor.pushOpenButton(); // ACTION
        vanDoor.setChildSafe(false);

        assertFalse(vanDoor.isChildSafe());
        assertEquals(LogEntry.NO_ACTION_TAKEN, vanDoor.getLastLogEntry());
    }

    /*
     * Test setting child-safety to true when door is closed We expect the child-safety not to be
     * changed in this case, with an event log entry that reflects this.
     */
    @Test
    public void testSetChildSafeWhenDoorClosedWithDefaults()
    {
        vanDoor.setChildSafe(true); // ACTION

        assertFalse(vanDoor.isChildSafe());
        assertEquals(LogEntry.CHILD_SAFE_CHANGE_REFUSED, vanDoor.getLastLogEntry());
        assertEquals(1, vanDoor.getLogEntryCount());
    }

    /*
     * Test opening door by pull inside when child-safety is off We expect the door to open in this
     * case, with an event log entry that reflects this.
     */
    @Test
    public void testPullInsideHandleWithDefaults()
    {
        vanDoor.pullInsideHandle(); // ACTION

        assertTrue(vanDoor.isOpen());
        assertEquals(LogEntry.DOOR_OPENED, vanDoor.getLastLogEntry());
    }

    /*
     * Test opening door by pull inside when child-safety is on We expect the door not to open in
     * this case, with an event log entry that reflects this.
     */
    @Test
    public void testPullInsideHandleWithChildSafetyOn()
    {
        testSetChildSafeOnWhenDoorOpen(); // ACTION
        vanDoor.closeDoor();
        vanDoor.pullInsideHandle();

        assertFalse(vanDoor.isOpen());
        assertEquals(LogEntry.OPEN_REFUSED_CHILD_SAFE, vanDoor.getLastLogEntry());
    }

    /*
     * Test opening door by pull inside when gear is not on park We expect the door not to open in
     * this case, with an event log entry that reflects this.
     */
    @Test
    public void testPullInsideHandleWithGearNotPark()
    {
        testSetGearNotPark(); // ACTION
        vanDoor.pullInsideHandle();

        assertFalse(vanDoor.isOpen());
        assertEquals(LogEntry.OPEN_REFUSED_GEAR, vanDoor.getLastLogEntry());
    }

    /*
     * Test opening door by pull outside when it's unlocked and gear is on park We expect the door
     * to open in this case, with an event log entry that reflects this.
     */
    @Test
    public void testPullOutsideHandleWithDefaults()
    {
        vanDoor.pullOutsideHandle(); // ACTION

        assertTrue(vanDoor.isOpen());
        assertEquals(LogEntry.DOOR_OPENED, vanDoor.getLastLogEntry());
    }

    /*
     * Test opening door by pull outside when door is already opened We expect the door to remain
     * opened with no actions taken in this case, with an event log entry that reflects this.
     */
    @Test
    public void testPullOutsideHandleWhenDoorOpen()
    {
        testPullOutsideHandleWithDefaults(); // ACTION
        vanDoor.pullOutsideHandle();

        assertTrue(vanDoor.isOpen());
        assertEquals(LogEntry.NO_ACTION_TAKEN, vanDoor.getLastLogEntry());
    }

    /**
     * Testing dummy cases for ENUM coverages
     */
    @Test
    public void testDummyCaseForEnumCoverage()
    {
        assertNotNull(Gear.valueOf(Gear.PARK.name()));
        assertNotNull(LogEntry.valueOf(LogEntry.NO_ACTION_TAKEN.name()));
    }
}
