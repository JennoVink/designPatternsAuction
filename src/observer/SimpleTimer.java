package observer;

import java.util.Timer;
import java.util.TimerTask;

import factory.Product;

/**
 * This class is responsible for counting to zero.
 * This is a subject because the Auctioneer class is subscribed to the ticks of the clock.
 * 
 */
public class SimpleTimer extends Subject {
   private static TimerTask task = null;
   private static Timer timer = new Timer();
   private static int startCount = 5;
        
   public SimpleTimer(int startCount){
	   this.startCount = startCount;
   }
   
   public SimpleTimer(){
	   this.startCount = 5;
   }
         
   @Override
   public void startTimer(){
	    timer = new Timer();
			        
		task = new CustomTimerTask(startCount, this);
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