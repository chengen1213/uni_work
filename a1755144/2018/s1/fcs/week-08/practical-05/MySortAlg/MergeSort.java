
public class MergeSort extends MySortAlg {

    public int[] sort(int[] toBeSorted) {
        //keep the original array
        int[] array = toBeSorted.clone();
        return mergeSort(array, 0, array.length - 1);
    }

    private int[] mergeSort(int[] array, int index1, int index2) {
        //divide and call itself recursively
        if (index2 > index1) {
            int midIndex = (index1 + index2) / 2;
            mergeSort(array, index1, midIndex);
            mergeSort(array, midIndex + 1, index2);
            //merge the divided sub-arrays
            merge(array, index1, midIndex, index2);
        }
        return array;
    }

    private void merge(int[] array, int index1, int index2, int index3) {
        int currentIndex = index1;
        int leftIndex = 0;
        int rightIndex = 0;
        int[] leftPart = new int[index2 - index1 + 1];
        int[] rightPart = new int[index3 - index2];
        //clone the two sub-array
        for (int i = 0; i < leftPart.length; i++) {
            leftPart[i] = array[currentIndex];
            currentIndex++;
        }
        for (int i = 0; i < rightPart.length; i++) {
            rightPart[i] = array[currentIndex];
            currentIndex++;
        }
        //sort the elements of the two sub-array
        for (int i = index1; i <= index3; i++) {
            if (leftIndex < leftPart.length && rightIndex < rightPart.length) {
                if (leftPart[leftIndex] > rightPart[rightIndex]) {
                    array[i] = leftPart[leftIndex];
                    leftIndex++;
                } else {
                    array[i] = rightPart[rightIndex];
                    rightIndex++;
                }
            } else {//one of the two array is empty
                if (leftIndex < leftPart.length) {
                    array[i] = leftPart[leftIndex];
                    leftIndex++;
                } else {
                    array[i] = rightPart[rightIndex];
                    rightIndex++;
                }
            }
        }
    }

}