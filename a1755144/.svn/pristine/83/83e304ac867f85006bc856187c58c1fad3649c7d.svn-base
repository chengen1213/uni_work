public class MergeSort implements Sort{
	public int[] sort(int[] array){

	}
	// public abstract double[] sort(int[] array);
	// public abstract String[] sort(String[] array);
	// public abstract int[] sortIntIndex(int[] array);
	// public abstract int[] sortDoubleIndex(double[] array);
	// public abstract int[] sortStringIndex(String[] array);

	private void mergeSort(int[] array, int leftIndex, int rightIndex){

		if(rightIndex>leftIndex){

			int midIndex = (leftIndex+rightIndex)/2;
			mergeSort(array, leftIndex, midIndex);
			mergeSort(array, midIndex+1, rightIndex);
			merge(array, leftIndex, midIndex, rightIndex);
		}
	}

	private void merge(int[] array, int leftIndex, int midIndex, int rightIndex){

		int[] leftPart = new int[midIndex-leftIndex+1];
		int[] rightPart = new int[rightIndex-midIndex];
		int leftPartIndex = 0;
		int rightPartIndex = 0;
		int index = leftIndex;

		for(int i = 0; i < leftPart.length; i++){
			leftPart[i] = array[index];
			index++;
		}
		for(int i = 0; i < rightPart.length; i++){
			rightPart[i] = array[index];
			index++;
		}

		for(int i = leftIndex; i <= rightIndex; i++){
			if(leftPartIndex < leftPart.length && rightPartIndex < rightPart.length){
				if(leftPart[leftPartIndex]<rightPart[rightPartIndex]){
					array[i] = leftPart[leftPartIndex];
					leftPartIndex++;
				}else{
					array[i] = rightPart[rightPartIndex];
					rightPartIndex++;
				}
			}else{
				if(leftPartIndex < leftPart.length){
					array[i] = leftPart[leftPartIndex];	
					leftPartIndex++;				
				}else{
					array[i] = rightPart[rightPartIndex];
					rightPartIndex++;
				}
			}
		}
	}
}