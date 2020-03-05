public class ParkingPlace{

	String owner;
	String address;
	String type;
	String vehicle;
	int price;

	int count = 0;

	ParkingPlace(){
		this.setType(type);
		this.setVehicleType(vehicle);
		this.setPrice(0);
	}   	

	ParkingPlace(String owner, String address, String type, String vehicle){
		this.setOwner(owner);
		this.setAddress(address);
		this.setType(type);
		this.setVehicleType(vehicle);
		this.setPrice(0);
	}   

	void setOwner(String tmpOwner){
		this.owner = tmpOwner;
	}                         // sets the owner's name of a parking place
	String getOwner(){
		return this.owner;
	}                                      // returns the owner's name of a parking place         
	void setAddress(String tmpAddress){
		this.address = tmpAddress;
	}                     // set the address
	String getAddress(){
		return this.address;
	}
	void setType(String type){
		count++;
		this.type = "on-street";
		if(type == "off-street"){
			this.type = type;
		}
	}         
	                                    // returns the address of a parking place
	String getType(){
		return this.type;
	}

	void setVehicleType(String vehicleType){
		this.vehicle = "car";
		if(vehicleType == "motorcycle"){
			this.vehicle = vehicleType;
		}
		return;
	}   
	                                    // returns the type of a parking place
	String getVehicleType(){
		return this.vehicle;
	}                                // returns the vehicle's type that a parking place may accommodate 
	void setPrice(int tmpPrice){
		if(this.type == "off-street"){
			this.price = 15;
		}else if(this.type == "on-street" && vehicle == "motorcycle"){
			this.price = 0;
		}else{
			if(count%2==0){
				this.price = 0;
			}else{
				this.price = 10;
			}
		}
	}                             // sets the price of a packing place;
                                                       // price is to be non-negative integer value
	int getPrice(){
		return this.price;
	}                                         // returns the price of a packing place
}