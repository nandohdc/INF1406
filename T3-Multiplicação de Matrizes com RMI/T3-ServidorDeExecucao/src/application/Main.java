package application;
/**
 * Created by Fernando on 5/30/17.
 */

import configuracao.Configuracao;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;


public class Main {
    private static final int Port = 2000;
    private static int MaxThread = 0;
    private static String serverName = null;

    public static void main(String[] args) throws RemoteException {
        if (args.length != 1) {
            System.out.println("Erro: Falta o parametro de numero de threads");
            System.exit(1);
        }

        if (Integer.parseInt(args[0]) < 1) {
            System.out.println("Erro: numero de threads invalido!");
            System.exit(1);
        }

        MaxThread = Integer.parseInt(args[0]);

        System.out.println("Servidor de Execucao: STARTING");

        //Pegar o policy
        System.setProperty("java.rmi.server.hostname", "10.80.70.121");
        System.setProperty("java.security.policy", "server.policy");

        //Pegar CodeBase
        //System.out.println(System.getProperty("java.class.path"));
        //Path codeBase = Paths.get(System.getProperty("java.class.path")); -- Erro com Fernando
        Path codeBase = Paths.get("C:\\Users\\nando\\OneDrive\\Documentos\\GitHub\\INF1406\\T3-Multiplicação de Matrizes com RMI\\T3-ServidorDeExecucao\\out\\production\\T3-ServidorDeExecucao");
        System.setProperty("java.rmi.server.codebase", codeBase.toUri().toString());
        System.setProperty("java.rmi.server.useCodebaseOnly", "false");

        serverName = "ServidorDeExecucao";

        if (System.getSecurityManager() == null) {
            System.setSecurityManager(new SecurityManager());
        }

        //Criar o Objeto remoto
        Servidor servidor = new Servidor(MaxThread);
        Configuracao servidorStub = null;
        Registry registry = null;
        try {
            registry = LocateRegistry.createRegistry(Port); //Porta
            servidorStub = (Configuracao) UnicastRemoteObject.exportObject(servidor, 0);
            registry.rebind(serverName, servidorStub);
            //Tenta fazer o bind com servidor de execucao
        } catch (RemoteException exception) {
            System.out.println("Servidor de Execucao: Erro ao gerar objeto remoto " + exception.getMessage());
            System.exit(1);
        }
    }
}