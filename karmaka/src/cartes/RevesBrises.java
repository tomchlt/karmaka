package cartes;

import java.io.Serializable;

import jeu.Carte;
import jeu.Joueur;
import jeu.JoueurHumain;
import jeu.JoueurVirtuel;
import jeu.Partie;

public class RevesBrises extends Carte implements Serializable {

	private static final long serialVersionUID = -7118868086166759625L;

	public RevesBrises(Partie partie) {
		super(0, 0, 2, "Rêves Brisés", "Placez la première carte de la Vie Future d'un rival sur la vôtre", partie);
	}

	public void activerCapacite(Joueur joueur) {
		Joueur joueurAdverse = determinerJoueurAdverse(joueur);
		// si la Vie Future du rival n'est pas vide alors on peut effectuer l'action
		if (joueurAdverse.getVieFuture().isEmpty() == false) {
			Carte carteADeplacer = joueurAdverse.getVieFuture().getLast();
			if (joueur instanceof JoueurHumain) {
				console.afficher(
						"Vous prenez la première carte de la Vie Future de votre rival pour la mettre sur la vôtre sur la vôtre.");
			}
			if (joueur instanceof JoueurVirtuel) {
				console.afficher("Le Joueur Virtuel prend la première carte de votre Vie Future qui est :");
				console.afficher(carteADeplacer.toString());
			}
			// l'action est de déplacer une carte d'une vie future à une autre
			joueur.deplacerCarte(carteADeplacer, joueurAdverse.getVieFuture(), joueur.getVieFuture());
		} else {
			// sinon message d'erreur à l'intention du joueur
			if (joueur instanceof JoueurHumain) {
				console.afficher(
						"Votre rival n'a pas de carte dans sa Vie Future, vous ne pouvez donc pas lui prendre de carte");
			}
		}
	}
}
