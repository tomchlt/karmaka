package cartes;

import java.io.Serializable;
import java.util.*;

import karmaka.Carte;
import karmaka.Joueur;
import karmaka.Partie;

public class Destinee extends Carte implements Serializable {

	private static final long serialVersionUID = 9162100002008505199L;

	public Destinee(Partie partie) {
		super(0, 0, 2, "Destinée",
				"Regarder les 3 premières cartes de la Source; ajoutez en jusqu'à deux à votre Vie Future. Replacez le reste dans l'ordre souhaité",
				partie);
	}

	public void activerCapacite(Joueur joueur) {
		LinkedList<Carte>source = partie.getSource();
		Carte carte1 = partie.getSource().getLast();
		Carte carte2 = partie.getSource().get(partie.getSource().size()-2);
		Carte carte3 = partie.getSource().get(partie.getSource().size()-3);

		int n = 0;
		int nbCartesGardees = 0;
		//Affficher cartes 1, 2 et 3
		//A RAJOUTER : demander au joueur si il veut déplacer une carte vers sa Vie Future
		while (n < 3 && nbCartesGardees < 2) {
			// A RAJOUTER : demander au joueur s'il veut deplacer la carte n dans sa vie future
			if (choix=="oui") {
				nbCartesGardees++;
				Carte carte = partie.getSource().get(partie.getSource().size()-n);
				joueur.deplacerCarte(carte, partie.getSource(), joueur.getMain());
			}
			n++;
			if (n==3) {
				n=0;
				//On peut faire en sorte que le joueur puisse revisionner les cartes autant qu'il veut
			}
		}

		int choix1 = 0;
		while (choix1 != 1 || choix1 != 2) {
			// A RAJOUTER : demander au joueur combien de cartes il veut garder
		}
		if (choix1 == 1) {
			int choix2 = 0;
			while (choix2 != 1 || choix2 != 2 || choix2 != 3) {
				// A RAJOUTER : demander au joueur quelle carte il veut garder parmi
				// joueur.main.get(joueur.main.size() - 1
				// joueur.main.get(joueur.main.size() - 2
				// joueur.main.get(joueur.main.size() - 3
			}
			if (choix2 == 1) {
				joueur.deplacerCarte(joueur.getMain().get(joueur.getMain().size() - 1), joueur.getMain(), joueur.getSource());
				joueur.deplacerCarte(joueur.getMain().get(joueur.getMain().size() - 2), joueur.getMain(), joueur.getSource());
			} else if (choix2 == 2) {
				joueur.deplacerCarte(joueur.getMain().get(joueur.getMain().size() - 1), joueur.getMain(), joueur.getSource());
				joueur.deplacerCarte(joueur.getMain().get(joueur.getMain().size() - 3), joueur.getMain(), joueur.getSource());
			} else if (choix2 == 3) {
				joueur.deplacerCarte(joueur.getMain().get(joueur.getMain().size() - 2), joueur.getMain(), joueur.getSource());
				joueur.deplacerCarte(joueur.getMain().get(joueur.getMain().size() - 3), joueur.getMain(), joueur.getSource());
			}
			// Le fait de déplacer des cartes comme cela ne risque pas de trigger l'observer
			// à cahque fois ?
		}
		if (choix1 == 2) {
			String choix2 = null;
			while (choix2 != "1 et 2" || choix2 != "1 et 3" || choix2 != "2 et 3") {
				// A RAJOUTER : demander au joueur quelle carte il veut garder parmi (il peut en
				// garder 2, placer le string dans le bon ordre)
				// joueur.main.get(joueur.main.size() - 1
				// joueur.main.get(joueur.main.size() - 2
				// joueur.main.get(joueur.main.size() - 3
			}
			if (choix2 == "1 et 2") {
				joueur.deplacerCarte(joueur.getMain().get(joueur.getMain().size() - 1), joueur.getMain(), joueur.getSource());
			} else if (choix2 == "1 et 3") {
				joueur.deplacerCarte(joueur.getMain().get(joueur.getMain().size() - 2), joueur.getMain(), joueur.getSource());
			} else if (choix2 == "2 et 3") {
				joueur.deplacerCarte(joueur.getMain().get(joueur.getMain().size() - 3), joueur.getMain(), joueur.getSource());
			}
		}
	}
}
