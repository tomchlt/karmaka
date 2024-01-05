package cartes;

import java.io.Serializable;
import java.util.*;

import jeu.Carte;
import jeu.Joueur;
import jeu.JoueurHumain;
import jeu.JoueurVirtuel;
import jeu.Partie;

public class Destinee extends Carte implements Serializable {

	private static final long serialVersionUID = 9162100002008505199L;

	public Destinee(Partie partie) {
		super(0, 0, 2, "Destinée",
				"Regarder les 3 premières cartes de la Source; ajoutez en jusqu'à deux à votre Vie Future. Replacez le reste dans l'ordre souhaité",
				partie);
	}

	public void activerCapacite(Joueur joueur) {
		// on vérifie que la source à assez de cartes
		LinkedList<Carte> cartesARegarder = new LinkedList<>();
		Iterator<Carte> itCartes = cartesARegarder.iterator();
		LinkedList<Carte> source = joueur.getSource();
		int choixCarte1 = -1;
		int choixCarte2 = -1;
		for (int i = 0; i <= 2; i++) {
			joueur.deplacerCarte(source.get(source.size() - i), source, cartesARegarder);
			if (source.size() == 0) {
				while (joueur.getFosse().size() > 3) {
					joueur.deplacerCarte(joueur.getFosse().getLast(), joueur.getFosse(), source);
				}
				partie.melangerSource();
			}
		}
		if (joueur instanceof JoueurHumain) {
			console.afficher("Voici les 3 premières cartes de la Source : ");
			while (itCartes.hasNext()) {
				Carte carteAffichee = itCartes.next();
				console.afficher(carteAffichee.toString());
			}
			console.afficher("Parmi ces Cartes, vous pouvez en afficher jusqu'à deux dans votre Vie Future");
			while (choixCarte1 < 0 || choixCarte1 > 3) {
				console.afficher("Entrez 1, 2 ou 3 pour la carte que vous voulez ou 0 si vous n'en voulez pas");
				choixCarte1 = console.lireInt();
			}
			if (choixCarte1 != 0) {
				Carte carteADeplacer1 = joueur.getSource().get(choixCarte1 - 1);
				joueur.deplacerCarte(carteADeplacer1, source, joueur.getVieFuture());
				console.afficher("Vous prenez la carte" + carteADeplacer1.toString());
				console.afficher("Vous pouvez en déplacer une deuxième");
				while (choixCarte2 < 0 || choixCarte1 > 3) {
					console.afficher("Entrez 1 ou 2 pour la carte que vous voulez ou 0 si vous n'en voulez pas");
					choixCarte2 = console.lireInt();
				}
				if (choixCarte2 != 0) {
					Carte carteADeplacer2 = joueur.getSource().get(choixCarte2 - 1);
					joueur.deplacerCarte(carteADeplacer2, source, joueur.getVieFuture());
					console.afficher("Vous prenez la carte" + carteADeplacer1.toString());
				}
				if (choixCarte2 == 0) {
					console.afficher("Vous ne prenez pas de seconde carte");
				}
			}
			if (choixCarte1 == 0) {
				console.afficher("Vous ne prenez aucune carte");
			}
			console.afficher("Vous pouvez replacer les cartes suivantes dans l'ordre que vous voulez dans la Source :");
			while (itCartes.hasNext()) {
				Carte carteAffichee = itCartes.next();
				console.afficher(carteAffichee.toString());
			}
			while (cartesARegarder.isEmpty() == false) {
				if (cartesARegarder.size() == 1) {
					joueur.deplacerCarte(cartesARegarder.getLast(), cartesARegarder, joueur.getSource());
				} else {
					int ordreCarte = -1;
					console.afficher(
							"Vous pouvez replacer les cartes suivantes dans l'ordre que vous voulez dans la Source :");
					while (itCartes.hasNext()) {
						Carte carteAffichee = itCartes.next();
						console.afficher(carteAffichee.toString());
					}
					while (ordreCarte < 0 || ordreCarte >= cartesARegarder.size()) {
						console.afficher(
								"Sélectionnez la carte que vous voulez remettre d'abord (Entrez le numéro de la carte)");
						ordreCarte = console.lireInt();
					}
					Carte carteARemettre = joueur.getMain().get(ordreCarte);
					joueur.deplacerCarte(carteARemettre, cartesARegarder, joueur.getSource());
				}
			}
		}
		if (joueur instanceof JoueurVirtuel) {
			//on considère que le bot prendra toujours 2 cartes, il n'en replacera alors qu'un seule
			Carte carteADeplacer1 = ((JoueurVirtuel) joueur).getStrategie().selectionnerCarte(cartesARegarder);
			joueur.deplacerCarte(carteADeplacer1, cartesARegarder, joueur.getVieFuture());
			Carte carteADeplacer2 = ((JoueurVirtuel) joueur).getStrategie().selectionnerCarte(cartesARegarder);
			joueur.deplacerCarte(carteADeplacer2, cartesARegarder, joueur.getVieFuture());
			joueur.deplacerCarte(cartesARegarder.getLast(), cartesARegarder, joueur.getSource());
			console.afficher("Le Joueur Virtuel place 2 des cartes dans sa Vie Future");
		}
	}
}
