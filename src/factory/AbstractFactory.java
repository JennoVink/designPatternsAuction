package factory;

public interface AbstractFactory {
	//Generate a random product without parameters.
	abstract Product generateRandomProduct();
	//Generate a product following the type defined in the String parameter.
	abstract Product generateProduct(String string);
	
}
