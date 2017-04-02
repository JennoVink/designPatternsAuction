package factory;

import noPattern.Randomizer;
public class Bike extends Product {

	public Bike()
	{
		super("Bike", Randomizer.getRandomInt(100, 200), Randomizer.getRandomInt(40, 100), Randomizer.getRandomInt(0, 50));
	}
	
	public String getDescription()
	{
		return "A bike";
	}
}
