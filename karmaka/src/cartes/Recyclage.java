package cartes;

import java.io.Serializable;
import java.util.LinkedList;

import jeu.Carte;
import jeu.Joueur;
import jeu.JoueurHumain;
import jeu.JoueurVirtuel;
import jeu.Partie;

public class Recyclage extends Carte implements Serializable {

	private static final long serialVersionUID = 6644982015570858271L;

	public Recyclage(Partie partie) {
		super(0, 1, 0, "Recyclage", "Ajoutez à votre Vie Future une des 3 dernières cartes de la Fosse.", partie);
	}

	public void activerCapactite(Joueur joueur) {
		// on sélectionne les cartes en fonction de la taille de la source
		if (joueur.getFosse().isEmpty() == false) {
			int choixCarte = 0;
			Carte carte1 = null;
			Carte carte2 = null;
			Carte carte3 = null;
			Carte carte = null;
			LinkedList<Carte> cartesAChoisir = new LinkedList<>();
			LinkedList<Carte> fosse = joueur.getFosse();
			if (fosse.size() == 1) {
				carte1 = fosse.get(fosse.size() - 1);
				carte = carte1;
				if (joueur instanceof JoueurHumain) {
					console.afficher("Il n'y a qu'une carte dans la Fosse, cette carte est :");
					console.afficher(carte1.toString());
					console.afficher("Vous la déplacez donc dans votre Vie Future");
				}
				if (joueur instanceof JoueurVirtuel) {
					console.afficher(
							"Le Joueur Virtuel prend la seule Carte de la Fosse et la place dans sa Vie Future");
				}
			} else if (fosse.size() == 2) {
				carte1 = fosse.get(fosse.size() - 1);
				carte2 = fosse.get(fosse.size() - 2);
				// on présente les cartes au joueur
				if (joueur instanceof JoueurHumain) {
					console.afficher("Il n'y a que deux cartes dans la Fosse qui sont :");
					console.afficher(carte1.toString());
					console.afficher(carte2.toString());
					// le joueur choisi la carte qu'il veut
					while (choixCarte != 1 || choixCarte != 2) {
						console.afficher("Quelle carte voulez-vous placer dans votre Vie Future ? (Entrez 1 ou 2)");
						choixCarte = console.lireInt();
					}
					carte = fosse.get(fosse.size() - choixCarte);
				}
				if (joueur instanceof JoueurVirtuel) {
					cartesAChoisir.add(carte1);
					cartesAChoisir.add(carte2);
					carte = ((JoueurVirtuel) joueur).getStrategie().selectionnerCarte(cartesAChoisir);
					console.afficher(
							"Le Joueur Virtuel prend une des 2 seules Cartes de la Fosse et la place dans sa Vie Future");

				}
			} else {
				carte1 = fosse.get(fosse.size() - 1);
				carte2 = fosse.get(fosse.size() - 2);
				carte3 = fosse.get(fosse.size() - 3);
				// on présente les cartes au joueur
				if (joueur instanceof JoueurHumain) {
					console.afficher("Voici les 3 dernières cartes de la fosse :");
					console.afficher(carte1.toString());
					console.afficher(carte2.toString());
					console.afficher(carte3.toString());
					// le joueur choisi la carte qu'il veut
					while (choixCarte <= 0 || choixCarte > 3) {
						console.afficher("Quelle carte voulez-vous placer dans votre Vie Future ? (Entrez 1, 2 ou 3)");
						choixCarte = console.lireInt();
					}
					carte = fosse.get(fosse.size() - choixCarte);
				}
				// le joueur virtuel choisi la carte selon sa stratégie
				if (joueur instanceof JoueurVirtuel) {
					cartesAChoisir.add(carte1);
					cartesAChoisir.add(carte2);
					cartesAChoisir.add(carte3);
					carte = ((JoueurVirtuel) joueur).getStrategie().selectionnerCarte(cartesAChoisir);
					console.afficher(
							"Le Joueur Virtuel prend une des 3 dernières Cartes de la Fosse et la place dans sa Vie Future");
				}
			}
			// une fois la carte sélectionnée, on effectue le déplacement
			joueur.deplacerCarte(carte, fosse, joueur.getVieFuture());
		} else {
			if (joueur instanceof JoueurHumain) {
				console.afficher("La fosse est vide, il n'y a pas de carte à prendre");
			}
		}
	}
}
