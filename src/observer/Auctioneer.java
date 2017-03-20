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
		
		//Let the timer know someone is watching him.
		timer.registerObserver(auctioneer);
		
		//now it's time for some bidders:
		//note: the haveEnoughBudget() method in Bidder doesn't really work.
		Bidder bidder = new BrokeBidder(20, "Japse de hond", auctioneer);
		auctioneer.registerObserver(bidder);
		
//		bidder = new BrokeBidder(200, "Foxie het konijn", auctioneer);
//		auctioneer.registerObserver(bidder);
		
		auctioneer.startAuction();
		
	}
	
	private Product currentProduct = new TestProduct();
	
	//In this case, the Observer is a Bidder.
	//Note: we don't want to use an arraylist: the registerObserver is then the same as the bidder
	//they both implement the subject interface (so why not an abstract class then?)
	
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
			
	public void gotNewBid(Bid bid){
		currentProduct.setHighestBid(bid);
		printNewBidMessage(bid);
		timer.resetTimer();
	}
	
	private void printNewBidMessage(Bid bid) {
		System.out.println("We've got a new bid (" + bid.getPrice() + ") by\r\n" + bid.getBidder());
		
	}

	public Product getNewProduct(String type){
		return productFactory.generateRandomProduct("car");
	}
		
	public void setNewProduct(){
		//todo: get from factory: productFactory.generateRandomProduct(null);
		this.currentProduct = new TestProduct();
	}
	
	public void notifyObservers() {
		for(Observer bidder : observers){
			bidder.update(this.count, currentProduct);
			System.out.println(bidder);
		}
	}

	/**
	 * With the update method, the auctioneer is going to shout something.
	 * note: Product'll always be null in this class because it is just an auctioneer.
	 * The update method in the Bidder class'll always have a non-null Product p.
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
			System.out.println("---------------andermaal---------------");
			break;
		case 2:
			System.out.println("---------------eenmaal---------------");
			break;
		}
		
		notifyObservers();
	}
	
	/**
	 * If no one wants a product, the price must be lowerd (if not already the lowest price)
	 * or a new product must be created.
	 * @return true if a new product must be created by the factory.
	 */
	public boolean lowerPriceOrSetSold(){
		timer.resetTimer();
		
		if(currentProduct.getHighestBid().getBidder() == null){
			//try to lower the price.
			if(!currentProduct.lowerPrice()){
				System.out.println("Product " + currentProduct.getName() + " not sold.");
			} else {
				System.out.println("---------------niet verkocht! Prijs verlaagd---------------");
				//price is successfully lowered, no new product must be made.
				return false;				
			}
		} else {
			//the product is sold: set the owner.
			System.out.println("---------------verkocht!---------------");
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
