package configuracao;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Created by nando on 6/6/2017.
 */
public interface MatricesMultiplication extends Remote{
    void run() throws RemoteException;
}
