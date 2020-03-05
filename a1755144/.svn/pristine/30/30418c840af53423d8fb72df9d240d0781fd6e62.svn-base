public class FinalistList{

	Finalist head;
	int count = 0;

	public FinalistList(){
		head = null;
	}

	public void add(Finalist finalist){

		if(count < 3){
			if(head!=null){
				Finalist temp = head;
				while(temp.getNext()!=null){
					temp = temp.getNext();
				}
				temp.setNext(finalist);
			}else {
				head = finalist;
			}
			count++;
		}else {
			System.out.println("The list is full!");
		}
	}

	public void printList(){
		Finalist current = head;
		while (current!=null) {
			current.display();
			current = current.getNext();
		}
	}
}