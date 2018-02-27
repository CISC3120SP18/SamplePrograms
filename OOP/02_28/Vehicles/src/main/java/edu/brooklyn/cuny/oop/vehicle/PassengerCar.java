package edu.brooklyn.cuny.oop.vehicle;

public class PassengerCar extends WheeledVehicle {
    public PassengerCar(double weight, int numOfSeats) {
        super(weight, numOfSeats, 4);
    }
    public String toString() {
        return "PassengerCar { weight=" + weight + ", numOfWheels=" + numOfWheels + ", numOfSeats=" + numOfSeats + " }";
    }
}
