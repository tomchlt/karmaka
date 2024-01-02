package cartes;

import java.io.Serializable;

import karmaka.Carte;
import karmaka.Joueur;
import karmaka.JoueurHumain;
import karmaka.Partie;

public class Vengeance extends Carte implements Serializable {
	
	private static final long serialVersionUID = -3785714503881950277L;

	public Vengeance(Partie partie) {
		super(3, 0, 0, "Vengeance", "Défaussez l'Oeuvre exposée d'un rival.", partie);
	}
	
	public void activerCapacite(Joueur joueur) {
		
		// Le joueur adverse défausse son oeuvre exposée
		Joueur joueurAdverse = determinerJoueurAdverse(joueur);
		if (joueurAdverse.getOeuvre().isEmpty()==false) {
			if (joueur instanceof JoueurHumain) {
				console.afficher("Votre adversaire défausse son Oeuvre exposée.");
			}
			joueurAdverse.defausser(joueurAdverse.getOeuvre().getLast(), joueurAdverse.getOeuvre());
		} else {
			if (joueur instanceof JoueurHumain) {
				console.afficher("Votre adversaire n'a aucune carte dans ses Oeuvres...");
			}
		}
		
	}

}
