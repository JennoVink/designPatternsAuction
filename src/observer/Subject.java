package observer;

import java.util.ArrayList;

import noPattern.Bid;

public abstract class Subject {
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
	
	public void gotNewBid(Bid bid) {}
	
	//note: the SimpleTimer is also a Subject. So he needs to override the following methods:
	public void startTimer() {}
	public void stopTimer() {}
	public void resetTimer() {}
		
	abstract void notifyObservers();
}
