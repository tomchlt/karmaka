package cartes;

import java.io.Serializable;

import jeu.Carte;
import jeu.Joueur;
import jeu.JoueurHumain;
import jeu.JoueurVirtuel;
import jeu.Partie;

public class Transmigration extends Carte implements Serializable {

	private static final long serialVersionUID = 5566288548082763988L;

	public Transmigration(Partie partie) {
		super(0, 0, 1, "Transmigration", "Placez dans votre Main n'importe quelle carte de votre Vie Future", partie);
	}

	public void activerCapacité(Joueur joueur) {
		// si la Vie Future du joueur n'est pas vide alors on peut effectuer l'action
		if (joueur.getVieFuture().isEmpty() == false) {
			Carte carteChoisie = null;
			int choixCarte = -1;
			if (joueur instanceof JoueurHumain) {
				// le joueur choisi une carte de sa vie future à mettre dans sa main
				console.afficher("Choisissez une carte de votre Vie Future à mettre dans votre Main");
				console.afficher("Cartes dans votre Vie Future :");
				((JoueurHumain) joueur).afficherCartes(joueur.getVieFuture());
				while (choixCarte < 0 || choixCarte >= joueur.getMain().size()) {
					console.afficher(
							"Quelle carte voulez-vous placer dans votre Main ? (Entrez le numéro de la carte)");
					choixCarte = console.lireInt();
				}
			} else if (joueur instanceof JoueurVirtuel) {
				console.afficher("Le Joueur Virtuel prend une carte de sa Vie Future pour la remettre dans sa Main");
				carteChoisie = ((JoueurVirtuel) joueur).getStrategie().trouverCartePtsMin(joueur.getVieFuture());
			}
			// on déplace la carte choisie
			carteChoisie = joueur.getVieFuture().get(choixCarte);
			joueur.deplacerCarte(carteChoisie, joueur.getVieFuture(), joueur.getMain());
		} else {
			// sinon message d'erreur
			if (joueur instanceof JoueurHumain) {
				console.afficher("Vous n'avez pas de carte dans votre Vie Future");
			}
			if (joueur instanceof JoueurVirtuel) {
				console.afficher("Le Joueur Virtuel n'a pas de carte dans sa Vie Future");
			}
		}
	}
}
