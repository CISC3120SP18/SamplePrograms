package edu.brooklyn.cuny.oop;

import edu.brooklyn.cuny.oop.vehicle.PassengerCar;
import edu.brooklyn.cuny.oop.vehicle.Vehicle;
import edu.brooklyn.cuny.oop.vehicle.WheeledVehicle;

public class VehicleApp {
    public static void main(String[] args) {
        Vehicle vehicles[] = {new WheeledVehicle(1000., 2, 2), new PassengerCar(6000., 5)};
        for (Vehicle v: vehicles) {
            System.out.println(v.toString());
        }
    }
}
