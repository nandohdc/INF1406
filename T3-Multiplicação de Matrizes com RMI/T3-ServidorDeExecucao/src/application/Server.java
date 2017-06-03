package application;
/**
 * Created by Fernando on 5/30/17.
 */

import configuracao.Configuracao;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;


public class Server {

    Server(int numThreads){
        try{
            System.out.println("Começando a rodar o server...");

            //Pegar o policy
            System.setProperty("java.rmi.server.hostname","10.80.70.121");
            System.setProperty("java.security.policy", "server.policy");
            //System.out.println(System.getProperty("java.class.path"));
    		//Path codeBase = Paths.get(System.getProperty("java.class.path")); -- Erro com Fernando
            Path codeBase = Paths.get("C:\\Users\\nando\\OneDrive\\Documentos\\GitHub\\INF1406\\T3-Multiplicação de Matrizes com RMI\\T3-ServidorDeExecucao\\out\\production\\T3-ServidorDeExecucao");



    		System.setProperty("java.rmi.server.codebase",codeBase.toUri().toString());
    		System.setProperty("java.rmi.server.useCodebaseOnly", "false");
            
            if (System.getSecurityManager() == null) {
                System.setSecurityManager(new SecurityManager());
            }

            //Criar o Objeto remoto
            Executor ObjExecutor = new Executor(numThreads);

            //Transformar o Objeto local em Objeto Remoto(Servants)
            Configuracao executeStub = (Configuracao) UnicastRemoteObject.exportObject(ObjExecutor,0);
            Registry registry = LocateRegistry.createRegistry(1099); //Porta
            registry.rebind("Servidor-de-Execucao", executeStub);
        
            
        }

        catch(Exception e){
            e.printStackTrace();
        }
        System.out.println("Running...");
        System.out.println("Port: " + Registry.REGISTRY_PORT);
    }


    public static void main(String[] args) {
        /*if (args.length != 1) {
            System.exit(1);
        }*/

        //int numThreads = Integer.parseInt(args[0]);
        new Server(10);
    }
}