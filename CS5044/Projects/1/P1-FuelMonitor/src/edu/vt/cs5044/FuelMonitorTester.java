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
        
        // Testing functionality after adding fuel twice
        System.out.println();
        monitor.addFuel(7.9);
        monitor.addFuel(2.3);
        System.out.println("-- After F(7.9), then F(2.3)");
        System.out.println("Trip miles (expected: 365) was: " + monitor.getTripMiles());
        System.out.println("Lifetime miles (expected: 590) was: " + monitor.getLifetimeMiles());

        // We added 7.9+2.3 gallons, so 1.532 + 7.9 + 2.3 = 11.732 gallons remain
        System.out.println("Fuel remaining (expected: 11.732) was: " + monitor.getFuelRemaining());
        System.out.println("Trip MPG (expected: 32.8) was: " + monitor.getTripMPG());
        System.out.println("Lifetime MPG (expected: 31.8) was: " + monitor.getLifetimeMPG());

        // Green miles remaining is 11.732 * 34.5 = 404.754 truncated to 400
        System.out.println("Green miles remaining (expected: 400) was: " + monitor.getGreenMilesRemaining());

        // Sport miles remaining is 11.732 * 18.0018... = 211.197... truncated to 210
        System.out.println("Sport miles remaining (expected: 210) was: " + monitor.getSportMilesRemaining());

        // Testing functionality after resetting and not driving any miles in any modes
        System.out.println();
        monitor.resetTrip();
        System.out.println("-- After R()");
        System.out.println("Trip miles (expected: 0) was: " + monitor.getTripMiles());
        System.out.println("Lifetime miles (expected: 590) was: " + monitor.getLifetimeMiles());
        System.out.println("Fuel remaining (expected: 11.732) was: " + monitor.getFuelRemaining());
        System.out.println("Trip MPG (expected: 0) was: " + monitor.getTripMPG());
        System.out.println("Lifetime MPG (expected: 31.8) was: " + monitor.getLifetimeMPG());
        
        // Green and Sport modes remaining miles will be 0 since (0/gallonsUsed)*fuelLevel = 0
        System.out.println("Green miles remaining (expected: 0) was: " + monitor.getGreenMilesRemaining());
        System.out.println("Sport miles remaining (expected: 0) was: " + monitor.getSportMilesRemaining());

        // Testing functionality after reset twice
        System.out.println();
        monitor.resetTrip();
        monitor.resetTrip();
        System.out.println("-- After R() then R()");
        System.out.println("Trip miles (expected: 0) was: " + monitor.getTripMiles());
        System.out.println("Lifetime miles (expected: 590) was: " + monitor.getLifetimeMiles());
        System.out.println("Fuel remaining (expected: 11.732) was: " + monitor.getFuelRemaining());
        System.out.println("Trip MPG (expected: 0) was: " + monitor.getTripMPG());
        System.out.println("Lifetime MPG (expected: 31.8) was: " + monitor.getLifetimeMPG());
        System.out.println("Green miles remaining (expected: 0) was: " + monitor.getGreenMilesRemaining());
        System.out.println("Sport miles remaining (expected: 0) was: " + monitor.getSportMilesRemaining());
        
        // Testing functionality after resetting and driving miles in green mode only
        System.out.println();
        monitor.resetTrip();
        monitor.useInGreenMode(11, 0.3);
        System.out.println("-- After R(), then G(11, 0.3)");
        System.out.println("Trip miles (expected: 11) was: " + monitor.getTripMiles());
        System.out.println("Lifetime miles (expected: 601) was: " + monitor.getLifetimeMiles());
        System.out.println("Fuel remaining (expected: 11.431999999999999) was: " + monitor.getFuelRemaining());
        System.out.println("Trip MPG (expected: 36.6) was: " + monitor.getTripMPG());
        System.out.println("Lifetime MPG (expected: 31.9) was: " + monitor.getLifetimeMPG());
        System.out.println("Green miles remaining (expected: 410) was: " + monitor.getGreenMilesRemaining());
        System.out.println("Sport miles remaining (expected: 0) was: " + monitor.getSportMilesRemaining());

        // Testing functionality after resetting and driving miles in sport mode only
        System.out.println();
        monitor.resetTrip();
        monitor.useInSportMode(3, 0.9);
        System.out.println("-- After R(), then S(3, 0.9)");
        System.out.println("Trip miles (expected: 3) was: " + monitor.getTripMiles());
        System.out.println("Lifetime miles (expected: 604) was: " + monitor.getLifetimeMiles());
        System.out.println("Fuel remaining (expected: 10.531999999999998) was: " + monitor.getFuelRemaining());
        System.out.println("Trip MPG (expected: 3.3) was: " + monitor.getTripMPG());
        System.out.println("Lifetime MPG (expected: 30.6) was: " + monitor.getLifetimeMPG());
        System.out.println("Green miles remaining (expected: 0) was: " + monitor.getGreenMilesRemaining());
        System.out.println("Sport miles remaining (expected: 30) was: " + monitor.getSportMilesRemaining());
        
        // Testing resetting after adding fuel without any driving
        System.out.println();
        monitor.addFuel(12.3);
        monitor.resetTrip();
        System.out.println("-- After F(12.3), then R()");
        System.out.println("Trip miles (expected: 0) was: " + monitor.getTripMiles());
        System.out.println("Lifetime miles (expected: 604) was: " + monitor.getLifetimeMiles());
        System.out.println("Fuel remaining (expected: 22.832) was: " + monitor.getFuelRemaining());
        System.out.println("Trip MPG (expected: 0) was: " + monitor.getTripMPG());
        System.out.println("Lifetime MPG (expected: 30.6) was: " + monitor.getLifetimeMPG());
        System.out.println("Green miles remaining (expected: 0) was: " + monitor.getGreenMilesRemaining());
        System.out.println("Sport miles remaining (expected: 0) was: " + monitor.getSportMilesRemaining());
     
        // Testing adding fuel after resetting trip without any driving
        System.out.println();
        monitor.resetTrip();
        monitor.addFuel(25.5);
        System.out.println("-- After R(), then F(25.5)");
        System.out.println("Trip miles (expected: 0) was: " + monitor.getTripMiles());
        System.out.println("Lifetime miles (expected: 604) was: " + monitor.getLifetimeMiles());
        System.out.println("Fuel remaining (expected: 48.332) was: " + monitor.getFuelRemaining());
        System.out.println("Trip MPG (expected: 0) was: " + monitor.getTripMPG());
        System.out.println("Lifetime MPG (expected: 30.6) was: " + monitor.getLifetimeMPG());
        System.out.println("Green miles remaining (expected: 0) was: " + monitor.getGreenMilesRemaining());
        System.out.println("Sport miles remaining (expected: 0) was: " + monitor.getSportMilesRemaining());
        
        // Testing driving in green mode between two resets
        System.out.println();
        monitor.resetTrip();
        monitor.useInGreenMode(3, 0.1);
        monitor.resetTrip();
        System.out.println("-- After R(), G(3, 0.1) then R()");
        System.out.println("Trip miles (expected: 0) was: " + monitor.getTripMiles());
        System.out.println("Lifetime miles (expected: 607) was: " + monitor.getLifetimeMiles());
        System.out.println("Fuel remaining (expected: 48.232) was: " + monitor.getFuelRemaining());
        System.out.println("Trip MPG (expected: 0) was: " + monitor.getTripMPG());
        System.out.println("Lifetime MPG (expected: 30.6) was: " + monitor.getLifetimeMPG());
        System.out.println("Green miles remaining (expected: 0) was: " + monitor.getGreenMilesRemaining());
        System.out.println("Sport miles remaining (expected: 0) was: " + monitor.getSportMilesRemaining());
        
        
        // Testing driving in sport mode between two resets
        System.out.println();
        monitor.resetTrip();
        monitor.useInSportMode(5, 1.2);
        monitor.resetTrip();
        System.out.println("-- After R(), S(5, 1.2), then R()");
        System.out.println("Trip miles (expected: 0) was: " + monitor.getTripMiles());
        System.out.println("Lifetime miles (expected: 612) was: " + monitor.getLifetimeMiles());
        System.out.println("Fuel remaining (expected: 47.032) was: " + monitor.getFuelRemaining());
        System.out.println("Trip MPG (expected: 0) was: " + monitor.getTripMPG());
        System.out.println("Lifetime MPG (expected: 29.1) was: " + monitor.getLifetimeMPG());
        System.out.println("Green miles remaining (expected: 0) was: " + monitor.getGreenMilesRemaining());
        System.out.println("Sport miles remaining (expected: 0) was: " + monitor.getSportMilesRemaining());
                
        // Testing driving in green mode between two fuel adding
        System.out.println();
        monitor.addFuel(10.5);
        monitor.useInGreenMode(8, 2.1);
        monitor.addFuel(7.3);
        System.out.println("-- After F(10.5), G(8, 2.1) then F(7.3)");
        System.out.println("Trip miles (expected: 8) was: " + monitor.getTripMiles());
        System.out.println("Lifetime miles (expected: 620) was: " + monitor.getLifetimeMiles());
        System.out.println("Fuel remaining (expected: 62.73199999999999) was: " + monitor.getFuelRemaining());
        System.out.println("Trip MPG (expected: 3.8) was: " + monitor.getTripMPG());
        System.out.println("Lifetime MPG (expected: 26.8) was: " + monitor.getLifetimeMPG());
        System.out.println("Green miles remaining (expected: 230) was: " + monitor.getGreenMilesRemaining());
        System.out.println("Sport miles remaining (expected: 0) was: " + monitor.getSportMilesRemaining());
        
        
        // Testing driving in sport mode between two fuel adding
        System.out.println();
        monitor.addFuel(20.7);
        monitor.useInSportMode(7, 3.1);
        monitor.addFuel(5.1);
        System.out.println("-- After F(20.7), S(7, 3.1) then F(5.1)");
        System.out.println("Trip miles (expected: 15) was: " + monitor.getTripMiles());
        System.out.println("Lifetime miles (expected: 627) was: " + monitor.getLifetimeMiles());
        System.out.println("Fuel remaining (expected: 85.43199999999999) was: " + monitor.getFuelRemaining());
        System.out.println("Trip MPG (expected: 2.8) was: " + monitor.getTripMPG());
        System.out.println("Lifetime MPG (expected: 23.9) was: " + monitor.getLifetimeMPG());
        System.out.println("Green miles remaining (expected: 320) was: " + monitor.getGreenMilesRemaining());
        System.out.println("Sport miles remaining (expected: 190) was: " + monitor.getSportMilesRemaining());
        
        
    }

}
