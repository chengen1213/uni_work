public class QuickSort implements SortAlgs {

    int[] indices;

    public int[] sort(int[] toBeSorted) {
        int[] array = toBeSorted.clone();
        int[] indices = new int[array.length];
        for (int i = 0; i < array.length; i++) {
            indices[i] = i;
        }
        quickSort(array, indices, 0, array.length - 1);
        return array;
    }

    public int[] sortIndex(int[] toBeSorted) {
        int[] array = toBeSorted.clone();
        int[] indices = new int[array.length];
        for (int i = 0; i < array.length; i++) {
            indices[i] = i;
        }
        quickSort(array, indices, 0, array.length - 1);
        return indices;
    }

    private void quickSort(int[] array, int[] indices, int startIndex, int endIndex) {
        if (endIndex > startIndex) {
            int pivotPos = partition(array, indices, startIndex, endIndex);
            quickSort(array, indices, startIndex, pivotPos - 1);
            quickSort(array, indices, pivotPos + 1, endIndex);
        }
    }

    private int partition(int[] array, int[] indices, int startIndex, int endIndex) {
        int pivot = array[endIndex];
        int pivotPos = startIndex;
        for (int i = startIndex; i < endIndex; i++) {
            if (array[i] <= pivot) {
                swap(array, indices, i, pivotPos);
                pivotPos++;
            }
        }
        swap(array, indices, pivotPos, endIndex);
        return pivotPos;
    }

    private void swap(int[] array, int[] indices, int index1, int index2) {
        int temp = array[index1];
        array[index1] = array[index2];
        array[index2] = temp;
        int tempIndex = indices[index1];
        indices[index1] = indices[index2];
        indices[index2] = tempIndex;
    }

    public int[] sort(String[] toBeSorted) {
        String[] array = toBeSorted.clone();
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