import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {

        int numOfElements = 10;
        ArrayList<MySearchAlg> mySearchAlgs = new ArrayList<>();
        MySearchAlg linearSearch = new LinearSearch();
        MySearchAlg binarySearch = new BinarySearch();
        mySearchAlgs.add(linearSearch);
        mySearchAlgs.add(binarySearch);
        SortAlgs sortAlg = new QuickSort();

        for (MySearchAlg mySearchAlg : mySearchAlgs) {
            System.out.println();
            System.out.println(mySearchAlg.getClass().getName());
            System.out.println();
            //test each search algorithm for 3 times
            for (int i = 0; i < 3; i++) {
                int[] targetArray = randomArrayGenerator(numOfElements, 1);
                System.out.println("Test case " + (i + 1));
                System.out.print("Target array: ");
                printArray(targetArray);
                int ans = randomNumGenerator(targetArray.length);
                int result;
                //process for binary search
                if (mySearchAlg.getClass().getName().equals("BinarySearch")) {
                    //sorted index array
                    int[] indices = sortAlg.sortIndex(targetArray);
                    //sorted target array
                    int[] sortedArray = sortAlg.sort(targetArray);
                    //index of sorted array
                    result = mySearchAlg.search(sortedArray, targetArray[ans]);
                    //index of original array
                    result = indices[result];
                } else {
                    result = mySearchAlg.search(targetArray, targetArray[ans]);
                }
                //print test result
                System.out.println("Target value = " + targetArray[ans]
                        + ", result = " + result + ", ans = " + ans + ", test: " + test(result, ans));
                System.out.println("------------------------------------------");
            }
        }

        System.out.println();

        System.out.println("findMaxVal");
        System.out.println();
        //test the method for three times
        for (int i = 0; i < 3; i++) {
            System.out.println("Test case " + (i + 1));
            int[] randomArray = randomArrayGenerator(10, 2);
            System.out.print("Target array: ");
            printArray(randomArray);
            System.out.println("Max value = " + randomArray[findMaxVal(randomArray)]
                    + ", index = " + findMaxVal(randomArray) + ".");
            System.out.println("------------------------------------------");
        }

    }

    private static boolean test(int result, int ans) {
        return result == ans;
    }

    private static int findMaxVal(int[] array) {
        for (int i = 0; i < array.length - 1; i++) {
            if (array[i] > array[i + 1]) {
                return i;
            }
        }
        return array.length - 1;
//        SortAlgs sortAlgs = new QuickSort();
//        MySearchAlg mySearchAlg = new LinearSearch();
//        int[] result = sortAlgs.sort(array);
//        return mySearchAlg.search(array, result[array.length - 1]);
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

    private static int[] randomArrayGenerator(int arrayLength, int function) {
        int[] randomArray = new int[arrayLength];

        //function 0: simple random numbers
        if (function == 0) {
            for (int i = 0; i < arrayLength; i++) {
                int randomNum = (int) (Math.random() * 20);
                randomArray[i] = randomNum;
            }
        }
        //function 1: none repeated random numbers
        for (int i = 0; i < arrayLength; i++) {
            if (function == 1) {
                Boolean test = true;
                int randomNum = 0;
                while (test) {
                    test = false;
                    randomNum = (int) (Math.random() * 20);
                    for (int j = 0; j < i; j++) {
                        if (randomArray[j] == randomNum) {
                            test = true;
                            break;
                        }
                    }
                }
                randomArray[i] = randomNum;
            }
        }
        //function 2: none repeated, first ascending then descending
        int highpoint = (int) (Math.random() * (arrayLength - 2)) + 1;
        int prev = 0;
        for (int i = 0; i < arrayLength; i++) {
            if (function == 2) {
                if (i <= highpoint) {
                    int randomNum = (int) (Math.random() * 100);
                    randomArray[i] = randomNum + prev + 1;
                    prev = randomArray[i];
                } else {
                    int randomNum = (int) (Math.random() * 100);
                    randomArray[i] = prev - randomNum - 1;
                    prev = randomArray[i];
                }
            }
        }

        return randomArray;
    }

    private static int randomNumGenerator(int maxValue) {

        return (int) (Math.random() * maxValue);
    }

}	