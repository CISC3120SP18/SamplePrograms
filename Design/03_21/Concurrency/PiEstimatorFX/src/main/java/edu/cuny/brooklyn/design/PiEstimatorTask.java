package edu.cuny.brooklyn.design;

import java.util.Random;

import javafx.concurrent.Task;

public class PiEstimatorTask extends Task<Double>{
	
	private final long numOfPoints;
	private final Random rngX;
	private final Random rngY;

	public PiEstimatorTask(long numOfPoints, long seedX, long seedY) {
		this.numOfPoints = numOfPoints;
		this.rngX = new Random(seedX);
		this.rngY = new Random(seedY);
	}
	
	@Override
	protected Double call() throws Exception {
		long accepted = 0;
		for (long i = 0; i < numOfPoints; i++) {
			if (isCancelled()) {
				break;
			}
			
			double x = rngX.nextDouble();
			double y = rngY.nextDouble();
			if (x * x + y * y < 1.0) {
				accepted++;
			} 
			updateProgress(i, numOfPoints-1);
			updateValue((double)accepted/(double)numOfPoints * 4.0);
		}
		return (double)accepted/(double)numOfPoints * 4.0;
	}
	
	@Override
	protected void succeeded() {
		super.succeeded();
		updateMessage(Double.toString(getValue()));
	}

}
