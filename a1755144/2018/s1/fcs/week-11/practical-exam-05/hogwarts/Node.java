public class Node{
	/*node attributes
	  next indicates next element*/
	private Student info;
	private Node next;
	//empty constructor
	public Node(){
		info = null;
		next = null;
	}
	//constructor with student object
	public Node(Student tmpStudent){
		info = tmpStudent;
		next = null;
	}	
	//accessors and mutators
	public void setInfo(Student info){
		this.info = info;
	}

	public void setNext(Node next){
		this.next = next;
	}

	public Student getInfo(){
		return this.info;
	}

	public Node getNext(){
		return this.next;
	}		
}