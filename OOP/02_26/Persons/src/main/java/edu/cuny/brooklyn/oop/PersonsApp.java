package edu.cuny.brooklyn.oop;

import edu.cuny.brooklyn.oop.person.Person;
import edu.cuny.brooklyn.oop.person.Student;

public class PersonsApp 
{
	
	public static void main(String[] args) {
		Person ben = new Person("Ben Franklin", "00124", "2901 Bedford Ave");
		Student adam = new Student("Adam Smith", "00248", "2902 Bedford Ave");
		System.out.println(ben.toString());
		System.out.println(adam.toString());
	}
	
	
	
//	public static void main(String[] args) {
//		Person ben = new Person("Ben Franklin", "00124", "2901 Bedford Ave");
//		Person adam = new Student("Adam Smith", "00248", "2902 Bedford Ave");
//		System.out.println(ben.toString());
//		System.out.println(adam.toString());
//	}

	
	
	
//	public static void main(String[] args) {
//		Person ben = new Person("Ben Franklin", "00124", "2901 Bedford Ave");
//		Person adam = new Student("Adam Smith", "00248", "2902 Bedford Ave");
//		display(ben);
//		display(adam);
//	}
//
//	public static void display(Person person) {
//		System.out.println(person.toString());
//	}
	
	
//	public static void main(String[] args) {
//		Person ben = new Person("Ben Franklin", "00124", "2901 Bedford Ave");
//		Student adam = new Student("Adam Smith", "00248", "2902 Bedford Ave");
//		adam.haveTakenClass("CISC3120");
//		display(ben); display(adam);
//	}	
//	
//	public static void display(Person person) {
//		System.out.println(person.toString());
//	}	
	
	
//	public static void main(String[] args) {
//		Person ben = new Person("Ben Franklin", "00124", "2901 Bedford Ave");
//		Person adam = new Student("Adam Smith", "00248", "2902 Bedford Ave");
//		((Student)adam).haveTakenClass("CISC3120");
//		display(ben); display(adam);
//	}	
//	
//	public static void display(Person person) {
//		System.out.println(person.toString());
//	}
}
