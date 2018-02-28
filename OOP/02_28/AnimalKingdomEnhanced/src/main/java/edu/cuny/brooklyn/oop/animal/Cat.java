package edu.cuny.brooklyn.oop.animal;

//public class Cat extends Feline {
//
//	public Cat(String name) {
//		super(name);
//	}
//
//	@Override
//	public void makeNoise() {
//		System.out.println("Meowing");
//	}
//
//}


public class Cat extends Feline implements CatMotion {

	public Cat(String name) {
		super(name);
	}

	@Override
	public void makeNoise() {
		System.out.println("Meowing");
	}

	public void tap(Animal animal) {
		System.out.println("Tapping " + animal.getName());
	}
}
