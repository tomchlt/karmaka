package fr.utt.karmaka;

public class Mimetisme extends Carte {

	public Mimetisme(Partie partie) {
		super(1, 1, 1, "Mimétisme", "Choisissez un rival. Copiez le pouvoir de son Oeuvre exposée.", partie);
	}
	
	public void activerCapacite(Joueur joueur) {
		Joueur joueurAdverse = determinerJoueurAdverse(joueur);
		joueurAdverse.getOeuvre().getLast().activerCapacite();
	}

}
