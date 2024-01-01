package cartes;

import java.io.Serializable;

import karmaka.Carte;
import karmaka.Joueur;
import karmaka.JoueurHumain;
import karmaka.JoueurVirtuel;
import karmaka.Partie;

public class Jubile extends Carte implements Serializable {

	private static final long serialVersionUID = 6158669912375296624L;

	public Jubile(Partie partie) {
		super(0, 3, 0, "Jubilé", "Placez 2 cartes de votre Main sur vos Oeuvres.", partie);
	}
	
	public void activerCapacite(Joueur joueur) {
		
		// Le joueur choisit quelle carte placer sur ses Oeuvres
		int choixCarte1 = -1;
		if (joueur instanceof JoueurHumain) {
			while (choixCarte1<0 || choixCarte1>=joueur.getMain().size()) {
				console.afficher("Quelle carte voulez-vous placer sur vos Oeuvres ? (Entrez le numéro de la carte)");
				choixCarte1 = console.lireInt();
			}
		} else if (joueur instanceof JoueurVirtuel) {
			// A RAJOUTER
		}
		Carte carteChoisie1 = joueur.getMain().get(choixCarte1);
		joueur.deplacerCarte(carteChoisie1, joueur.getMain(), joueur.getOeuvre());
		
		// Le joueur choisit s'il veut placer une deuxième carte sur ses Oeuvres
		int choixContinuer = 0;
		if (joueur instanceof JoueurHumain) {
			while (choixContinuer!=1 && choixContinuer!=2) {
				console.afficher("Voulez-vous placer une deuxième carte sur vos Oeuvres ? (Entrez [1] pour OUI, [2] pour NON)");
				choixContinuer = console.lireInt();
			}
		} else if (joueur instanceof JoueurVirtuel) {
			// A RAJOUTER
		}
		
		// Si le joueur a choisi de placer une autre carte, il choisit quelle carte poser sur ses Oeuvres
		if (choixContinuer==1) {
			int choixCarte2 = -1;
			if (joueur instanceof JoueurHumain) {
				while (choixContinuer!=1 && choixContinuer!=2) {
					console.afficher("Quelle carte voulez-vous placer sur vos Oeuvres ? (Entrez le numéro de la carte)");
					choixCarte2 = console.lireInt();
				}
			} else if (joueur instanceof JoueurVirtuel) {
				// A RAJOUTER
			}
			Carte carteChoisie2 = joueur.getMain().get(choixCarte2);
			joueur.deplacerCarte(carteChoisie2, joueur.getMain(), joueur.getOeuvre());
		}
		
	}

}
