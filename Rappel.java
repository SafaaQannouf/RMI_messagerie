package messagerie;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class Rappel extends UnicastRemoteObject implements IRappel {

	public Rappel() throws RemoteException {
	}

	public void remoteSignal() throws RemoteException {
		synchronized (this) {
			this.notifyAll();
		}
	}

}
