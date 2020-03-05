public class BinarySearch extends MySearchAlg {

    public int search(int[] array, int num) {
        int leftIndex = 0;
        int rightIndex = array.length - 1;
        while (rightIndex - leftIndex >= 0) {
            int midIndex = (rightIndex + leftIndex) / 2;
            //if found, return, otherwise, narrow the target area
            if (num > array[midIndex]) {
                leftIndex = midIndex + 1;
            } else if (num < array[midIndex]) {
                rightIndex = midIndex - 1;
            } else {
                return midIndex;
            }
        }
        return -1;
    }

    //implement with recursion
    public int search(int[] array, int value, int leftIndex, int rightIndex) {
        //base case
        if (rightIndex - leftIndex < 0) {
            return -1;
        }
        int midIndex = (leftIndex + rightIndex) / 2;
        //if found, return, otherwise, narrow the target area and call itself recursively
        if (array[midIndex] == value) {
            return midIndex;
        } else if (array[midIndex] > value) {
            rightIndex = midIndex - 1;
            return search(array, value, leftIndex, rightIndex);
        } else {
            leftIndex = midIndex + 1;
            return search(array, value, leftIndex, rightIndex);
        }
    }
}