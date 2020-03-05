public class QuickSort implements SortAlgs {

    int[] indices;

    public int[] sort(int[] array) {
        quickSort(array, 0, array.length - 1);
        return array;
    }

    private void quickSort(int[] array, int startIndex, int endIndex) {
        if (endIndex > startIndex) {
            int pivotPos = partition(array, startIndex, endIndex);
            quickSort(array, startIndex, pivotPos - 1);
            quickSort(array, pivotPos + 1, endIndex);
        }
    }

    private int partition(int[] array, int startIndex, int endIndex) {
        int pivot = array[endIndex];
        int pivotPos = startIndex;
        for (int i = startIndex; i < endIndex; i++) {
            if (array[i] <= pivot) {
                swap(array, i, pivotPos);
                pivotPos++;
            }
        }
        swap(array, pivotPos, endIndex);
        return pivotPos;
    }

    private void swap(int[] array, int index1, int index2) {
        int temp = array[index1];
        array[index1] = array[index2];
        array[index2] = temp;
    }

    public int[] sort(String[] array) {
        indices = new int[array.length];
        for (int i = 0; i < indices.length; i++) {
            indices[i] = i;
        }
        quickSort(array, 0, array.length - 1);
        return indices;
    }

    private void quickSort(String[] array, int startIndex, int endIndex) {
        if (endIndex > startIndex) {
            int pivotPos = partition(array, startIndex, endIndex);
            quickSort(array, startIndex, pivotPos - 1);
            quickSort(array, pivotPos + 1, endIndex);
        }
    }

    private int partition(String[] array, int startIndex, int endIndex) {
        String pivot = array[endIndex];
        int pivotPos = startIndex;
        for (int i = startIndex; i < endIndex; i++) {
            if (compareString(pivot, array[i]) >= 0) {
                swap(array, i, pivotPos);
                pivotPos++;
            }
        }
        swap(array, pivotPos, endIndex);
        return pivotPos;
    }

    private void swap(String[] array, int index1, int index2) {
        String temp = array[index1];
        int temp2 = indices[index1];
        array[index1] = array[index2];
        indices[index1] = indices[index2];
        array[index2] = temp;
        indices[index2] = temp2;
    }

    public int compareString(String s1, String s2) {
        int strDif = 0;
        for (int i = 0; i < s1.length() && i < s2.length(); i++) {
            strDif = s1.charAt(i) - s2.charAt(i);
            if (strDif != 0) {
                return strDif;
            }
        }
        return s1.length() - s2.length();
    }

}