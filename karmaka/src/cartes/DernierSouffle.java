package cartes;

import java.io.Serializable;

import jeu.Carte;
import jeu.Joueur;
import jeu.JoueurHumain;
import jeu.JoueurVirtuel;
import jeu.Partie;

public class DernierSouffle extends Carte implements Serializable {

	private static final long serialVersionUID = 5735742941318495225L;

	public DernierSouffle(Partie partie) {
		super(1, 0, 0, "Dernier Souffle", "Le joueur de votre choix défausse une carte de sa Main.", partie);
	}
	
	public void activerCapacite(Joueur joueur) {
		
		// Le joueur adverse choisit quelle carte de sa main il préfère défausser
		Joueur joueurAdverse = determinerJoueurAdverse(joueur);
		if (joueurAdverse.getMain().isEmpty()==false) {
			int choixCarte = -1;
			if (joueur instanceof JoueurHumain) {
				console.afficher("Votre adversaire va défausser une carte de sa Main.");
			}
			if (joueurAdverse instanceof JoueurHumain) {
				console.afficher("\n**********************************************************************");
				console.afficher("/!\\ Choix pour " + joueurAdverse.getNom() + " /!\\");
				console.afficher("Cartes dans votre Main :");
				((JoueurHumain) joueurAdverse).afficherCartes(joueurAdverse.getMain());
				while (choixCarte<0 || choixCarte>=joueurAdverse.getMain().size()) {
					console.afficher("Quelle carte de votre Main préférez-vous défausser ? (Entrez le numéro de la carte)");
					choixCarte = console.lireInt();
				}
				console.afficher("**********************************************************************\n");
			} else if (joueurAdverse instanceof JoueurVirtuel) {
				// A RAJOUTER
			}
			Carte carteChoisie = joueurAdverse.getMain().get(choixCarte);
			joueurAdverse.defausser(carteChoisie, joueurAdverse.getMain());
		} else {
			if (joueur instanceof JoueurHumain) {
				console.afficher("Votre adversaire n'a aucune carte dans sa Main...");
			}
		}
		
	}

}
