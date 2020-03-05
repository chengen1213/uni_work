public class Contestant extends Person{
	//attributes
	protected String name, country;
	protected int age;
	protected double[] skills;
	//constructors
	public Contestant(String name, String country, int age){
		this.name = name;
		this.country = country;
		this.age = age;
	}
	public Contestant(String name, String country, int age, double[] skills){
		this.name = name;
		this.country = country;
		this.age = age;
		this.skills = skills;
	}
	//methods
	public double getMean(){
		double mean = 0;
		int count = 0;
		//iterate each element in the skills
		for(double score : skills){
			if(score != 0){
			mean += score;
			count++;
			}
		}
		mean = mean/count;
		return mean;
	}
	//accessors and mutators
	public void setName(String name){
		this.name = name;
	} 

	public String getName(){
		return this.name;
	}

	public void setCountry(String country){
		this.country = country;
	} 

	public String getCountry(){
		return this.country;
	}

	public void setAge(int age){
		this.age = age;
	} 

	public int getAge(){
		return this.age;
	}

	public void setSkills(double[] skills){
		this.skills = skills;
	} 

	public double[] getSkills(){
		return this.skills;
	}
}