public class Porche extends Petrol{
	public Porche(){
		kpL = 4.2f;
		fuel = 60;
	}

	public void reFill(float f){
		if(fuel+f<60){
			fuel += f;
		}else{
			fuel = 60;
			System.out.println("Full!");
		}
	}
}