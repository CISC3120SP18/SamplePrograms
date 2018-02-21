package edu.cuny.brooklyn.oop.animal;

public class Direction {
	// use a spherical coordinate system, see http://mathworld.wolfram.com/SphericalCoordinates.html
	private double azimuthal; // azimuthal angle theta
	private double polar;  // polar angle phi
	
	public Direction(double azimuthalAngle, double polarAngle) {
		azimuthal = azimuthalAngle;
		polar = polarAngle;
	}
	
	public double getAzimuthualAngle() {
		return azimuthal;
	}
	
	public double getPolarAngle() {
		return polar;
	}
	
	public String toString() {
		return "(" + azimuthal + "," + polar + ")";
	}
}
