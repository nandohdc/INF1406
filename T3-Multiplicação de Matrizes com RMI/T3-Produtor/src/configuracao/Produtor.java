package configuracao;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Produtor extends Remote{
	//Matrizes
	double[][][] obtemMatrizes() throws RemoteException;
}