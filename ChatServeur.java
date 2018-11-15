package messagerie;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class ChatServeur {
	public static void main(String[] argv) {
		try {
			Registry reg = LocateRegistry.createRegistry(6666);
			reg.rebind("CHATRMI", new ChatRMI());

			System.out.println("Le serveur est prêt");
		} catch (Exception e) {
			System.out.println("Erreur serveur : "+e);
		}
	}
}
