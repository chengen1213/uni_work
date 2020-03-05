public class BinarySearch<T extends Comparable> {

    public int search(T[] array, T value) {
        int leftIndex = 0;
        int rightIndex = array.length - 1;
        while (rightIndex - leftIndex >= 0) {
            int midIndex = (rightIndex + leftIndex) / 2;
            if (value.compareTo(array[midIndex]) > 0) {
                leftIndex = midIndex + 1;
            } else if (value.compareTo(array[midIndex]) < 0) {
                rightIndex = midIndex - 1;
            } else {
                return midIndex;
            }
        }
        return -1;
    }

    public int search(T[] array, T value, int leftIndex, int rightIndex) {
        if (rightIndex - leftIndex < 0) {
            return -1;
        }
        int midIndex = (leftIndex + rightIndex) / 2;
        if (array[midIndex] == value) {
            return midIndex;
        } else if (array[midIndex].compareTo(value) > 0) {
            rightIndex = midIndex - 1;
            return search(array, value, leftIndex, rightIndex);
        } else {
            leftIndex = midIndex + 1;
            return search(array, value, leftIndex, rightIndex);
        }
    }
}