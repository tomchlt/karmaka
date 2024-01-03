package cartes;

import java.io.Serializable;

import jeu.Carte;
import jeu.Joueur;
import jeu.Partie;

public class Transmigration extends Carte implements Serializable {
	
	private static final long serialVersionUID = 5566288548082763988L;

	public Transmigration(Partie partie) {
		super(0, 0, 1, "Transmigration", "Placez dans votre Main n'importe quelle carte de votre Vie Future", partie);
	}

	public void activerCapacité(Joueur joueur) {
		System.out.println(joueur.getVieFuture());// Vérifier si cette méthode permet bien d'afficher tout la collection
													// vie future
		// A RAJOUTER : demander au joueur quelle carte de sa vie future il veut mettre
		// dans sa main
		joueur.deplacerCarte(carte, joueur.getVieFuture(), joueur.getMain());
	}
}
