package observer;
import java.util.ArrayList;
import factory.AbstractFactory;
import factory.Product;
import noPattern.Bid;

public class Auctioneer implements Subject, Observer{
	public static void main(String[] args){
		//Auctioneer a = new Auctioneer();
		//a.startTimer();
	}
	
	private Product currentProduct;
	private ArrayList<Bidder> observers;
	private AbstractFactory productFactory;
	private Subject timer;
	
	private int count;

	public Auctioneer(){
		
	}
	
	public void registerObserver(Bidder b){
				
	}
	
	public void removeObserver(Bidder b){
		
	}
	
	public void gotNewBid(Bid b){
		currentProduct.setHighestBid(b);
	}
	
	public Product getNewProduct(String type){
		return productFactory.generateRandomProduct("car");
	}
	
	
	public void timerTicked(int count){	
		//notify observers
		this.count = count;
		
		notifyObservers();
		
		
	}
	

	public void notifyObservers() {
		
		System.out.println();
		
	}

	@Override
	public void update(int count, Product p) {
		this.count = count;
		
		switch(count) {
		case 0:
			System.out.println("verkocht!");
			break;
		case 1: 
			System.out.println("andermaal");
			break;
		case 2:
			System.out.println("eenmaal");
			break;
  }
		
		notifyObservers();
	}
	
}
