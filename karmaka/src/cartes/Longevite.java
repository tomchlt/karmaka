package cartes;

import java.io.Serializable;

import jeu.Carte;
import jeu.Joueur;
import jeu.JoueurHumain;
import jeu.JoueurVirtuel;
import jeu.Partie;

public class Longevite extends Carte implements Serializable {
	
	private static final long serialVersionUID = -2023161043716899180L;

	public Longevite(Partie partie) {
		super(0, 2, 0, "Longévité", "Placez 2 cartes puisées à la Source sur la Pile d'un joueur", partie);
	}

	public void activerCapactite(Joueur joueur) {
		Joueur joueurAdverse = determinerJoueurAdverse(joueur);
		int joueurSelectionne = 0;
		//on demande au joueur humain à qui il veut donner les cartes
		if (joueur instanceof JoueurHumain) {
			while (joueurSelectionne!=1 && joueurSelectionne!=2) {
				console.afficher("A quel joueur voulez vous donner les 2 cartes puisées ? (Entrez [1] pour VOUS, [2] pour le JOUEUR ADVERSE)");
				joueurSelectionne = console.lireInt();
			}
		}
		if (joueur instanceof JoueurVirtuel) {
			//on considère que le joueur virtuel prendra toujours les cartes pour lui
			joueurSelectionne = 1;
		}
		if (joueurSelectionne==1) {
			joueur.puiser();
			joueur.puiser();
			if (joueur instanceof JoueurHumain) {
				console.afficher("Vous puisez 2 cartes que vous mettez dans votre Pile");
			}
			if (joueur instanceof JoueurVirtuel) {
				console.afficher("Le Joueur Virtuel récupère les 2 Cartes puisées à la Source pour sa Pile");
			}
		}
		if (joueurSelectionne==2) {
			joueurAdverse.puiser();
			joueurAdverse.puiser();
			if (joueur instanceof JoueurHumain) {
				console.afficher("Votre adversaire puise 2 cartes qu'il met dans sa Pile");
			}
			if (joueur instanceof JoueurVirtuel) {
				console.afficher("Le Joueur Virtuel vous fait puiser 2 cartes à la Source pour votre Pile");
			}
		}
	}
}
