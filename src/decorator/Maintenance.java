package decorator;

import factory.Product;
public class Maintenance extends Decorator {
	
	public Maintenance(Product wrappedProduct)
	{
		super(wrappedProduct);
	}
	
	public String getDescription()
	{
		return wrappedProduct.getDescription() + ", with a maintenance period";
	}
	public int getStartPrice()
	{
		return (int)(wrappedProduct.getStartPrice() * 1.2);
	}
		
	
}
