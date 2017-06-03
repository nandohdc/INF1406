package application;
import java.io.Serializable;
import java.util.concurrent.Callable;

/**
 * Created by nando on 4/18/2017.
 */
public class MatricesMultiplication implements Serializable,Runnable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 3L;
	private double[] MatrixLine;
    private double[] MatrixColumn;
    private Double result;
    private Callback CallbackTask;

    public MatricesMultiplication(double[] Line, double[] Column, Callback newCbTask){
        this.MatrixLine = Line;
        this.MatrixColumn = Column;
        this.CallbackTask = newCbTask;
        this.result = null;
        System.out.println("Hello");
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
    public void run() {
        System.out.println("Hello");
        this.execute();
        Result resultado = this.CallbackTask.getResultado();
        resultado.setResultado(result);
        this.CallbackTask.entregaResultado(resultado);
    }

    public void execute(){
        for(int i = 0; i < this.MatrixLine.length; i++){
            this.result += this.MatrixLine[i]*this.MatrixColumn[i];
            System.out.println(this.result + this.MatrixLine[i] + this.MatrixColumn[i]);
        }

    }
}
