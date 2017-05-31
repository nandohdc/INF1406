/**
 * Created by Fernando on 5/30/17.
 */

import contract.Contract;
import tarefa.Executor;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;


public class Server {

    Server(int numThreads){
        try{
            System.out.println("Começando a rodar o server...");

            //Pegar o policy
            System.setProperty("java.rmi.server.hostname","139.82.254.68");
            System.setProperty("java.security.policy","server.policy");

            if (System.getSecurityManager() == null) {
                System.setSecurityManager(new SecurityManager());
                System.exit(0);
            }

            //Criar o Objeto remoto
            Executor ObjExecutor = new Executor(numThreads);

            //Transformar o Objeto local em Objeto Remoto(Servants)
            Contract executeStub = (Contract) UnicastRemoteObject.exportObject(ObjExecutor,0);
            Registry registry = LocateRegistry.createRegistry(2020); //Porta
            registry.rebind("Servidor de Execucao", executeStub);
        }

        catch(Exception e){
            e.printStackTrace();
        }
        System.out.println("Running...");
        System.out.println("Port: " + Registry.REGISTRY_PORT);
    }


    public static void main(String[] args) {
        if (args.length != 1) {
            System.exit(1);
        }
        System.out.println("Helloo");
        int numThreads = Integer.parseInt(args[0]);

        final Server server = new Server(10);
    }
}