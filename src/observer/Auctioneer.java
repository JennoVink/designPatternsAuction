package observer;
import java.util.ArrayList;
import factory.AbstractFactory;
import factory.Product;
import noPattern.Bid;

public class Auctioneer extends Subject implements Observer{
	public static void main(String[] args){
		System.out.println("Hello friend. Hello, friend.");
				
		SimpleTimer timer = new SimpleTimer();
		AbstractFactory productFactory = null;
		Auctioneer auctioneer = new Auctioneer(productFactory, timer);
		
		//Let the timer know someone is watching him.
		timer.registerObserver(auctioneer);
		auctioneer.startAuction();
		
	}
	
	private Product currentProduct;
	
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
//		currentProduct = productFactory.generateRandomProduct(null);
		timer.startTimer();
	}
			
	public void gotNewBid(Bid bid){
		currentProduct.setHighestBid(bid);
		timer.resetTimer();
	}
	
	public Product getNewProduct(String type){
		return productFactory.generateRandomProduct("car");
	}
	
	public void notifyObservers() {
		for(Observer bidder : observers){
			bidder.update(this.count, currentProduct);
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
		
		switch(count) {
		case 0:
			System.out.println("verkocht!");
			break;
		case 1: 
			System.out.println("andermaal");
			timer.resetTimer();
			break;
		case 2:
			System.out.println("eenmaal");
			break;
		}
		
		notifyObservers();
	}
	
	public void printWelcomeMessage(){
		System.out.println("----Welcome to the:-----");
		System.out.println("---------Unfair---------");
		System.out.println("---------Auction--------");
	
	}
	
}
