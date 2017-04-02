package decorator;

import factory.Product;

public class XLSize extends Decorator {

	public XLSize(Product wrappedProduct)
	{
		super(wrappedProduct);
	}
	
	public String getDescription()
	{
		return wrappedProduct.getDescription() + ", in XL size";
	}
	public int getPrice()
	{
		return (int)(wrappedProduct.getStartPrice() * 1.4);
	}
	
		
	
}
