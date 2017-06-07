package application;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.concurrent.LinkedBlockingQueue;

import contract.Configuration;
import contract.Produtor;

public class Gerador {
	ConfiguradorServ cServant;
	ProdutorServ pServant;
	private Registry registryProxy;
	private Produtor remoteProdutor;
	private Configuration remoteConfiguracao;
	
	void produzir(String path) {
		//Servant do Produtor
		pServant = new ProdutorServ();
		//Servant do Configurador
		cServant = new ConfiguradorServ();
		//Matriz que vai ser passada para o consumidor
		//podia ser volatil
		LinkedBlockingQueue<double[][][]> matrizes  = pServant.matrizes;
		//Gerar o run para ficar lendo as matrizes 
		(new Thread(new Leitor(path,matrizes,cServant.delaySleep))).start();	//Sujo
		
		this.publicarObjRemoto();
	}
	
	private void publicarObjRemoto(){
		
		int porta = 5502;
		
		try{ //Criar os objetos remotos
			remoteProdutor = (Produtor) UnicastRemoteObject.exportObject(pServant, 0);
			remoteConfiguracao = (Configuration) UnicastRemoteObject.exportObject(cServant, 0);
		}
		catch (RemoteException e){
			System.out.println("Erro exportar obj remoto");
			e.printStackTrace();
		}
		
		try{
			this.registryProxy = LocateRegistry.createRegistry(porta);
		}
		catch (RemoteException e){
			System.out.println("Falha gerar registro");
			e.printStackTrace();
		}
		try{
			registryProxy.rebind("Produtor",remoteProdutor);
			registryProxy.rebind("Configuracao",remoteConfiguracao);
			
			/*registry.rebind(serverName, servidorStub);*/
		}
		catch(RemoteException e){
			System.out.println("Conex�o de registro falhou");
			e.printStackTrace();
		}
	}	
		
	public static void main(String[] args) {

		System.out.println("Comecando a rodar o Produtor");

		//Pegar o path do arquivo txt
		if (args.length == 0) {
			System.out.println("Argumentos Insuficientes");
			System.exit(1);
		}
			
		String pathInput = args[0];
		
		//Setar as policy's
		System.setProperty("java.security.policy", "produtor.policy");
		System.setProperty("java.rmi.server.useCodebaseOnly", "false");
		if (System.getSecurityManager() == null) {
			System.setSecurityManager(new SecurityManager());
		}

		Gerador produtor = new Gerador();
		produtor.produzir(pathInput);

	}
}
