public class InsertionSort {

    public int[] sort(int[] array) {

        for (int i = 1; i < array.length; i++) {
            int value = array[i];
            int index = i;
            while (index > 0 && array[index - 1] < value) {
                array[index] = array[index - 1];
                index--;
            }
            array[index] = value;
        }
        return array;
    }

    public double[] sort(double[] array) {

        for (int i = 1; i < array.length; i++) {
            double value = array[i];
            int index = i;
            while (index > 0 && array[index - 1] < value) {
                array[index] = array[index - 1];
                index--;
            }
            array[index] = value;
        }
        return array;
    }

    public String[] sort(String[] toBeSorted) {
        String[] array = toBeSorted.clone();
        for (int i = 1; i < array.length; i++) {
            String value = array[i];
            int index = i;
            while (index - 1 >= 0 && Tools.stringComparator(value, array[index - 1])) {
                array[index] = array[index - 1];
                index--;
            }
            array[index] = value;
//            insert(array,i);
        }
        return array;
    }

    public void insert(String[] array, int currentIndex) {
        String value = array[currentIndex];
        int index = currentIndex;
        for (int i = currentIndex - 1; i >= 0 && Tools.stringComparator(array[i], value); i--) {
            array[i + 1] = array[i];
            index = i;
        }
        array[index] = value;
    }

    public void printStringArray(String[] arr) {
        String out = "[";
        for (int i = 0; i < arr.length; i++) {
            out += arr[i];
            if (i + 1 != arr.length) {
                out += ", ";
            }
        }
        out += "]";
        System.out.println(out);
    }

}