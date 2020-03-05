public class Queue{
	/*queue arrtibutes
	  front indicates the front of the queue 
	  back indicates the back of the queue*/
	private Node front;
	private Node back;
	//empty constructor
	public Queue(){
		this.front = null;
		this.back = null;
	}
	//accessors and mutators
	public void setFront(Node front){
		this.front = front;
	}

	public void setBack(Node back){
		this.back = back;
	}

	public Node getFront(){
		return this.front;
	}

	public Node getBack(){
		return this.back;
	}
	//method enqueue: push an element into the back of queue
	public void enqueue(Student tmpStudent){
		Node newNode = new Node(tmpStudent);
		if(isEmpty()){
			//empty queue: update front and back
			front = newNode;
			back = newNode;
		}else{
			//insert tmpStudent to the back and update back
			back.setNext(newNode);
			back = newNode;
		}
	}
	//method dequeue: pop an element from the front of the queue
	public Student dequeue(){
		if(isEmpty()){
			//empty, nothing returned
			return null;
		}else{
			Student student = front.getInfo();
			if(front.getNext() == null){
				//the queue become empty, set front and back to null
				front = null;
				back = null;
			}else{
				//remove the element from the front of the queue
				front = front.getNext();
			}
			return student;
		}
	}
	//method displayQueue: display all the elements in the queue
	public void displayQueue(){
		if(isEmpty()){
			System.out.println("The Queue is Empty!");
		}else{
			Node current = front;
			int count = 0;
			//iterate all the students in the queue
			while (current != null) {
				count++;
				Student student = current.getInfo();
				//print student info
				System.out.println("#" + count + " " + student.getName() + ", "
					+ student.getAge() + " years old, "
					+ student.getPeriod() + "st year in Hogwards;");
				current = current.getNext();
			}
		}
	}
	//check if the queue is empty
	public boolean isEmpty(){
		return front == null;
	}			
}