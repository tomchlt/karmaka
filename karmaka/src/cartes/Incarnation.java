package cartes;

import java.io.Serializable;

import karmaka.Carte;
import karmaka.Joueur;
import karmaka.JoueurHumain;
import karmaka.JoueurVirtuel;
import karmaka.Partie;

public class Incarnation extends Carte implements Serializable {

	private static final long serialVersionUID = -6140027867046876427L;

	public Incarnation(Partie partie) {
		super(1, 1, 1, "Incarnation", "Choisissez une de vos Oeuvres. Copiez son pouvoir.", partie);
	}
	
	public void activerCapacite(Joueur joueur) {
		
		// Le joueur choisit une carte de ses oeuvres dont il veut copier le pouvoir
		if (joueur.getOeuvre().isEmpty()==false) {
			int choixCarte = -1;
			if (joueur instanceof JoueurHumain) {
				console.afficher("Cartes dans votre Main :");
				((JoueurHumain) joueur).afficherCartes(joueur.getMain());
				while (choixCarte<0 || choixCarte>=joueur.getOeuvre().size()) {
					console.afficher("De quelle carte voulez-vous copier le pouvoir ? (Entrez le numéro de cette carte)");
					choixCarte = console.lireInt();
				}
			} else if (joueur instanceof JoueurVirtuel) {
				// A RAJOUTER
			}
			Carte carteChoisie = joueur.getOeuvre().get(choixCarte);
			carteChoisie.activerCapacite(joueur);
		} else {
			if (joueur instanceof JoueurHumain) {
				console.afficher("Vous n'avez aucune carte dans vos Oeuvres...");
			}
		}
		
	}

}
