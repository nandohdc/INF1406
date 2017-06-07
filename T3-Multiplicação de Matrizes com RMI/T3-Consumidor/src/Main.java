import application.*;
import configuracao.Execucao;
import configuracao.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 * Created by Fernando Homem da Costa on 4/18/2017.
 */
public class Main {
    private static final int Port = 2000;
    private static String consumidorName = null;
    private static String produtorName = null;
    private static String execucaoName = null;

    public static void main(String[] args) {
        if (args.length != 4) {
            System.out.println("Erro: Falta o parametro de numero de threads");
            System.exit(1);
        }

        System.out.println("Consumidor: STARTING");
        //Pegar o policy
        System.setProperty("java.rmi.server.hostname", "127.0.0.1");
        System.setProperty("java.security.policy", "consumidor.policy");

        //Path codeBase = Paths.get(System.getProperty("java.class.path"));
        Path codeBase = Paths.get("C:\\Users\\nando\\Documents\\GitHub\\INF1406\\T3-Multiplicação de Matrizes com RMI\\T3-Consumidor\\out\\production\\T3-Consumidor");
        System.setProperty("java.rmi.server.codebase",codeBase.toUri().toString());
        System.setProperty("java.rmi.server.useCodebaseOnly", "false");

        consumidorName = "Consumidor";
        produtorName = "Produtor";
        execucaoName = "ServerDeExecucao";

        if (System.getSecurityManager() == null) {
            System.setSecurityManager(new SecurityManager());
        }


        String path = args[0];
        int MatrixDimension = Integer.parseInt(args[1]);
        int MaxThreads = Integer.parseInt(args[2]);
        int MaxMatrices = Integer.parseInt(args[3]);
        /*String hostProdutor = args[0];
        int portProdutor = Integer.parseInt(args[1]);
        String hostExecucao = args[2];
        int portExecucao = Integer.parseInt(args[3]);*/

        String hostProdutor = "127.0.0.1";
        int portProdutor = 2000;
        String hostExecucao = "127.0.0.1";
        int portExecucao = 2000;

        System.out.println(path + MatrixDimension + MaxThreads + MaxMatrices);

        Registry regServer = null;
        Registry regProdutor = null;
        Execucao ObjExecutor = null;

        try{
            regProdutor = LocateRegistry.getRegistry(hostProdutor, portProdutor);
            Produtor produtorStub = (Produtor) regProdutor.lookup(produtorName);

            regServer = LocateRegistry.getRegistry(hostExecucao, portExecucao);
            Execucao execucaoStub = (Execucao) regServer.lookup(execucaoName);

            ObjExecutor = (Execucao)regServer.lookup("Execucao");
            Matrices matrices = new Matrices(MatrixDimension, MaxThreads, MaxMatrices, ObjExecutor);
            Reader reader = new Reader(path  + "matrix.txt", MatrixDimension);
            reader.openFile();
            matrices.creatingMatrices(reader.readFile());
            matrices.Multiplication();
            System.out.println(matrices.getTime());
            reader.closeFile();
            // Writer writer = new Writer(path + "resultado.txt");
            // writer.openFile();
            // writer.toWrite(matrices.getResult().getAllLines());
            //writer.closeFile();

        } catch (RemoteException exception){
            System.out.println("Consumidor: Erro ao gerar objeto remoto " + exception.getMessage());
            System.exit(1);
        } catch (NotBoundException e) {
            System.out.println("Consumidor: Erro ao gerar objeto remoto " + e.getMessage());
            System.exit(1);
        }
    }
}
