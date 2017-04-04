package decorator;

import factory.Product;
import noPattern.Bid;
import observer.RandomBidder;

public abstract class Decorator extends Product {

	protected Product wrappedProduct;
	public Decorator(Product wrappedProduct)
	{
		//This is interesting...
		super(wrappedProduct.getDescription(), 
				wrappedProduct.getStartPrice(), 
				wrappedProduct.getLowestPrice(), 
				wrappedProduct.getIncreasePrice(),
				wrappedProduct.getUrl());
		this.wrappedProduct = wrappedProduct;	
		
		//note: when an object is wrapped, their constructor are also called.
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
