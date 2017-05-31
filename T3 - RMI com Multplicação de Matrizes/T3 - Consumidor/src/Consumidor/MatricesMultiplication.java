package Consumidor;
import java.util.concurrent.Callable;

/**
 * Created by nando on 4/18/2017.
 */
public class MatricesMultiplication implements Callable<Double> {
    private double[] MatrixLine;
    private double[] MatrixColumn;

    public MatricesMultiplication(double[] Line, double[] Column){
        this.MatrixLine = Line;
        this.MatrixColumn = Column;
    }

    public void printMatrixLine(){
        for(int i = 0; i < this.MatrixLine.length; i++){
            System.out.println(MatrixLine[i]);
        }
    }

    public void printMatrixColumn(){
        for(int i = 0; i < this.MatrixColumn.length; i++){
            System.out.println(MatrixColumn[i]);
        }
    }

    @Override
    public Double call() throws Exception {
        double result = 0;

        for(int i = 0; i < this.MatrixLine.length; i++){
            result += this.MatrixLine[i]*this.MatrixColumn[i];
        }
        return result;
    }
}
