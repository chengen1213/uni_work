
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ISession extends Remote {

    Calculator getSession() throws RemoteException;
}
