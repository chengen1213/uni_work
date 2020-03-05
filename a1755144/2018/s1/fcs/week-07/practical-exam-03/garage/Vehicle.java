public class Vehicle implements VehicleInterface{

	protected float fuel;
	protected float kms;
	protected float power;
	protected String model;

	public Vehicle(){
		this.kms = 0;
		this.fuel = 100;
		this.power = 100;
		this.model = "";		
	}

	public float getFuel(){
		return this.fuel;
	}

	public float getKms(){
		return this.kms;
	}

	public float getPower(){
		return this.power;
	}

	public String getmModel(){
		return this.model;
	}

	public void setFuel(float fuel){
		this.fuel = fuel;
	}

	public void setKms(float kms){
		this.kms = kms;
	}

	public void setPower(float power){
		this.power = power;
	}

	public void setmModel(String model){
		this.model = model;
	}
	public void move(){
		this.fuel -= 10;
		this.kms += 10;
	}

	public void reFill(float f){
		this.fuel += f;
	}

	public void displayTravel(){
		System.out.println("This car has traveled for " + kms + "km, " + fuel + " fuel remains.");
	}
}