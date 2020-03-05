public class TaxiRank{

	Taxi head;

	public TaxiRank(){
		super();
	}

	public void add(Taxi taxi){

		if (isEmpty()) {
			head = taxi;
		}else{
			Taxi current = head;
			while(current.next != null){
				current = current.next;
			}
			current.next = taxi; 
		}
	}

	public Taxi remove(){

		Taxi tmp = new Taxi();
		if(isEmpty()){
			System.out.println("0 taxi!");
		}else{
			tmp = head;
			head = head.next;
		}
		return tmp;
	}

	public boolean isEmpty(){
		return head == null;
	}
}