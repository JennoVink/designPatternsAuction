package observer;

import factory.Product;
import noPattern.Bid;
import noPattern.Randomizer;

public class SniperBidder extends Bidder {
	private Product lastProduct = null;
	
	protected SniperBidder(int budget, String name, Subject auctioneer) {
		super(budget, name, auctioneer);
	}

	@Override
	protected void makeBid(int count) {
		//if there is no highest bidder yet...
		if(currentProduct.getHighestBid().getBidder() == null){
			//make the first bid if enough budget:
			if(haveEnoughBudget(true) 
					&& count == Randomizer.getRandomInt(1, 2) 
					&& !didISnipe(currentProduct) 
					&& Randomizer.getRandomBool(2)){
//				System.out.println("COUNT IN SNIPER: " + count);
				auctioneer.gotNewBid(new Bid(this, currentProduct.getHighestBid().getPrice()));
				return;
			}
		} else if(haveEnoughBudget(false) 
				&& !amIHighestBidder() 
				&& count == Randomizer.getRandomInt(1, 2) 
				&& !didISnipe(currentProduct)
				&& Randomizer.getRandomBool(2)){
//			System.out.println("COUNT IN SNIPER: " + count);
			auctioneer.gotNewBid(new Bid(this, currentProduct.getHighestBid().getPrice() + currentProduct.getIncreasePrice()));

		}
		
	}
	
	/**
	 * Check if the bidder sniped the item before so he 
	 * wont try sniping again, avoiding a sniper war.
	 */
	private boolean didISnipe(Product currentProduct)
	{
		if(lastProduct != null)
		{
			//is the product I sniped the same as the last one?
			if(currentProduct == lastProduct)
			{
				return true;
			}
		}
		lastProduct = currentProduct;
		return false;
	}
}
