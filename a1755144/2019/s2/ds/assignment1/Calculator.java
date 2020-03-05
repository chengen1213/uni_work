
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Calculator extends Remote {
    void pushValue(int operand) throws RemoteException;
    void pushOperator(String operator) throws RemoteException;
    int pop() throws RemoteException;
    boolean isEmpty() throws RemoteException;
    int delayPop(int millis) throws RemoteException;
}
