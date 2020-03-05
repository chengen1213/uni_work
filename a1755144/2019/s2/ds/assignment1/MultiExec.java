import java.io.File;
import java.io.FileInputStream;

public class MultiExec {

    public static void main(String[] args) {
        try {
            //test simple calculator for single client
            Runtime.getRuntime().exec("java CalculatorClient input00 output0").waitFor();
            //run three clients in sequence, the result shows that they are using the same stack
            Runtime.getRuntime().exec("java CalculatorClient input01 output1").waitFor();
            Runtime.getRuntime().exec("java CalculatorClient input02 output2").waitFor();
            Runtime.getRuntime().exec("java CalculatorClient input03 output3").waitFor();
            //test bonus calculator for single client
            Runtime.getRuntime().exec("java CalculatorClientBonus input00 output4").waitFor();
            //run three processes concurrently
            Runtime.getRuntime().exec("java CalculatorClientBonus input00 output5");
            Runtime.getRuntime().exec("java CalculatorClientBonus input00 output6");
            Runtime.getRuntime().exec("java CalculatorClientBonus input00 output7");

            Thread.sleep(5000);

            File expected = new File("expected");
            File expected36 = new File("expected36");
            File output0 = new File("output0");
            File output3 = new File("output3");
            File output4 = new File("output4");
            File output5 = new File("output5");
            File output6 = new File("output6");
            File output7 = new File("output7");
            if (compareFile(output0, expected)) {
                System.out.println("The output0 file is the same as expected file, simple client works correctly with a single client!");
            }
            if (compareFile(output3, expected36)) {
                System.out.println("The output3 file is the same as expected36 file, simple client works correctly with multiple clients!");
            }
            if (compareFile(output4, expected)) {
                System.out.println("The output4 file is the same as expected file, bonus client works correctly with a single client!");
            }
            if (compareFile(output5, expected)) {
                System.out.println("The output5 file is the same as expected file, bonus client works correctly with multiple clients!");
            }
            if (compareFile(output6, expected)) {
                System.out.println("The output6 file is the same as expected file, bonus client works correctly with multiple clients!");
            }
            if (compareFile(output7, expected)) {
                System.out.println("The output7 file is the same as expected file, bonus client works correctly with multiple clients!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static boolean compareFile(File file1, File file2) throws Exception {
        boolean areFilesIdentical = true;
        FileInputStream fis1 = new FileInputStream(file1);
        FileInputStream fis2 = new FileInputStream(file2);
        int i1 = fis1.read();
        int i2 = fis2.read();
        while (i1 != -1) {
            if (i1 != i2) {
                areFilesIdentical = false;
                break;
            }
            i1 = fis1.read();
            i2 = fis2.read();
        }
        fis1.close();
        fis2.close();
        return areFilesIdentical;
    }

}
