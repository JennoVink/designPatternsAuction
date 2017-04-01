package observer;

import java.util.ArrayList;

import noPattern.Bid;

public abstract class Subject {
	//by initiating the collection in the super class, all the subclasses use the same kind of collection for storing the Observers.
	protected ArrayList<Observer> observers = new ArrayList<>();
	
	public final void registerObserver(Observer observer){
		observers.add(observer);
	}
	
	public final void removeObserver(Observer observer){
		int i = observers.indexOf(observer);        
		if (i >= 0) {            
			observers.remove(i);        
		} 
	}

	/**
	 * call the update method on every Observer.
	 */
	abstract void notifyObservers();
	
	//the new bid method is only for the Auctioneer, the Bidder doens't have to override this method.
	public void gotNewBid(Bid bid) {}
	
	//note: the SimpleTimer is also a Subject. So he needs to override the following methods:
	//Note that the Auctioneer (also a Subject) could also override these methods, in case he wants to use his own timer.
	public void startTimer() {}
	public void stopTimer() {}
	public void resetTimer() {}
		
}
