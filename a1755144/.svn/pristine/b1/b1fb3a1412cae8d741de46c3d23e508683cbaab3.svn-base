
public class QuickSort extends MySortAlg {

    public int[] sort(int[] toBeSorted) {
        //keep the original array
        int[] array = toBeSorted.clone();
        return quickSort(array, 0, array.length - 1);
    }

    private int[] quickSort(int[] array, int startIndex, int endIndex) {

        if (startIndex < endIndex) {
            //partition the array and find the index of the pivot
            int pivotPos = partition(array, startIndex, endIndex);
            //call itself recursively
            quickSort(array, startIndex, pivotPos - 1);
            quickSort(array, pivotPos + 1, endIndex);
        }
        return array;
    }

    private int partition(int[] array, int startIndex, int endIndex) {

        int pivot = array[endIndex];
        int pivotPos = startIndex;
        //partition the array by the pivot
        for (int i = startIndex; i < endIndex; i++) {
            if (array[i] >= pivot) {
                swap(array, i, pivotPos);
                pivotPos++;
            }
        }
        swap(array, pivotPos, endIndex);
        return pivotPos;
    }
    //swap the given index of elements
    private void swap(int[] array, int index1, int index2) {
        int temp = array[index1];
        array[index1] = array[index2];
        array[index2] = temp;
    }
}
