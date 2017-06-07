package application;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.util.concurrent.Semaphore;

/**
 * Created by nando on 6/3/2017.
 */
public class CallbackImp implements Serializable,configuracao.Callback {
    private static final long serialVersionUID = 2L;
    private  Double[][] resultado;
    private int ResultDimension;
    private Semaphore sema;

    public CallbackImp(int newResultDimension){
        this.ResultDimension = newResultDimension;
        this.resultado = new Double[this.ResultDimension][this.ResultDimension];
        for(int i = 0; i < this.ResultDimension; i++){
            for(int j = 0; j < this.ResultDimension; j++){
                this.resultado[i][j] = 0.0;
            }
        }
        this.sema = new Semaphore(0);
    }

    @Override
    public void entregaResultado(Result newResultado, int line, int column) throws RemoteException {
        this.resultado[newResultado.getLineResultado()][newResultado.getColumnResultado()] = newResultado.getResultado();
        System.out.println("Nao dei release" + Thread.currentThread().getName());
        this.sema.release();
    }

    @Override
    public Double[][] getResultado() throws RemoteException, InterruptedException {
        try {
            System.out.println("Nao Passei Acquire.");
            System.out.println("Result: " + this.ResultDimension);
            sema.acquire(this.ResultDimension*this.ResultDimension);
            System.out.println("Passei Acquire.");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return this.resultado;
    }

    public void printMatrixResultado(){
        for(int i = 0; i < this.ResultDimension; i++){
            for(int j = 0; j < this.ResultDimension; j++){
                System.out.println(this.resultado[i][j]);
            }
        }
    }
}
