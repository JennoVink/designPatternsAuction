package factory;

import decorator.Decorator;
import decorator.GiftPaper;
import decorator.Maintenance;
import decorator.Warranty;
import decorator.XLSize;
import noPattern.Randomizer;

import java.util.Collections;
import java.util.Stack;


public class ProductFactory implements AbstractFactory {

	//Returns a product generated with random integers.
	public Product generateRandomProduct()
	{
		switch(Randomizer.getRandomInt(1, 3))
		{
			case 1: 
				return addDecorators(new Car());
			case 2:
				return addDecorators(new Plane());
			case 3:
				return addDecorators(new Bike());
		}
		System.out.println("Product is null!");
		return null;		
	}
	
	//Adds the decorators after the creation of the product is finished the
	//switch case inside check for duplicates and adds them to the decorator list.
	private Product addDecorators(Product product)
	{
		Stack<Product> decoratorStack  = new Stack<>();
		
		Product tempProduct = product;
		
		decoratorStack.add(new Warranty(tempProduct));
		decoratorStack.add(new Maintenance(tempProduct));
		decoratorStack.add(new GiftPaper(tempProduct));
		decoratorStack.add(new XLSize(tempProduct));
		
		Collections.shuffle(decoratorStack);
		
		int maxIter = Randomizer.getRandomInt(0,4);
		
		for(int i = 0; i < maxIter ; i++)
		{
			System.out.println(i);
			product = decoratorStack.pop();
		}
		return product;
	}
	
	private Product wrapProduct(Decorator dec, Product pro)
	{
		return new GiftPaper(pro);
	}
	
	//Generate a product with the already defined type, following the String parameter.
	public Product generateProduct(String type)
	{
		switch(type)
		{
			case "Car": 
				return addDecorators(new Car());
			case "Plane":
				return addDecorators(new Plane());
			case "Bike":
				return addDecorators(new Bike());
		}
		return null;	
	}
	
}
