package decorator;

import factory.Product;
public class Warranty extends Decorator {

	public Warranty(Product wrappedProduct)
	{
		super(wrappedProduct);
	}
	
	public String getDescription()
	{
		return wrappedProduct.getDescription() + ", with warranty";
	}
	public int getPrice()
	{
		return (int)(wrappedProduct.getStartPrice() * 1.2); 
	}
		
	
}
