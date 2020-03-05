public class LinkedList{
	
	Node head;

	public LinkedList(){
		head = null;
	}

	public void addFromHead(int value){
		Node node = new Node(value);
		if(head==null) {
			head = node;
		}else {
			node.setNext(head);
			head = node;
		}
	}

	public void addToTail(int value){
		Node node = new Node(value);
		if(head==null){
			head = node;
		}else {
			Node current = head;
			while(current.getNext()!=null){
				current = current.getNext();
			}
			current.setNext(node);
		}		
	}

	public boolean del(int value){
		if(head==null){
			return false;
		}else {
			Node prev = null;
			Node current = head;
			while(current!=null){
				if(current.getValue()==value){
					if(prev==null){
						head = current.getNext();
					}else {
						prev.setNext(current.getNext());
					}
					return true;
				}else {
					prev = current;
					current = current.getNext();
				}
			}
			return false;
		}
	}

	public boolean delIterate(int value){
		if(head==null){
			return false;
		}else {
			Node prev = null;
			Node current = head;
			boolean result = false;
			while(current!=null){
				if(current.getValue()==value){
					if(prev==null){
						head = current.getNext();
					}else {
						prev.setNext(current.getNext());
					}
					current = current.getNext();
					result = true;
				}else {
					prev = current;
					current = current.getNext();
				}
			}
			return result;
		}
	}

	public void print(){
		Node current = head;
		while(current!=null){
			System.out.print(current.getValue()+" ");
			current = current.getNext();
		}
	}
}