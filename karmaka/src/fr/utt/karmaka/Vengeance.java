package fr.utt.karmaka;

public class Vengeance extends Carte {

	public Vengeance(Partie partie) {
		super(3, 0, 0, "Vengeance", "Défaussez l'Oeuvre exposée d'un rival.", partie);
	}
	
	public void activerCapacite(Joueur joueur) {
		Joueur joueurAdverse = determinerJoueurAdverse(joueur);
		joueurAdverse.defausser(joueurAdverse.getOeuvre().getLast(), joueurAdverse.getOeuvre());
	}

}
