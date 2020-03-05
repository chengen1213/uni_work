public class Student{
	//student attributes
	private String name; 
	private int age; 
	private int period;
	//empty constructor
	public Student(){
		name = "unknown";
		age = 0;
		period = 0;
	}
	/*constructor with values
	  construct the student with given info*/
	public Student(String name, int age, int period){
		this.name = name;
		this.age = age;
		this.period = period;
	}
	//accessors and mutators
	public void setName(String name){
		this.name = name;
	}

	public void setAge(int age){
		this.age = age;
	}

	public void setPeriod(int period){
		this.period = period;
	}

	public String getName(){
		return this.name;
	}

	public int getAge(){
		return this.age;
	}

	public int getPeriod(){
		return this.period;
	}

}