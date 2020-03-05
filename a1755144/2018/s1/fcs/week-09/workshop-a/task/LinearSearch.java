public class LinearSearch<T extends Comparable> {

    public int search(T[] array, T value) {
        for (int i = 0; i < array.length; i++) {
            if (array[i].compareTo(value) == 0) {
                return i;
            }
        }
        return -1;
    }

    public int search(T[] array, T value, int index) {

        if (index >= array.length) {
            return -1;
        }

        if (array[index].compareTo(value) == 0) {
            return index;
        } else {
            return search(array, value, index + 1);
        }
    }

}