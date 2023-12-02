package fr.utt.karmaka;

public class Panique extends Carte {

	public Panique(Partie partie) {
		super(1, 0, 0, "Panique", "Défaussez la première carte de la Pile d'un joueur. Vous pouvez ensuite jouer une autre carte.", partie);
	}
	
	public void activerCapacite(Joueur joueur) {
		Joueur joueurAdverse = determinerJoueurAdverse(joueur);
		joueurAdverse.defausser(joueurAdverse.pile.getFirst(), joueur.pile);
		System.out.println("Vous pouvez maintenant jouer une autre carte.");
		// A RAJOUTER : demander au joueur quelle carte il veut maintenant jouer
		carte.activerCapacite();
	}

}
