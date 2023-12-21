package cartes;

import java.io.Serializable;

import karmaka.Carte;
import karmaka.Joueur;
import karmaka.Partie;

public class Jubile extends Carte implements Serializable {

	private static final long serialVersionUID = 6158669912375296624L;

	public Jubile(Partie partie) {
		super(0, 3, 0, "Jubilé", "Placez 2 cartes de votre Main sur vous Oeuvres", partie);
	}
	
	public void activerCapacite(Joueur joueur) {
		// A RAJOUTER : demander au joueur quelle carte de sa main il veut envoyer dans ses oeuvres
		joueur.deplacerCarte(carte, joueur.getMain(), joueur.getOeuvre());
		// A RAJOUTER : demander au joueur si il veut continuer avec une 2e carte
		if (veutContinuer) {
			// A RAJOUTER : demander au joueur quelle carte de sa main il veut envoyer dans ses oeuvres
			joueur.deplacerCarte(carte, joueur.getMain(), joueur.getOeuvre());
		}
	}

}
