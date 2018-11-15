package messagerie;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IRappel extends Remote {
	void remoteSignal() throws RemoteException;
}
