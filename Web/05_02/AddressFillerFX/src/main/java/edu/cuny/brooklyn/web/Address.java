package edu.cuny.brooklyn.web;

public class Address {

	private String city;
	private String state;
	private String country;

	public Address(String country, String state, String city) {
		this.country = country;
		this.state = state;
		this.city = city;
	}
	

	public String getCity() {
		return city;
	}

	public String getState() {
		return state;
	}

	public String getCountry() {
		return country;
	}
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("{").append("country=").append(country).append(",")
			.append("state=").append(state).append(",")
			.append("city=").append(city).append("}");
		return sb.toString();
	}

}
