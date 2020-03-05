public class SelectionSort{

	public void sort(int[] array){
		for(int i = 0; i < array.length; i++){
			swap(array, i, findMinIndex(array,i));
		}
	}

	private int findMinIndex(int[] array, int start){
		int value = array[start];
		int index = start;
		for(int i = start; i < array.length; i++){
			if(array[i] < value){
				value = array[i];
				index = i;
			}
		}
		return index;
	}

	private void swap(int[] array, int index1, int index2){
		int temp = array[index1];
		array[index1] = array[index2];
		array[index2] = temp;
	} 
}