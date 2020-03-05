public class InsertionSort{

	public void sort(int[] array){
		for(int i = 1; i < array.length; i++){
			for(int j = i; j > 0&&array[j] < array[j-1]; j--){
				swap(array, j, j-1);
			}
		}
	}

	private void swap(int[] array, int index1, int index2){
		int temp = array[index1];
		array[index1] = array[index2];
		array[index2] = temp;
	} 
}