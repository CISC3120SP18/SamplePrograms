package edu.cuny.brooklyn.design.view;

import edu.cuny.brooklyn.design.grade.TranscriptService;

public class SimpleShowGPAView extends ShowGPAView implements TranscriptServiceSetter {

	@Override
	public void setService(TranscriptService transcriptService) {
		if (transcriptService == null) {
			throw new IllegalArgumentException("TranscriptService must not be null.");
		}
		this.transcriptService = transcriptService;
	}
}
