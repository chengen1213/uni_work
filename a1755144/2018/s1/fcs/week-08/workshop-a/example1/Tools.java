public class Tools {
    public static boolean stringComparator(String string1, String string2) {
        int length;
        boolean b;
        if (string1.length() > string2.length()) {
            length = string2.length();
            b = true;
        } else {
            length = string1.length();
            b = false;
        }
        //compare each character
        for (int i = 0; i < length; i++) {
            if (string1.charAt(i) > string2.charAt(i)) {
                return true;
            } else if (string1.charAt(i) < string2.charAt(i)) {
                return false;
            }
        }
        //if all characters are the same, compare length
        return b;
    }

}
