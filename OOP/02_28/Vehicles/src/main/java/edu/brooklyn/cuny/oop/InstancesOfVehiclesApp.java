package edu.brooklyn.cuny.oop;

import edu.brooklyn.cuny.oop.vehicle.PassengerCar;
import edu.brooklyn.cuny.oop.vehicle.Vehicle;
import edu.brooklyn.cuny.oop.vehicle.WheeledVehicle;

public class InstancesOfVehiclesApp {
	public static void main(String[] args) {
		Vehicle docHudson = new PassengerCar(5000, 5);
		PassengerCar sallyCarrera = new PassengerCar(4500, 5);
		Vehicle ramone = new WheeledVehicle(5000, 5, 4); 
		
		if (docHudson instanceof Vehicle) {
			System.out.println("docHudson instanceof Vehicle: docHudson is an instance of the Vehicle data type");
		}
		if (docHudson instanceof WheeledVehicle) {
			System.out.println("docHudson instanceof Vehicle: docHudson is an instance of the WheeledVehicle data type");
		}
		if (docHudson instanceof PassengerCar) {
			System.out.println("docHudson instanceof Vehicle: docHudson is an instance of the Passenger data type");
		}
		System.out.println("----------------------------------");
		
		if (ramone.getClass().isInstance(docHudson)) {
			System.out.println("ramone.getClass().isInstance(docHudson): docHudson is an instance of the data type (WheeledVehicle) of which Ramone is");
		} else {
			System.out.println("ramone.getClass().isInstance(docHudson): docHudson ***ISN'T*** an instance of the data type (WheeledVehicle) of which Ramone is");
		}
		
		if (sallyCarrera.getClass().isInstance(docHudson)) {
			System.out.println("sallyCarrera.getClass().isInstance(docHudson): docHudson is an instance of the data type (PassengerCar) of which sallyCarrera is");
		} else {
			System.out.println("sallyCarrera.getClass().isInstance(docHudson): docHudson is ***NOT*** an instance of the data type (PassengerCar) of which sallyCarrera is");
		}
		System.out.println("----------------------------------");		
		
		Vehicle mater = new WheeledVehicle(8000, 5, 4);
		if (docHudson instanceof WheeledVehicle) {
			System.out.println("docHudson instanceof WheeledVehicle: docHudson is an instance of the WheeledVehicle");
		}
		if (mater.getClass().isInstance(docHudson)) {
			System.out.println("mater.getClass().isInstance(docHudson): docHudson is an instance of the data type of which mater is");
		} else {
			System.out.println("mater.getClass().isInstance(docHudson): docHudson is ***NOT*** an instance of the data type of which mater is");
		}
		
		if (docHudson.getClass().isAssignableFrom(mater.getClass())) {
			System.out.println("docHudson.getClass().isAssignableFrom(mater.getClass()): docHudson can be assiged from Mater");
			docHudson = mater;
		} else {
			System.out.println("docHudson.getClass().isAssignableFrom(mater.getClass()): docHudson ***CANNOT*** be assiged from Mater");
			try {
				docHudson = (PassengerCar)mater;
			} catch(ClassCastException e) {
				System.out.println("docHudson = (PassengerCar)mater: " + e.getMessage());
			}
		}

		if (mater.getClass().isAssignableFrom(docHudson.getClass())) {
			System.out.println("mater.getClass().isAssignableFrom(docHudson.getClass()): mater can be assiged from docHudson");
			mater = (WheeledVehicle)docHudson;
		} else {
			System.out.println("mater.getClass().isAssignableFrom(docHudson.getClass()): mater ***CANNOT*** be assiged from docHudson");
			mater = (WheeledVehicle)docHudson;
		}
	}
}
