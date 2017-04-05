package decorator;

import factory.Product;
import noPattern.Bid;

public abstract class Decorator extends Product {

	//The object(Product) that is being wrapped by the decorator.
	protected Product wrappedProduct;
	
	public Decorator(Product wrappedProduct)
	{
		//Weird construction here, the decorator needs to extend
		//the Product so we have to call super() again.
		//We are just giving the object that we are wrapping back for the sake of it.
		super(wrappedProduct.getDescription(), 
				wrappedProduct.getStartPrice(), 
				wrappedProduct.getLowestPrice(), 
				wrappedProduct.getIncreasePrice(),
				wrappedProduct.getUrl());
		this.wrappedProduct = wrappedProduct;	
		
		//note: when an object is wrapped, their constructor is also called.
		//the problem is that the most outer wrapper new (e.g. a(new b(new c))) -> a is the most outer)
		//never sets the right price as a initial "null bidding".
		setHighestBid(new Bid(null, getStartPrice()));
		syncStartPrice();
	}
	//Gives the description to Product.
	public abstract String getDescription();
	//Gives the price to Product
	public abstract int getStartPrice();
}
