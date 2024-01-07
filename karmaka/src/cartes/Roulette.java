package cartes;

import java.io.Serializable;
import java.util.Random;

import jeu.Carte;
import jeu.Joueur;
import jeu.JoueurHumain;
import jeu.JoueurVirtuel;
import jeu.Partie;

public class Roulette extends Carte implements Serializable {

	private static final long serialVersionUID = -738058442454954400L;

	public Roulette(Partie partie) {
		super(2, 0, 0, "Roulette",
				"Défaussez jusqu'à 2 cartes de votre Main. Vous pouvez ensuite puiser à la Source autant de cartes + 1.",
				partie);
	}

	public void activerCapacite(Joueur joueur) {

		// Le joueur choisit quelle carte de sa main défausser

		int nbCartesAPuiser = 1;
		int choixJouerCarte1 = -1;
		Random random = new Random();
		if (joueur.getMain().isEmpty() == false) {
			int choixCarte1 = -1;
			Carte carteChoisie1 = null;
			if (joueur instanceof JoueurHumain) {
				console.afficher("Cartes dans votre Main :");
				((JoueurHumain) joueur).afficherCartes(joueur.getMain());
				while (choixCarte1 < 0 || choixCarte1 >= joueur.getMain().size()) {
					console.afficher("Quelle carte voulez-vous défausser ? (Entrez le numéro de la carte)");
					choixCarte1 = console.lireInt();
				}
				carteChoisie1 = joueur.getMain().get(choixCarte1);
			} else if (joueur instanceof JoueurVirtuel) {
				choixJouerCarte1 = random.nextInt(2);
				if (choixJouerCarte1 == 1) {
					console.afficher(
							"Le Joueur Virtuel défausse une première carte de sa Main et peut en défausser une autre");
					carteChoisie1 = ((JoueurVirtuel) joueur).getStrategie()
							.choisirCarteDéfausser((JoueurVirtuel) joueur, joueur.getMain());
				}
			}
			joueur.defausser(carteChoisie1, joueur.getMain());
			nbCartesAPuiser++;

			// Le joueur choisit s'il veut défausser une deuxième carte

			if (joueur.getMain().isEmpty() == false) {
				Carte carteChoisie2 = null;
				int choixContinuer = 0;
				if (joueur instanceof JoueurHumain) {
					while (choixContinuer != 1 && choixContinuer != 2) {
						console.afficher(
								"Voulez-vous défausser une deuxième carte ? (Entrez [1] pour OUI, [2] pour NON)");
						choixContinuer = console.lireInt();
					}
					if (joueur instanceof JoueurVirtuel) {
						choixContinuer = 1;
					}
				}

				// Si le joueur a choisi de défausser une autre carte, il choisit laquelle

				if (choixContinuer == 1) {
					int choixCarte2 = -1;
					if (joueur instanceof JoueurHumain) {
						while (choixCarte2 != 1 && choixCarte2 != 2) {
							console.afficher("Quelle carte voulez-vous défausser ? (Entrez le numéro de la carte)");
							choixCarte2 = console.lireInt();
						}
						carteChoisie2 = joueur.getMain().get(choixCarte2);
					} else if (joueur instanceof JoueurVirtuel) {
						if (choixJouerCarte1 == 1) {
							int choixJouerCarte2 = random.nextInt(2);
							if (choixJouerCarte2 == 1) {
								console.afficher("Le Joueur Virtuel défausse une seconde carte");
								carteChoisie2 = ((JoueurVirtuel) joueur).getStrategie()
										.choisirCarteDéfausser((JoueurVirtuel) joueur, joueur.getMain());
							} else {
								console.afficher("Le Joueur Virtuel ne défausse pas de seconde carte");
							}
						}
					}
					joueur.defausser(carteChoisie2, joueur.getMain());
					nbCartesAPuiser++;
				}

			} else {
				if (joueur instanceof JoueurHumain) {
					console.afficher(
							"Vous avez défaussé 1 carte de votre Main, mais vous n'avez plus de cartes dans votre Main...");
				}
				if (joueur instanceof JoueurVirtuel) {
					console.afficher("Le Joueur Virtuel défausse 1 carte de sa Main, mais il n'en as plus d'autres");
				}
			}

		} else {
			if (joueur instanceof JoueurHumain) {
				console.afficher("Vous n'avez aucune carte dans votre Main...");
			}
			if (joueur instanceof JoueurVirtuel) {
				console.afficher("Le Joueur Virtuel n'a pas de carte dans sa Main à défausser");
			}
		}

		// Le joueur puise à la source autant de cartes qu'il en a défaussé + 1

		if (joueur instanceof JoueurHumain) {
			console.afficher("Vous puisez " + nbCartesAPuiser + " cartes à la Source.");
		}
		if (joueur instanceof JoueurVirtuel) {
			console.afficher("Le Joueur Virtuel adverse puise " + nbCartesAPuiser + " cartes à la Source.");
		}
		for (int i = 0; i < nbCartesAPuiser; i++) {
			joueur.puiser();
		}

	}

}
