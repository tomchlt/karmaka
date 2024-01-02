package cartes;

import java.io.Serializable;

import karmaka.Carte;
import karmaka.Joueur;
import karmaka.JoueurHumain;
import karmaka.JoueurVirtuel;
import karmaka.Partie;

public class Voyage extends Carte implements Serializable {
	
	private static final long serialVersionUID = -4211357722298307957L;

	public Voyage(Partie partie) {
		super(0, 3, 0, "Voyage", "Puisez 3 cartes à la Source. Vous pouvez ensuite jouer une autre carte.", partie);
	}
	
	public void activerCapacite(Joueur joueur) {
		
		// Le joueur puise 3 cartes à la source
		if (joueur instanceof JoueurHumain) {
			console.afficher("Vous puisez 3 cartes à la Source.");
		}
		for (int i=0;i<3;i++) {
			joueur.puiser(joueur.getMain());
		}
		
		// Le joueur choisit de jouer une autre carte ou non
		int choixJouer = 0;
		if (joueur instanceof JoueurHumain) {
			console.afficher("Cartes dans votre Main :");
			((JoueurHumain) joueur).afficherCartes(joueur.getMain());
			while (choixJouer!=1 && choixJouer!=2) {
				console.afficher("Voulez-vous jouer une autre carte ? (Entrez [1] pour OUI, [2] pour NON)");
				choixJouer = console.lireInt();
			}
		} else if (joueur instanceof JoueurVirtuel) {
			// A RAJOUTER
		}
		
		// Si le joueur a choisi de jouer une autre carte, il choisit quelle carte jouer et comment la jouer
		if (choixJouer==1) {
			int choixCarte = -1;
			int choix = 0;
			if (joueur instanceof JoueurHumain) {
				while (choixCarte<0 || choixCarte>=joueur.getMain().size()) {
					console.afficher("Quelle carte voulez-vous maintenant jouer ? (Entrez le numéro de la carte)");
					choixCarte = console.lireInt();
				}
				while (choix!=1 && choix!=2 && choix!=3) {
					console.afficher("Voulez-vous jouer cette carte pour ses points [1], son pouvoir [2], ou votre Vie Future [3]?");
					choix = console.lireInt();
				}
			} else if (joueur instanceof JoueurVirtuel) {
				// A RAJOUTER
			}
			Carte carteChoisie = joueur.getMain().get(choixCarte);
			switch (choix) {
			case 1:
				joueur.deplacerCarte(carteChoisie, joueur.getMain(), joueur.getOeuvre());
				break;
			case 2:
				Joueur joueurAdverse = determinerJoueurAdverse(joueur);
				joueur.deplacerCarte(carteChoisie, joueur.getMain(), joueurAdverse.getTempo());
				carteChoisie.activerCapacite(joueur);
				break;
			case 3:
				joueur.deplacerCarte(carteChoisie, joueur.getMain(), joueur.getVieFuture());
				break;
			}
		}
		
	}

}
