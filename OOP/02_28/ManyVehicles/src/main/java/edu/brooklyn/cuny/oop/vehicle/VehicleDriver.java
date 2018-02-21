package edu.brooklyn.cuny.oop.vehicle;

public class VehicleDriver {
    public static void main(String[] args) {
        Vehicle vehicles[] = {new WheeledVehicle(1000., 2, 2), new PassengerCar(6000., 5)};
        for (Vehicle v: vehicles) {
            System.out.println(v.toString());
        }
    }
}
