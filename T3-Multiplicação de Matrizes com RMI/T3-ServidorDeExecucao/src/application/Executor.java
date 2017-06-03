package application;
import java.rmi.RemoteException;
import java.rmi.server.RemoteObject;
import java.util.concurrent.*;
import configuracao.Configuracao;

/**
 * Created by Fernando && T�ssio on 5/30/17.
 */
public class Executor extends RemoteObject implements Configuracao{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private  ExecutorService executor = null;
    private final int numOfThread;

    public Executor(int newThreads){
        this.numOfThread = newThreads;
        this.executor = Executors.newFixedThreadPool(this.numOfThread);
    }

	public void execute(Runnable task) throws RemoteException {
		// TODO Auto-generated method stub
        //this.executor.execute((Runnable) task);
        System.out.println("Hello");
        this.executor.execute(task);
	}
}

