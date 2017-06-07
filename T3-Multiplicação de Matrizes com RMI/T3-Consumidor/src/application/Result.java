package application;

import configuracao.Resultado;

import java.io.Serializable;

/**
 * Created by nando on 6/3/2017.
 */
public class Result implements Resultado{
    private Double result;
    private int line;
    private int column;

    public Result(int newLine, int newColumn){
        this.result = null;
        this.line = newLine;
        this.column = newColumn;

    }

    @Override
    public void setResultado(Double newResult) {
            this.result = newResult;
    }

    @Override
    public Double getResultado() {
        return this.result;
    }

    @Override
    public int getLineResultado() {
        return this.line;
    }

    @Override
    public int getColumnResultado() {
        return this.column;
    }

    @Override
    public void printResultado() {
        if(this.result != null)
            System.out.println("Line: " + this.line + " Column: " + this.column + "Result:" + this.result);
        else
            System.out.println("Resultado: NULL!");
    }
}
