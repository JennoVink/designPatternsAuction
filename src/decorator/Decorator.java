package decorator;

import factory.Product;

public abstract class Decorator extends Product {

	protected Product wrappedProduct;
	public Decorator(Product wrappedProduct)
	{
		//This is sad...
		super(wrappedProduct.getDescription(), 
				wrappedProduct.getStartPrice(), 
				wrappedProduct.getLowestPrice(), 
				wrappedProduct.getIncreasePrice());
		this.wrappedProduct = wrappedProduct;		
	}
	//Gives the description to Product.
	public abstract String getDescription();
	//Gives the price to Product
	public abstract int getPrice();
}
