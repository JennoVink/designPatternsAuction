package observer;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class SimpleTimer {
   private static TimerTask task = null;
   private static Timer timer = new Timer();
     
   //This is weird... The auctioneer is going to be a bidder? whuutwhuut.
   private ArrayList<Observer> observers;
   
   public SimpleTimer(){}
   
   public void startTimer(){
	   timer = new Timer();
	   	int count = 5;
			        
		task = new CustomTimerTask(count, this);
		long delay = 1000L;
	  	  
		timer.scheduleAtFixedRate(task, delay, delay);
   }
   
   public void resetTimer(){
	   timer.cancel();
	   task = null;
   }
   
   public int getCount(){ 
	   if(task != null){
		   return ((CustomTimerTask) task).getCount();
	   }
	   return 0;
   }
   
   public void removeObserver(Bidder b){
	   
   }
   
   public void registerObserver(Bidder b){
	   
   }
   
   public void notifyObservers(int count){
	   for(Observer observer : observers){
		   observer.update(count, null);
	   }
   }
   
  // public abstract void timerTicked(int count);
}

class CustomTimerTask extends TimerTask {
   private int count;
   private SimpleTimer timer;

   public CustomTimerTask(int count, SimpleTimer timer) {
      this.count = count;
      this.timer = timer;
   }

   @Override
   public void run() {
	  count--;
      
      if(count == 0){
    	  cancel();
      }
      
      timer.notifyObservers(count);    
   }  
   
   public final int getCount(){
	   return count;
   }
   
     
}