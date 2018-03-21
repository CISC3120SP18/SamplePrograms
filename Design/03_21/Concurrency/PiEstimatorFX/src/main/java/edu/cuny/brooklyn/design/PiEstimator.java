package edu.cuny.brooklyn.design;

import java.util.Random;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ProgressBar;
import javafx.scene.paint.Color;

public class PiEstimator {
	private long numOfPoints;
	private Random rngX;
	private Random rngY;
	private ProgressBar progressBar;

	public PiEstimator(long numOfPoints, long seedX, long seedY) {
		this.numOfPoints = numOfPoints;
		this.rngX = new Random(seedX);
		this.rngY = new Random(seedY);
	}
	
	public double run(GraphicsContext gc, double width, double height) {
		long accepted = 0l;
		for (long i=0; i<numOfPoints; i++) {
			double x = rngX.nextDouble();
			double y = rngY.nextDouble();
			if (x * x + y * y < 1.0) {
				accepted++;
				if (gc != null) {
					gc.setFill(Color.RED);
				}
			} else {
				if (gc != null) {
					gc.setFill(Color.RED);
				}
			}
			if (gc != null) {
				plotPoint(gc, x, y, width, height);
			}
			updateProgress(i, numOfPoints);
		}
		double pi = (double)accepted/(double)numOfPoints * 4.0;
		return pi;
	}
	
	public void setProgressBar(ProgressBar progressBar) {
		if (progressBar == null) {
			throw new IllegalArgumentException("ProgressBar must not be null.");
		}
		this.progressBar = progressBar;
	}
	
	private void updateProgress(long i, long max) {
		validateProgressBar();
		progressBar.setProgress((double)(i + 1)/(double)max);
	}
	
	private void validateProgressBar() {
		if (progressBar == null) {
			throw new IllegalArgumentException("ProgressBar must not be null.");
		}
	}
	
	private void plotPoint(GraphicsContext gc, double x, double y, double width, double height) {
		int w = (int) (width * x);
		int h = (int) (height * y);
		gc.fillRect(w, h, 1, 1);
		gc.stroke();
	}

}
