package observer;

import noPattern.Bid;
import noPattern.Randomizer;

/**
 * Bidder that doubles the bidding. 
 */
public class DoubleDownBidder extends Bidder{

	protected DoubleDownBidder(int budget, String name, Subject auctioneer) {
		super(budget, name, auctioneer);
	}
	
	@Override
	public boolean haveEnoughBudget(boolean forFirstBid){	
		if(forFirstBid){
			return false;
		}
		return this.budget >= ((currentProduct.getHighestBid().getPrice() + currentProduct.getIncreasePrice()) * 2);
	}

	@Override
	protected void makeBid(int count) {
		if(haveEnoughBudget(false) && !amIHighestBidder() && Randomizer.getRandomBool(8)){
			auctioneer.gotNewBid(new Bid(this, currentProduct.getHighestBid().getPrice() * 2));			
		}
	}

}
