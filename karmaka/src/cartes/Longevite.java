package cartes;

import java.io.Serializable;

import jeu.Carte;
import jeu.Joueur;
import jeu.Partie;

public class Longevite extends Carte implements Serializable {
	
	private static final long serialVersionUID = -2023161043716899180L;

	public Longevite(Partie partie) {
		super(0, 2, 0, "Longévité", "Placez 2 cartes puisées à la Source sur la Pile d'un joueur", partie);
	}

	public void activerCapactite(Joueur joueur) {
		// A RAJOUTER : demander au joueur à quel joueur il veut donner les cartes
		joueurj.puiser();
		joueurj.puiser();
	}
}
