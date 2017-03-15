package observer;

import factory.Product;

public interface Observer {
	abstract void update(int count, Product p);
}
