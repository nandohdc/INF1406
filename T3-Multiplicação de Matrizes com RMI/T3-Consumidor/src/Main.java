import application.*;
import configuracao.Configuracao;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 * Created by Fernando Homem da Costa on 4/18/2017.
 */
public class Main {
    public static void main(String[] args) {

            System.setProperty("java.security.policy", "consumidor.policy");
    		//Path codeBase = Paths.get(System.getProperty("java.class.path"));
    		Path codeBase = Paths.get("C:\\Users\\nando\\OneDrive\\Documentos\\GitHub\\INF1406\\T3-Multiplicação de Matrizes com RMI\\T3-Consumidor\\out\\production\\T3-Consumidor");
    		System.setProperty("java.rmi.server.codebase",codeBase.toUri().toString());
    		System.setProperty("java.rmi.server.useCodebaseOnly", "false");
    		if (System.getSecurityManager() == null) {
                    System.setSecurityManager(new SecurityManager());
            }
            //System.setSecurityManager(null);

            String path = args[0];
            int MatrixDimension = Integer.parseInt(args[1]);
            int MaxThreads = Integer.parseInt(args[2]);
            int MaxMatrices = Integer.parseInt(args[3]);

            System.out.println(path + MatrixDimension + MaxThreads + MaxMatrices);

            try{
            	Registry regServer = LocateRegistry.getRegistry("10.80.70.121",1099);
                Configuracao ObjExecutor = (Configuracao)regServer.lookup("Servidor-de-Execucao");

                Matrices matrices = new Matrices(MatrixDimension, MaxThreads, MaxMatrices, ObjExecutor);
                Reader reader = new Reader(path  + "matrix.txt" , MatrixDimension);
                reader.openFile();
                matrices.creatingMatrices(reader.readFile());
                try {
                    matrices.Multiplication();
                } catch (RemoteException e) {
                        e.printStackTrace();
                }
                System.out.println(matrices.getTime());
                reader.closeFile();
               // Writer writer = new Writer(path + "resultado.txt");
               // writer.openFile();
               // writer.toWrite(matrices.getResult().getAllLines());
                //writer.closeFile();
            }
            catch(Exception e){
                e.printStackTrace();
            }
    }
}
