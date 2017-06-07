package configuracao;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Configuration extends Remote {
	void aplicaIntervalo(int intervalo) throws RemoteException;
}
