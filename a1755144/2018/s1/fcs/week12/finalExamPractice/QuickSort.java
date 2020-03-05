public class QuickSort{
	
	public void sort(int[] array){
		quickSort(array, 0, array.length-1);
	}

	private void quickSort(int[] array, int start, int end){
		if(end > start){
			int pivotPos = divide(array, start, end);
			quickSort(array, start, pivotPos-1);
			quickSort(array, pivotPos+1, end);
		}

	}

	private int divide(int[] array, int start, int end){
		int pivot = array[end];
		int pivotPos = start;
		for(int i = start; i < end; i++){
			if(array[i] < pivot){
				swap(array ,i, pivotPos);
				pivotPos++;
			}
		}
		swap(array ,pivotPos, end);
		return pivotPos;
	}

	private void swap(int[] array, int index1, int index2){
		int temp = array[index1];
		array[index1] = array[index2];
		array[index2] = temp;
	} 
}