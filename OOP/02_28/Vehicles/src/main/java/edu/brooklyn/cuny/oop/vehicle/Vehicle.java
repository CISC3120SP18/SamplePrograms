package edu.brooklyn.cuny.oop.vehicle;

public abstract class Vehicle {
    double weight;
    int numOfSeats;
    public Vehicle(double weight, int numOfSeats) { this.weight = weight; this.numOfSeats = numOfSeats; }
    public String toString() { return "Vehicle { weight=" + weight + ", numofSeats=" + numOfSeats +  " }"; }
}
