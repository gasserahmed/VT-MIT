package edu.vt.cs5044;

/**
 * Basic test suite for the FuelMonitor class.
 *
 * You will need to develop additional tests to ensure correctness of your system. This file is
 * ignored by Web-CAT, but it will be graded by a human to ensure you've added reasonable tests.
 *
 */
//CHECKSTYLE:OFF
@SuppressWarnings("javadoc")
public class FuelMonitorTester {

    public static void main(String[] args) {

        // create a new fuel monitor object
        System.out.println("-- Constructing new FuelMonitor object FM(14.5)");
        FuelMonitor monitor = new FuelMonitor(14.5);
        System.out.println("Trip miles (expected: 0) was: " + monitor.getTripMiles());
        System.out.println("Lifetime miles (expected: 0) was: " + monitor.getLifetimeMiles());
        System.out.println("Fuel remaining (expected: 14.5) was: " + monitor.getFuelRemaining());

        System.out.println();
        monitor.useInGreenMode(5, 0.123);
        System.out.println("-- After G(5, 0.123)");
        System.out.println("Trip miles (expected: 5) was: " + monitor.getTripMiles());
        System.out.println("Lifetime miles (expected: 5) was: " + monitor.getLifetimeMiles());

        // We've used 0.123 gallons, so 14.5 - 0.123 = 14.377 gallons remain
        System.out.println("Fuel remaining (expected: 14.377) was: " + monitor.getFuelRemaining());

        // Trip and Lifetime MPG are 5 / 0.123 = 40.6504... truncated to 40.6
        System.out.println("Trip MPG (expected: 40.6) was: " + monitor.getTripMPG());
        System.out.println("Lifetime MPG (expected: 40.6) was: " + monitor.getLifetimeMPG());

        // Green miles remaining is 40.6504... * 14.377 = 584.4309... truncated to 580
        System.out.println("Green miles remaining (expected: 580) was: " + monitor.getGreenMilesRemaining());

        System.out.println();
        monitor.useInSportMode(23, 1.234);
        monitor.useInGreenMode(150, 3.14);
        System.out.println("-- After S(23, 1.234) then G(150, 3.14)");
        System.out.println("Trip miles (expected: 178) was: " + monitor.getTripMiles());
        System.out.println("Lifetime miles (expected: 178) was: " + monitor.getLifetimeMiles());

        // We've used an additional 4.374 gallons, so 14.377 - 4.374 = 10.003 gallons remain
        System.out.println("Fuel remaining (expected: 10.003) was: " + monitor.getFuelRemaining());

        // Trip and Lifetime MPG are 178 / 4.497 = 39.5819... truncated to 39.5
        System.out.println("Trip MPG (expected: 39.5) was: " + monitor.getTripMPG());
        System.out.println("Lifetime MPG (expected: 39.5) was: " + monitor.getLifetimeMPG());

        // Green MPG this trip is 155 / 3.263 = 47.5023...
        // Green miles remaining is 10.003 * 47.5023... = 475.1655 truncated to 470
        System.out.println("Green miles remaining (expected: 470) was: " + monitor.getGreenMilesRemaining());

        // Sport MPG this trip is 23 / 1.234 = 18.6386...
        // Sport miles remaining is 10.003 * 18.6383... = 186.4417... truncated to 180
        System.out.println("Sport miles remaining (expected: 180) was: " + monitor.getSportMilesRemaining());

        System.out.println();
        monitor.addFuel(5.55);
        monitor.useInSportMode(0, 0.5);
        monitor.useInSportMode(42, 2.4);
        monitor.useInGreenMode(5, 0.01);
        System.out.println("-- After F(5.55), S(0, 0.5), S(42, 2.4), then G(5, 0.01)");
        System.out.println("Trip miles (expected: 225) was: " + monitor.getTripMiles());
        System.out.println("Lifetime miles (expected: 225) was: " + monitor.getLifetimeMiles());

        // We've added a net 2.64 gallons, so 10.003 + 2.64 = 12.643 gallons remain
        System.out.println("Fuel remaining (expected: 12.643) was: " + monitor.getFuelRemaining());

        // Trip and Lifetime MPG are 225 / (2.91 + 4.497) = 30.3767... truncated to 30.3
        System.out.println("Trip MPG (expected: 30.3) was: " + monitor.getTripMPG());
        System.out.println("Lifetime MPG (expected: 30.3) was: " + monitor.getLifetimeMPG());

        // Green MPG this trip is 160 / 3.273 = 48.8848...
        // Green miles remaining is 12.643 * 48.8848... = 618.0507... truncated to 610
        System.out.println("Green miles remaining (expected: 610) was: " + monitor.getGreenMilesRemaining());

        // Sport MPG this trip is 65 / 4.134 = 15.7233...
        // Sport miles remaining is 12.643 * 15.7233... = 198.7893... truncated to 190
        System.out.println("Sport miles remaining (expected: 190) was: " + monitor.getSportMilesRemaining());

        System.out.println();
        monitor.resetTrip();
        monitor.useInSportMode(20, 1.111);
        monitor.useInGreenMode(345, 10);
        System.out.println("-- After R(), S(20, 1.111), then G(345, 10)");
        System.out.println("Trip miles (expected: 365) was: " + monitor.getTripMiles());
        System.out.println("Lifetime miles (expected: 590) was: " + monitor.getLifetimeMiles());

        // We've used 11.111 gallons, so 12.643 - 11.111 = 1.532 gallons remain
        System.out.println("Fuel remaining (expected: 1.532) was: " + monitor.getFuelRemaining());

        // Trip MPG is 365 / 11.111 = 32.8503... truncated to 32.8
        System.out.println("Trip MPG (expected: 32.8) was: " + monitor.getTripMPG());

        // Lifetime MPG is 590 / (7.407 + 11.111) = 31.8609... truncated to 31.8
        System.out.println("Lifetime MPG (expected: 31.8) was: " + monitor.getLifetimeMPG());

        // Green MPG this trip is 345 / 10 = 34.5
        // Green miles remaining is 1.532 * 34.5 = 52.854 truncated to 50
        System.out.println("Green miles remaining (expected: 50) was: " + monitor.getGreenMilesRemaining());

        // Sport MPG this trip is 20 / 1.111 = 18.0018...
        // Sport miles remaining is 1.532 * 18.0018... = 27.5788... truncated to 20
        System.out.println("Sport miles remaining (expected: 20) was: " + monitor.getSportMilesRemaining());
        
        
        
        // TODO: Add your own test casese here...

    }

}
