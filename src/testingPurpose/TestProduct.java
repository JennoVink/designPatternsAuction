package testingPurpose;

import factory.Product;
import noPattern.Randomizer;

public class TestProduct extends Product{
	public TestProduct(){
		super("testProduct", 2200, 100, 300, Randomizer.getRandomUrl("test"));
	}
	
}
