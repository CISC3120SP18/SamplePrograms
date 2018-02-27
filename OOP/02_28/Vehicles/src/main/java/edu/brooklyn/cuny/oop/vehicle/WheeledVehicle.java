package edu.brooklyn.cuny.oop.vehicle;

public class WheeledVehicle extends Vehicle {
    int numOfWheels;
    public WheeledVehicle(double weight, int numOfSeats, int numOfWheels) {
        super(weight, numOfSeats);
        this.numOfWheels = numOfWheels;
    }
    public String toString() { return "WheeledVehicle { weight=" + weight 
            + ", numofSeats=" + numOfSeats +  ", numOfWheels=" + numOfWheels + " }"; }
}