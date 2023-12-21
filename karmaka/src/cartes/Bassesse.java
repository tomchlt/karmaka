package cartes;

import java.io.Serializable;

import java.util.Random;

import karmaka.Carte;
import karmaka.Joueur;
import karmaka.Partie;

public class Bassesse extends Carte implements Serializable {

	private static final long serialVersionUID = 9170075839040553100L;

	public Bassesse(Partie partie) {
		super(3, 0, 0, "Bassesse", "Défaussez au hasard 2 cartes de la Main d'un rival.", partie);
	}
	
	public void activerCapacite(Joueur joueur) {
		Joueur joueurAdverse = determinerJoueurAdverse(joueur);
		for (int i=0;i<2;i++) {
			Random rand = new Random();
			int randInt = rand.nextInt(joueurAdverse.main.size());
			Carte carte = joueurAdverse.main.get(randInt);
			joueurAdverse.defausser(carte, joueurAdverse.getMain());
		}
	}

}
