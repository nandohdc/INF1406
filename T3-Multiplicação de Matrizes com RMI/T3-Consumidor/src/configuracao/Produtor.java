package configuracao;

import application.Matrices;
import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Created by nando on 6/7/2017.
 */
public interface Produtor extends Remote {

    public Matrices obtemMatrices() throws RemoteException;

}
