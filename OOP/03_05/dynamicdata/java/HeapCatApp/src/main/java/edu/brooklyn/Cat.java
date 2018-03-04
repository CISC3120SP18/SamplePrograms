package edu.brooklyn;

public class Cat {
	private String name;

	public Cat() {
		this.name = "cat who does not yet to have a name";
	}

	public Cat(String name) {
		this.name = name;
	}

	public void tap(Cat other) {
		System.out.println(this.name + " is tapping " + other.name);
	}

	public String getName() {
		return name;
	}
}
