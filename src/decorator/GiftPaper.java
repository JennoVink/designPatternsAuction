package decorator;

import factory.Product;
public class GiftPaper extends Decorator {

	public GiftPaper(Product wrappedProduct)
	{
		super(wrappedProduct);
	}
	
	public String getDescription()
	{
		return wrappedProduct.getDescription() + ", wrapped in gift paper";
	}
	public int getPrice()
	{
		return (int)(wrappedProduct.getStartPrice() * 1.05);  
	}
}
