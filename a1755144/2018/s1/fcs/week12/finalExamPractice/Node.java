public class Node{
	
	int value;
	Node next;

	public Node(int value){
		this.value = value;
		this.next = null;
	}

	public void setValue(int value){
		this.value = value;
	}

	public int getValue(){
		return this.value;
	}

	public void setNext(Node next){
		this.next = next;
	}

	public Node getNext(){
		return this.next;
	}
}