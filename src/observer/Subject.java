package observer;

public interface Subject {
	abstract void registerObserver(Bidder b);
	abstract void removeObserver(Bidder b);
	abstract void notifyObservers();
}
