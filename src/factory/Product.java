package factory;

import virtualProxy.ImageView;

import java.net.MalformedURLException;
import java.net.URL;

import noPattern.Bid;
import observer.Bidder;

public abstract class Product {
	private Bidder owner; // If the product is sold is decided by this
							// variable.
	private String description;
	private int startPrice;
	private int lowestPrice;
	private int increasePrice;
	private Bid highestBid; // The highest bid at this moment.
	private URL imageURL;

	/**
	 * When a subclass is initiated, this method is also called.
	 * 
	 * @param description
	 *            Description of the product.
	 * @param startPrice
	 *            The auctioneer starts the bidding round with this price.
	 * @param lowestPrice
	 *            The auctioneer'll not sell the product for a price lower than
	 *            the lowestPrice.
	 * @param increasePrice
	 *            The price is increased with every bid by the increasePrice
	 * @param imageURL
	 */
	public Product(String description, int startPrice, int lowestPrice, int increasePrice, URL imageURL) {
		this.description = description;
		if (startPrice < lowestPrice) {
			System.out.println("the startPrice cannot be lower than the lowestPrice, default value of 100 is set now.");
			System.out.println("Startprice: " + startPrice);
			System.out.println("LowestPrice: " + lowestPrice);

			startPrice = 100;
			lowestPrice = 100;
		}

		this.imageURL = imageURL;
		this.startPrice = startPrice;
		this.lowestPrice = lowestPrice;
		this.increasePrice = increasePrice;

		// set the 'highestBid' to a nullBidder.
		this.highestBid = new Bid(null, startPrice);
	}

	public final void setProductSold() {
		// at this point, there is always a highestBidder (/highestBidder !=
		// null)
		// note: we have honest bidders: they only bid when they have enough
		// budget (just like a real auction).
		highestBid.getBidder().takeBudget(highestBid.getPrice());
		this.owner = highestBid.getBidder();
	}

	/**
	 * Sets the highest bid if it is a valid bid.
	 * 
	 * @param bid
	 *            The bid that is going to be checked.
	 */
	public final void setHighestBid(Bid bid) {
		// if the product is not sold (yet) or there is no highest bidder ->
		// accept the Bid.
		if (highestBid.getBidder() == null) {
			this.highestBid = bid;
		} else if (owner == null) {
			if (bid.getPrice() > highestBid.getPrice()) {
				this.highestBid = bid;
			}
		}
	}

	/**
	 * @return the owner of a product when the product is sold.
	 */
	public final Bidder getOwner() {
		return owner;
	}

	/**
	 * @return the name of a person Not final: decorators needs to add an extra
	 *         description.
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @return the highestBid on the product
	 */
	public final Bid getHighestBid() {
		return highestBid;
	}

	/**
	 * @return the imageUrl
	 */
	public final URL getUrl() {
		return imageURL;
	}

	/**
	 * The startPrice of a product is the price the auctioneer'll start the
	 * bidding at. Note: this method is not final of course, the decorators need
	 * to @Override this method.
	 * 
	 * @return startPrice
	 */
	public int getStartPrice() {
		return this.startPrice;
	}

	/**
	 * gets the lowest price. When no one makes a starting bid at the
	 * startingPrice of startPrice the starting price is lowered until the
	 * lowestPrice. If no one wants to make a bid then, the product is simply
	 * not sold.
	 * 
	 * @return the lowest price a product can be sold.
	 */
	public final int getLowestPrice() {
		return lowestPrice;
	}

	/**
	 * When a bid is made, the highestBid is increased by the increasePrice.
	 * 
	 * @return price a bid is increased with.
	 */
	public final int getIncreasePrice() {
		return increasePrice;
	}

	/**
	 * method that lowers the price, if no one makes a bid to a product.
	 * 
	 * @return true if the price is successfully lowered, false if the
	 *         Auctioneer should skip this product because it'll not be sold.
	 */
	public final boolean lowerPrice() {
		if ((startPrice - increasePrice) > lowestPrice) {
			startPrice -= increasePrice;

			// set a highestBid to the new startPrice.
			highestBid = new Bid(null, startPrice);
			return true;
		}
		// else: the product'll not be sold in the auction... the auctioneer'll
		// skip this product.
		return false;
	}

	/**
	 * Bug fix: sometimes the startprice wasn't in sync with the getStartPrice
	 * method. This method is used to fix that. The reason for this bug is that
	 * the Product object is wrapped and the price is calculated wrongly.
	 */
	public final void syncStartPrice() {
		this.startPrice = getStartPrice();
	}

	@Override
	public String toString() {
		return "Product: " + this.getDescription() + "\r\n" + "Start price: " + this.startPrice + " (increasing with "
				+ this.getIncreasePrice() + ")\r\n" + "Current highest bid: "
				+ (highestBid.getBidder() != null ? highestBid.getPriceString() : "-none-" + "\r\n");
	}

	/**
	 * returns a string with an euro sign (e.g. €10,- if the price is 10). note:
	 * if someone decides that they prefer to use another currency (like BTC or
	 * dollars) it (almost) only needs to be changed here (also in Bid class),
	 * instead of the whole application. (same goes for deciding that we want to
	 * use another data type like double for storing the prices).
	 * 
	 * @return string with an euro sign.
	 */
	public String getPriceString() {
		return "€" + this.getStartPrice() + ",-";
	}
}
