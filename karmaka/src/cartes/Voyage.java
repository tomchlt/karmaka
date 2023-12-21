package cartes;

import java.io.Serializable;

import karmaka.Carte;
import karmaka.Joueur;
import karmaka.Partie;

public class Voyage extends Carte implements Serializable {
	
	private static final long serialVersionUID = -4211357722298307957L;

	public Voyage(Partie partie) {
		super(0, 3, 0, "Voyage", "Puisez 3 cartes à la Source. Vous pouvez ensuite jouer une autre carte.", partie);
	}
	
	public void activerCapacite(Joueur joueur) {
		for (int i=0;i<3;i++) {
			joueur.puiser(joueur.main);
		}
		// A RAJOUTER : demander au joueur quelle carte il veut maintenant jouer
		carte.activerCapacite();
	}

}
