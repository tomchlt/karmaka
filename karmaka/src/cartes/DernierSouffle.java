package cartes;

import java.io.Serializable;

import karmaka.Carte;
import karmaka.Joueur;
import karmaka.JoueurHumain;
import karmaka.JoueurVirtuel;
import karmaka.Partie;

public class DernierSouffle extends Carte implements Serializable {

	private static final long serialVersionUID = 5735742941318495225L;

	public DernierSouffle(Partie partie) {
		super(1, 0, 0, "Dernier Souffle", "Le joueur de votre choix défausse une carte de sa Main.", partie);
	}
	
	public void activerCapacite(Joueur joueur) {
		
		// Le joueur adverse choisit quelle carte de sa main il préfère défausser
		Joueur joueurAdverse = determinerJoueurAdverse(joueur);
		int choixCarte = -1;
		if (joueurAdverse instanceof JoueurHumain) {
			console.afficher("**********************************************************************");
			console.afficher("Choix pour le joueur adverse (" + joueurAdverse.getNom() + ") :");
			while (choixCarte<0 || choixCarte>=joueurAdverse.getMain().size()) {
				console.afficher("Quelle carte de votre Main préférez-vous défausser ? (Entrez le numéro de la carte)");
				choixCarte = console.lireInt();
			}
			console.afficher("**********************************************************************");
		} else if (joueurAdverse instanceof JoueurVirtuel) {
			// A RAJOUTER
		}
		Carte carteChoisie = joueurAdverse.getMain().get(choixCarte);
		joueurAdverse.defausser(carteChoisie, joueurAdverse.getMain());
		
	}

}
