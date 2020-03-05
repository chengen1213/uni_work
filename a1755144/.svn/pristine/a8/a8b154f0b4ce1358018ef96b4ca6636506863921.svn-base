public class LinkedList{
	Node head;
	int numNodes;
	
	LinkedList(){
		this.head = new Node();
		numNodes = 0;
	}

	public void add(int value){

		System.out.println("Value: " + value);
		if(this.numNodes == 0){
			this.head = new Node(value);
			this.numNodes++;
			System.out.println("First-Value: " + value);

		} else{			
			Node tmp = this.head;
			while(tmp.getNext() != null){
				tmp = tmp.getNext();
			}
			System.out.println("Other-Value: " + value);
			tmp.setNext(new Node(value));
			this.numNodes++;
		}
	}

	public void addFromHead(int value){

		System.out.println("Value: " + value);
		if(this.numNodes == 0){
			this.head = new Node(value);
			this.numNodes++;
			System.out.println("First-Value: " + value);

		} else{

			Node temp = new Node(Value);
			temp.setNext(head);
			head = temp;			
			System.out.println("Other-Value: " + value);
			this.numNodes++;
		}
	}

	public int del(int value){

		if(this.numNodes == 0){
			return -1;
		}else if(head.getValue() == value){
			head = head.getNext();
			this.numNodes--;
			return 1;
		}else{
			
			Node tmp = this.head;
			Node previous = new Node();
			while(tmp != null){
				if(tmp.getValue() == value){
					previous.setNext(tmp.getNext());
					tmp = tmp.getNext();
					this.numNodes--;
				}else{
					previous = tmp;
					tmp = tmp.getNext();
				}
			}
			return 1;
		}
	}



	public void display(){
		Node tmp = this.head;
		String ans = "[";
		while(tmp != null){
			if(tmp.getNext() == null)
				ans += tmp.getValue();
			else
				ans += tmp.getValue() + ", ";
			tmp = tmp.getNext();
		}
		System.out.println(ans + "]");

	}

}