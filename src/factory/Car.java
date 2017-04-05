package factory;

import noPattern.Randomizer;

public class Car extends Product {

	public Car()
	{
		//Call to the Product constructor, creating random
		//prices and sending the type.
		super("Car", 
				Randomizer.getRandomInt(5, 20) * 10, 
				Randomizer.getRandomInt(4, 10) * 10, 
				Randomizer.getRandomInt(1, 5) * 10, 
				Randomizer.getRandomUrl("car"));
	}
	
	/**
	 * returns the base description for this class.
	 */
	public String getDescription()
	{
		return "A car";
	}
}
