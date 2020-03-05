public class MergeSort{

	public void sort(int[] array){
		mergeSort(array, 0, array.length-1);
	}

	public void mergeSort(int[] array, int start, int end){
		if(end > start){
			int midIndex = (start+end)/2;
			mergeSort(array, start, midIndex);
			mergeSort(array, midIndex+1, end);
			merge(array, start, end, midIndex);
		}
	}

	public void merge(int[] array, int start, int end, int midIndex){
		int[] left = new int[midIndex-start+1];
		int[] right = new int[end-midIndex];
		int index = start;

		for(int i = 0; i < left.length; i++){
			left[i] = array[index];
			index++;
		}
		for(int i = 0; i < right.length; i++){
			right[i] = array[index];
			index++;
		}
		int leftIndex = 0;
		int rightIndex = 0;

		for(int i = start; i <= end; i++){
			if(leftIndex<=left.length-1&&rightIndex<=right.length-1){
				if(left[leftIndex] < right[rightIndex]){
					array[i] = left[leftIndex];
					leftIndex++;
				}else {
					array[i] = right[rightIndex];
					rightIndex++;
				}
			}else {
				if(leftIndex<=left.length-1){
					array[i] = left[leftIndex];
					leftIndex++;
				}else {
					array[i] = right[rightIndex];
					rightIndex++;					
				}
			}
		}
	}
}