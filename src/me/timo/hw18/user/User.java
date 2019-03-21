package me.timo.hw18.user;

public class User {
	
	private String name;
	private boolean drinks;
	private boolean man;
	private int points;

	public User(String name, boolean drinks, boolean man) {
		this.name = name;
		this.drinks = drinks;
		this.man = man;
		points = 0;
	}
	
	public String getName() {
		return name;
	}
	
	public boolean doesDrink() {
		return drinks;
	}
	
	public int getPoints() {
		return points;
	}
	
	public void setPoints(int points) {
		this.points = points;
	}
	
	public boolean isMan() {
		return man;
	}
	
	public String toString() {
		return name + " | " + (drinks ? "Trinkt" : "Trinkt nicht") + " | " + points + "P";
	}

}
