package cartes;

import java.io.Serializable;

import karmaka.Carte;
import karmaka.Joueur;
import karmaka.Partie;

public class Crise extends Carte implements Serializable {

	private static final long serialVersionUID = -1072719917735251400L;

	public Crise(Partie partie) {
		super(2, 0, 0, "Crise", "Le rival de votre choix défausse une de ses Oeuvres.", partie);
	}
	
	public void activerCapacite(Joueur joueur) {
		Joueur joueurAdverse = determinerJoueurAdverse(joueur);
		// A RAJOUTER : demander au joueur adverse quelle carte de ses oeuvres il préfère défausser
		joueurAdverse.defausser(carte, joueurAdverse.getOeuvre());
	}

}
