package messagerie;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

public class ChatRMI extends UnicastRemoteObject implements IChatRMI {

	private static final long serialVersionUID = 1L;
	private ArrayList<String> strings = new ArrayList<>();
	// liste des clients à rappeler
	private ArrayList<IRappel> rappels = new ArrayList<>();

	protected ChatRMI() throws RemoteException {
		super();
	}

	public void add_string(String text) throws RemoteException {
		// question 5 : résolution du problème de concurrence
		synchronized (this) {
			strings.add(text);
		}
		// question 6 : notifier les clients
		for (IRappel r : rappels) {
			r.remoteSignal();
		}
	}

	public String[] get_strings() throws RemoteException {
		return strings.toArray(new String[0]);
	}

	public void connect(IRappel r) throws RemoteException {
		rappels.add(r);
	}

}
