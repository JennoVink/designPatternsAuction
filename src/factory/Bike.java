package factory;

import noPattern.Randomizer;
public class Bike extends Product {

	public Bike()
	{
		super("Bike", Randomizer.getRandomInt(10, 20), Randomizer.getRandomInt(1, 5), Randomizer.getRandomInt(1, 5));
	}
	
	public String getDescription()
	{
		return "A bike";
	}
}
