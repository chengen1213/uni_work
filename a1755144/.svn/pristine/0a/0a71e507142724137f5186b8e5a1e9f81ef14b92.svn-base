import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;

public class Test {

    static final int TWO_SEC = 2000;
    static final int SIXTEEN_SEC = 16000;
    final String rmClass = "rm *.class";
    final String javac = "javac *.java";
    final String rmFeeds = "rm feeds";
    final String rmKey1 = "rm key1.txt";
    final String rmKey2 = "rm key2.txt";

    final String command1 = "java AggregationServer";
    final String command2 = "java ContentServer ";
    final String command3 = "java GETClient ";

    public Test() {
        try {
            File file = new File("content1.xml");
            if (file.exists())
                file.delete();
            Files.copy(new File("contentNormal.xml").toPath(), file.toPath());
            Runtime.getRuntime().exec(rmClass).waitFor();
            Runtime.getRuntime().exec(javac).waitFor();
            Runtime.getRuntime().exec(rmFeeds).waitFor();
            Runtime.getRuntime().exec(rmKey1).waitFor();
            Runtime.getRuntime().exec(rmKey2).waitFor();
        } catch (Exception e) {
            System.out.println("Setting the environment...");
        }
    }

    public void testCase1() throws Exception {
        System.out.println("Start test case 1!");
        System.out.println("Testing on normal case, aggregation server, content server and the client work properly...");
        String fileName = "result1.txt";
        String expect = "expect1.txt";
        deleteFeeds();
        Process p1 = Runtime.getRuntime().exec(command1);
        Thread.sleep(TWO_SEC);
        Runtime.getRuntime().exec(command2).waitFor();
        Runtime.getRuntime().exec(command3 + fileName).waitFor();
        p1.destroy();
        if (compareFile(new File(fileName), new File(expect))) {
            System.out.println("The " + fileName + " file and " + expect + " are identical!");
            System.out.println("Test case 1 pass!");
        }
    }

    public void testCase2() throws Exception {
        System.out.println("Start test case 2!");
        System.out.println("Testing on case of multiple content servers...");
        String fileName = "result2.txt";
        String expect = "expect2.txt";
        deleteFeeds();
        Process p1 = Runtime.getRuntime().exec(command1);
        Thread.sleep(TWO_SEC);
        Runtime.getRuntime().exec(command2 + 2).waitFor();
        Runtime.getRuntime().exec(command3 + fileName).waitFor();
        p1.destroy();
        if (compareFile(new File(fileName), new File(expect))) {
            System.out.println("The " + fileName + " file and " + expect + " are identical!");
            System.out.println("Test case 2 pass!");
        }
    }

    public void testCase3() throws Exception {
        System.out.println("Start test case 3!");
        System.out.println("Testing on case of multiple clients...");
        String fileName1 = "result31.txt";
        String fileName2 = "result32.txt";
        String expect = "expect3.txt";
        deleteFeeds();
        Process p1 = Runtime.getRuntime().exec(command1);
        Thread.sleep(TWO_SEC);
        Runtime.getRuntime().exec(command2).waitFor();
        Runtime.getRuntime().exec(command3 + fileName1).waitFor();
        Runtime.getRuntime().exec(command3 + fileName2).waitFor();
        p1.destroy();
        File expectFile = new File(expect);
        if (compareFile(new File(fileName1), expectFile) && compareFile(new File(fileName2), expectFile)) {
            System.out.println("The " + fileName1 + ", " + fileName2 + " file and " + expect + " are identical!");
            System.out.println("Test case 3 pass!");
        }
    }

    public void testCase4() throws Exception {
        System.out.println("Start test case 4!");
        System.out.println("Testing on case of feeds expire...");
        String fileName = "result4.txt";
        String expect = "expect4.txt";
        deleteFeeds();
        Process p1 = Runtime.getRuntime().exec(command1);
        Thread.sleep(TWO_SEC);
        Runtime.getRuntime().exec(command2).waitFor();
        Thread.sleep(SIXTEEN_SEC);
        Runtime.getRuntime().exec(command3 + fileName).waitFor();
        p1.destroy();
        if (compareFile(new File(fileName), new File(expect))) {
            System.out.println("The " + fileName + " file and " + expect + " are identical!");
            System.out.println("Test case 4 pass!");
        }
    }

    public void testCase5() throws Exception {
        System.out.println("Start test case 5!");
        System.out.println("Testing on case of the aggregation server crashes...");
        String fileName = "result5.txt";
        String expect = "expect5.txt";
        deleteFeeds();
        Process p1 = Runtime.getRuntime().exec(command1);
        Thread.sleep(TWO_SEC);
        Runtime.getRuntime().exec(command2).waitFor();
        Runtime.getRuntime().exec(command3 + expect).waitFor();
        p1.destroy();
        Thread.sleep(TWO_SEC);
        Process p2 = Runtime.getRuntime().exec(command1);
        Thread.sleep(TWO_SEC);
        Runtime.getRuntime().exec(command3 + fileName).waitFor();
        p2.destroy();

        if (compareFile(new File(fileName), new File(expect))) {
            System.out.println("The " + fileName + " file and " + expect + " are identical!");
            System.out.println("Test case 5 pass!");
        }
    }

    public void testCase6() throws Exception {
        System.out.println("Start test case 6!");
        System.out.println("Testing on case of the content server crashes...");
        String fileName = "result6.txt";
        String expect = "expect6.txt";
        deleteFeeds();
        Process p1 = Runtime.getRuntime().exec(command1);
        Thread.sleep(TWO_SEC);
        Runtime.getRuntime().exec(command2).waitFor();
        Runtime.getRuntime().exec(command3 + expect).waitFor();
        Runtime.getRuntime().exec(command2).waitFor();
        Runtime.getRuntime().exec(command3 + fileName).waitFor();
        p1.destroy();

        if (compareFile(new File(fileName), new File(expect))) {
            System.out.println("The " + fileName + " file and " + expect + " are identical!");
            System.out.println("Test case 6 pass!");
        }
    }

    public void testCase7() throws Exception {
        System.out.println("Start test case 7!");
        System.out.println("Testing on case of the client cannot establish connection...");
        String fileName = "result7.txt";
        String expect = "expect7.txt";
        deleteFeeds();
        Process p1 = Runtime.getRuntime().exec(command1);
        Thread.sleep(TWO_SEC);
        Runtime.getRuntime().exec(command2).waitFor();
        Runtime.getRuntime().exec(command3 + expect).waitFor();
        p1.destroy();
        Runtime.getRuntime().exec(command3 + fileName);
        Process p2 = Runtime.getRuntime().exec(command1);
        Thread.sleep(TWO_SEC);
        Runtime.getRuntime().exec(command2).waitFor();
        p2.destroy();

        if (compareFile(new File(fileName), new File(expect))) {
            System.out.println("The " + fileName + " file and " + expect + " are identical!");
            System.out.println("Test case 7 pass!");
        }
    }

    public void testCase8() throws Exception {
        System.out.println("Start test case 8!");
        System.out.println("Testing on the response message of put method (201/200)...");
        deleteFeeds();
        Process p1 = Runtime.getRuntime().exec(command1);
        Thread.sleep(TWO_SEC);
        Process p2 = Runtime.getRuntime().exec(command2);
        String output1 = readOutput(p2);
        Process p3 = Runtime.getRuntime().exec(command2);
        String output2 = readOutput(p3);
        Process p4 = Runtime.getRuntime().exec(command2);
        String output3 = readOutput(p4);
        p1.destroy();
        if ("201 Created".equals(output1.trim()) && "200 OK".equals(output2.trim()) && "200 OK".equals(output3.trim())) {
            System.out.println("First PUT: " + output1.trim());
            System.out.println("Second PUT: " + output2.trim());
            System.out.println("Third PUT: " + output3.trim());
            System.out.println("Test case 8 pass!");
        }
    }

    public void testCase9() throws Exception {
        System.out.println("Start test case 9!");
        System.out.println("Testing on unsupported request method!");

        deleteFeeds();
        Process p1 = Runtime.getRuntime().exec(command1);
        Thread.sleep(TWO_SEC);
        Process p2 = Runtime.getRuntime().exec(command2 + "1 " +"p");
        String output = readOutput(p2);
        if ("400 Bad Request".equals(output.trim())) {
            System.out.println("Response: " + output.trim());
            System.out.println("Test case 9 pass!");
        }
        p1.destroy();
    }

    public void testCase10() throws Exception {
        System.out.println("Start test case 10!");
        System.out.println("Testing on bad feeds!");

        deleteFeeds();
        File file = new File("content1.xml");
        file.delete();
        Files.copy(new File("contentError.xml").toPath(), file.toPath());
        Process p1 = Runtime.getRuntime().exec(command1);
        Thread.sleep(TWO_SEC);
        Process p2 = Runtime.getRuntime().exec(command2);
        String output = readOutput(p2);
        if ("500 Internal Server Error".equals(output.trim())) {
            System.out.println("Response: " + output.trim());
            System.out.println("Test case 10 pass!");
        }
        p1.destroy();
    }

    public void testCase11() throws Exception {
        System.out.println("Start test case 11!");
        System.out.println("Testing on empty feeds!");

        deleteFeeds();
        File file = new File("content1.xml");
        file.delete();
        Files.copy(new File("contentEmpty.xml").toPath(), file.toPath());
        Process p1 = Runtime.getRuntime().exec(command1);
        Thread.sleep(TWO_SEC);
        Process p2 = Runtime.getRuntime().exec(command2);
        String output = readOutput(p2);
        if ("204 No Content".equals(output.trim())) {
            System.out.println("Response: " + output.trim());
            System.out.println("Test case 11 pass!");
        }
        p1.destroy();
    }

    //remove the saved feeds to restart the aggregation server properly
    public void deleteFeeds() {
        try {
            File file = new File("feeds");
            if (file.exists())
                file.delete();
            Thread.sleep(TWO_SEC);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    //compare two files and see if they are identical
    public boolean compareFile(File file1, File file2) throws Exception {
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
    //read the output from a process
    public String readOutput(Process p) throws Exception{
        BufferedReader stdInput = new BufferedReader(new
                InputStreamReader(p.getInputStream()));

//        BufferedReader stdError = new BufferedReader(new
//                InputStreamReader(p.getErrorStream()));
//        String err;
//        StringBuilder sb = new StringBuilder();
//        while ((err = stdError.readLine()) != null) {
//            sb.append(err);
//        }
        // Read the output from the command
        StringBuilder stringBuilder = new StringBuilder() ;
        String s;
        while ((s = stdInput.readLine()) != null) {
            stringBuilder.append(s);
        }
        return stringBuilder.toString();
    }

    public static void main(String[] args) {

        Test test = new Test();

        try {
            //normal
            test.testCase1();
            Thread.sleep(TWO_SEC);
            //multiple content server
            test.testCase2();
            Thread.sleep(TWO_SEC);
            //multiple client
            test.testCase3();
            Thread.sleep(TWO_SEC);
            //delete feed without update for 15 secs
            test.testCase4();
            Thread.sleep(TWO_SEC);
            //aggregation server dies
            test.testCase5();
            Thread.sleep(TWO_SEC);
            //content server dies
            test.testCase6();
            Thread.sleep(TWO_SEC);
            //client retries after fails
            test.testCase7();
            Thread.sleep(TWO_SEC);
            //response code 201/200
            test.testCase8();
            Thread.sleep(TWO_SEC);
            //response code 400
            test.testCase9();
            Thread.sleep(TWO_SEC);
            //response code 500
            test.testCase10();
            Thread.sleep(TWO_SEC);
            //response code 204
            test.testCase11();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
