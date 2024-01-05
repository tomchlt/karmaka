package cartes;

import java.io.Serializable;

import jeu.Carte;
import jeu.Joueur;
import jeu.JoueurHumain;
import jeu.JoueurVirtuel;
import jeu.Partie;

public class Semis extends Carte implements Serializable {

	private static final long serialVersionUID = -8510928523076852307L;

	public Semis(Partie partie) {
		super(0, 2, 0, "Semis", "Puissez 2 cartes à la Source puis placez sur votre VieFuture 2 cartes de votre Main.",
				partie);
	}

	public void activerCapactite(Joueur joueur) {
		joueur.puiser();
		joueur.puiser();
		//on commence par puiser les Cartes
		int choixCarte1 = -1;
		Carte carteChoisie1 = null;
		int choixCarte2 = -1;
		Carte carteChoisie2 = null;
		if (joueur instanceof JoueurHumain) {
			//on lui présente les cartes de sa main et on déplace celle qu'il veut mettre dans sa vie Future une première fois
			console.afficher("Cartes dans votre Main :");
			((JoueurHumain) joueur).afficherCartes(joueur.getMain());
			while (choixCarte1 < 0 || choixCarte1 >= joueur.getMain().size()) {
				console.afficher(
						"Vous devez en placer 2 dans votre Vie Future, (Entrez le numéro de la première carte)");
				choixCarte1 = console.lireInt();
			}
			carteChoisie1 = joueur.getMain().get(choixCarte1);
			joueur.deplacerCarte(carteChoisie1, joueur.getMain(), joueur.getVieFuture());
			//puis une deuxième fois
			console.afficher("Voici les cartes restantes dans votre Main :");
			((JoueurHumain) joueur).afficherCartes(joueur.getMain());
			while (choixCarte2 < 0 || choixCarte2 >= joueur.getMain().size()) {
				console.afficher(
						"Vous devez en placer 2 dans votre Vie Future, (Entrez le numéro de la deuxième carte)");
				choixCarte2 = console.lireInt();
			}
			carteChoisie2 = joueur.getMain().get(choixCarte2);
			joueur.deplacerCarte(carteChoisie2, joueur.getMain(), joueur.getVieFuture());
		}
		if (joueur instanceof JoueurVirtuel) {
			carteChoisie1 = ((JoueurVirtuel) joueur).getStrategie().choisirCarteVieFuture((JoueurVirtuel)joueur);
			joueur.deplacerCarte(carteChoisie1, joueur.getMain(), joueur.getVieFuture());
			carteChoisie2 = ((JoueurVirtuel) joueur).getStrategie().choisirCarteVieFuture((JoueurVirtuel)joueur);
			joueur.deplacerCarte(carteChoisie2, joueur.getMain(), joueur.getVieFuture());
			console.afficher("Le Joueur Virtuel puise 2 carte de la Source et en place 2 dans sa Vie Future");
		}
	}
}
