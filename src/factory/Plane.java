package factory;

import noPattern.Randomizer;

public class Plane extends Product {

	public Plane()
	{
		super("Plane", Randomizer.getRandomInt(11, 20) * 100, Randomizer.getRandomInt(4, 10) * 100, Randomizer.getRandomInt(5, 50) * 10, Randomizer.getRandomUrl("plane"));
	}
	
	public String getDescription()
	{
		return "A plane";
	}
}
