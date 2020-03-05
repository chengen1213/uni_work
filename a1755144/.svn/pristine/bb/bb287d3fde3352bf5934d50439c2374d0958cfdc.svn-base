
public class SelectionSort extends MySortAlg {

    public int[] sort(int[] toBeSorted) {

        int[] array = toBeSorted.clone();
        //looking for the max value from the rest of unsorted elements 
        for (int i = 0; i < array.length; i++) {
            swap(array, i, findMinIndex(array, i));
        }
        return array;
    }

    private int findMinIndex(int[] array, int starIndex) {
        int minNum = array[starIndex];
        int index = starIndex;
        for (int i = starIndex + 1; i < array.length; i++) {
            if (array[i] > minNum) {
                minNum = array[i];
                index = i;
            }
        }
        return index;
    }
    //swap the given index of elements
    private void swap(int[] array, int index1, int index2) {
        int temp = array[index1];
        array[index1] = array[index2];
        array[index2] = temp;
    }

}