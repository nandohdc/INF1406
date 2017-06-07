package application;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

import configuracao.Configurador;

public class ConfiguradorImp {

    private String produtorIP;
    private String consumidorIP;

    private Integer produtorPorta;
    private Integer consumidorPorta;

    public ConfiguradorImp(String pIP, String cIP,Integer pPORT,  Integer cPORT) {

        produtorIP = pIP;
        produtorPorta = pPORT;
        consumidorIP = cIP;
        consumidorPorta = cPORT;

        try {
            System.setProperty("java.security.policy", "configurador.policy");
            if (System.getSecurityManager() == null) {
                System.setSecurityManager(new SecurityManager());
            }
        } catch (SecurityException e) {
            e.printStackTrace();
        }
    }
    public void configurarServer () throws RemoteException, NotBoundException {
        Scanner scanner = new Scanner(System.in);
        Registry registry = null;
        Configurador servicoDeEntrega = null;	//Servant do run do servidor de Execucao
        int entrada;

        while (true) {

            System.out.println("Você deve digitar as informacoes das conexões ");
            System.out.println("Escreva:\n0: Produtor \n1: Consumidor \n2: Fechar");

            if (scanner.hasNext()) {
                entrada = scanner.nextInt();
                if (entrada == 0) { //Achar o registro do Produtor
                    registry = LocateRegistry.getRegistry(produtorIP, produtorPorta);
                } else if (entrada == 1) { //Achar o registro do Consumidor
                    registry = LocateRegistry.getRegistry(consumidorIP, consumidorPorta);
                }
                else if (entrada == 2)
                    break;
                else {
                    continue;
                }
                servicoDeEntrega = (Configurador) registry.lookup("Configuracao");
                System.out.println("Entre com o valor do intervalo do Servidor de Execução");
                if (scanner.hasNext()) {
                    servicoDeEntrega.aplicaIntervalo(scanner.nextInt());
                }
            }
        }
        scanner.close();
    }
}
