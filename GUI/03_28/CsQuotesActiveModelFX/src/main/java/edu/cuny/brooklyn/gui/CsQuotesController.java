package edu.cuny.brooklyn.gui;

public class CsQuotesController {
	private CsQuotesModel model;
	private CsQuotesView view;
	
	public CsQuotesController(CsQuotesModel model, CsQuotesView view) {
		this.model = model;
		this.view = view;
	}
	
	public void initialize(int startIndex) {
		model.setActiveQuoteIndex(startIndex);
		view.updateView(startIndex);
		view.getNextQuoteButton().setOnAction(e -> nextQuote());
	}
	
	public void nextQuote() {
		model.advanceActiveQuoteIndex();
		view.updateView(model.getActiveQuoteIndex());
	}
}
