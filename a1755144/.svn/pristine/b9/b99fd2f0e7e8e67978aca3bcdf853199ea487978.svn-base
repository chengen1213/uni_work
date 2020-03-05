public class Pageant{

	private Finalist[] finalistList = new Finalist[3];
	private int index = 0;
	//private FinalistList finalistList = new FinalistList();

	public void addFinalist(String name, String country, int age, double[] skills, String moto){
		if(index<3){
			finalistList[index] = new Finalist(name, country, age, skills, moto);
			index++;
		}else {
			System.out.println("The list is full!");
		}
		//finalistList.add(new Finalist(name, country, age, skills, moto));
	}

	public void sortFinalist(){
		for(int i = 0; i < finalistList.length; i++){
			swap(i,findMax(i));
		}
	}

	public void printFinalists(){
		for(Finalist finalist : finalistList){
			finalist.display();
		}
		System.out.println("-------------------------");
		//finalistList.printList();
	}

	private int findMax(int from){
		double value = finalistList[from].getMean();
		int index = from;
		for(int i = from+1; i < finalistList.length; i++){
			if(finalistList[i].getMean() > value){
				value = finalistList[i].getMean();
				index = i;
			}
		}
		return index;
	}

	private void swap(int index1, int index2){
		Finalist temp = finalistList[index1];
		finalistList[index1] = finalistList[index2];
		finalistList[index2] = temp;
	}
}