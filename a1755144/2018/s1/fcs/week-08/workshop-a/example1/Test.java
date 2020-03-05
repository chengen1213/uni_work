import java.util.Random;

public class Test {
    public static int[] getRandomArray(int numElements) {
        Random rand = new Random();
        int[] ans = new int[numElements];
        for (int i = 0; i < numElements; i++) {
            ans[i] = rand.nextInt(50);
        }
        return ans;
    }

    public static String[] getRandomStringArray(int numElements) {
        String[] abc = {"a", "b", "c", "d", "e", "f", "g", "h", "i", "j"};
        Random rand = new Random();
        String[] ans = new String[numElements];
        for (int i = 0; i < numElements; i++) {
            ans[i] = abc[rand.nextInt(5)];
        }
        return ans;
    }

    public static void printArray(int[] arr) {
        String out = "[";
        for (int i = 0; i < arr.length; i++) {
            out += arr[i];
            if (i + 1 != arr.length) {
                out += ", ";
            }
        }
        out += "]";
        System.out.println(out);
    }

    public static void printStringArray(String[] arr) {
        String out = "[";
        for (int i = 0; i < arr.length; i++) {
            out += arr[i];
            if (i + 1 != arr.length) {
                out += ", ";
            }
        }
        out += "]";
        System.out.println(out);
    }

    public static void main(String[] args) {
        InsertionSort insertion = new InsertionSort();
        SelectionSort selectionSort = new SelectionSort();
        BubbleSort bubbleSort = new BubbleSort();
        MergeSort mergeSort = new MergeSort();
        QuickSort quickSort = new QuickSort();
        int[] arr = getRandomArray(5);
        String[] arr2 = getRandomStringArray(10);
        printStringArray(arr2);
        // int [] sortedArray = insertion.sort(arr);
        int[] sortedArray = quickSort.sort(arr);
        String[] sortedArray2 = quickSort.sort(arr2);
        printStringArray(sortedArray2);
    }
}