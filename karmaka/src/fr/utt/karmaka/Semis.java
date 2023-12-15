package fr.utt.karmaka;

public class Semis extends Carte {
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
