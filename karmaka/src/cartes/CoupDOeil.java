package cartes;

import java.io.Serializable;

import karmaka.Carte;
import karmaka.Joueur;
import karmaka.Partie;

public class CoupDOeil extends Carte implements Serializable {

	private static final long serialVersionUID = -6930221912727674082L;

	public CoupDOeil(Partie partie) {
		super(0, 0, 1, "Coup d'Oeil", "Regardez la Main d'un rival, vous pouvez ensuite jouer une autre carte.",
				partie);
	}

	public void activerCapacite(Joueur joueur) {
		Joueur joueurAdverse = determinerJoueurAdverse(joueur);
		System.out.println(joueurAdverse.getMain());
		// A RAJOUTER : demander au joueur si il veut continuer en jouant une autre
		// carte
		if (veutContinuer) {
			joueur.jouer();
		}
	}
}
