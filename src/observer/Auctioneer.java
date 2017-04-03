package observer;import java.util.Collections;

import decorator.Decorator;
import decorator.GiftPaper;
import decorator.Warranty;
import decorator.Maintenance;
import decorator.XLSize;
import factory.AbstractFactory;
import factory.Bike;
import factory.Car;
import factory.Product;
import factory.ProductFactory;
import noPattern.Bid;
import testingPleaseDelete.BrokeBidder;
import testingPleaseDelete.TestProduct;

public class Auctioneer extends Subject implements Observer{
	public static void main(String[] args){
//		System.out.println("Hello friend. Hello, friend.");
//				
//		SimpleTimer timer = new SimpleTimer();
		AbstractFactory productFactory = new ProductFactory();
//		Auctioneer auctioneer = new Auctioneer(productFactory, timer);
//		
//		//let the timer know someone is watching him.
//		timer.registerObserver(auctioneer);
//		
//		//now it's time for some bidders:
//		Bidder bidder = new BrokeBidder(200, "Japse de hond", auctioneer);
//		auctioneer.registerObserver(bidder);
//		
//		bidder = new BrokeBidder(200, "Foxie het konijn", auctioneer);
//		auctioneer.registerObserver(bidder);
//		
//		auctioneer.startAuction();
		
		for(int i = 0; i < 300; i++)
		{
			Product p = productFactory.generateRandomProduct();
			System.out.println(p);
		}
	}
	
	private Product currentProduct = new TestProduct();
	
	private AbstractFactory productFactory;
	private Subject timer;
	
	//this is painful... The SimpleTimer class now has a counter, and so do the Auctioneer...
	private int count;
	
	public Auctioneer(AbstractFactory productFactory, Subject timer){
		if(productFactory == null || timer == null){
			System.out.println("No ProductFactory and/or Timer given to the auctioneer. Aborting...");
			System.exit(0);
		}
		this.productFactory = productFactory;
		this.timer = timer;
	}
	
	public void startAuction(){
		printWelcomeMessage();
		setNewProduct();
		timer.startTimer();
	}
	
	public void stopAuction(){
		printGoodbyMessage();
		timer.stopTimer();
	}
			
	public void gotNewBid(Bid bid){
		currentProduct.setHighestBid(bid);
		printNewBidMessage(bid);
		timer.resetTimer();
	}
	
	private void printNewBidMessage(Bid bid) {
		System.out.println("We've got a new bid (" + bid.getPriceString() + ") by\r\n" + bid.getBidder());
		
	}

	public Product getNewProduct(){
		return productFactory.generateRandomProduct();
	}
		
	/**
	 * Sets a new Product to auction. This is in a seperate method so the shuffeling of the observers
	 * can easily be managed. 
	 */
	public void setNewProduct(){
	    Collections.shuffle(observers);
		
		this.currentProduct = productFactory.generateRandomProduct();
	    
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
					System.out.println("---------------" + currentProduct.getHighestBid().getPriceString() + " twice!---------------");
				} else {
					System.out.println("---------------For the last time! No one for " + currentProduct.getHighestBid().getPriceString() + " ...?---------------");					
				}
				break;
			case 2:
				//if there is a highest bid:
				if(currentProduct.getHighestBid().getBidder() != null){
					System.out.println("---------------" + currentProduct.getHighestBid().getPriceString() + " once...---------------");
				} else {
					System.out.println("---------------No one for " + currentProduct.getHighestBid().getPriceString() + " ...?---------------");					
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
				System.out.println("Product " + currentProduct.getDescription() + " not sold due to the lack of interest.");
			} else {
				System.out.println("---------------Not sold! lowered the price---------------");
				//price is successfully lowered, no new product must be made.
				return false;				
			}
		} else {
			//the product is sold: set the owner.
			System.out.println("---------------Sold for " + currentProduct.getHighestBid().getPriceString() + " to " + currentProduct.getHighestBid().getBidder().name + "---------------");
			Collections.shuffle(observers);
			currentProduct.setProductSold();
		}

		return true;
	}
	
	public String toString(){
		return "the auctioneer";
	}
	
	private void printInformation() {
		System.out.println("---auctioneer-info---");
		System.out.println("---currentProduct: " + currentProduct);
		System.out.println("---amount of bidders at the auction: " + observers.size());
	}

	public void printWelcomeMessage(){
		System.out.println("----Welcome to the:-----");
		System.out.println("---------Unfair---------");
		System.out.println("---------Auction--------");
	}
	
	public void printGoodbyMessage(){
		System.out.println("----Goodby!-----");
		System.out.println("---Hope you liked this auction, you're welcome to come back later.---");
	}
	
}
