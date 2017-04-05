package decorator;

import factory.Product;

public class XLSize extends Decorator {

	public XLSize(Product wrappedProduct)
	{
		super(wrappedProduct);
	}
	
	/**
	 * Adds its decoration to the description of the wrappedProduct.
	 */
	public String getDescription()
	{
		return wrappedProduct.getDescription() + ", in XL size";
	}
	
	/**
	 * Gives the wrappedProduct the right price increase according to the value 
	 * of the decoration.
	 *@return int - returns the startprice of the Product multiplied by a simple number.
	 */
	public int getStartPrice()
	{
		return (int)(wrappedProduct.getStartPrice() * 1.4);
	}
	
		
	
}
