package configuracao;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;

/**
 * Created by Fernando && Tássio on 5/30/17.
 */

/* Contrato entre Servidor de Execução e o tarefa
* Quem vai implementar é o Servidor de execução, pois ele é quem recebe a tarefa para executar*/
public interface Configuracao extends Remote{
        public void execute(Runnable task) throws RemoteException;
}
