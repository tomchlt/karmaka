package fr.utt.karmaka;

import java.io.Serializable;

public class Mimetisme extends Carte implements Serializable {

	private static final long serialVersionUID = -2613949088021074217L;

	public Mimetisme(Partie partie) {
		super(1, 1, 1, "Mimétisme", "Choisissez un rival. Copiez le pouvoir de son Oeuvre exposée.", partie);
	}
	
	public void activerCapacite(Joueur joueur) {
		Joueur joueurAdverse = determinerJoueurAdverse(joueur);
		joueurAdverse.getOeuvre().getLast().activerCapacite();
	}

}
