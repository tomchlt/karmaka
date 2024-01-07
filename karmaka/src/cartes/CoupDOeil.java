package cartes;

import java.io.Serializable;

import jeu.Carte;
import jeu.Joueur;
import jeu.JoueurHumain;
import jeu.JoueurVirtuel;
import jeu.Partie;

public class CoupDOeil extends Carte implements Serializable {

	private static final long serialVersionUID = -6930221912727674082L;

	public CoupDOeil(Partie partie) {
		super(0, 0, 1, "Coup d'Oeil", "Regardez la Main d'un rival, vous pouvez ensuite jouer une autre carte.",
				partie);
	}

	public void activerCapacite(Joueur joueur) {
		Joueur joueurAdverse = determinerJoueurAdverse(joueur);
		int choixContinuer = 0;
		// Si la main du rival n'est pas vide alors on peut effectuer l'action
		if (joueurAdverse.getMain().isEmpty() == false) {
			if (joueur instanceof JoueurHumain) {
				console.afficher("Cartes dans la Main de votre adversaire :");
				// on affiche les cartes de la main de l'adversaire
				((JoueurHumain) joueur).afficherCartes(joueurAdverse.getMain());
				while (choixContinuer != 1 && choixContinuer != 2) {
					// on demande au joueur si il vaut joueur une autre carte
					console.afficher("Voulez-vous jouer une autre carte ? (Entrez [1] pour OUI, [2] pour NON)");
					choixContinuer = console.lireInt();
				}
			} else if (joueur instanceof JoueurVirtuel) {
				// on consièdre que le bot voudras touours jouer une autre carte, ce qui est
				// souvent plus avantageux
				choixContinuer = 1;
			}
		} else {
			if (joueur instanceof JoueurHumain) {
				// sinon message d'erreur
				console.afficher("Votre adversaire n'a pas de carte dans sa Main");
				// mais le joueur peut quand même jouer une autre carte
				while (choixContinuer != 1 && choixContinuer != 2) {
					console.afficher("Voulez-vous jouer une autre carte ? (Entrez [1] pour OUI, [2] pour NON)");
					choixContinuer = console.lireInt();
				}
			} else if (joueur instanceof JoueurVirtuel) {
				choixContinuer = 1;
			}
		}
		// On appelle la fonction jouer() du joueur pour jouer une autre carte
		if (choixContinuer == 1) {
			if (joueur instanceof JoueurHumain) {
				console.afficher("Vous jouez une autre Carte");
				console.afficher("Cartes dans votre Main :");
				((JoueurHumain) joueur).afficherCartes(joueur.getMain());
			}
			if (joueur instanceof JoueurVirtuel) {
				console.afficher("Le Joueur Virtuel joue une autre carte");
			}
			joueur.jouer();
		}
	}
}
