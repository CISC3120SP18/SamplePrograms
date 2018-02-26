package edu.cuny.brooklyn.oop;

public class Equidae {
	private int numberOfToes;
	private String name;
	private String gender;

	public final String ORDER = "odd-toed ungulate";

	public Equidae(String name, String gender, int numberOfToes) {
		this.name = name;
		this.gender = gender;
		this.numberOfToes = numberOfToes;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getNumberOfToes() {
		return numberOfToes;
	}

	public String getGender() {
		return gender;
	}

	public String[] getMySong() {
		String[] lyrics = { "Sadly, I haven't heard a song written for us collectively!" };
		return lyrics;
	}

}
