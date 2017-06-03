package application;

import configuracao.Resultado;

import java.io.Serializable;

/**
 * Created by nando on 6/3/2017.
 */
public class Callback implements Serializable,configuracao.Callback {
    private static final long serialVersionUID = 2L;

    private Result resultado;

    public Callback(Result newRsultado){
        this.resultado = newRsultado;
    }


    @Override
    public void entregaResultado(Result newResultado) {
        if(this.resultado != null) {
            this.resultado = newResultado;
        } else {
            System.out.println("Callback: Obj Resulado NULL");
        }
    }

    @Override
    public Result getResultado() {
        if(this.resultado != null) {
            return this.resultado;
        } else {
            System.out.println("Callback: Obj Resulado NULL");
            return null;
        }
    }
}
