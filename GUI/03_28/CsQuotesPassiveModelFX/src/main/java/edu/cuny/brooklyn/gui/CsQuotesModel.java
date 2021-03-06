package edu.cuny.brooklyn.gui;

public class CsQuotesModel {
	private static final String[] QUOTES = { 
			"In computing, the mean time to failure keeps getting shorter.",
			"Everything should be built top-down, except the first time.",
			"Computers are good at following instructions, but not at reading your mind.",
			"Computer Science is no more about computers than astronomy is about telescopes.",
			"Besides a mathematical inclination, an exceptionally good mastery of one's native tongue is the most vital asset of a competent programmer." };
	private static final String[] AUTHORS = { "Alan Perlis", "Alan Perlis", "Donald Knuth", "Edsger Dijkstra",
			"Edsger Dijkstra" };
	private static final String[] PORTRAITS = { "alan_perlis.jpg", "alan_perlis.jpg", "don_knuth.jpg",
			"edsger_dijkstra.jpg", "edsger_dijkstra.jpg" };
	
	private int activeQuoteIndex;
	
	public CsQuotesModel() {
		activeQuoteIndex = 0;
	}
	
	public void advanceActiveQuoteIndex() {
		activeQuoteIndex = (activeQuoteIndex + 1) % getNumOfQuotes();
	}
	
	public int getActiveQuoteIndex() {
		return activeQuoteIndex;
	}

	public String getQuote(int index) {
		return QUOTES[index];
	}

	public String getAuthor(int index) {
		return AUTHORS[index];
	}

	public String getPortrait(int index) {
		return PORTRAITS[index];
	}

	public int getNumOfQuotes() {
		return QUOTES.length;
	}
	
	public void setActiveQuoteIndex(int activeQuoteIndex) {
		this.activeQuoteIndex = activeQuoteIndex;
	}
}
