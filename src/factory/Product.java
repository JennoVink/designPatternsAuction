package factory;

import virtualProxy.Icon;
import noPattern.Bid;
import observer.Bidder;

public abstract class Product {
	protected Bidder owner; //If the product is sold is decided by this variable. 
	protected String name; //The name of the product.
	protected int startPrice; //The startPrice: the auctioneer starts the bidding round with this price.
	protected int lowestPrice; //The auctioneer'll not sell the product for a price lower than the lowestPrice.
	protected int increasePrice; //The price is increased with every bid by the increasePrice
	protected Bid highestBid; //The highest bid.
	protected Icon icon; //The icon of a product.
	
	//add icon.
	public Product(String name, int startPrice, int lowestPrice, int increasePrice){
		this.name = name;

		if(startPrice < lowestPrice){
			System.out.println("the startPrice cannot be lower than the lowestPrice, default value of 100 is set now.");
			startPrice = 100;
			lowestPrice = 100;
		}
		
		this.startPrice = startPrice;
		this.lowestPrice = lowestPrice;
		this.increasePrice = increasePrice;
		//set the 'highestBid' to a nullBidder, and the lowestPrice to a int that the bidding'll never reach.
		this.highestBid = new Bid(null, startPrice);
	}
	
	public void setProductSold(){
		//at this point, there is always a highestBidder (/highestBidder != null)
		this.owner = highestBid.getBidder();
		highestBid.getBidder().takeBudget(highestBid.getPrice());
	}
	
	/**
	 * Sets the highest bid if it is a valid bid.
	 * @param bid
	 */
	public final void setHighestBid(Bid bid){
		//if the product is not sold (yet).		
		if(owner == null){
			if(bid.getPrice() > highestBid.getPrice()) {
				this.highestBid = bid;
			}
		}
	}
	
	/**
	 * @return the owner of a product when the product is sold.
	 */
	public final Bidder getOwner(){
		return owner;
	}
	
	/**
	 * paints the icon/image on the screen.	
	 */
	//question: make this method final?
	public void paintIcon(){
		icon.paintIcon();
	}
	
	/**
	 * Sets the icon variable.
	 * @param icon
	 */
	public void setIcon(Icon icon){
		this.icon = icon;
	}
	
	/**
	 * @return the name of a person
	 */
	public final String getName(){
		return name;
	}
	
	/**
	 * @return the highestBid on the product
	 */
	public final Bid getHighestBid(){
		return highestBid;
	}
	
	/**
	 * The startPrice of a product is the price the auctioneer'll start the bidding at.
	 * @return startPrice
	 */
	public final int getStartPrice(){
		return startPrice;
	}
	
	/**
	 * gets the lowest price. When no one makes a starting bid at the startingPrice of startPrice
	 * the starting price is lowered until the lowestPrice.
	 * If no one wants to make a bid then, the product is simply not sold. 
	 * @return the lowest price a product can be sold.
	 */
	public final int getLowestPrice(){
		return lowestPrice;
	}
	
	/**
	 * When a bid is made, the highestBid is increased by the increasePrice.
	 * @return price a bid is increased with. 
	 */
	public final int getIncreasePrice(){
		return increasePrice;
	}
	
	public String toString(){
		return "Product: " + name + "\r\n"
				+ "Start price: " + startPrice + "\r\n"
				+ "Current highest bid: " + (highestBid.getBidder() != null ? highestBid.getPrice() : "-none-\r\n");
		
	}

	/**
	 * method that lowers the price, if no one makes a bid to a product.
	 * @return
	 */
	public boolean lowerPrice() {	
		if((startPrice - increasePrice) > lowestPrice){
			startPrice -= increasePrice;
			return true;
		}
		//else: the product'll not be sold in the auction... the auctioneer'll skip this product.
		return false;
	}
	
	
	
}
