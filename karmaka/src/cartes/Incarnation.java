package cartes;

import java.io.Serializable;

import karmaka.Carte;
import karmaka.Joueur;
import karmaka.Partie;

public class Incarnation extends Carte implements Serializable {

	private static final long serialVersionUID = -6140027867046876427L;

	public Incarnation(Partie partie) {
		super(1, 1, 1, "Incarnation", "Choisissez une de vos Oeuvres. Copiez son pouvoir.", partie);
	}
	
	public void activerCapacite(Joueur joueur) {
		// A RAJOUTER : demander au joueur de quelle carte parmi ses oeuvres il veut copier le pouvoir
		carte.activerCapacite();
	}

}
