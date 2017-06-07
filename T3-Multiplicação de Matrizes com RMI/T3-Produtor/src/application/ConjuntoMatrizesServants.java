package application;

import java.util.ArrayList;

import configuracao.Matriz;
import configuracao.Matrizes;

public class ConjuntoMatrizesServants implements Matrizes{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	int dimMatrices;
	ArrayList<Matriz> matrices;

	public ConjuntoMatrizesServants(ArrayList<Matriz> matrices) {
		this.matrices = matrices;
	}

	public int getDimMatrices() {
		return this.dimMatrices;
	}
	
	public void setDimMatrices(int dimMatrices) {
		this.dimMatrices = dimMatrices;
	}
	
	public int getNumMatrices() {
		return this.matrices.size();
	}

	public ArrayList<Matriz> getMatrices() {
		return matrices;
	}
	
	public Matriz getMatrix(int i){
		return matrices.get(i);
	}

	public void appendMatrix(Matriz matrix){
		matrices.add(matrix);
	}
	
	public void putResultedMatrix(Matriz resultedMatrix){
		matrices.set(0, resultedMatrix);
		matrices.remove(1);
	}
}
