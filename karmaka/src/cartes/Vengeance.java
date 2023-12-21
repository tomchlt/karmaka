package cartes;

import java.io.Serializable;

import karmaka.Carte;
import karmaka.Joueur;
import karmaka.Partie;

public class Vengeance extends Carte implements Serializable {
	
	private static final long serialVersionUID = -3785714503881950277L;

	public Vengeance(Partie partie) {
		super(3, 0, 0, "Vengeance", "Défaussez l'Oeuvre exposée d'un rival.", partie);
	}
	
	public void activerCapacite(Joueur joueur) {
		Joueur joueurAdverse = determinerJoueurAdverse(joueur);
		joueurAdverse.defausser(joueurAdverse.getOeuvre().getLast(), joueurAdverse.getOeuvre());
	}

}
