public class MergeSort {

    public int[] getSubArray(int[] arr, int start, int end) {
        int[] ans = new int[end - start];
        int nElement = 0;
        for (int i = start; i < end; i++) {
            ans[nElement] = arr[i];
            nElement++;
        }
        return ans;
    }

    public void printArray(int[] arr) {
        String ans = "[";
        for (int i = 0; i < arr.length; i++) {
            ans += arr[i];
            if (i != arr.length - 1)
                ans += ", ";
        }
        ans += "]";
        System.out.println(ans);
    }

    public int[] merge(int[] arr1, int[] arr2) {
        int[] ans = new int[arr1.length + arr2.length];
        int[] firstArray = arr1;
        int[] secondArray = arr2;
        int firstIndex = 0;
        int secondIndex = 0;
        int ansIndex = 0;

        while (firstIndex < firstArray.length && secondIndex < secondArray.length) {
            if (firstArray[firstIndex] > secondArray[secondIndex]) {
                ans[ansIndex] = firstArray[firstIndex];
                firstIndex++;
            } else {
                ans[ansIndex] = secondArray[secondIndex];
                secondIndex++;
            }
            ansIndex++;
        }
        while (firstIndex < firstArray.length) {
            ans[ansIndex] = firstArray[firstIndex];
            firstIndex++;
            ansIndex++;
        }
        while (secondIndex < secondArray.length) {
            ans[ansIndex] = secondArray[secondIndex];
            secondIndex++;
            ansIndex++;
        }

        return ans;
    }


    public int[] mergeSort(int[] arr) {
        int n = arr.length;
        if (n != 1) {
            int n2 = (int) Math.ceil((double) n / 2);
            int[] firstArray = getSubArray(arr, 0, n2);
            //this.printArray(firstArray);
            int[] sorted1 = mergeSort(firstArray);


            int[] secondArray = getSubArray(arr, n2, n);
            //this.printArray(secondArray);
            int[] sorted2 = mergeSort(secondArray);

            //System.out.println("returning-array: ");
            //printArray(this.merge(sorted1, sorted2));
            return this.merge(sorted1, sorted2);
        } else {
            //System.out.println("returning: " + arr[0]);
            return arr;
        }


    }

    public int[] sort(int[] arr) {
        return this.mergeSort(arr);
    }


    public double[] getSubArray(double[] arr, int start, int end) {
        double[] ans = new double[end - start];
        int nElement = 0;
        for (int i = start; i < end; i++) {
            ans[nElement] = arr[i];
            nElement++;
        }
        return ans;
    }

    public void printArray(double[] arr) {
        String ans = "[";
        for (int i = 0; i < arr.length; i++) {
            ans += arr[i];
            if (i != arr.length - 1)
                ans += ", ";
        }
        ans += "]";
        System.out.println(ans);
    }

    public double[] merge(double[] arr1, double[] arr2) {
        double[] ans = new double[arr1.length + arr2.length];
        double[] firstArray = arr1;
        double[] secondArray = arr2;
        int firstIndex = 0;
        int secondIndex = 0;
        int ansIndex = 0;

        while (firstIndex < firstArray.length && secondIndex < secondArray.length) {
            if (firstArray[firstIndex] > secondArray[secondIndex]) {
                ans[ansIndex] = firstArray[firstIndex];
                firstIndex++;
            } else {
                ans[ansIndex] = secondArray[secondIndex];
                secondIndex++;
            }
            ansIndex++;
        }
        while (firstIndex < firstArray.length) {
            ans[ansIndex] = firstArray[firstIndex];
            firstIndex++;
            ansIndex++;
        }
        while (secondIndex < secondArray.length) {
            ans[ansIndex] = secondArray[secondIndex];
            secondIndex++;
            ansIndex++;
        }

        return ans;
    }


    public double[] mergeSort(double[] arr) {
        int n = arr.length;
        if (n != 1) {
            int n2 = (int) Math.ceil((double) n / 2);
            double[] firstArray = getSubArray(arr, 0, n2);
            //this.printArray(firstArray);
            double[] sorted1 = mergeSort(firstArray);


            double[] secondArray = getSubArray(arr, n2, n);
            //this.printArray(secondArray);
            double[] sorted2 = mergeSort(secondArray);

            //System.out.println("returning-array: ");
            //printArray(this.merge(sorted1, sorted2));
            return this.merge(sorted1, sorted2);
        } else {
            //System.out.println("returning: " + arr[0]);
            return arr;
        }


    }

    public double[] sort(double[] arr) {
        return this.mergeSort(arr);
    }

    public String[] sort(String[] toBeSorted) {
        String[] array = toBeSorted.clone();
        mergeSort(array, 0, array.length - 1);
        return array;
    }

    public void mergeSort(String[] array, int index1, int index2) {
        if (index2 > index1) {
            int midIndex = (index1 + index2) / 2;
            mergeSort(array, index1, midIndex);
            mergeSort(array, midIndex + 1, index2);
            merge(array, index1, midIndex, index2);
        }
    }

    public void merge(String[] array, int index1, int midIndex, int index2) {
        String[] leftPart = new String[midIndex - index1 + 1];
        String[] rightPart = new String[index2 - midIndex];
        for (int i = 0; i < leftPart.length; i++) {
            leftPart[i] = array[index1 + i];
        }
        for (int i = 0; i < rightPart.length; i++) {
            rightPart[i] = array[midIndex + 1 + i];
        }
        int leftIndex = 0;
        int rightIndex = 0;
        for (int i = index1; i <= index2; i++) {
            if (leftIndex < leftPart.length && rightIndex < rightPart.length) {
                if (Tools.stringComparator(leftPart[leftIndex], rightPart[rightIndex])) {
                    array[i] = leftPart[leftIndex];
                    leftIndex++;
                } else {
                    array[i] = rightPart[rightIndex];
                    rightIndex++;
                }
            } else {
                if (leftIndex < leftPart.length) {
                    array[i] = leftPart[leftIndex];
                    leftIndex++;
                } else {
                    array[i] = rightPart[rightIndex];
                    rightIndex++;
                }
            }
        }
    }

}