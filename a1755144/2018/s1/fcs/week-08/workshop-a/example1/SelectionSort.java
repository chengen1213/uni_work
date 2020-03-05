public class SelectionSort {

    public int[] sort(int[] array) {
        int value;
        for (int i = 0; i < array.length; i++) {
            swap(array, i, findMinIndex(array, i));
        }
        return array;
    }

    public void swap(int[] array, int index1, int index2) {
        int temp = array[index1];
        array[index1] = array[index2];
        array[index2] = temp;
    }

    public int findMinIndex(int[] array, int startIndex) {
        int value = array[startIndex];
        int index = startIndex;
        for (int i = startIndex + 1; i < array.length; i++) {
            if (array[i] > value) {
                value = array[i];
                index = i;
            }
        }
        return index;
    }

    public double[] sort(double[] array) {
        int value;
        for (int i = 0; i < array.length; i++) {
            swap(array, i, findMinIndex(array, i));
        }
        return array;
    }

    public void swap(double[] array, int index1, int index2) {
        double temp = array[index1];
        array[index1] = array[index2];
        array[index2] = temp;
    }

    public int findMinIndex(double[] array, int startIndex) {
        double value = array[startIndex];
        int index = startIndex;
        for (int i = startIndex + 1; i < array.length; i++) {
            if (array[i] > value) {
                value = array[i];
                index = i;
            }
        }
        return index;
    }

    public String[] sort(String[] toBeSorted) {
        String[] array = toBeSorted.clone();
        for (int i = 0; i < array.length; i++) {
            swap(array, i, findMaxIndex(array, i));
        }
        return array;
    }

    public void swap(String[] array, int index1, int index2) {
        String temp = array[index1];
        array[index1] = array[index2];
        array[index2] = temp;
    }

    public int findMaxIndex(String[] array, int starIndex) {
        String max = array[starIndex];
        int index = starIndex;
        for (int i = starIndex + 1; i < array.length; i++) {
            if (Tools.stringComparator(array[i], max)) {
                max = array[i];
                index = i;
            }
        }
        return index;
    }
}