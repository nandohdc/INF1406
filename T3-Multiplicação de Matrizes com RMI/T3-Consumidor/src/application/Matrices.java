package application;
import configuracao.Execucao;
import configuracao.Matrizes;
import configuracao.Callback;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

/**
 * Created by nando on 4/18/2017.
 */
public class Matrices implements Matrizes{
    /**
	 * 
	 */
	private static final long serialVersionUID = 4L;
	
	private int nMatrices;
    private int dimension;
    private Matrix[] matrices;
    private Matrix result;
    private int MaxThreads;
    private long time;
    private Execucao object;
    private Result resultado;
    private CallbackImp cbTask;


    public Matrices(int MatricesDimension, int NumberOfThreads, int numMatrices, Execucao newObject){
        this.nMatrices = numMatrices;
        this.dimension = MatricesDimension;
        this.MaxThreads = NumberOfThreads;
        this.matrices = null;
        this.result = null;
        this.time = 0;
        this.object = newObject;
        this.resultado = null;
        this.cbTask = null;

    }

    public void setTime(long Time){
        this.time = Time;
    }

    public long getTime(){
        return this.time;
    }

    public void creatingMatrices(List<Double> Data){
        this.matrices = new Matrix[this.nMatrices];
        for(int i = 0; i < this.nMatrices; i++){
            this.matrices[i] = new Matrix(this.dimension);
            this.matrices[i].creatingMatrix(Data, i);
        }
    }

    public Matrix getResult(){
        return this.result;
    }

    public Matrix getMatrix(int matrix){
        return this.matrices[matrix];
    }

    public void Multiplication () {
        this.setTime(System.currentTimeMillis());
        if(this.nMatrices < 2){
            System.out.println("Erro: numero de matrizes invalido!");
        }
        else if(this.nMatrices < 3){
                this.result = this.MultiplicationMatrices(this.getMatrix(0), this.getMatrix(1));
        } else{
                this.result = this.MultiplicationMatrices(this.getMatrix(0), this.getMatrix(1));
            for(int i = 2 ; i < this.nMatrices; i++) {
                this.result = this.MultiplicationMatrices(result, this.getMatrix(i));
            }
        }

        this.setTime(System.currentTimeMillis() - this.getTime());
    }

    public Matrix MultiplicationMatrices(Matrix A, Matrix B) {
        this.cbTask = new CallbackImp(this.dimension);
        try {
            Callback callbackStub = (Callback) UnicastRemoteObject.exportObject(this.cbTask, 0);
            for(int i = 0; i < this.dimension; i++){
                for(int j = 0; j < this.dimension; j++) {
                    MatricesMultiplication task = new MatricesMultiplication(A.getLine(i), B.getColumn(j), i, j, callbackStub);
                    try {
                        this.object.execute(task);
                    } catch(RemoteException e){
                        System.out.println("Consumidor: Erro ao executar o objeto remoto");
                        System.exit(1);
                    }

                }
            }
            this.cbTask.getResultado();
            this.cbTask.printMatrixResultado();
            return this.result;
        } catch (RemoteException | InterruptedException e )  {
            e.printStackTrace();
        }

        return null;
    }
}

