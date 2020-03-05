import java.util.ArrayList;
import java.util.Comparator;

public class Main {
    public static void main(String[] args) {

        ArrayList<MySortAlg> sortAlgs = new ArrayList<>();

        MySortAlg selectionSort = new SelectionSort();
        MySortAlg insertionSort = new InsertionSort();
        MySortAlg bubbleSort = new BubbleSort();
        MySortAlg mergeSort = new MergeSort();
        MySortAlg quickSort = new QuickSort();

        sortAlgs.add(selectionSort);
        sortAlgs.add(insertionSort);
        sortAlgs.add(bubbleSort);
        sortAlgs.add(mergeSort);
        sortAlgs.add(quickSort);

        //outer loop for all algorithms
        for (MySortAlg sortAlg : sortAlgs) {
            //print the names of each algorithm
            System.out.println(sortAlg.getClass().getName());
            System.out.println("-------------");
            //inner loop for times of testing
            for (int i = 0; i < 3; i++) {
                //generate random array for testing
                int[] randomArray = randomArrayGenerator(10);
                System.out.print("Original array: ");
                printArray(randomArray);
                System.out.print("Answer array: ");
                //generate the answer array
                int[] answerArray = sortArray(randomArray);
                printArray(answerArray);
                System.out.print("After sorted: ");
                //sort with specific algorithm
                int[] afterSorted = sortAlg.sort(randomArray);
                printArray(afterSorted);
                //test "test" method
                System.out.println("Test answer & sorted array: " + test(answerArray, afterSorted));
                //test "compare" method
                System.out.println("Compare original & sorted array: " + compare(randomArray, afterSorted));
                //test "findSmallestSum" method
                System.out.println("Smallest sum: " + findSmallestSum(randomArray));
                System.out.println("--------------------------------------");
            }
            System.out.println("----------------------------------------------");
        }

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

    private static int[] randomArrayGenerator(int arrayLength) {
        int[] randomArray = new int[arrayLength];
        for (int i = 0; i < arrayLength; i++) {
            randomArray[i] = (int) (Math.random() * 100);
        }
        return randomArray;
    }

    //sort with method sort of ArrayList
    private static int[] sortArray(int[] toBeSorted) {
        int[] sortedArray = new int[toBeSorted.length];
        ArrayList<Integer> arrayList = new ArrayList<>();
        for (int value : toBeSorted) {
            arrayList.add(value);
        }
        //define anonymous comparator
        arrayList.sort(new Comparator<Integer>() {
            @Override
            public int compare(Integer integer, Integer t1) {
                return t1 - integer;
            }
        });
        for (int i = 0; i < sortedArray.length; i++) {
            sortedArray[i] = arrayList.get(i);
        }
        return sortedArray;
    }

    private static boolean test(int[] result, int[] ans) {
        if (result.length != ans.length) {
            return false;
        }
        for (int i = 0; i < result.length; i++) {
            if (result[i] != ans[i]) {
                return false;
            }
        }
        return true;
    }

    private static boolean compare(int[] arr1, int[] arr2) {
        MySortAlg alg = new QuickSort();
        return test(alg.sort(arr1), alg.sort(arr2));
    }

    private static int findSmallestSum(int[] array) {
        MySortAlg alg = new QuickSort();
        int[] result = alg.sort(array);
        return result[result.length - 1] + result[result.length - 2];
    }
}