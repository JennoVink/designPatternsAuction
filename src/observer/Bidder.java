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
	
	/**
	 * Depending on the currentProduct and the param count, here the subclasses have to decide
	 * if they want to make a bid or not.
	 * @param count
	 */
	protected abstract void makeBid(int count);

	/**
	 * A method that checks if the bidder is able to make a bid.
	 * @return true if the bidder has enough budget, false if not.
	 */
	public boolean haveEnoughBudget(){	
		if(this.budget >= (currentProduct.getHighestBid().getPrice() + currentProduct.getIncreasePrice())){
			System.out.println("have enough budget!");
			return true;
		}	
		System.out.println("i am to poor!");
		return false;
	}
	
	/**
	 * It makes no sense that a person makes two biddings after eachother.
	 * @return true if the Bidder object (this) is the same as the highestBidder on the current product.
	 */
	public final boolean amIHighestBidder(){
		if(currentProduct.getHighestBid().getBidder() != null){
			return currentProduct.getHighestBid().getBidder().equals(this);	
		}
		return false;
	}
	
	public void update(int count, Product product) {
		this.currentProduct = product;
		makeBid(count);
	}
	
	/**
	 * returns a string with information about the bidder (budget etc.)
	 */
	public String toString(){
		//note: when concatenating a string with an object, the .toString() method is automatically called on that object. 
		return "Name: " + this.name + "\r\n"
				+ "budget: €" + this.budget + ",-\r\n"
				+ "current product bidding on: " + this.currentProduct
				+ "subject: " + auctioneer + "\r\n";	
	}

	/**
	 * Take some budget from a Bidder.
	 * A bidder only makes a bid if they have enough money so the budget cannot be lower than zero.
	 * @param price
	 */
	public void takeBudget(int price) {
		if(price > budget){
			throw new IllegalArgumentException("meer price dan budget bij de bidder");
		}
		this.budget -= price;
		
	}
}
