package fr.utt.karmaka;

public class Vol extends Carte {
	public Vol(Partie partie) {
		super(0, 0, 3, "Vol", "Placez dans votre Main l'Oeuvre Exposée d'un rival.", partie);
	}

	public void activerCapacite(Joueur joueur) {
		Joueur joueurAdverse = determinerJoueurAdverse(joueur);
		joueurAdverse.deplacerCarte(joueurAdverse.getOeuvre().getLast(), joueurAdverse.getOeuvre(), joueur.getOeuvre());
	}

}
