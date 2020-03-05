import java.io.*;
//utility functions for both simple client and bonus client
public class Utility {
    //read the content of the file
    public static String readFile(String fileName) throws Exception {
        StringBuilder sb = new StringBuilder();
        BufferedReader br = new BufferedReader(new FileReader(fileName));
        String line = br.readLine();

        while (line != null) {
            sb.append(line);
            sb.append(System.lineSeparator());
            line = br.readLine();
        }
        br.close();
        return sb.toString();
    }
    //according to different action, call different method
    public static String test(String[] actions, Calculator stub) throws Exception {
        StringBuilder br = new StringBuilder();
        for (int i = 0; i < actions.length; i++) {
            switch (actions[i]) {
                case "p":
                    br.append(stub.pop()).append('\n');
                    break;
//                case "dp":
//                    br.append(stub.delayPop(10)).append('\n');
//                    break;
                case "+":
                case "-":
                case "*":
                case "/":
                    stub.pushOperator(actions[i]);
                    break;
                default:
                    int num = 0;
                    try {
                        num = Integer.parseInt(actions[i]);
                        stub.pushValue(num);
                    } catch (NumberFormatException e) {
                        num = Integer.parseInt(actions[i].substring(1));
                        br.append(stub.delayPop(num)).append('\n');
                    }
                    break;
            }
        }
        return br.toString();
    }
    //write the result to the file
    public static void writeResult(String fileName, String output)
            throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));
        writer.write(output);
        writer.close();
    }
}
