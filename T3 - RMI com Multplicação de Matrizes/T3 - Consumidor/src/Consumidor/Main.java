package Consumidor;
import contract.Contract;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 * Created by Fernando Homem da Costa on 4/18/2017.
 */
public class Main {
    public static void main(String[] args) {
            Contract ObjExecutor = null;

            System.setProperty("java.security.policy", "client.policy");
            if (System.getSecurityManager() == null) {
                    System.setSecurityManager(new SecurityManager());
            }
            //System.setSecurityManager(null);

            try{
                    Registry regServer = LocateRegistry.getRegistry("10.80.70.115", 2020);
                    ObjExecutor = (Contract) regServer.lookup("Servidor de Execucao");

            }
            catch(Exception e){
                    e.printStackTrace();
            }

            String path = args[0];
            int MatrixDimension = Integer.parseInt(args[1]);
            int MaxThreads = Integer.parseInt(args[2]);
            int MaxMatrices = Integer.parseInt(args[3]);

            System.out.println(path + MatrixDimension + MaxThreads + MaxMatrices);
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
            Writer writer = new Writer(path + "resultado.txt");
            writer.openFile();
            writer.toWrite(matrices.getResult().getAllLines());
            writer.closeFile();
    }
}
