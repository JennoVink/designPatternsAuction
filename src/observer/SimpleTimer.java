package observer;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import factory.Product;

public class SimpleTimer extends Subject {
   private static TimerTask task = null;
   private static Timer timer = new Timer();
        
   public SimpleTimer(){}
   
   @Override
   public void startTimer(){
	   timer = new Timer();
	   	int count = 5;
			        
		task = new CustomTimerTask(count, this);
		long delay = 1000L;
	  	  
		timer.scheduleAtFixedRate(task, delay, delay);
   }
   
   @Override
   public void stopTimer() {
	   timer.cancel();
	   task = null;
   }
   
   @Override
   public void resetTimer(){
	   timer.cancel();
	   task = null;
	   startTimer();
   }
   
   public int getCount(){ 
	   if(task != null){
		   return ((CustomTimerTask) task).getCount();
	   }
	   return 0;
   }
      
   /**
    * In this case, the timer doesn't have to broadcast a product, only a count to the auctioneer.
    * @param count
    */
   public void notifyObservers(){
	   for(Observer observer : observers){
		   observer.update(getCount(), null);
	   }
   }
   
   
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
      
      timer.notifyObservers();    
   }  
   
   public final int getCount(){
	   return count;
   }
   
     
}