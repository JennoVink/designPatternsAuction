package factory;

import noPattern.Randomizer;
public class Bike extends Product {

	public Bike()
	{
		//Call to the Product constructor, creating random
		//prices and sending the type.
		super("Bike", 
				Randomizer.getRandomInt(10, 20), 
				Randomizer.getRandomInt(1, 5), 
				Randomizer.getRandomInt(1, 5), 
				Randomizer.getRandomUrl("bicyle"));
	}
	
	/**
	 * returns the base description for this class.
	 */
	public String getDescription()
	{
		return "A bike";
	}
}
