package configuracao;

import java.io.Serializable;
import java.rmi.RemoteException;

/**
 * Created by nando on 6/6/2017.
 */
public interface Tarefa extends Runnable, Serializable {
    public void execute() throws RemoteException;
}
