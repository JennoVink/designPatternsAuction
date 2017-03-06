import java.util.Timer;
import java.util.TimerTask;

public abstract class SimpleTimer {
   private static TimerTask task = null;
   private static Timer timer = new Timer();
     
   
   public SimpleTimer(){}
     
   public void startTimer(){
	   	timer = new Timer();
	   	int count = 5;
			        
		task = new customTimerTask(count, this);
		long delay = 1000L;
	  	  
		timer.scheduleAtFixedRate(task, delay, delay);
   }
   
   public void resetTimer(){
	   timer.cancel();
	   task = null;
   }
   
   public int getCount(){ 
	   if(task != null){
		   return ((customTimerTask) task).getCount();
	   }
	   return 0;
   }
   
   public abstract void timerTicked(int count);
}

class customTimerTask extends TimerTask {
   private int count;
   private SimpleTimer sw;

   public customTimerTask(int count, SimpleTimer stopwatch) {
      this.count = count;
      this.sw = stopwatch;
   }

   @Override
   public void run() {
	  count--;
//      System.out.println("Count is: " + count);
      
      if(count == 0){
    	  cancel();
      }
      
      sw.timerTicked(count);    
   }  
   
   public int getCount(){
	   return count;
   }
   
     
}