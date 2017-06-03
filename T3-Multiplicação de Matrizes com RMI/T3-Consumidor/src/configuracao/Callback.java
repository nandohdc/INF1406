package configuracao;

import application.Result;

/**
 * Created by nando on 6/3/2017.
 */
public interface Callback {

    public void entregaResultado(Result resultado);

    public Result getResultado();

}
