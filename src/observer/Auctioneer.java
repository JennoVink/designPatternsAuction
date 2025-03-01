package observer;

import java.net.MalformedURLException;
import java.util.Collections;

import factory.AbstractFactory;
import factory.Product;
import factory.ProductFactory;
import noPattern.Bid;
import virtualProxy.ImageView;

public class Auctioneer extends Subject implements Observer {
	public static void main(String[] args) {
		System.out.println("Hello friend. Hello, friend. Welcome to the animal auction");

		SimpleTimer timer = new SimpleTimer();

		ImageView ui = new ImageView();

		AbstractFactory productFactory = new ProductFactory();
		Auctioneer auctioneer = new Auctioneer(productFactory, timer, ui);

		// let the timer know someone is watching him.
		timer.registerObserver(auctioneer);

		// now it's time for some bidders:
		Bidder bidder = new RandomBidder(2000, "Stormtrooper", auctioneer);
		auctioneer.registerObserver(bidder);

		Bidder snipeBidder = new SniperBidder(2000, "Darth vader", auctioneer);
		auctioneer.registerObserver(snipeBidder);

		bidder = new RandomBidder(2000, "Stormtrooper 2", auctioneer);
		auctioneer.registerObserver(bidder);

		bidder = new RandomBidder(2000, "Rick", auctioneer);
		auctioneer.registerObserver(bidder);

		bidder = new DoubleDownBidder(5000, "High roler", auctioneer);
		auctioneer.registerObserver(bidder);

		snipeBidder = new SniperBidder(2000, "Morty", auctioneer);
		auctioneer.registerObserver(snipeBidder);

		// finally: start the auction
		auctioneer.startAuction();
	}

	private Product currentProduct;

	private AbstractFactory productFactory;
	private Subject timer;

	// the user interface:
	private ImageView ui;

	// a counter to check if 10 products in a row are not sold due to the lack
	// of budget of the bidders.
	private int notSoldCounter;

	// this is painful... The SimpleTimer class now has a counter, and so does
	// the Auctioneer. This is because the Auctioneer is a Subject and an
	// Observer at the same time.
	private int count;

	/**
	 * Initialize the auction
	 * 
	 * @param productFactory
	 *            an AbstractFactory where the products are produced.
	 * @param timer
	 *            a timer that counts and returns the count to the Auctioneer
	 * @param ui
	 *            the user interface for displaying the images.
	 */
	public Auctioneer(AbstractFactory productFactory, Subject timer, ImageView ui) {
		if (productFactory == null || timer == null || ui == null) {
			System.out.println("No ProductFactory and/or Timer and/or ImageView given to the auctioneer. Aborting...");
			System.exit(0);
		}
		this.notSoldCounter = 0;
		this.productFactory = productFactory;
		this.ui = ui;
		this.timer = timer;
	}

	public void startAuction() {
		printWelcomeMessage();
		setNewProduct();
		timer.startTimer();
	}

	public void stopAuction() {
		printGoodbyMessage();
		timer.stopTimer();
	}

	public void gotNewBid(Bid bid) {
		currentProduct.setHighestBid(bid);
		printNewBidMessage(bid);
		timer.resetTimer();
	}

	private void printNewBidMessage(Bid bid) {
		System.out.println("We've got a new bid (" + bid.getPriceString() + ") by\r\n" + bid.getBidder());

	}

	public Product getNewProduct() {
		return productFactory.generateRandomProduct();
	}

	/**
	 * Sets a new Product to auction. This is in a seperate method so the
	 * shuffeling of the observers can easily be managed.
	 * 
	 * @throws MalformedURLException
	 */
	public void setNewProduct() {
		Collections.shuffle(observers);

		this.currentProduct = productFactory.generateRandomProduct();
		
		ui.paintIcon(currentProduct.getUrl());

		System.out.println("-----A new Product is set!-----");
		System.out.println(currentProduct);
		System.out.println(
				"-----We're going to use steps of " + currentProduct.getIncreasePrice() + " for the biddings.-----");
		System.out.println("-----The Product'll not go away for a price lower than " + currentProduct.getLowestPrice()
				+ ". Happy bidding everyone!-----");
	}

	/**
	 * notify the observers (bidders) with the current product and the count.
	 */
	public void notifyObservers() {
		for (Observer bidder : observers) {
			bidder.update(this.count, currentProduct);
		}
	}

	/**
	 * With the update method, the Auctioneer is going to shout something to his
	 * audience (the observers). This method is called by the SimpleTimer,
	 * that's why the Product p parameter'll always be null in this case (the
	 * SimpleTimer have no idea what Product is going to be traded).
	 */
	@Override
	public void update(int count, Product p) {
		this.count = count;

		printInformation();

		switch (count) {
		case 0:
			// lowerPriceOrSetSold returns true if a new product needs to be made
			if (lowerPriceOrSetSold()) {
				setNewProduct();
			}
			break;
		case 1:
			// if there is a highest bid:
			if (currentProduct.getHighestBid().getBidder() != null) {
				System.out.println(
						"---------------" + currentProduct.getHighestBid().getPriceString() + " twice!---------------");
			} else {
				System.out.println("---------------For the last time! No one for "
						+ currentProduct.getHighestBid().getPriceString() + " ...?---------------");
			}
			break;
		case 2:
			// if there is a highest bid:
			if (currentProduct.getHighestBid().getBidder() != null) {
				System.out.println("---------------" + currentProduct.getHighestBid().getPriceString()
						+ " once...---------------");
			} else {
				System.out.println("---------------No one for " + currentProduct.getHighestBid().getPriceString()
						+ " ...?---------------");
			}
			break;
		}

		notifyObservers();
	}

	/**
	 * If no one wants a product, the price must be lowered (if not already the
	 * lowest price) or a new product must be created.
	 * 
	 * @return true if a new product must be created by the factory. False if
	 *         the price is successfully lowered and no action is required.
	 */
	public boolean lowerPriceOrSetSold() {
		timer.resetTimer();

		if (currentProduct.getHighestBid().getBidder() == null) {
			// try to lower the price.
			if (!currentProduct.lowerPrice()) {
				System.out.println(
						"Product " + currentProduct.getDescription() + " not sold due to the lack of interest.");
				notSoldCounter++;
				// Stop the auction if 10 times in a row a product is not sold.
				if (notSoldCounter >= 10) {
					System.out.println("The auction is stopped, because " + notSoldCounter
							+ " products are not sold due the lack of budget.");
					stopAuction();
				}
			} else {
				System.out.println("---------------Not sold! lowered the price---------------");
				// price is successfully lowered, no new product must be made.
				return false;
			}
		} else {
			// the product is sold: set the owner.
			System.out.println("---------------Sold for " + currentProduct.getHighestBid().getPriceString() + " to "
					+ currentProduct.getHighestBid().getBidder().name + "---------------");
			Collections.shuffle(observers);
			currentProduct.setProductSold();
		}
		
		notSoldCounter = 0;
		return true;
	}

	public String toString() {
		return "the auctioneer";
	}

	public void printInformation() {
		System.out.println("---auctioneer-info---");
		System.out.println("---currentProduct: " + currentProduct);
		System.out.println("---amount of bidders at the auction: " + observers.size());
	}

	public void printWelcomeMessage() {
		System.out.println("----Welcome to the:-----");
		System.out.println("---------Unfair---------");
		System.out.println("---------Auction--------");
	}

	public void printGoodbyMessage() {
		System.out.println("----Goodby!-----");
		System.out.println("---Hope you liked this auction, you're welcome to come back later.---");
	}

}
