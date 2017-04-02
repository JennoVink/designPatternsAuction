package factory;

import noPattern.Randomizer;

public class Plane extends Product {

	public Plane()
	{
		super("Plane", Randomizer.getRandomInt(100, 200), Randomizer.getRandomInt(40, 100), Randomizer.getRandomInt(0, 50));
	}
	
	public String getDescription()
	{
		return "A plane";
	}
}
