package configuracao;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;


/**
 * Created by nando on 6/3/2017.
 */
public interface Configuracao extends Remote  {
    public void execute(Runnable task) throws RemoteException;
}
