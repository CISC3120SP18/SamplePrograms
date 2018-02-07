package edu.cuny.brooklyn.cisc3120.oop;

import java.util.Random;

public class SelectionExamples {
	public static void main(String[] args) {
		SelectionExamples examples = new SelectionExamples();
		
		System.out.println("examples.getCapital(\"USA\") ==> " + examples.getCapital("US"));
		System.out.println("examples.getCapital(\"UNITED MEXICAN STATES\") ==> " + examples.getCapital("UNITED MEXICAN STATES"));
		System.out.println("examples.getLetterGrade(91) ==> " + examples.getLetterGrade(91));
		
		System.out.println("examples.printVowelsAndConsonats() ==>");
		examples.printVowelsAndConsonats();
		System.out.println();
	}
	
	String getLetterGrade(double score) {
		String grade = "NG";
		if (score >= 90.) {
			grade = "A";
		} else if (score >=80.) {
			grade = "B";
		} else if (score >=70.) {
			grade = "C";
		} else if (score >=60.) {
			grade = "D";
		} else {
			grade = "F";
		}
		return grade;
		
		// for this particular method, alternatively you can also write it at least
		// two differnt forms. However, which one is better way to write? 
//		if (score >= 90) {
//			return "A";
//		}
//		
//		if (score >= 80) {
//			return "B";
//		}
//		
//		if (score >= 70) {
//			return "C";
//		}
//		
//		if (score >= 60) {
//			return "D";
//		}
//		
//		return "F";
		
		// for this particular method, alternatively you can also write
//		String grade = "NG";
//		if (score >= 90.) {
//			grade = "A";
//		}
//		
//		if (score >= 80 && score < 90) {
//			grade = "B";
//		}
//		
//		if (score >= 70 && score < 80) {
//			grade = "C";
//		}
//		
//		if (score >= 60 && score < 70) {
//			grade = "D";
//		}
//		
//		if (score < 60) {
//			grade = "F";
//		}
//		retrun grade;
		
	}
	
	String getCapital(String country) {
		switch(country.toUpperCase()) {
		case "US":
		case "USA":
		case "U.S.":
		case "U.S.A.":
		case "UNITED STATES":
		case "UNITED STATES OF AMERICA":
		case "THE UNITED STATES OF AMERICA":
			return "Washington DC";
		case "UK":
		case "BRITAIN":
		case "UNITED KINGDOM":
		case "THE UNITED KINGDOM":
		case "UNITED KINGDOM OF GREAT BRITAIN AND NORTHERN IRELAND":
		case "THE UNITED KINGDOM OF GREAT BRITAIN AND NORTHERN IRELAND":
			return "London";
		case "MX":
		case "MEXICO":
		case "UNITED MEXICAN STATES":
		case "THE UNITED MEXICAN STATES":
			return "Mexico City";
		case "CA":
		case "CANADA":
			return "Ottawa";
		default:
			return "Not yet supported";
		}
	}
	
	void printVowelsAndConsonats() {
		Random rand = new Random();
		for (int i = 0; i < 100; i++) {
			int c = rand.nextInt(26) + 'a';
			System.out.print((char) c + ", " + c + ": ");

			switch (c) {
			case 'a':
			case 'e':
			case 'i':
			case 'o':
			case 'u':
				System.out.println("vowel");
				break;
			case 'y':
			case 'w':
				System.out.println("Sometimes a vowel");
				break;
			default:
				System.out.println("consonant");
			}
		}
	}
}
