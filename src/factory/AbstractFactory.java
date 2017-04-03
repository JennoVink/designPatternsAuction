package factory;

public interface AbstractFactory {
	abstract Product generateRandomProduct();
	abstract Product generateProduct(String string);

}
