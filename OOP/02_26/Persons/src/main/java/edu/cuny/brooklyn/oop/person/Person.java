package edu.cuny.brooklyn.oop.person;

public class Person {
	protected String name;    // how about first, last, middle names?
	protected String id;      // is it a string?
	protected String address; // how about street numbers, city, zip code ...?
	
	public Person(String name, String id, String address) {
		this.name = name;
		this.id = id;
		this.address = address;
	}
	
	public void changeAddress(String address) {
		this.address = address;
	}
	
	public String getName() {
		return name;
	}
	
	public String getId() {
		return id;
	}
	
	public String getAddress() {
		return address;
	}
	
	public String toString() {
		return "Person (name=" + name + ", id=" + id + ", address=" + address + ")";
	}
}
