package edu.cuny.brooklyn.oop;

import java.text.ParseException;

import edu.cuny.brooklyn.oop.SeveralHorsesApp.Horse;
import edu.cuny.brooklyn.oop.SeveralHorsesApp.HorseWithSong;

public class SeveralHorsesNotSoGreatApp {
	public static void main(String[] args) throws ParseException {
		// Exercise 5
		SeveralHorsesApp horsesApp = new SeveralHorsesApp();
		Equidae[] horses = { horsesApp.new Horse("major", "male", "07/16/2004"),
				horsesApp.new Horse("khan", "male", "06/05/1998") };

		for (Equidae aHorse : horses) {
			if (aHorse instanceof Horse) {
				System.out.println("Horse " + aHorse.getName() + " is " + ((Horse) aHorse).getAge() + " years old.");
			}
		}
		
		// Exercise 6
		HorseWithSong horse = new HorseWithSong();
		for(String line: horse.getSong()) {
			System.out.println(line);
		}
	}
}
