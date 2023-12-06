package fr.utt.karmaka;

public class Deni extends Carte {
	public Deni(Partie partie) {
		super(0, 0, 2, "Déni", "Défaussez une carte de votre Main. Copiez le pouvoir de cette carte", partie);
	}

	public void activerCapactite(Joueur joueur) {
		joueur.defausser(carte, joueur.getMain());
		//Comment copier le pouvoir d'une carte ?
		carte.activerCapactite(Joueur joueur);//Ne faudrait-il pas mettre toutes les capacité de carte avec la même signature ?
	}
}
