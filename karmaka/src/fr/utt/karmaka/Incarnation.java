package fr.utt.karmaka;

public class Incarnation extends Carte {

	public Incarnation(Partie partie) {
		super(1, 1, 1, "Incarnation", "Choisissez une de vos Oeuvres. Copiez son pouvoir.", partie);
	}
	
	public void activerCapacite(Joueur joueur) {
		// A RAJOUTER : demander au joueur de quelle carte parmi ses oeuvres il veut copier le pouvoir
		carte.activerCapacite();
	}

}
