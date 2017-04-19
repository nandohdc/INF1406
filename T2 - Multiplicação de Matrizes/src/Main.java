/**
 * Created by Fernando Homem da Costa on 4/18/2017.
 */
public class Main {
    public static void main(String[] args) {
            String path = args[0];
            int MatrixDimension = Integer.parseInt(args[1]);
            int MaxThreads = Integer.parseInt(args[2]);
            int MaxMatrices = Integer.parseInt(args[3]);

            System.out.println(path + MatrixDimension + MaxThreads + MaxMatrices);
            Matrices matrices = new Matrices(MatrixDimension, MaxThreads, MaxMatrices);
            Reader reader = new Reader(path  + "matrix.txt" , MatrixDimension);
            reader.openFile();
            matrices.creatingMatrices(reader.readFile());
            matrices.Multiplication();
            System.out.println(matrices.getTime());
            reader.closeFile();
            Writer writer = new Writer(path + "resultado.txt");
            writer.openFile();
            writer.toWrite(matrices.getResult().getAllLines());
            writer.closeFile();
    }
}
