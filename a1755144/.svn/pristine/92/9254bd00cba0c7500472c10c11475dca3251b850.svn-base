public class LinearSearch extends MySearchAlg {

    public int search(int[] array, int num) {
        for (int i = 0; i < array.length; i++) {
            if (array[i] == num) {
                return i;
            }
        }
        return -1;
    }

    //implement with recursion
    public int search(int[] array, int value, int index) {
        //base case
        if (index >= array.length) {
            return -1;
        }
        //hit!
        if (array[index] == value) {
            return index;
        } else {
            return search(array, value, index + 1);
        }
    }

}