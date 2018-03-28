package edu.cuny.brooklyn.gui;

import java.util.Timer;
import java.util.TimerTask;

public class CsQuotesTimerController {
	private CsQuotesModel model;
	
	private Timer timer;
	
	
	public CsQuotesTimerController(CsQuotesModel model) {
		this.model = model;
	}
	
	public void startTimer() {
		if (timer != null) {
			timer.cancel();
		} 
		
		timer = new Timer();
		TimerTask task = new TimerTask() {

			@Override
			public void run() {
				model.advanceActiveQuoteIndex();
			}
			
		};
		
		timer.schedule(task, 0, 10000l);
	}
	
	public void stopTimer() {
		if (timer != null) {
			timer.cancel();
		}
	}
}
