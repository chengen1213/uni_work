public class Main{

	public static void main(String[] args) {
		
		TaxiRank tr = new TaxiRank();
		for (int i = 0; i < 10; i++) {
			tr.add(new Taxi(i));	
		}
		for (int i = 0; i < 5; i++) {
			System.out.println(tr.remove().number);		
		}	
	}
}