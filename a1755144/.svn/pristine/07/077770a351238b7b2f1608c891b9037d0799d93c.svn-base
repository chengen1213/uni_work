
public class BubbleSort {

    public int[] sort(int[] array) {

        for (int i = array.length - 1; i >= 0; i--) {
            for (int j = 0; j < i; j++) {
                rise(array, j);
            }
        }
        return array;
    }

    public void rise(int[] array, int index) {
        if (array[index] < array[index + 1]) {
            swap(array, index, index + 1);
        }
    }

    public void swap(int[] array, int index1, int index2) {
        int temp = array[index1];
        array[index1] = array[index2];
        array[index2] = temp;
    }

    public double[] sort(double[] array) {

        for (int i = array.length - 1; i >= 0; i--) {
            for (int j = 0; j < i; j++) {
                rise(array, j);
            }
        }
        return array;
    }

    public void rise(double[] array, int index) {
        if (array[index] < array[index + 1]) {
            swap(array, index, index + 1);
        }
    }

    public void swap(double[] array, int index1, int index2) {
        double temp = array[index1];
        array[index1] = array[index2];
        array[index2] = temp;
    }

    public String[] sort(String[] toBeSorted) {
        String[] array = toBeSorted.clone();
        for (int i = array.length - 1; i > 0; i--) {
            rise(array, i);
        }
        return array;
    }

    private void rise(String[] array, int stopIndex) {
        for (int i = 0; i < stopIndex; i++) {
            if (!Tools.stringComparator(array[i], array[i + 1])) {
                swap(array, i, i + 1);
            }
        }
    }

    public void swap(String[] array, int index1, int index2) {
        String temp = array[index1];
        array[index1] = array[index2];
        array[index2] = temp;
    }

}
