
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

import static java.rmi.registry.LocateRegistry.createRegistry;

public class CalculatorServer {

    public static void main(String[] args) {
        try {
            //simple calculator, all clients use the same stack
            CalculatorImplementation calculatorImplementation = new CalculatorImplementation();
            Calculator stub1 = (Calculator) UnicastRemoteObject.exportObject(calculatorImplementation, 9998);
            //session is an agent which create a RMI object and publish it to each client
            Session session = new Session();
            ISession stub2 = (ISession) UnicastRemoteObject.exportObject(session, 9999);

            // Bind the remote object's stub in the registry
            Registry registry = LocateRegistry.getRegistry();
//            Registry registry = LocateRegistry.createRegistry(1888);
            registry.bind("calculator", stub1);
            registry.bind("bonus", stub2);


            System.err.println("Server ready");
        } catch (Exception e) {
            System.err.println("Server exception: " + e.toString());
            e.printStackTrace();
        }
    }

}
