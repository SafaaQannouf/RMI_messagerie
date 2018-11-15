package messagerie;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IChatRMI extends Remote {
	void add_string(String text) throws RemoteException;

	String[] get_strings() throws RemoteException;

	void connect(IRappel r) throws RemoteException;
}
