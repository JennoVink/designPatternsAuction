package observer;

import factory.Product;

public abstract class Bidder implements Observer{
	protected String name;
	protected Product currentProduct;
	protected int budget;
	protected Subject auctioneer;
	
	protected Bidder(int budget, String name, Subject auctioneer){
		this.budget = budget;
		this.name = name;
		this.auctioneer = auctioneer;
	}

	public void update(int count, Product p) {
				
	}
}
