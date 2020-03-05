
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class Session implements ISession{
    /*when a client call this method, the session create a new RMI object and publish it,
    this allows each client to have their own stack*/
    @Override
    public Calculator getSession() throws RemoteException {
        CalculatorImplementation calculatorImplementation = new CalculatorImplementation();
        Calculator stub = (Calculator) UnicastRemoteObject.exportObject(calculatorImplementation, 10000);
        return stub;
    }
}
