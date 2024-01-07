package cartes;

import java.io.Serializable;

import jeu.Carte;
import jeu.Joueur;
import jeu.JoueurHumain;
import jeu.JoueurVirtuel;
import jeu.Partie;

public class Crise extends Carte implements Serializable {

	private static final long serialVersionUID = -1072719917735251400L;

	public Crise(Partie partie) {
		super(2, 0, 0, "Crise", "Le rival de votre choix défausse une de ses Oeuvres.", partie);
	}
	
	public void activerCapacite(Joueur joueur) {
		
		// Le joueur adverse choisit quelle carte de ses oeuvres il préfère défausser
		Joueur joueurAdverse = determinerJoueurAdverse(joueur);
		if (joueurAdverse.getOeuvre().isEmpty()==false) {
			int choixCarte = -1;
			Carte carteChoisie = null;
			if (joueur instanceof JoueurHumain) {
				console.afficher("Votre adversaire va défausser une carte de ses Oeuvres.");
			}
			if (joueurAdverse instanceof JoueurHumain) {
				console.afficher("\n**********************************************************************");
				console.afficher("/!\\ Choix pour " + joueurAdverse.getNom() + " /!\\");
				console.afficher("Vous devez défausser une carte de vos Oeuvres");
				console.afficher("Cartes dans vos Oeuvres :");
				((JoueurHumain) joueurAdverse).afficherCartes(joueurAdverse.getOeuvre());
				while (choixCarte<0 || choixCarte>=joueurAdverse.getOeuvre().size()) {
					console.afficher("Quelle carte de vos Oeuvres préférez-vous défausser ? (Entrez le numéro de la carte)");
					choixCarte = console.lireInt();
				}
				carteChoisie = joueurAdverse.getOeuvre().get(choixCarte);
				console.afficher("**********************************************************************\n");
			} else if (joueurAdverse instanceof JoueurVirtuel) {
				carteChoisie = ((JoueurVirtuel) joueurAdverse).getStrategie().choisirCarteDéfausser((JoueurVirtuel)joueurAdverse, joueurAdverse.getOeuvre());
				console.afficher("Le Joueur Virtuel adverse défausse de ses Oeuvres la carte : ");
				console.afficher(carteChoisie.toString());
			}
			joueurAdverse.defausser(carteChoisie, joueurAdverse.getOeuvre());
		} else {
			if (joueur instanceof JoueurHumain) {
				console.afficher("Votre adversaire n'a aucune carte dans ses Oeuvres...");
			}
			if (joueur instanceof JoueurVirtuel) {
				console.afficher("Vous n'avez pas de Carte dans vos Oeuvre, vous ne pouvez donc pas en défausser");
			}
		}
		
	}

}
