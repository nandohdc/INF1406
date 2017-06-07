package application;

import java.rmi.RemoteException;
import java.util.concurrent.atomic.AtomicInteger;
import configuracao.Configuration;

public class ConfiguradorServ implements Configuration {
//Servant de Configuracao
	AtomicInteger delaySleep = new AtomicInteger(60000);
	
	@Override	
	public void aplicaIntervalo(int intervalo) throws RemoteException {
		// TODO Auto-generated method stub
		delaySleep.set(intervalo);	
	}
	protected AtomicInteger getIntervalo(){
		return this.delaySleep;
	}
}
