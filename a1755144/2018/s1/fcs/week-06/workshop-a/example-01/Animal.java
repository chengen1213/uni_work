public class Animal{
	protected String name;
	private int age;
	private String favoriteFood;


	public void eat()
	{
		System.out.println("This animal has to eat!");
	}

	public void run()
	{
		System.out.println("When this animal is in danger, it runs!");
	}

	public String getName(){
		return this.name;
	}
	public void setName(String name){
		this.name = name;
	}

}