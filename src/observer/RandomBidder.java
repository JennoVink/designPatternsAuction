package observer;

import noPattern.Bid;
import noPattern.Randomizer;

public class RandomBidder extends Bidder{

	protected RandomBidder(int budget, String name, Subject auctioneer) {
		super(budget, name, auctioneer);
	}

	@Override
	protected void makeBid(int count) {
		//if there is no highest bidder yet...
		if(currentProduct.getHighestBid().getBidder() == null){
			//make the first bid if enough budget:
			if(haveEnoughBudget(true) && Randomizer.getRandomBool()){
				auctioneer.gotNewBid(new Bid(this, currentProduct.getHighestBid().getPrice()));
				return;
			}
		} else if(haveEnoughBudget(false) && !amIHighestBidder() && Randomizer.getRandomBool()){
			auctioneer.gotNewBid(new Bid(this, currentProduct.getHighestBid().getPrice() + currentProduct.getIncreasePrice()));			
		}
		
	}

}
