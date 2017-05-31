package contract;
import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Created by Fernando && Tássio on 5/30/17.
 */

/* Contrato entre Servidor de Execução e o tarefa
* Quem vai implementar é o Servidor de execução, pois ele é quem recebe a tarefa para executar*/
public interface Contract extends Remote {
    void execute(Runnable task) throws RemoteException;
}
