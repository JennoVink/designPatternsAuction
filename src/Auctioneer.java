
public class Auctioneer extends SimpleTimer {
	public static void main(String[] args){
		Auctioneer a = new Auctioneer();
		a.startTimer();
		
	
	}

	public Auctioneer(){
		
	}
	
	public void timerTicked(int count){
//		System.out.println("hello from timer ticked! " + count);
		
		//notify observers
		notifyObservers();
		
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
	}
	

	private void notifyObservers() {
		int count = getCount();
		System.out.println(count);
		
		//kan een observer tussen andermaal en verkocht nog een bieding maken?
		//ja. Bijkomend probleem:
		//helaas zit het systeem zo in elkaar dat in deze methode elke observer wordt aangeroepen
		//en gevraagd om een keuze te maken (bieden en hoeveel of niet bieden)
		//als de observer die in de iteratie op dat moment aan de beurt is besluit het wel te doen
		//dan is het meer dat er in een veilingszaal aan elk persoon één voor één wordt gevraagd of ze een
		//bieding willen doen...
		
		
	}
	
}
