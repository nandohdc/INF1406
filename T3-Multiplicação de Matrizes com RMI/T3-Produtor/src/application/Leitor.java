package application;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

public class Leitor implements Runnable {

	private String pathInput;
	private LinkedBlockingQueue<double[][][]> filaMatrizes;
	private AtomicInteger delaySleep;
	
	public Leitor(String arquivoEntrada, LinkedBlockingQueue<double[][][]> fila,AtomicInteger delaySleep) {
		this.pathInput = arquivoEntrada;
		this.filaMatrizes = fila;
		this.delaySleep = delaySleep;
	}
	
	
	double[][][] inputMatrizes(String path,int size, int numMatrizes) {
		
		Scanner in = null; 
        Path pathAtual = Paths.get(System.getProperty("user.dir"));
        String pathArquivo = pathAtual.toString()+path;
        
		try {
			in = new Scanner(new File(pathArquivo));
			System.out.println("Path do arquivo lido: "+pathArquivo);
		}
		catch(IOException e) {
			e.printStackTrace();
			System.out.println("paths:"+pathArquivo);
			System.exit(1);
		}
		
		/*	Criando array de matrizes */
		double[][][] vet = new double[numMatrizes][][];
		
		/* Lendo numero por numero */
		for (int x = 0; x < numMatrizes; x++) {
			double [][]matriz = new double[size][size];
			vet[x] = matriz;
			for	(int i = 0; i < size; i++) {
				for(int j = 0; j < size; j++) {
					//NO PC DO TASSIO A MATRIZ TEM QUE SER COM VIRGULA POIS O MEU PC � UMA B*
					vet[x][i][j] = in.nextDouble();		
				}
			}
		}
		in.close();
		return vet;
	}

	@Override
	public void run() {
		
		Scanner scanner = null; 
		try {
			scanner = new Scanner(new File(pathInput));
		}
		catch(IOException e) {
			System.out.println("Erro na leitura");
			//System.out.println(e.getMessage());
			//System.exit(1);
		}
		
		while(scanner.hasNextLine()){
			
			String path = scanner.next();
			//System.out.println("path lido:" +path);
			int size = scanner.nextInt();
			//System.out.println("int lido:" + size);
			int numMatrizes = scanner.nextInt();
			//System.out.println("int lido:" + numMatrizes);

			try {
				filaMatrizes.put(this.inputMatrizes(path, size, numMatrizes));
				Thread.sleep(delaySleep.get());
			} catch (InterruptedException e1) {
				//e1.printStackTrace();
			}
		
		}
		scanner.close();		
	}
}