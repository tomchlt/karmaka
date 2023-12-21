package cartes;

import java.io.Serializable;

import karmaka.Carte;
import karmaka.Joueur;
import karmaka.Partie;

public class Lendemain extends Carte implements Serializable {
	
	private static final long serialVersionUID = 4573116733231266007L;

	public Lendemain(Partie partie) {
		super(0, 1, 0, "Lendemain", "Puisez une carte à la Source. Vous pouvez ensuite jouer une autre carte", partie);
	}
	
	public void activerCapactite(Joueur joueur) {
		joueur.puiser();
		joueur.jouer();
	}
}
