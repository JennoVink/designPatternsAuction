package factory;

import noPattern.Randomizer;

public class Plane extends Product {

	public Plane()
	{
		//Call to the Product constructor, creating random
		//prices and sending the type.
		super("Plane", 
				Randomizer.getRandomInt(11, 20) * 100, 
				Randomizer.getRandomInt(4, 10) * 100, 
				Randomizer.getRandomInt(5, 50) * 10, 
				Randomizer.getRandomUrl("plane"));
	}
	
	/**
	 * returns the base description for this class.
	 */
	public String getDescription()
	{
		return "A plane";
	}
}
