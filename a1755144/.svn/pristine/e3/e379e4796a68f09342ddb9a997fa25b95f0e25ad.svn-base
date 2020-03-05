import java.util.Random;

public class Tools {

    final static String[] abc = {"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z",
            "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"
    };

    public static int stringComparator(String string1, String string2) {
        int length;
        int test = string1.length() - string2.length();
        if (string1.length() > string2.length()) {
            length = string2.length();
        } else {
            length = string1.length();
        }
        //compare each character
        for (int i = 0; i < length; i++) {
            if (string1.charAt(i) != string2.charAt(i)) {
                return string1.charAt(i) - string2.charAt(i);
            }
        }
        //if all characters are the same, compare length
        return test;
    }

    public static int[] getRandomArray(int numElements) {
        Random rand = new Random();
        int[] ans = new int[numElements];
        for (int i = 0; i < numElements; i++) {
            ans[i] = rand.nextInt(50);
        }
        return ans;
    }

    public static String[] getRandomStringArray(int numElements) {
        Random rand = new Random();
        String[] ans = new String[numElements];
        for (int i = 0; i < numElements; i++) {
            ans[i] = abc[rand.nextInt(51)];
        }
        return ans;
    }

    public static void printArray(int[] arr) {
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

    public static String nameGenerator() {

        Random rand = new Random();
        String name = "";
        int length = 5 + rand.nextInt(5);
        for(int i = 0; i < length; i++){
            name+=abc[rand.nextInt(51)];
        }
        return name;
    }

    public static int yearGenerator() {

        Random rand = new Random();
        int year = 1800;
        year += rand.nextInt(218);
        return year;
    }

    public static int gradeGenerator() {

        Random rand = new Random();
        int grade = 40;
        grade += rand.nextInt(60);
        return grade;
    }

}
