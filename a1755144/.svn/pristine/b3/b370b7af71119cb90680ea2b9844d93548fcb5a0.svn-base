public class Main{
	public static void main(String[] args) {
		
		int[] array = randomArrayGenerator(10);
		QuickSort quickSort = new QuickSort();
		MergeSort mergeSort = new MergeSort();
		InsertionSort insertionSort = new InsertionSort();
		BubbleSort bubbleSort = new BubbleSort();
		SelectionSort selectionSort = new SelectionSort();
		BinarySearch binarySearch = new BinarySearch();
		LinearSearch linearSearch = new LinearSearch();
		mergeSort.sort(array);
		for(int value : array){
			System.out.print(value + " ");
		}
		System.out.println();
		System.out.println(binarySearch.search(array,9));
		System.out.println(binarySearch.binarySearch(array,9));
		int[] array2 = {5,7,6,3,4,1,2,8,9};
		System.out.println(linearSearch.search(array2,3));
	}

	private static int[] randomArrayGenerator(int arrayLength) {
        int[] randomArray = new int[arrayLength];
        for (int i = 0; i < arrayLength; i++) {
            randomArray[i] = (int) (Math.random() * 100);
        }
        return randomArray;
    }
}