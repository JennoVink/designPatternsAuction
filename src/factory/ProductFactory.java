package factory;

import decorator.GiftPaper;
import decorator.Maintenance;
import decorator.Warranty;
import decorator.XLSize;
import noPattern.Randomizer;
import testingPurpose.TestProduct;

import java.util.Collections;
import java.util.Stack;

public class ProductFactory implements AbstractFactory {

	/**
	 * Returns a product generated with random integers.
	 */
	public Product generateRandomProduct() {
		switch (Randomizer.getRandomInt(1, 3)) {
		case 1:
			return addDecorators(new Car(), null);
		case 2:
			return addDecorators(new Plane(), null);
		case 3:
			return addDecorators(new Bike(), null);
		default:
			System.out.println("Product type not recognized, default TestProdcut is set.");
			return addDecorators(new TestProduct(), null);
		}
	}

	/**
	 * Recursive function that decorates a product randomly. First a stack is
	 * initialized with random decorations. These decorations are applied
	 * recursively to the product.
	 * 
	 * @param product
	 *            the Product that'll get wrapped in the decorations.
	 * @param decoratorStack
	 *            a stack that holds the decorations (strings in this case,
	 *            classes and dynamic constructor calling didn't work out in
	 *            this case.
	 * @return Product a product with(out) random decoration(s).
	 */
	private Product addDecorators(Product product, Stack<String> decoratorStack) {
		if (decoratorStack == null) {
			decoratorStack = new Stack<>();
			decoratorStack.add("warranty");
			decoratorStack.add("maintenance");
			decoratorStack.add("giftpaper");
			decoratorStack.add("XLsize");

			Collections.shuffle(decoratorStack);

			for (int i = 0; i < Randomizer.getRandomInt(0, 4); i++) {
				decoratorStack.pop();
			}
		}
		
		//Here starts the fun part, we pop off a string
		//and continue to add them as decorators!
		if (!decoratorStack.empty()) {
			switch (decoratorStack.pop()) {
			case "warranty":
				product = new Warranty(product);
				break;
			case "maintenance":
				product = new Maintenance(product);
				break;
			case "giftpaper":
				product = new GiftPaper(product);
				break;
			case "XLsize":
				product = new XLSize(product);
				break;
			}
			//Start recursion
			return addDecorators(product, decoratorStack);
		}

		return product;
	}

	/**
	 * Generates a product with the already defined type, following the String
	 * parameter.
	 */
	public Product generateProduct(String type) {
		switch (type) {
		case "Car":
			return addDecorators(new Car(), null);
		case "Plane":
			return addDecorators(new Plane(), null);
		case "Bike":
			return addDecorators(new Bike(), null);
		default:
			System.out.println("Product type not recognized, default TestProdcut is set.");
			return addDecorators(new TestProduct(), null);
		}
	}
}
