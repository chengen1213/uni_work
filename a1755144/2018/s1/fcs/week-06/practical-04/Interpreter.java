public class Interpreter {

    public final static String[] inforMap = {"Rock", "Paper", "Scissors"};

    //translate move from integer to string
    public static String intToStr(int move) {

        String output = "error";
        if (move >= 0 && move <= 2) {
            output = inforMap[move];
        }
        return output;

    }

    //translate move from string to integer
    public static int strToInt(String move) {
        int output = -1;

        for (int i = 0; i < inforMap.length; i++) {
            if (move.equals(inforMap[i])) {
                output = i;
                break;
            }
        }

        return output;
    }

    //check input and translate to regular expression 
    public static String check(String move) {

        String output;
        String lowercaseMove = move.toLowerCase();

        if ("rocks".startsWith(lowercaseMove)) {
            output = inforMap[0];
        } else if ("papers".startsWith(lowercaseMove)) {
            output = inforMap[1];
        } else if ("scissors".startsWith(lowercaseMove)) {
            output = inforMap[2];
        } else {
            output = "error";
        }
        return output;
    }
}
