package cartes;

import java.io.Serializable;
import java.util.*;

import jeu.Carte;
import jeu.Joueur;
import jeu.JoueurHumain;
import jeu.JoueurVirtuel;
import jeu.Partie;

public class Duperie extends Carte implements Serializable {

	private static final long serialVersionUID = -8671599952223933535L;

	public Duperie(Partie partie) {
		super(0, 0, 3, "Duperie", "Regarder 3 cartes de la Main d'un rival; ajoutez en une à votre Main", partie);
	}

	public void activerCapacite(Joueur joueur) {
		Joueur joueurAdverse = determinerJoueurAdverse(joueur);
		if (joueurAdverse.getMain().isEmpty() == false) {
			int choixCarte = 0;
			Carte carte1 = null;
			Carte carte2 = null;
			Carte carte3 = null;
			Carte carte = null;
			LinkedList<Carte> cartesAChoisir = new LinkedList<>();
			LinkedList<Carte> mainJA = joueurAdverse.getMain();
			// Dans le cas où le joueur adverse n'a qu'une carte dans sa main, le joueur
			// n'a pas le choix de la carte qu'il peut prendre
			if (mainJA.size() == 1) {
				carte1 = mainJA.getFirst();
				carte = carte1;
				if (joueur instanceof JoueurHumain) {
					console.afficher("Votre adversaire n'a qu'une carte dans sa main qui est :");
					console.afficher(carte1.toString());
					console.afficher("Vous l'ajoutez à votre Main");
				}
			} else if (mainJA.size() == 2) {
				// Dans le cas où le joueur adverse a plusieurs cartes dans sa main, le joueur
				// a le choix de la carte qu'il peut prendre
				carte1 = mainJA.getFirst();
				carte2 = mainJA.getLast();
				if (joueur instanceof JoueurHumain) {
					console.afficher("Votre adversaire n'a que deux cartes dans sa main qui sont :");
					console.afficher(carte1.toString());
					console.afficher(carte2.toString());
					// le joueur choisi la carte qu'il veut
					while (choixCarte != 1 || choixCarte != 2) {
						console.afficher("Quelle carte voulez-vous placer dans votre Main ? (Entrez 1 ou 2)");
						choixCarte = console.lireInt();
					}
					carte = mainJA.get(mainJA.size() - choixCarte);
				}
				if (joueur instanceof JoueurVirtuel) {
					cartesAChoisir.add(carte1);
					cartesAChoisir.add(carte2);
					carte = ((JoueurVirtuel) joueur).getStrategie().selectionnerCarte(cartesAChoisir);
				}
			} else if (mainJA.size() == 3) {
				// Dans le cas où le joueur adverse a plusieurs cartes dans sa main, le joueur
				// a le choix de la carte qu'il peut prendre
				carte1 = mainJA.getFirst();
				carte2 = mainJA.get(1);
				carte3 = mainJA.getLast();
				if (joueur instanceof JoueurHumain) {
					console.afficher("Voici les 3 seules cartes de la main de votre adversaire");
					console.afficher(carte1.toString());
					console.afficher(carte2.toString());
					console.afficher(carte3.toString());
					// le joueur choisi la carte qu'il veut
					while (choixCarte < 1 || choixCarte > 3) {
						console.afficher("Quelle carte voulez-vous placer dans votre Main ? (Entrez 1, 2 ou 3)");
						choixCarte = console.lireInt();
					}
					carte = mainJA.get(mainJA.size() - choixCarte);
				}
				if (joueur instanceof JoueurVirtuel) {
					cartesAChoisir.add(carte1);
					cartesAChoisir.add(carte2);
					cartesAChoisir.add(carte3);
					carte = ((JoueurVirtuel) joueur).getStrategie().selectionnerCarte(cartesAChoisir);
				}
			} else {
				// Dans le cas où le joueur adverse a plusieurs cartes dans sa main, le joueur
				// a le choix de la carte qu'il peut prendre
				Random random = new Random();
				while (carte1 == carte2 || carte1 == carte3 || carte2 == carte3) {
					carte1 = mainJA.get(random.nextInt(mainJA.size()));
					carte2 = mainJA.get(random.nextInt(mainJA.size()));
					carte3 = mainJA.get(random.nextInt(mainJA.size()));
				}
				if (joueur instanceof JoueurHumain) {
					console.afficher("Voici 3 cartes aléatoires de la Main de votre adversaire : ");
					console.afficher(carte1.toString());
					console.afficher(carte2.toString());
					console.afficher(carte3.toString());
					// le joueur choisi la carte qu'il veut
					while (choixCarte < 1 || choixCarte > 3) {
						console.afficher("Quelle carte voulez-vous placer dans votre Main ? (Entrez 1, 2 ou 3)");
						choixCarte = console.lireInt();
					}
					carte = mainJA.get(mainJA.size() - choixCarte);
				}
				if (joueur instanceof JoueurVirtuel) {
					cartesAChoisir.add(carte1);
					cartesAChoisir.add(carte2);
					cartesAChoisir.add(carte3);
					carte = ((JoueurVirtuel) joueur).getStrategie().selectionnerCarte(cartesAChoisir);
				}
			}
			if (joueur instanceof JoueurVirtuel) {
			console.afficher("Le Joueur Virtuel vous prend la carte : ");
			console.afficher(carte.toString());
			}
			joueur.deplacerCarte(carte, mainJA, joueur.getMain());
		}
	}
}
