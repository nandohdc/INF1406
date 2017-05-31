package Consumidor;
import java.util.List;

/**
 * Created by nando on 4/18/2017.
 */
public class Matrix {
    private int columns;
    private int lines;
    private double[][] matrix;

    public Matrix (int dimension){
        this.columns = dimension;
        this.lines = dimension;
        this.matrix = null;
    }

    public void initializingMatrix(){
        this.matrix = new double[this.lines][this.columns];
        for(int i = 0; i < this.lines; i++){
            for(int j = 0; j < this.columns; j++){
                this.setMatrix(0, i, j);
            }
        }
    }

    private void setMatrix(double a, int i, int j){

        this.matrix[i][j] = a;
    }

    public void fillInMatrix(double a, int i, int j){
        this.setMatrix(a, i, j);
    }

    public void creatingMatrix(List<Double> Data, int numberOfMatrix){
        this.initializingMatrix();
        for(int i = 0; i < this.lines; i++){
            for(int j = 0; j < this.columns; j++){
                this.setMatrix(Data.get((i*this.columns + (j + (numberOfMatrix*(this.columns*this.columns))))), i, j);
            }
        }
    }

    public double[] getLine(int Line){
        double[] local = new double[this.columns];
        for(int i = 0; i < this.columns; i++){
            local[i] = this.matrix[Line][i];
        }
        return local;
    }

    public double[] getColumn(int Column){
        double[] local = new double[this.columns];
        for(int i = 0; i < this.lines; i++){
            local[i] = this.matrix[i][Column];
        }
        return local;
    }

    public double[][] getAllLines(){
        return this.matrix;
    }

    public void printMatrix(){
        System.out.println("Printing the metrix...");
        for(int i = 0; i < this.lines; i++){
            for(int j = 0; j < this.columns; j++){
                System.out.println(this.matrix[i][j]);
            }
        }
        System.out.println("Finished ...");

    }
}
