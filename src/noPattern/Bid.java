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

	/**
	 * returns a string with an euro sign (e.g. €10,- if the price is 10).
	 * note: if someone decides that they prefer to use another currency (like BTC or dollars)
	 * it (almost) only needs to be changed here (also in Bid class), instead of the whole application. (same goes for deciding
	 * that we want to use another data type like double for storing the prices).
	 * @return string with an euro sign.
	 */
	public String getPriceString() {
		return "€" + price + ",-";
	}	
	
}
