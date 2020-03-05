/*
*   Foundations of Computer Science
*   2018, Semester 01
*   Practical-Exam-04
*
*   student (id): a111111
*   student (name): John Smitth
*
* Note: in order to finish your exam you need to make changes in this class
*
*/
public class Selection extends Sort{

	@Override
	public int [] sortIntByIndex(int [] arr){

		int [] index = this.getIndex(arr.length);

		for(int i = 0; i < arr.length; i++){
			swap(arr,index,i,findMinInt(arr,i));
		}
		return index;
	}

	@Override
	public int [] sortInt(int [] arr){

		int [] index = this.getIndex(arr.length);

		for(int i = 0; i < arr.length; i++){
			swap(arr,index,i,findMinInt(arr,i));
		}
		return arr;
	}

	@Override
	public int [] sortStringByIndex(String [] arr){

		int [] index = this.getIndex(arr.length);

		for(int i = 0; i < arr.length; i++){
			swap(arr,index,i,findMinString(arr,i));
		}
		return index;
	}

	@Override
	public String [] sortString(String [] arr){

		int [] index = this.getIndex(arr.length);

		for(int i = 0; i < arr.length; i++){
			swap(arr,index,i,findMinString(arr,i));
		}
		return arr;
	}

	private void swap(int[] array, int[] indices, int index1, int index2){
		int temp = array[index1];
		int temp2 = indices[index1];
		array[index1] = array[index2];
		indices[index1] = indices[index2];
		array[index2] = temp;
		indices[index2] = temp2;
	}

	private void swap(String[] array, int[] indices, int index1, int index2){
		String temp = array[index1];
		int temp2 = indices[index1];
		array[index1] = array[index2];
		indices[index1] = indices[index2];
		array[index2] = temp;
		indices[index2] = temp2;
	}

	private int findMinInt(int[] array, int startIndex){

		int value = array[startIndex];
		int index = startIndex;
		for(int i = startIndex+1; i < array.length; i++){
			if(array[i] < value){
				value = array[i];
				index = i;
			}
		}
		return index;
	}

	private int findMinString(String[] array, int startIndex){
		
		String value = array[startIndex];
		int index = startIndex;
		for(int i = startIndex+1; i < array.length; i++){
			if(array[i].compareTo(value) < 0){
				value = array[i];
				index = i;
			}
		}
		return index;
	}
}
