package observer;

import factory.Product;

public abstract class Bidder implements Observer {
	protected String name;
	protected Product currentProduct;
	protected int budget;
	protected Subject auctioneer;

	/**
	 * create a Bidder.
	 * 
	 * @param budget
	 *            The amount of money someone has. This is an int instead of a
	 *            double because the bids are always whole numbers (not bidding
	 *            for 50 cents or something).
	 * @param name
	 *            The name of the bidder.
	 * @param auctioneer
	 *            The auctioneer he's watching.
	 */
	protected Bidder(int budget, String name, Subject auctioneer) {
		this.budget = budget;
		this.name = name;
		this.auctioneer = auctioneer;
	}

	/**
	 * Depending on the currentProduct and the param count, here the subclasses
	 * have to decide if they want to make a bid or not.
	 * 
	 * @param count
	 *            the count of the clock/SimpleTimer at that moment.
	 */
	protected abstract void makeBid(int count);

	/**
	 * A method that checks if the bidder is able to make a bid.
	 * 
	 * @param forFirstBid
	 *            true if no one has made a bid to the product. note:
	 *            currentProduct.getHighestBid().getBidder() == null doesn't
	 *            worked out in this case so this is a good fix.
	 * @return true if the bidder has enough budget, false if not.
	 */
	public boolean haveEnoughBudget(boolean forFirstBid) {
		if (forFirstBid) {
			return this.budget >= currentProduct.getHighestBid().getPrice();
		}

		return this.budget >= (currentProduct.getHighestBid().getPrice() + currentProduct.getIncreasePrice());
	}

	/**
	 * It makes no sense that a person makes two biddings after eachother on the
	 * same product.
	 * 
	 * @return true if the Bidder object (this) is the same as the highestBidder
	 *         on the current product.
	 */
	public final boolean amIHighestBidder() {
		if (currentProduct.getHighestBid().getBidder() != null) {
			return currentProduct.getHighestBid().getBidder().equals(this);
		}
		return false;
	}

	/**
	 * The bidder receives a count from the Auctioneer (which gets it from the
	 * SimpleTimer). Then he decides if he's going to make a bid (with the
	 * makeBid method).
	 * 
	 * @param int
	 *            count the count he gets from the auctioneer
	 * @param Product
	 *            product The product that is currently going to be sold by the
	 *            Auctioneer.
	 */
	public void update(int count, Product product) {
		this.currentProduct = product;
		makeBid(count);
	}

	/**
	 * returns a string with information about the bidder (budget etc.)
	 */
	public String toString() {
		return "Name: " + this.name + "\r\n" + "budget: €" + this.budget + ",-\r\n" + "current product bidding on: "
				+ this.currentProduct + "\r\n";
	}

	/**
	 * Take some budget from a Bidder. A bidder only makes a bid if they have
	 * enough money so the budget cannot be lower than zero.
	 * 
	 * @param price
	 *            The amount of budget thats needs to be taken from a person's
	 *            budget.
	 */
	public void takeBudget(int price) {
		// this final check is just to make sure no Bidder is cheating. When a
		// bidder is cheating, an Exception is throwed.
		if (price > budget) {
			// note that we prefer println's over Exception throwing. But in
			// this case the auction can't go on.
			throw new IllegalArgumentException("Price is higher than budget...");
		}
		this.budget -= price;

	}
}
