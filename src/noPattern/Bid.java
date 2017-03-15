package noPattern;

import observer.Bidder;

public class Bid {
	private Bidder bidder;
	private int price;
	
	
	public Bid(Bidder bidder, int price){
		this.bidder = bidder;
		this.price = price;
	}
	
	public Bidder getBidder(){
		return bidder;
	}
	
	/**
	 * @return the price
	 */
	public int getPrice() {
		return price;
	}	
	
}
