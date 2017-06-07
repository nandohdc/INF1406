package configuracao;

import java.io.Serializable;

/**
 * Created by nando on 6/3/2017.
 */
public interface Resultado extends Serializable{

    public void setResultado(Double newResultado);

    public Double getResultado();

    public int getLineResultado();

    public int getColumnResultado();

    public void printResultado();

}
