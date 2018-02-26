package edu.cuny.brooklyn.oop;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class SeveralHorsesApp {
	public static void main(String[] args) throws ParseException {
		SeveralHorsesApp app = new SeveralHorsesApp();
		
		// Exercise 1
		Equidae[] horses = {
			app.new Horse("major", "male", "07/16/2004"),
			app.new Horse("khan", "male", "06/05/1998")
		};
		
		for (Equidae aHorse: horses) {
			if (aHorse instanceof Horse) {
				System.out.println("Horse " + aHorse.getName() + " is " + ((Horse) aHorse).getAge() + " years old.");
			}
		}
		
		// Exercise 2
		System.out.println();
		System.out.println("Let's repeat!");
		app.createAndPrintHorses();
		
		// Exercise 3
		System.out.println();
		System.out.println("Another horse song:");
		app.createHorseWithSongInObject();
		
		// Exercise 4
		System.out.println();
		System.out.println("Another horse song. Let's repeat.");
		createHorseWithSongInClass();		
	}
	
	public void createAndPrintHorses() throws ParseException {
		Equidae[] horses = {
				this.new Horse("major", "male", "07/16/2004"),
				this.new Horse("khan", "male", "06/05/1998")
			};
			
		for (Equidae aHorse : horses) {
			if (aHorse instanceof Horse) {
				System.out.println("Horse " + aHorse.getName() + " is " + ((Horse) aHorse).getAge() + " years old.");
			}
		}
	}
	
	public void createHorseWithSongInObject() {
		HorseWithSong horse = new HorseWithSong();
		for(String line: horse.getSong()) {
			System.out.println(line);
		}
	}
	
	public static void createHorseWithSongInClass() {
		HorseWithSong horse = new HorseWithSong();
		for(String line: horse.getSong()) {
			System.out.println(line);
		}
	}
	
	class Horse extends Equidae {
		private Date birthday;
		
		public Horse(String name, String gender, String mmddyyyy) throws ParseException {
			super(name, gender, 1);
			
			DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
			this.birthday = df.parse(mmddyyyy);
		}
		
		public Date getBirthday() {
			return birthday;
		}
		
		public int getAge() {
			Calendar currentCalendar = Calendar.getInstance();
			Date currentDate = new Date();
			currentCalendar.setTime(currentDate);
			
			Calendar birthdayCalendar = Calendar.getInstance();
			birthdayCalendar.setTime(birthday);
			
			int yearsDiff = currentCalendar.get(Calendar.YEAR) - birthdayCalendar.get(Calendar.YEAR);
			int daysDiff = currentCalendar.get(Calendar.DAY_OF_YEAR) - birthdayCalendar.get(Calendar.DAY_OF_YEAR);
			if (daysDiff < 0) {
				yearsDiff --;
			}
			
			return yearsDiff;
		}
	}
	
	static class HorseWithSong extends Equidae {
		public HorseWithSong() {
			super("Horse", "Any", 1);
		}
		
		public String[] getSong() {
			String[] lyrics = {
					"My horse is really smart.",
					"There’s so much he can do.",
					"He knows how to chop carrots",
					"and cook them in a stew."
			};
			return lyrics;
		}
	}
}
