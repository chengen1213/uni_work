import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        System.out.println("The input number is 109, the next happy number is " + nextHappyNum(109) + ".");
        System.out.println("Original Code: 3[b2[ca]1[d]], after decoded: " + decodeString("3[b2[ca]1[d]]") + ".");
        System.out.println("Original Code: 3[a3[b]1[ab]], after decoded: " + decodeString("3[a3[b]1[ab]]") + ".");
        System.out.println("Original Code: 11[3a]3[3bc]1[c], after decoded: " + decodeString("11[3a]3[3bc]1[c]") + ".");
    }

    private static int nextHappyNum(int num) {

        ArrayList<Integer> checkList = new ArrayList<>();
        boolean redo = true;
        while (redo) {
            num++;
            //not a happy, test next number
            redo = !checkHappy(num, checkList);
        }
        return num;
    }

    private static boolean checkHappy(int happyNum, ArrayList<Integer> checkList) {
        int sumOfSquare = 0;
        //square every digit number and add all of them together
        while (happyNum > 0) {
            int digit = happyNum % 10;
            sumOfSquare += digit * digit;
            happyNum = happyNum / 10;
        }
        //base case
        if (sumOfSquare == 1) {
            return true;
        } else {
            for (int value : checkList) {
                //see if the result is in the cycle
                if (value == sumOfSquare) {
                    return false;
                }
            }
        }
        //not equal to 1, not equal to any number in the checklist then
        //add to the checklist and call itself recursively
        checkList.add(sumOfSquare);
        return checkHappy(sumOfSquare, checkList);
    }

    private static String decodeString(String code) {
//        System.out.println(code);
        String target = code.trim();
        Boolean containNum = false;
        for (String s : target.split("")) {
            if (isANum(s)) {
                containNum = true;
            }
        }
        //base case
        if (target.length() < 2 || !containNum && target.indexOf("[") < 0) {
//            System.out.println(target);
            return target;
        }
        //split the given code
        String[] context = codeSplitter(target);

        String result = "";
        for (String subCode : context) {
//            System.out.println(subCode);
            if (subCode.length() > 0) {
                //fix the leading number
                String printTimes = "";
                if (subCode.length() > 1) {
                    while (isANum(subCode.substring(0, 1))) {
                        printTimes += subCode.substring(0, 1);
                        subCode = subCode.substring(1);
                    }
                }
                //no numbers before, print 1 time
                if (printTimes.equals("")) {
                    printTimes = "1";
                }
                //delete "[]"
                if (subCode.indexOf("[") >= 0) {
                    subCode = subCode.substring(1, subCode.length() - 1);
                }
                //decode the sub-part
                String decoded = decodeString(subCode);
                //repeat the decoded string for the leading number times
                for (int i = 0; i < Integer.parseInt(printTimes); i++) {
                    result += decoded;
//                    System.out.println(result);
                }
            }
        }
        return result;
    }

    //test a single position of a string if it is a number or not
    private static boolean isANum(String s) {
        String digits = "0123456789";
        if (digits.indexOf(s) >= 0) {
            return true;
        } else {
            return false;
        }
    }
    //test a single position of a string if it is a alphabet or not
    private static boolean isAnAlphabet(String s) {
        String digits = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
        if (digits.indexOf(s) >= 0) {
            return true;
        } else {
            return false;
        }
    }

    //partition the given string
    private static String[] codeSplitter(String code) {
        int numOfLeft = 0;
        int numOfRight = 0;
        String previousChar = "";
        StringBuilder stringBuilder = new StringBuilder(code);
        int count = 0;
        for (int i = 0; i < code.length(); i++) {
            String character = code.substring(i, i + 1);
            if (character.equals("[")) {
                //"[" counter
                numOfLeft++;
            } else if (character.equals("]")) {
                //"]" counter
                numOfRight++;
            //test insert split point condition  
            } else if ((!isANum(previousChar) && isANum(character) 
            || previousChar.equals("]") 
            || isAnAlphabet(previousChar) && isAnAlphabet(character)) 
            //"[" and "]" appear same times 
            && numOfLeft == numOfRight) {
                //found threshold, insert "," in that position
                stringBuilder.insert(i + count, ",");
                count++;
            }
            previousChar = character;
        }
//        System.out.println(stringBuilder.toString());
        //split the code by the identifier ","
        return stringBuilder.toString().split(",");
    }
}