package edu.vt.cs5044;

/**
 * A basic vehicle Fuel Monitor.
 *
 * Objects of this class track a vehicle's fuel usage and miles-per-gallon (MPG) efficiency, over
 * single trips and the lifetime of the object. The vehicle has two modes of operation, green mode
 * and sport mode, which have significantly different fuel efficiencies.
 *
 * Academic Note: In this project, you don't need to worry about validating the values received by
 * any of the methods. All parameters should be presumed valid and used as is. For example, you may
 * safely assume that the miles driven and fuel used will always be non-negative.
 *
 * Academic Note: Neither branches nor loops are required. Only primitive types int and double
 * should be used. The implementation must not use Math.round() or any other library. All
 * calculations should be performed at double precision, with only the final result being truncated
 * (where applicable) via arithmetic and casting.
 *
 */
public class FuelMonitor
{

    //
    // TODO: Your private instance variables (fields) go here
    //
    /**
     * Creates a new FuelMonitor object.
     *
     * The constructor will initialize the lifetime and trip miles counters to zero, and initialize
     * the fuel tank to be as specified. The first trip is implicitly started upon construction.
     *
     * @param initialFuel the starting amount of fuel in the tank, in gallons.
     */
    public FuelMonitor(double initialFuel)
    {
        // TODO: Your implementation goes here
    }

    /**
     * Indicate miles driven and gallons used in green mode.
     *
     * This method is called periodically by other components, as miles are driven and/or fuel is
     * used, while the vehicle is in green mode.
     *
     * @param miles number of miles driven.
     * @param gallons amount of fuel used.
     */
    public void useInGreenMode(int miles, double gallons)
    {
        // TODO: Your implementation goes here
    }

    /**
     * Indicate miles driven and gallons used in sport mode.
     *
     * This method is called periodically by other components, as miles are driven and/or fuel is
     * used, while the vehicle is in sport mode.
     *
     * @param miles number of miles driven.
     * @param gallons amount of fuel used.
     */
    public void useInSportMode(int miles, double gallons)
    {
        // TODO: Your implementation goes here
    }

    /**
     * Indicate that fuel has been added to the vehicle.
     *
     * This method is called when the fuel tank has been partially or completely refilled.
     *
     * @param gallons amount of fuel added.
     */
    public void addFuel(double gallons)
    {
        // TODO: Your implementation goes here
    }

    /**
     * Indicate that the trip meter has been reset.
     *
     * This method is called when the driver initiates a trip reset. This action completes the
     * current trip and begins a new trip.
     */
    public void resetTrip()
    {
        // TODO: Your implementation goes here
    }

    /**
     * Report the number of miles driven on the current trip.
     *
     * This includes all miles driven this trip, regardless of mode, since the last trip reset.
     *
     * @return miles driven this trip.
     */
    public int getTripMiles()
    {
        // TODO: Your implementation goes here
        return 0; // TODO: Replace this placeholder
    }

    /**
     * Report the average fuel efficiency of the current trip, in miles per gallon (MPG).
     *
     * This includes all trip miles, consistent with getTripMiles(), and all fuel used during the
     * current trip. The result is rounded down (truncated) to a multiple of 0.1 MPG.
     *
     * Academic Note: This method won't be called until at least some fuel has been used this trip.
     *
     * @return truncated fuel efficency this trip.
     */
    public double getTripMPG()
    {
        // TODO: Your implementation goes here
        return 0; // TODO: Replace this placeholder
    }

    /**
     * Report the amount of fuel remaining, in gallons.
     *
     * The value returned is exact, not truncated.
     *
     * @return fuel currently in the tank.
     */
    public double getFuelRemaining()
    {
        // TODO: Your implementation goes here
        return 0; // TODO: Replace this placeholder
    }

    /**
     * Report the estimated number of miles remaining, in sport mode, with the current fuel.
     *
     * The estimate is based on the actual fuel efficiency achieved during the sport mode portion of
     * this trip, and the fuel remaining according to getFuelRemaning(). The value returned is
     * rounded down (truncated) to the nearest multiple of 10 miles.
     *
     * Academic Note: This method won't be called until at least some fuel has been used this trip
     * in sport mode.
     *
     * @return estimated sport miles remaining.
     */
    public int getSportMilesRemaining()
    {
        // TODO: Your implementation goes here
        return 0; // TODO: Replace this placeholder
    }

    /**
     * Report the estimated number of miles remaining, in green mode, with the current fuel.
     *
     * The estimate is based on the actual fuel efficiency achieved during the green mode portion of
     * this trip, and the fuel remaining according to getFuelRemaning(). The value returned is
     * rounded down (truncated) to the nearest multiple of 10 miles.
     *
     * Academic Note: This method won't be called until at least some fuel has been used this trip
     * in green mode.
     *
     * @return estimated green miles remaining.
     */
    public int getGreenMilesRemaining()
    {
        // TODO: Your implementation goes here
        return 0; // TODO: Replace this placeholder
    }

    /**
     * Report the total number of miles driven.
     *
     * This is the sum of all miles driven in any mode since this object was constructed.
     *
     * @return lifetime miles driven.
     */
    public int getLifetimeMiles()
    {
        // TODO: Your implementation goes here
        return 0; // TODO: Replace this placeholder
    }

    /**
     * Report the average fuel efficiency, in miles per gallon, across all trips.
     *
     * The return value is rounded down (truncated) to a multiple of 0.1 MPG.
     *
     * Academic Note: This method won't be called until at least some fuel has been used.
     *
     * @return lifetime average miles per gallon.
     */
    public double getLifetimeMPG()
    {
        // TODO: Your implementation goes here
        return 0; // TODO: Replace this placeholder
    }

}
