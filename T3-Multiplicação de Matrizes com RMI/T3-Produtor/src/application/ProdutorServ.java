package application;

import java.rmi.RemoteException;
import java.util.concurrent.LinkedBlockingQueue;

public class ProdutorServ implements contract.Produtor{

	/*public class LinkedBlockingQueue<E>
	extends AbstractQueue<E>
	implements BlockingQueue<E>, Serializable
	An optionally-bounded blocking queue based on linked nodes. This queue orders elements FIFO (first-in-first-out). 
	The head of the queue is that element that has been on the queue the longest time. The tail of the queue is that 
	element that has been on the queue the shortest time. New elements are inserted at the tail of the queue, and the 
	queue retrieval operations obtain elements at the head of the queue. Linked queues typically have higher throughput 
	than array-based queues but less predictable performance in most concurrent applications. */
	
	LinkedBlockingQueue<double[][][]> matrizes = new LinkedBlockingQueue<double[][][]>();
	
	@Override	
	public double[][][] obtemMatrizes() throws RemoteException {
		try {
			return matrizes.take();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return null;
	}
}
