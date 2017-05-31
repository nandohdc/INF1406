package tarefa;

import contract.Contract;

import java.rmi.RemoteException;
import java.rmi.server.RemoteObject;
import java.util.concurrent.*;

/**
 * Created by Fernando && Tássio on 5/30/17.
 */
public class Executor extends RemoteObject implements Contract{
    private  ExecutorService executor = null;
    private final int numOfThread;

    public Executor(int newThreads){
        this.numOfThread = newThreads;
        this.executor = Executors.newFixedThreadPool(this.numOfThread);
    }

    @Override
    public void execute(Runnable task) throws RemoteException {
        this.executor.execute(task);
    }
}
