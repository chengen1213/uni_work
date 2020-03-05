public class Ferrari extends Petrol{
	public Ferrari(){
		kpL = 5;
		fuel = 55;
	}

	public void reFill(float f){
		if(fuel+f<55){
			fuel += f;
		}else{
			fuel = 55;
			System.out.println("Full!");
		}
	}

	public void move(){
		if(fuel - 15/kpL >=0){
			fuel = fuel - 15/kpL;
			kms = kms + 15;
		}else{
			System.out.println("Not enough fuel!");
		}
	}	
}