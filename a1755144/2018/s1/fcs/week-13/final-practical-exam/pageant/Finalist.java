public class Finalist extends Contestant{

	private String moto;
	private String[] skillNames = {"confidance", "ambition", "credibility", "grace", "authenticity"};
	private Finalist next;

	public Finalist(String name, String country, int age, String moto){
		super(name, country, age);
		this.moto = moto;
	}

	public Finalist(String name, String country, int age, double[] skills, String moto){
		super(name, country, age, skills);
		this.moto = moto;
	}

	public void display(){

		System.out.println("::Contestant: "+name + " " + age + " yr from " + country);
		for(int i = 0; i < skillNames.length; i++){
			System.out.println(":: " + skillNames[i] + ": " + skills[i]);
		}
		System.out.println(":: moto: " + this.moto);

	}

	public void setMoto(String moto){
		this.moto = moto;
	}

	public String getMoto(){
		return this.moto;
	}

	public void setNext(Finalist next){
		this.next = next;
	}

	public Finalist getNext(){
		return this.next;
	}
}