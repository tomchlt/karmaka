package cartes;

import java.io.Serializable;

import jeu.Carte;
import jeu.Joueur;
import jeu.JoueurHumain;
import jeu.JoueurVirtuel;
import jeu.Partie;

public class Mimetisme extends Carte implements Serializable {

	private static final long serialVersionUID = -2613949088021074217L;

	public Mimetisme(Partie partie) {
		super(1, 1, 1, "Mimétisme", "Choisissez un rival. Copiez le pouvoir de son Oeuvre exposée.", partie);
	}
	
	public void activerCapacite(Joueur joueur) {
		
		// Le pouvoir de l'oeuvre exposée du joueur adverse est copié
		Joueur joueurAdverse = determinerJoueurAdverse(joueur);
		if (joueurAdverse.getOeuvre().isEmpty()==false) {
			Carte carte = joueurAdverse.getOeuvre().getLast();
			if (joueur instanceof JoueurHumain) {
				console.afficher("Vous copiez le pouvoir de l'Oeuvre exposée de votre adversaire : " + carte.getNom());
			}
			if (joueur instanceof JoueurVirtuel) {
				console.afficher("Le Joueur Virtuel adverse copie le pouvoir de votre Oeuvre exposée : " + carte.getNom());
			}
			carte.activerCapacite(joueur);
		} else {
			if (joueur instanceof JoueurHumain) {
				console.afficher("Votre adversaire n'a aucune carte dans ses Oeuvres...");
			}
			if (joueur instanceof JoueurVirtuel) {
				console.afficher("Vous n'avez pas de carte dans vos Oeuvres dont le Joueur Virtuel adverse puisse copier le pouvoir");
			}
		}
		
	}

}
