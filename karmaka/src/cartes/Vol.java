package cartes;

import java.io.Serializable;

import jeu.Carte;
import jeu.Joueur;
import jeu.JoueurHumain;
import jeu.JoueurVirtuel;
import jeu.Partie;

public class Vol extends Carte implements Serializable {

	private static final long serialVersionUID = 983537493789214942L;

	public Vol(Partie partie) {
		super(0, 0, 3, "Vol", "Placez dans votre Main l'Oeuvre Exposée d'un rival.", partie);
	}

	public void activerCapacite(Joueur joueur) {
		Joueur joueurAdverse = determinerJoueurAdverse(joueur);
		if (joueurAdverse.getOeuvre().isEmpty() == false) {
			Carte carteAVoler = joueurAdverse.getOeuvre().getLast();
			if (joueur instanceof JoueurHumain) {
				console.afficher("Vous volez l'Oeuvre exposée de votre adversaire qui est :");
				console.afficher(carteAVoler);
			}
			if (joueur instanceof JoueurVirtuel) {
				console.afficher("Le Joueur Virtuel vous vole votre Oeuvre exposée qui est :");
				console.afficher(carteAVoler);
			}
			joueurAdverse.deplacerCarte(carteAVoler, joueurAdverse.getOeuvre(), joueur.getMain());
		}
		else {
			if (joueur instanceof JoueurHumain) {
				console.afficher("Votre adversaire n'a pas de carte dans ses Oeuvre");
			}
			if (joueur instanceof JoueurVirtuel) {
				console.afficher("Vous n'avez pas de carte dans vos Oeuvres que le joueur Virtuel peut prendre");
			}
		}
	}
}
