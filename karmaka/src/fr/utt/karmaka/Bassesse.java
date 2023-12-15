package fr.utt.karmaka;

import java.util.Random;

public class Bassesse extends Carte {

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
