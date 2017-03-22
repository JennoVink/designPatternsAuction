package observer;
import factory.AbstractFactory;
import factory.Product;
import noPattern.Bid;
import testingPleaseDelete.BrokeBidder;
import testingPleaseDelete.TestProduct;

public class Auctioneer extends Subject implements Observer{
	public static void main(String[] args){
		System.out.println("Hello friend. Hello, friend.");
				
		SimpleTimer timer = new SimpleTimer();
		AbstractFactory productFactory = null;
		Auctioneer auctioneer = new Auctioneer(productFactory, timer);
		
		//let the timer know someone is watching him.
		timer.registerObserver(auctioneer);
		
		//now it's time for some bidders:
		Bidder bidder = new BrokeBidder(20, "Japse de hond", auctioneer);
		auctioneer.registerObserver(bidder);
		
//		bidder = new BrokeBidder(200, "Foxie het konijn", auctioneer);
	//	auctioneer.registerObserver(bidder);
		
		auctioneer.startAuction();
		
	}
	
	private Product currentProduct = new TestProduct();
	
	private AbstractFactory productFactory;
	private Subject timer;
	
	//this is painful... The SimpleTimer class now has a counter, and so do the Auctioneer...
	private int count;
	
	public Auctioneer(AbstractFactory productFactory, Subject timer){
//		if(productFactory == null || timer == null){
//			System.out.println("No ProductFactory and/or Timer given to the auctioneer. Aborting...");
//			System.exit(0);
//		}
//		this.productFactory = productFactory;
		this.timer = timer;
	}
	
	public void startAuction(){
		printWelcomeMessage();
		setNewProduct();
		timer.startTimer();
	}
	
	public void stopAuction(){
		//printAMessage
		timer.stopTimer();
	}
			
	public void gotNewBid(Bid bid){
		currentProduct.setHighestBid(bid);
		printNewBidMessage(bid);
		timer.resetTimer();
	}
	
	private void printNewBidMessage(Bid bid) {
		System.out.println("We've got a new bid (" + bid.getPrice() + ") by\r\n" + bid.getBidder());
		
	}

	public Product getNewProduct(String type){
		return productFactory.generateRandomProduct(type);
	}
		
	/**
	 * Sets a new Product to auction. This is in a seperate method so the shuffeling of the observers
	 * can easily be managed. 
	 */
	public void setNewProduct(){
		//hussle the observerList
		//todo: get from factory: this.currentProduct productFactory.generateRandomProduct(null);
		this.currentProduct = new TestProduct();
	}
	
	public void notifyObservers() {
		for(Observer bidder : observers){
			bidder.update(this.count, currentProduct);
			//System.out.println(bidder);
		}
	}

	/**
	 * With the update method, the Auctioneer is going to shout something to his audience (the observers).
	 * This method is called by the SimpleTimer, that's why the Product p parameter'll always be null in this case 
	 * (the SimpleTimer have no idea what Product is going to be traded).
	 */
	@Override
	public void update(int count, Product p) {	
		this.count = count;
		
		printInformation();
		
		switch(count) {
			case 0:
				//lowerPriceOrSetSold returns true if a new product needs to be made.
				if(lowerPriceOrSetSold()){
					setNewProduct();
				}
				break;
			case 1: 
				//if there is a highest bid:
				if(currentProduct.getHighestBid().getBidder() != null){
					System.out.println("---------------€" + currentProduct.getHighestBid().getPrice() + ",- twice!---------------");
				} else {
					System.out.println("---------------For the last time! No one for €" + currentProduct.getHighestBid().getPrice() + ",- ...?---------------");					
				}
				break;
			case 2:
				//if there is a highest bid:
				if(currentProduct.getHighestBid().getBidder() != null){
					System.out.println("---------------€" + currentProduct.getHighestBid().getPrice() + ",- once...---------------");
				} else {
					System.out.println("---------------No one for €" + currentProduct.getHighestBid().getPrice() + ",- ...?---------------");					
				}
				break;
		}
		
		notifyObservers();
	}
	
	/**
	 * If no one wants a product, the price must be lowered (if not already the lowest price)
	 * or a new product must be created.
	 * @return true if a new product must be created by the factory. False if the price is successfully
	 * lowered and no action is required.
	 */
	public boolean lowerPriceOrSetSold(){
		timer.resetTimer();
		
		if(currentProduct.getHighestBid().getBidder() == null){
			//try to lower the price.
			if(!currentProduct.lowerPrice()){
				System.out.println("Product " + currentProduct.getName() + " not sold.");
			} else {
				System.out.println("---------------Not sold! lowered the price---------------");
				//price is successfully lowered, no new product must be made.
				return false;				
			}
		} else {
			//the product is sold: set the owner.
			System.out.println("---------------Sold!---------------");
			currentProduct.setProductSold();
		}

		return true;
	}
	
	private void printInformation() {
		System.out.println("---auctioneer-info---");
		System.out.println("---currentProduct: " + currentProduct);
		System.out.println("---amount of bidders: " + observers.size());
	}

	public void printWelcomeMessage(){
		System.out.println("----Welcome to the:-----");
		System.out.println("---------Unfair---------");
		System.out.println("---------Auction--------");
	}
	
}
