package cartes;

import java.io.Serializable;

import jeu.Carte;
import jeu.Joueur;
import jeu.JoueurHumain;
import jeu.JoueurVirtuel;
import jeu.Partie;

public class Roulette extends Carte implements Serializable {
	
	private static final long serialVersionUID = -738058442454954400L;

	public Roulette(Partie partie) {
		super(2, 0, 0, "Roulette", "Défaussez jusqu'à 2 cartes de votre Main. Vous pouvez ensuite puiser à la Source autant de cartes + 1.", partie);
	}
	
	public void activerCapacite(Joueur joueur) {
		
		// Le joueur choisit quelle carte de sa main défausser
		
		int nbCartesAPuiser = 1;
		
		if (joueur.getMain().isEmpty()==false) {
			
			int choixCarte1 = -1;
			if (joueur instanceof JoueurHumain) {
				console.afficher("Cartes dans votre Main :");
				((JoueurHumain) joueur).afficherCartes(joueur.getMain());
				while (choixCarte1<0 || choixCarte1>=joueur.getMain().size()) {
					console.afficher("Quelle carte voulez-vous défausser ? (Entrez le numéro de la carte)");
					choixCarte1 = console.lireInt();
				}
			} else if (joueur instanceof JoueurVirtuel) {
				// A RAJOUTER
			}
			Carte carteChoisie1 = joueur.getMain().get(choixCarte1);
			joueur.defausser(carteChoisie1, joueur.getMain());
			nbCartesAPuiser++;
			
			// Le joueur choisit s'il veut défausser une deuxième carte
			
			if (joueur.getMain().isEmpty()==false) {
				
				int choixContinuer = 0;
				if (joueur instanceof JoueurHumain) {
					while (choixContinuer!=1 && choixContinuer!=2) {
						console.afficher("Voulez-vous défausser une deuxième carte ? (Entrez [1] pour OUI, [2] pour NON)");
						choixContinuer = console.lireInt();
					}
				} else if (joueur instanceof JoueurVirtuel) {
					// A RAJOUTER
				}
				
				// Si le joueur a choisi de défausser une autre carte, il choisit laquelle
				
				if (choixContinuer==1) {
					int choixCarte2 = -1;
					if (joueur instanceof JoueurHumain) {
						while (choixContinuer!=1 && choixContinuer!=2) {
							console.afficher("Quelle carte voulez-vous défausser ? (Entrez le numéro de la carte)");
							choixCarte2 = console.lireInt();
						}
					} else if (joueur instanceof JoueurVirtuel) {
						// A RAJOUTER
					}
					Carte carteChoisie2 = joueur.getMain().get(choixCarte2);
					joueur.defausser(carteChoisie2, joueur.getMain());
					nbCartesAPuiser++;
				}
				
			} else {
				if (joueur instanceof JoueurHumain) {
					console.afficher("Vous avez défaussé 1 carte de votre Main, mais vous n'avez plus de cartes dans votre Main...");
				}
			}
			
		} else {
			if (joueur instanceof JoueurHumain) {
				console.afficher("Vous n'avez aucune carte dans votre Main...");
			}
		}
		
		// Le joueur puise à la source autant de cartes qu'il en a défaussé + 1
		
		if (joueur instanceof JoueurHumain) {
			console.afficher("Vous puisez " + nbCartesAPuiser + " cartes à la Source.");
		}
		for (int i=0;i<nbCartesAPuiser;i++) {
			joueur.puiser();
		}
		
	}

}
