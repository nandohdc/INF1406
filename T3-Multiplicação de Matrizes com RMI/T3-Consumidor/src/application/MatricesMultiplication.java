package application;
import configuracao.Tarefa;

/**
 * Created by nando on 4/18/2017.
 */
public class MatricesMultiplication implements configuracao.MatricesMultiplication, Tarefa {
    /**
	 * 
	 */
	private static final long serialVersionUID = 3L;
	private double[] MatrixLine;
    private double[] MatrixColumn;
    private Double result;
    private Callback CallbackTask;
    private int Line;
    private int Col;
    private Result resultado;

    public MatricesMultiplication(double[] Line, double[] Column, int newLine, int newCol, Callback newCbTask){
        this.MatrixLine = Line;
        this.MatrixColumn = Column;
        this.CallbackTask = newCbTask;
        this.Line = newLine;
        this.Col = newCol;
        this.result = 0.0;
        this.resultado = new Result(this.Line, this.Col);
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
        this.execute();
    }

    public void execute(){

        for(int i = 0; i < this.MatrixLine.length; i++){
            this.result += this.MatrixLine[i]*this.MatrixColumn[i];
            System.out.println(this.result + this.MatrixLine[i] + this.MatrixColumn[i]);
        }
        resultado.setResultado(result);
        this.CallbackTask.entregaResultado(resultado, resultado.getLineResultado(), resultado.getColumnResultado());
        this.CallbackTask.printMatrixResultado();
    }
}
