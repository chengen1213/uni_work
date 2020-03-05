public class Main {

	public static void main(String[] args){
		BinarySearch bs = new BinarySearch();
        LinearSearch ls = new LinearSearch();
		QuickSort qs = new QuickSort();
		int[] array = randomArrayGenerator(10);
		printArray(array);
		int[] afterSorted = qs.sort(array);
	    printArray(afterSorted);
		System.out.println(bs.search(afterSorted,5,0,afterSorted.length-1));
        System.out.println(ls.search(afterSorted,5,0));
	}

	private static int[] randomArrayGenerator(int arrayLength) {
        int[] randomArray = new int[arrayLength];
        for (int i = 0; i < arrayLength; i++) {
            randomArray[i] = (int) (Math.random() * 10);
        }
        return randomArray;
    }

    private static void printArray(int[] array) {
        String output;
        if (array.length > 0) {
            output = "[";
            for (int value : array) {
                output += value + ",";
            }
            output = output.trim();
            output = output.substring(0, output.length() - 1) + "]";
        } else {
            output = "[]";
        }
        System.out.println(output);
    }
	
}