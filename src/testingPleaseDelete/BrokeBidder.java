package testingPleaseDelete;

import noPattern.Bid;
import observer.Bidder;
import observer.Subject;

public class BrokeBidder extends Bidder{

	public BrokeBidder(int budget, String name, Subject auctioneer) {
		super(budget, name, auctioneer);
	}

	@Override
	protected void makeBid(int count) {
		//== false... seriously...
		if(haveEnoughBudget() && amIHighestBidder() == false){
			auctioneer.gotNewBid(new Bid(this, currentProduct.getHighestBid().getPrice() + currentProduct.getIncreasePrice()));			
		}
		
	}

}
