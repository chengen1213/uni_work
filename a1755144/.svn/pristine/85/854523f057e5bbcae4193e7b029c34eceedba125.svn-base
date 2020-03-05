
public class InsertionSort extends MySortAlg {

    public int[] sort(int[] toBeSorted) {

        int[] array = toBeSorted.clone();

        for (int i = 1; i < array.length; i++) {
            insert(array, i);
        }
        return array;
    }

    private void insert(int[] array, int startIndex) {
        int index = startIndex;
        int value = array[startIndex];
        for (int i = startIndex - 1; i >= 0 && value > array[i]; i--) {
            array[i + 1] = array[i];
            index = i;
        }
        array[index] = value;
    }

}