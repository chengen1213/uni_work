public class Main{
	public static void main(String[] args){
		
		Person person1 = new Person();
		person1.setName("haha");
		person1.setAge(30);
		System.out.println("Name:"+person1.getName());
		System.out.println("Age:"+person1.getAge());

		Person person2 = new Person();
		person2.setName("hehe");
		person2.setAge(40);
		System.out.println("Name:"+person2.getName());
		System.out.println("Age:"+person2.getAge());

	}
}