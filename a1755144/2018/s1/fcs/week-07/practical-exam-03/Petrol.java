public class Petrol extends Vehicle{

	protected float kpL;

		public void move(){
			if(fuel - 10>=0){
				fuel = fuel - 10;
				kms = kms + 10*kpL;
			}else{
				System.out.println("Not enough fuel!");
			}
	}
}