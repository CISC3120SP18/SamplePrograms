package edu.cuny.brooklyn.design;

public class PiEstimatorState {
	private double x;
	private double y;
	private boolean isAccepted;
	private long numOfAccepted;
	
	public PiEstimatorState(double x, double y, boolean isAccepted, long numOfAccepted) {
		this.x = x;
		this.y = y;
		this.isAccepted = isAccepted;
		this.numOfAccepted = numOfAccepted;
	}

	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}

	public boolean isAccepted() {
		return isAccepted;
	}

	public long getNumOfAccepted() {
		return numOfAccepted;
	}
}
