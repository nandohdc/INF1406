package application;

import java.io.Serializable;

/**
 * Created by nando on 6/3/2017.
 */
public class Callback implements Serializable,configuracao.Callback {
    private static final long serialVersionUID = 2L;
    private  Double[][] resultado;
    private int ResultDimension;

    public Callback(int newResultDimension){
        this.ResultDimension = newResultDimension;
    }

    public void inicializaMatriz(){
        this.resultado = new Double[this.ResultDimension][this.ResultDimension];
        for(int i = 0; i < this.ResultDimension; i++){
            for(int j = 0; j < this.ResultDimension; j++){
                this.resultado[i][j] = 0.0;
            }
        }
    }

    public void insereResultado(Result newResultado, int line, int column){
        this.resultado[newResultado.getLineResultado()][newResultado.getColumnResultado()] = newResultado.getResultado();
    }

    @Override
    public void entregaResultado(Result newResultado, int line, int column) {
        this.insereResultado(newResultado,line,column);
    }

    @Override
    public Double[][] getResultado() {
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
