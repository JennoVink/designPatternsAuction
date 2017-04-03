package factory;

import noPattern.Randomizer;

public class Plane extends Product {

	public Plane()
	{
		super("Plane", Randomizer.getRandomInt(1000, 2000), Randomizer.getRandomInt(400, 1000), Randomizer.getRandomInt(50, 500));
	}
	
	public String getDescription()
	{
		return "A plane";
	}
}
