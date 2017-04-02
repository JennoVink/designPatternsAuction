package factory;

import noPattern.Randomizer;

public class Car extends Product {

	public Car()
	{
		super("Car", Randomizer.getRandomInt(100, 200), Randomizer.getRandomInt(40, 100), Randomizer.getRandomInt(0, 50));
	}
	
	public String getDescription()
	{
		return "A car";
	}
}
