package application;
import java.rmi.RemoteException;
import java.util.concurrent.*;
import configuracao.Execucao;

/**
 * Created by Fernando && T�ssio on 5/30/17.
 */
public class Servidor implements Execucao {
    private final int numOfThread;
	private  ExecutorService executor = null;


    public Servidor(int newThreads){
        this.numOfThread = newThreads;
        this.executor = Executors.newFixedThreadPool(this.numOfThread);
    }


    public void execute(Runnable task) throws RemoteException {
		// TODO Auto-generated method stub
        //System.out.println("Hello");
        this.executor.submit(task);
	}

    @Override
    public void shut_down() throws RemoteException {
        this.executor.shutdown();

    }
}

