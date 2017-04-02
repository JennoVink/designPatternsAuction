<<<<<<< HEAD
package factory;

import decorator.GiftPaper;
import decorator.Maintenance;
import decorator.Warranty;
import decorator.XLSize;
import noPattern.Randomizer;


public class ProductFactory implements AbstractFactory {

	//Returns a product generated with random integers.
	public Product generateRandomProduct()
	{
		int numberOfDecorators = Randomizer.getRandomInt(1,2);
		switch(Randomizer.getRandomInt(1, 3))
		{
			case 1: 
				return addDecorators(new Car(), numberOfDecorators);
			case 2:
				return addDecorators(new Plane(), numberOfDecorators);
			case 3:
				return addDecorators(new Bike(), numberOfDecorators);
		}
		System.out.println("Product is null!");
		return null;		
	}
	
	//Adds the decorators after the creation of the product is finished the
	//switch case inside check for duplicates and adds them to the decorator list.
	private Product addDecorators(Product product, int numberOfDecorators)
	{
		for(int i = 0; i < numberOfDecorators ; i++)
		{
			//pull a random number for a decorator
			switch(Randomizer.getRandomInt(1, 4))
			{
				case 1: 
					if(!product.containsDecorator("Warranty")){
					product = new Warranty(product);	
					product.setDecorator("Warranty");
					}
				case 2:
					if(!product.containsDecorator("Maintenance")){
					product = new Maintenance(product);	
					product.setDecorator("Maintenance");
					}
				case 3:
					if(!product.containsDecorator("GiftPaper")){
					product = new GiftPaper(product);
					product.setDecorator("GiftPaper");
					}
				case 4:
					if(!product.containsDecorator("XL")){
					product = new XLSize(product);
					product.setDecorator("XL");
					}
			}
		}
		return product;
	}
	
	//Generate a product with the already defined type, following the String parameter.
	public Product generateProduct(String type)
	{
		switch(type)
		{
			case "Car": 
				return addDecorators(new Car(), Randomizer.getRandomInt(1,2));
			case "Plane":
				return addDecorators(new Plane(), Randomizer.getRandomInt(1,2));
			case "Bike":
				return addDecorators(new Bike(), Randomizer.getRandomInt(1,2));
		}
		return null;	
	}
	
}
=======
package factory;

/**
 * Class that generates a Product for the Auctioneer randomly.
 */
public class ProductFactory implements AbstractFactory{

	public ProductFactory() {

		// TODO Auto-generated constructor stub
	}

	/**
	 * plz fix Daniël:
	 * Generate a random product (maybe use the Randomizer)
	 */
	@Override
	public Product generateRandomProduct(String string) {
		// TODO Auto-generated method stub
		return null;
	}

}
>>>>>>> branch 'master' of https://github.com/JennoVink/designPatternsAuction
