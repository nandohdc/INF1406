package configuracao;

import application.Result;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Created by nando on 6/3/2017.
 */
public interface Callback extends Remote {

    public void entregaResultado(Result resultado, int line, int column) throws RemoteException;

    public Double[][] getResultado() throws RemoteException, InterruptedException;

}
