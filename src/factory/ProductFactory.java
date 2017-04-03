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
				//return addDecorators(new Car(), null);
				return addDecorators(new Car(), null);
			case 2:
				return addDecorators(new Plane(), null);
			case 3:
				return addDecorators(new Bike(), null);
		}
		System.out.println("Product is null!");
		return null;		
	}
	
	//Adds the decorators after the creation of the product is finished the
	//switch case inside check for duplicates and adds them to the decorator list.
	private Product addDecorators(Product product, Stack<String> decoratorStack)
	{
//		Stack<Product> decoratorStack  = new Stack<>();
		
//		Product tempProduct = product;
		if(decoratorStack == null){
			decoratorStack = new Stack<>();
			decoratorStack.add("warranty");
			decoratorStack.add("maintenance");
			decoratorStack.add("giftpaper");
			decoratorStack.add("XLsize");
			
			Collections.shuffle(decoratorStack);
			
			for(int i = 0; i < Randomizer.getRandomInt(0,4); i++){
				decoratorStack.pop();
			}
		}				
		 
		if(!decoratorStack.empty()){
			switch(decoratorStack.pop()){
				case "warranty":
					product = new Warranty(product);
					break;
				case "maintenance":
					product = new Maintenance(product);
					break;
				case "giftpaper":
					product = new GiftPaper(product);
					break;
				case "XLsize":
					product = new XLSize(product);
					break;
					
			}
			
			return addDecorators(product, decoratorStack);
		}
			
		//System.out.println(product.getDescription());
		return product;
	}

	//Generate a product with the already defined type, following the String parameter.
	public Product generateProduct(String type)
	{
		switch(type)
		{
			case "Car": 
				return addDecorators(new Car(), null);
			case "Plane":
				return addDecorators(new Plane(), null);
			case "Bike":
				return addDecorators(new Bike(), null);
		}
		return null;	
	}
}
