package fr.utt.karmaka;

public class Lendemain extends Carte {
	public Lendemain(Partie partie) {
		super(0, 1, 0, "Lendemain", "Puisez une carte à la Source. Vous pouvez ensuite jouer une autre carte", partie);
	}
	
	public void activerCapactite(Joueur joueur) {
		joueur.puiser();
		joueur.jouer();
	}
}
