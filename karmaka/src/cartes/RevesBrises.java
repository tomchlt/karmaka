package cartes;

import java.io.Serializable;

import jeu.Carte;
import jeu.Joueur;
import jeu.Partie;

public class RevesBrises extends Carte implements Serializable {
	
	private static final long serialVersionUID = -7118868086166759625L;

	public RevesBrises(Partie partie) {
		super(0, 0, 2, "Rêves Brisés", "Placez la première carte de la Vie Future d'un rival sur la vôtre", partie);
	}

	public void activerCapacite(Joueur joueur) {
		Joueur joueurAdverse = determinerJoueurAdverse(joueur);
		joueur.deplacerCarte(joueurAdverse.getVieFuture().getLast(), joueurAdverse.getVieFuture(),
				joueur.getVieFuture());
	}
}
