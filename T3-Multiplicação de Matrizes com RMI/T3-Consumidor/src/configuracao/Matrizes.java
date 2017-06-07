package configuracao;

import application.Matrix;

import java.io.Serializable;
import java.util.List;

/**
 * Created by nando on 6/6/2017.
 */
public interface Matrizes extends Serializable{
    public void setTime(long Time);
    public void creatingMatrices(List<Double> Data);
    public Matrix getResult();
    public Matrix getMatrix(int matrix);
    public void Multiplication ();

}
