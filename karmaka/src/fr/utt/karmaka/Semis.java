package fr.utt.karmaka;

import java.io.Serializable;

public class Semis extends Carte implements Serializable {
	
	private static final long serialVersionUID = -8510928523076852307L;

	public Semis(Partie partie) {
		super(0, 2, 0, "Semis", "Puissez 2 cartes à la Source puis placez sur votre VieFuture 2 cartes de votre Main.",
				partie);
	}

	public void activerCapactite(Joueur joueur) {
		joueur.puiser();
		joueur.puiser();
		joueur.deplacerCarte(carte1, joueur.main, joueur.vieFuture);
		joueur.deplacerCarte(carte2, joueur.main, joueur.vieFuture);
	}
}
