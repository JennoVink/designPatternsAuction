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
		//first check if this is a chance to make the first bid:
//		if(currentProduct.getHighestBid().getBidder() == null){
//			//make the first bid if enough budget:
//			if(haveEnoughBudget(true)){
//				auctioneer.gotNewBid(new Bid(this, currentProduct.getHighestBid().getPrice()));
//				return;
//			}
//		}
		
		if(haveEnoughBudget(false) && !amIHighestBidder()){
			auctioneer.gotNewBid(new Bid(this, currentProduct.getHighestBid().getPrice() + currentProduct.getIncreasePrice()));			
		}
		
	}

}
