package Consumidor;
import contract.Contract;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * Created by nando on 4/18/2017.
 */
public class Matrices {
    private int nMatrices;
    private int dimension;
    private Matrix[] matrices;
    private Matrix result;
    private int MaxThreads;
    private long time;
    private Contract object;

    public Matrices(int MatricesDimension, int NumberOfThreads, int numMatrices, Contract newObject){
        this.nMatrices = numMatrices;
        this.dimension = MatricesDimension;
        this.MaxThreads = NumberOfThreads;
        this.matrices = null;
        this.result = null;
        this.time = 0;
        this.object = newObject;
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

    public void Multiplication () throws RemoteException {
        this.setTime(System.currentTimeMillis());
        if(this.nMatrices < 2){
            System.out.println("Erro: numero de matrizes invalido!");
        }
        else if(this.nMatrices < 3){
            this.result =  this.MultiplicationMatrices(this.getMatrix(0), this.getMatrix(1));
        } else{
            this.result =  this.MultiplicationMatrices(this.getMatrix(0), this.getMatrix(1));
            for(int i = 2 ; i < this.nMatrices; i++) {
                this.result = this.MultiplicationMatrices(result, this.getMatrix(i));
            }
        }

        this.setTime(System.currentTimeMillis() - this.getTime());
    }

    private Matrix MultiplicationMatrices(Matrix A, Matrix B) throws RemoteException {
        ExecutorService executor = Executors.newFixedThreadPool(this.MaxThreads);
        List<Future<Double>> list = new ArrayList<Future<Double>>();

        for(int i = 0; i < this.dimension; i++){
            for(int j = 0; j < this.dimension; j++) {
                Callable<Double> task = new MatricesMultiplication(A.getLine(i), B.getColumn(j));
                this.object.execute(task);
                //Future<Double> submit = executor.submit(task);
                //list.add(submit);
            }
        }

        this.result = new Matrix(this.dimension);
        this.result.initializingMatrix();

        for(int i = 0; i < this.dimension; i++){
            for(int j = 0; j < this.dimension; j++) {
                try {
                    this.result.fillInMatrix(list.get(i*this.dimension + j).get(),i ,j);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
            }
        }
        this.result.printMatrix();
        executor.shutdown();
        return this.result;
    }
}

