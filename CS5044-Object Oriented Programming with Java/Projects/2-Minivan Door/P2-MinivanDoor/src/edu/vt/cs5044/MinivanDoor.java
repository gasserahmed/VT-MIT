package edu.vt.cs5044;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * MinivanDoor Class
 * This class has the constructor, private instances, helpers, and public methods
 * that will be used for MinivanDoor class
 *
 * @author gasser18
 * @version Sep 22, 2019
 *
 */
public class MinivanDoor
{
    private boolean open;
    private boolean locked;
    private boolean childSafe;
    private Gear gear;
    private final List<LogEntry> eventLog;
    
    

    /**
     * Create a new MinivanDoor object.
     *
     */
    public MinivanDoor()
    {
        open = false;
        locked = false;
        childSafe = false;
        gear = Gear.PARK;
        eventLog = new ArrayList<LogEntry>();
    }

    /**
     * Request the state of the door
     *
     * @return the current open/closed state of door
     */
    public boolean isOpen()
    {
        return open;
    }

    /**
     * Request the state of the door's lock
     *
     * @return the current locked/unlocked state of door
     */
    public boolean isLocked()
    {
        return locked;
    }

    /**
     * Request the state of the child-safety feature
     *
     * @return the current state of childSafe
     */
    public boolean isChildSafe()
    {
        return childSafe;
    }

    /**
     * Request the state of the gear shift lever
     *
     * @return the current gear status
     */
    public Gear getGear()
    {
        return gear;
    }

    /**
     * Request the most recent entry from the event log (returns null if the event log is empty)
     *
     * @return the last log that has been entered in our logs or null if logs are empty
     */
    public LogEntry getLastLogEntry()
    {
        if (eventLog.isEmpty()) 
        {
            return null;
        }        
        return eventLog.get(eventLog.size() - 1);
    }

    /**
     * Request the number of entries in the event log
     *
     * @return the number of logs that has been entered
     */
    public int getLogEntryCount()
    {
        return eventLog.size();
    }

    /**
     * Request the specified log entry event (returns null if the index is invalid)
     *
     * @param index the position of the required log in our logs
     * @return the requested log 
     */
    public LogEntry getLogEntryAt(int index)
    {
        if (eventLog.isEmpty() || index >= eventLog.size() || index < 0) 
        {
            return null;
        }        
        return eventLog.get(index);
    }

    /**
     * Indicate an activation of the gear shift lever
     *
     * @param newGear the new requested gear to be shifted to
     */
    public void setGear(Gear newGear)
    {
        if (newGear == gear) 
        {
            eventLog.add(LogEntry.NO_ACTION_TAKEN);
        }
        else
        {
            if (newGear.equals(Gear.PARK)) 
            {
                eventLog.add(LogEntry.GEAR_PARKED);
            }
            else 
            {
                if (gear == Gear.PARK) 
                {
                    eventLog.add(LogEntry.GEAR_RELEASED);
                }
                else 
                {
                    eventLog.add(LogEntry.NO_ACTION_TAKEN);
                }                
            }
            gear = newGear;            
        }                
    }

    /**
     * Indicate an activation of the child-safety (on or off)
     * 
     * @param engage the child safe state to be changed to
     * */
    public void setChildSafe(boolean engage)
    {
        if (childSafe == engage) 
        {
            eventLog.add(LogEntry.NO_ACTION_TAKEN);
        }
        else 
        {
            if (open) 
            {
                childSafe = engage;
                if (engage) 
                {
                    eventLog.add(LogEntry.CHILD_SAFE_ENGAGED);
                }
                else 
                {
                    eventLog.add(LogEntry.CHILD_SAFE_DISENGAGED);
                }            
            }
            else 
            {
                eventLog.add(LogEntry.CHILD_SAFE_CHANGE_REFUSED);
            }
        }                
    }

    /**
     * Indicate an activation of the dashboard lock button
     *
     */
    public void pushLockButton()
    {
        if (locked) 
        {
            eventLog.add(LogEntry.NO_ACTION_TAKEN);
        }
        else 
        {
            locked = true;
            eventLog.add(LogEntry.DOOR_LOCKED);
        }        
    }

    /**
     * Indicate an activation of the dashboard unlock button
     *
     */
    public void pushUnlockButton()
    {
        if (locked) 
        {
            locked = false;
            eventLog.add(LogEntry.DOOR_UNLOCKED);
        }
        else 
        {
            eventLog.add(LogEntry.NO_ACTION_TAKEN);
        }        
    }

    /**
     * Indicate an activation of the dashboard open button
     *
     */
    public void pushOpenButton()
    {
        processBasicOpen();
    }

    /**
     * Indicate an activation of the inside handle
     *
     */
    public void pullInsideHandle()
    {
        if (childSafe)
        {
            eventLog.add(LogEntry.OPEN_REFUSED_CHILD_SAFE);
        }
        else 
        {
            processBasicOpen();
        }
    }

    /**
     * Indicate an activation of the outside handle
     * 
     * */
    public void pullOutsideHandle()
    {
        processBasicOpen();
    }

    /**
     * Indicate an activation of the door closure sensor
     * 
     * */
    public void closeDoor()
    {
        open = false;
        eventLog.add(LogEntry.DOOR_CLOSED);
    }
    
    /**
     * Helper function for the door open process
     * 
     * */
    private void processBasicOpen() 
    {
        if (gear != Gear.PARK) 
        {
            eventLog.add(LogEntry.OPEN_REFUSED_GEAR);
        }
        else if (locked) 
        {
            eventLog.add(LogEntry.OPEN_REFUSED_LOCK);
        }
        else 
        {
            if (open) 
            {
                eventLog.add(LogEntry.NO_ACTION_TAKEN);
            }
            else 
            {
                open = true;
                eventLog.add(LogEntry.DOOR_OPENED);    
            }            
        }
    }        
    
}
