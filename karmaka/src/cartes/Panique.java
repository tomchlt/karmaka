package cartes;

import java.io.Serializable;

import jeu.Carte;
import jeu.Joueur;
import jeu.JoueurHumain;
import jeu.JoueurVirtuel;
import jeu.Partie;

public class Panique extends Carte implements Serializable {

	private static final long serialVersionUID = -8968771836437717238L;

	public Panique(Partie partie) {
		super(1, 0, 0, "Panique", "Défaussez la première carte de la Pile d'un joueur. Vous pouvez ensuite jouer une autre carte.", partie);
	}
	
	public void activerCapacite(Joueur joueur) {
		
		// Le joueur adverse défausse la première carte de sa pile
		Joueur joueurAdverse = determinerJoueurAdverse(joueur);
		if (joueurAdverse.getPile().isEmpty()==false) {
			if (joueur instanceof JoueurHumain) {
				console.afficher("Vous faites défausser la première carte de sa Pile à votre adversaire.");
			}
			joueurAdverse.defausser(joueurAdverse.getPile().getLast(), joueurAdverse.getPile());
		} else {
			if (joueur instanceof JoueurHumain) {
				console.afficher("Votre adversaire n'a aucune carte dans sa Pile...");
			}
		}
		
		// Le joueur choisit de jouer une autre carte ou non
		int choixJouer = 0;
		if (joueur.getMain().isEmpty()==false) {
			if (joueur instanceof JoueurHumain) {
				console.afficher("Cartes dans votre Main :");
				((JoueurHumain) joueur).afficherCartes(joueur.getMain());
				while (choixJouer!=1 && choixJouer!=2) {
					console.afficher("Voulez-vous jouer une autre carte ? (Entrez [1] pour OUI, [2] pour NON)");
					choixJouer = console.lireInt();
				}
			} else if (joueur instanceof JoueurVirtuel) {
				// A RAJOUTER
			}
		} else {
			if (joueur instanceof JoueurHumain) {
				console.afficher("Vous n'avez plus de cartes dans votre Main...");
			}
		}
		
		// Si le joueur a choisi de jouer une autre carte, il choisit quelle carte jouer et comment la jouer
		if (choixJouer==1) {
			int choixCarte = -1;
			int choix = 0;
			if (joueur instanceof JoueurHumain) {
				while (choixCarte<0 || choixCarte>=joueur.getMain().size()) {
					console.afficher("Quelle carte voulez-vous maintenant jouer ? (Entrez le numéro de la carte)");
					choixCarte = console.lireInt();
				}
				while (choix!=1 && choix!=2 && choix!=3) {
					console.afficher("Voulez-vous jouer cette carte pour ses points [1], son pouvoir [2], ou votre Vie Future [3]?");
					choix = console.lireInt();
				}
			} else if (joueur instanceof JoueurVirtuel) {
				// A RAJOUTER
			}
			Carte carteChoisie = joueur.getMain().get(choixCarte);
			switch (choix) {
			case 1:
				joueur.deplacerCarte(carteChoisie, joueur.getMain(), joueur.getOeuvre());
				break;
			case 2:
				joueur.deplacerCarte(carteChoisie, joueur.getMain(), joueurAdverse.getTempo());
				carteChoisie.activerCapacite(joueur);
				break;
			case 3:
				joueur.deplacerCarte(carteChoisie, joueur.getMain(), joueur.getVieFuture());
				break;
			}
		}
		
	}

}
