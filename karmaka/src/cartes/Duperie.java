package cartes;

import java.io.Serializable;
import java.util.Random;

import jeu.Carte;
import jeu.Joueur;
import jeu.Partie;

public class Duperie extends Carte implements Serializable {

	private static final long serialVersionUID = -8671599952223933535L;

	public Duperie(Partie partie) {
		super(0, 0, 3, "Duperie", "Regarder 3 cartes de la Main d'un rival; ajoutez en une à votre Main", partie);
	}

	public void activerCapacite(Joueur joueur) {
		Joueur joueurAdverse = determinerJoueurAdverse(joueur);
		for (int i = 0; i<3; i++) {
			Random rand = new Random();
			int randInt = rand.nextInt(joueurAdverse.main.size());
			Carte carte = joueur.main.get(randInt);
			joueur.deplacerCarte(carte, joueurAdverse.main, joueur.main);
		}
		int choix = 0;
		while (choix != 1 || choix != 2 || choix != 3) {
			// A RAJOUTER : demander au joueur quelle carte il veut garder parmi
			//joueur.main.get(joueur.main.size() - 1
			//joueur.main.get(joueur.main.size() - 2
			//joueur.main.get(joueur.main.size() - 3		
		}
		if (choix == 1) {
			joueur.deplacerCarte(joueur.main.get(joueur.main.size() - 1), joueur.main, joueurAdverse.main);
			joueur.deplacerCarte(joueur.main.get(joueur.main.size() - 2), joueur.main, joueurAdverse.main);
		} else if (choix == 2) {
			joueur.deplacerCarte(joueur.main.get(joueur.main.size() - 1), joueur.main, joueurAdverse.main);
			joueur.deplacerCarte(joueur.main.get(joueur.main.size() - 3), joueur.main, joueurAdverse.main);
		} else if (choix == 3) {
			joueur.deplacerCarte(joueur.main.get(joueur.main.size() - 2), joueur.main, joueurAdverse.main);
			joueur.deplacerCarte(joueur.main.get(joueur.main.size() - 3), joueur.main, joueurAdverse.main);
		}
		//Le fait de déplacer des cartes comme cela ne risque pas de trigger l'observer à cahque fois ?
	}
}
