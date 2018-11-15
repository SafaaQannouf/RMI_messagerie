package messagerie;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class ChatClient {

	public static void main(String[] argv) {
		try {
			// Le client se connecte à l’objet distant
			Registry reg = LocateRegistry.getRegistry(6666);
			final IChatRMI chat = (IChatRMI) reg.lookup("CHATRMI");

			// Version asynchrone à base de Rappel
			new Thread(new Runnable() {
				@Override
				public void run() {
					try {

						// Début version asynchrone
						Rappel r = new Rappel();
						chat.connect(r);
						// Fin version assynchrone

						int compt = 0;
						while (true) {

							// Début version asynchrone
							synchronized (r) {
								r.wait(); // on attends notifyAll
							}
							// Fin version asynchrone

							// Début version synchrone (attente active)
							// dormir 1 seconde
							// Thread.sleep(1000);
							// Fin version synchrone

							// – récupère les chaînes présentes dans le chat
							String[] str = chat.get_strings();
							for (; compt<str.length; compt++) {
								// affiche les chaines pas encore affichés
								System.out.println(str[compt]);
							}
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}).start();

			// – Le client lance un thread qui attend la rentrée d’une chaîne
			// au clavier et appelle la méthode add_string
			// pour la rajouter sur la table du serveur.
			new Thread(new Runnable() {
				@Override
				public void run() {
					BufferedReader clavier = new BufferedReader(
					        new InputStreamReader(System.in));
					while (true) {
						try {
							String chaine = clavier.readLine();
							chat.add_string(chaine);
						} catch (IOException e) {
							e.printStackTrace();
						}

					}
				}
			}).start();
		} catch (Exception e) {
			System.out.println("Erreur client : "+e);
		}
	}
}
