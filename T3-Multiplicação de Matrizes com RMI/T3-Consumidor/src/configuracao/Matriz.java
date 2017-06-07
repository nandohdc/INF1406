package configuracao;

import java.io.Serializable;
import java.util.List;

/**
 * Created by nando on 6/6/2017.
 */
public interface Matriz extends Serializable{
    public void initializingMatrix();
    public void fillInMatrix(double a, int i, int j);
    public void creatingMatrix(List<Double> Data, int numberOfMatrix);
    public double[] getLine(int Line);
    public double[] getColumn(int Column);
    public double[][] getAllLines();
    public void printMatrix();

}
