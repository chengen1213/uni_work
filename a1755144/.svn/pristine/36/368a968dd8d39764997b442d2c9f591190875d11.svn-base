
public class BubbleSort extends MySortAlg {

    public int[] sort(int[] toBeSorted) {
        //keep the original array
        int[] array = toBeSorted.clone();
        //fix the unsorted elements
        for (int i = array.length - 1; i >= 0; i--) {
            for (int j = 0; j < i; j++) {
                rise(array, j);
            }
        }
        return array;
    }
    //compare the nearby elements and swap them if needed
    private void rise(int[] array, int index) {
        if (array[index] < array[index + 1]) {
            swap(array, index, index + 1);
        }
    }
    //swap the given index of elements
    private void swap(int[] array, int index1, int index2) {
        int temp = array[index1];
        array[index1] = array[index2];
        array[index2] = temp;
    }

}
