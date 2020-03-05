
public class QuickSort {

    public int[] sort(int[] array) {
        return quickSort(array, 0, array.length - 1);
    }

    public int[] quickSort(int[] array, int startIndex, int endIndex) {

        if (startIndex < endIndex) {
            int pivotPos = partition(array, startIndex, endIndex);
            quickSort(array, startIndex, pivotPos - 1);
            quickSort(array, pivotPos + 1, endIndex);
        }
        return array;
    }

    public int partition(int[] array, int startIndex, int endIndex) {
        int pivot = array[endIndex];
        int pivotPos = startIndex;

        for (int i = startIndex; i < endIndex; i++) {
            if (array[i] >= pivot) {
                swap(array, i, pivotPos);
                pivotPos++;
            }
        }
        swap(array, pivotPos, endIndex);
        return pivotPos;
    }

    public void swap(int[] array, int index1, int index2) {
        int temp = array[index1];
        array[index1] = array[index2];
        array[index2] = temp;
    }

    public double[] sort(double[] array) {
        return quickSort(array, 0, array.length - 1);
    }

    public double[] quickSort(double[] array, int startIndex, int endIndex) {

        if (startIndex < endIndex) {
            int pivotPos = partition(array, startIndex, endIndex);
            quickSort(array, startIndex, pivotPos - 1);
            quickSort(array, pivotPos + 1, endIndex);
        }
        return array;
    }

    public int partition(double[] array, int startIndex, int endIndex) {
        double pivot = array[endIndex];
        int pivotPos = startIndex;

        for (int i = startIndex; i < endIndex; i++) {
            if (array[i] >= pivot) {
                swap(array, i, pivotPos);
                pivotPos++;
            }
        }
        swap(array, pivotPos, endIndex);
        return pivotPos;
    }

    public void swap(double[] array, int index1, int index2) {
        double temp = array[index1];
        array[index1] = array[index2];
        array[index2] = temp;
    }

    public String[] sort(String[] toBeSorted) {
        String[] array = toBeSorted.clone();
        quickSort(array, 0, array.length - 1);
        return array;
    }

    public void quickSort(String[] array, int index1, int index2) {
        if (index2 > index1) {
            int pivotPos = partition(array, index1, index2);
            quickSort(array, index1, pivotPos - 1);
            quickSort(array, pivotPos + 1, index2);
        }
    }

    public int partition(String[] array, int index1, int index2) {
        String pivot = array[index2];
        int pivotPos = index1;
        for (int i = index1; i < index2; i++) {
            if (Tools.stringComparator(array[i], pivot)) {
                swap(array, i, pivotPos);
                pivotPos++;
            }
        }
        swap(array, pivotPos, index2);
        return pivotPos;
    }

    public void swap(String[] array, int index1, int index2) {
        String temp = array[index1];
        array[index1] = array[index2];
        array[index2] = temp;
    }
}
