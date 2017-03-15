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
	
	protected final void setOwner(Bidder owner){
		this.owner = owner;
	}
	
	public final void setHighestBid(Bid bid){
		if(bid.getPrice() > highestBid.getPrice()) {
			this.highestBid = bid;
		}
	}
	
}
