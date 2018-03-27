package edu.cuny.brooklyn.gui;

public class CsQuotesController {
	private CsQuotesModel model;
	private CsQuotesView view;
	private int quoteIndex;
	
	public CsQuotesController(CsQuotesModel model, CsQuotesView view) {
		this.model = model;
		this.view = view;
	}
	
	public void initialize(int startIndex) {
		this.quoteIndex = startIndex;
		view.updateView(startIndex);
		view.getNextQuoteButton().setOnAction(e -> nextQuote());
	}
	
	public void nextQuote() {
		quoteIndex = (quoteIndex + 1) % model.getNumOfQuotes();
		view.updateView(quoteIndex);
	}
}
