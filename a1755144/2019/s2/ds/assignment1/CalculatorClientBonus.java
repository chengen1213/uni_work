
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class CalculatorClientBonus {

    public static void main(String[] args) {
        //set the input and output file names
        String inputFileName = (args.length < 1) ? "input" : args[0];
        String outputFileName = (args.length < 2) ? "output" : args[1];
        String host = (args.length < 3) ? null : args[2];


        String testInput = "";
        //read actions from input file
        try {
            testInput = Utility.readFile(inputFileName);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //separate actions from each other
        String[] actions = testInput.split("\\s+");

        try {
            Registry registry = LocateRegistry.getRegistry(host);
            //find the stub according to name
            Calculator stub = ((ISession) registry.lookup("bonus")).getSession();
            //execute the right method according to each action specified in the input file
            String output = Utility.test(actions, stub);
            //write the result to target output file
            Utility.writeResult(outputFileName, output);

        } catch (Exception e) {
            System.err.println("Client exception: " + e.toString());
            e.printStackTrace();
        }
    }
}
