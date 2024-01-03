package cartes;

import java.io.Serializable;

import jeu.Carte;
import jeu.Joueur;
import jeu.Partie;

public class Vol extends Carte implements Serializable {
	
	private static final long serialVersionUID = 983537493789214942L;

	public Vol(Partie partie) {
		super(0, 0, 3, "Vol", "Placez dans votre Main l'Oeuvre Exposée d'un rival.", partie);
	}

	public void activerCapacite(Joueur joueur) {
		Joueur joueurAdverse = determinerJoueurAdverse(joueur);
		joueurAdverse.deplacerCarte(joueurAdverse.getOeuvre().getLast(), joueurAdverse.getOeuvre(), joueur.getOeuvre());
	}

}
