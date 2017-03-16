package factory;

import virtualProxy.Icon;
import noPattern.Bid;
import observer.Bidder;

public abstract class Product {
	protected Bidder owner;
	protected String name;
	protected int startPrice;
	protected int lowestPrice;
	protected int increasePrice;
	protected Bid highestBid;
	protected Icon icon;
	
	/**
	 * sets the owner of a product when a product is sold.
	 * @param owner 
	 */
	protected final void setOwner(Bidder owner){
		this.owner = owner;
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
	public final void paintIcon(){
		icon.paintIcon();
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
	
	
}
