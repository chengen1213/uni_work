public class InsertionSort{
	
	public int[] sort(int[] array){
		for(int i = 1; i < array.length; i++){
			int value = array[i];
			// int finalIndex = i;
			// for(int j = i-1; j>=0&&value<array[j]; j--){
			// 	array[j+1] = array[j];
			// 	finalIndex = j;
			// }
			// array[finalIndex] = value;
			int j;
			for(j = i; j > 0 && array[j-1] > value; j--){
				array[j] = array[j-1];
			}
			array[j] = value;
		}
		return array;
	}
}