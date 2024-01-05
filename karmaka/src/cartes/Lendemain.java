package cartes;

import java.io.Serializable;

import jeu.Carte;
import jeu.Joueur;
import jeu.JoueurHumain;
import jeu.JoueurVirtuel;
import jeu.Partie;

public class Lendemain extends Carte implements Serializable {

	private static final long serialVersionUID = 4573116733231266007L;

	public Lendemain(Partie partie) {
		super(0, 1, 0, "Lendemain", "Puisez une carte à la Source. Vous pouvez ensuite jouer une autre carte", partie);
	}

	public void activerCapactite(Joueur joueur) {
		int choix = 0;
		//on présente au joueur humain la carte qu'il pioche et on lui demande si il veut rejouer
		if (joueur instanceof JoueurHumain) {
			console.afficher("Vous piochez une carte à la source, vous pouvez ensuite jouer une autre carte");
			console.afficher("La carte que vous avez pioché est :");
			console.afficher(partie.getSource().getLast().toString());
			while (choix != 1 && choix != 2) {
				console.afficher("Voulez-vous jouer une autre carte ? (Entrez [1] pour OUI, [2] pour NON)");
				choix = console.lireInt();
			}
		}
		//le joueur virtuel voudra toujours rejouer, ce qui est plus avantageux
		if (joueur instanceof JoueurVirtuel) {
			console.afficher("Le Joueur Virtuel puise une carte à la Source et rejoue");
			choix = 1;
		}
		//on puise la carte et si le joueur veut rejouer, il jouera de nouveau
		joueur.puiser();
		if (choix == 1) {
			joueur.jouer();
		}
	}
}
