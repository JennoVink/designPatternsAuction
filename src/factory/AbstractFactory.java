package factory;

public interface AbstractFactory {
	/**
	 * Generates a random product from scratch and decorates it.
	 * @return Product - A random product chosen from the classes that extend Product.
	 */
	abstract Product generateRandomProduct();
	
	/**
	 * Generates a product with the type given.
	 * @param type - the type of a specific product.
	 * @return Product - A product from the given type
	 */
	abstract Product generateProduct(String type);

}
