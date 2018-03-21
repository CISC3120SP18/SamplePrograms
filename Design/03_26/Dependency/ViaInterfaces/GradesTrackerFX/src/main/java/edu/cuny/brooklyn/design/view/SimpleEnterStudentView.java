package edu.cuny.brooklyn.design.view;

import edu.cuny.brooklyn.design.grade.TranscriptService;
import javafx.stage.Stage;

public class SimpleEnterStudentView extends EnterStudentView implements TranscriptServiceSetter {

	public SimpleEnterStudentView(Stage nextStage) {
		super(nextStage);
		setShowGPAView(new SimpleShowGPAView());
	}
	
	@Override
	public void setService(TranscriptService transcriptService) {
		if (transcriptService == null) {
			throw new IllegalArgumentException("TranscriptService must not be null.");
		}
		this.transcriptService = transcriptService;
	}
}
