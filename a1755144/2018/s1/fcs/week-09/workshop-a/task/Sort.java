
public class Sort<T extends Comparable> {

//    T[] toBeSorted;
//
//    public T[] getToBeSorted() {
//        return toBeSorted;
//    }
//
//    public void setToBeSorted(T[] toBeSorted) {
//        this.toBeSorted = toBeSorted;
//    }
//
//    public Sort(T[] toBeSorted) {
//
//        this.toBeSorted = toBeSorted;
//    }

    public void sort(T[] array) {
        //keep the original array
        quickSort(array, 0, array.length - 1);
    }

    private void quickSort(T[] array, int startIndex, int endIndex) {

        if (startIndex < endIndex) {
            //partition the array and find the index of the pivot
            int pivotPos = partition(array, startIndex, endIndex);
            //call itself recursively
            quickSort(array, startIndex, pivotPos - 1);
            quickSort(array, pivotPos + 1, endIndex);
        }
    }

    private int partition(T[] array, int startIndex, int endIndex) {

        T pivot = array[endIndex];
        int pivotPos = startIndex;
        //partition the array by the pivot
        for (int i = startIndex; i < endIndex; i++) {
            if (array[i].compareTo(pivot) < 0) {
                swap(array, i, pivotPos);
                pivotPos++;
            }
        }
        swap(array, pivotPos, endIndex);
        return pivotPos;
    }

    private void swap(T[] array, int index1, int index2) {
        T temp = array[index1];
        array[index1] = array[index2];
        array[index2] = temp;
    }

}