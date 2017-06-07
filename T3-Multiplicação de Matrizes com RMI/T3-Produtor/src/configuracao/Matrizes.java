package configuracao;

import java.io.Serializable;
import java.util.ArrayList;

public interface Matrizes extends Serializable {

	public int getDimMatrices();
	
	public void putResultedMatrix(Matriz matrizResultado);
	
	public void setDimMatrices(int size);
	
	public int getNumMatrices();
	
	public ArrayList<Matriz> getMatrices();

	public void appendMatrix(Matriz matriz);
}
