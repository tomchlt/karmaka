package fr.utt.karmaka;

public class Destinee extends Carte {

	public Destinee(Partie partie) {
		super(0, 0, 2, "Destinée",
				"Regarder les 3 premières cartes de la Source; ajoutez en jusqu'à deux à votre Vie Future. Replacez le reste dans l'ordre souhaité",
				partie);
	}

	public void activerCapacite(Joueur joueur) {
		// Puisqu'on peut replacer les cartes dans l'ordre souhaité, faire un shuffle au
		// lieu d'un tirage aléatoire serait peut être mieux
		joueur.puiser();
		joueur.puiser();
		joueur.puiser();

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
				joueur.deplacerCarte(joueur.main.get(joueur.main.size() - 1), joueur.main, joueur.source);
				joueur.deplacerCarte(joueur.main.get(joueur.main.size() - 2), joueur.main, joueur.source);
			} else if (choix2 == 2) {
				joueur.deplacerCarte(joueur.main.get(joueur.main.size() - 1), joueur.main, joueur.source);
				joueur.deplacerCarte(joueur.main.get(joueur.main.size() - 3), joueur.main, joueur.source);
			} else if (choix2 == 3) {
				joueur.deplacerCarte(joueur.main.get(joueur.main.size() - 2), joueur.main, joueur.source);
				joueur.deplacerCarte(joueur.main.get(joueur.main.size() - 3), joueur.main, joueur.source);
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
				joueur.deplacerCarte(joueur.main.get(joueur.main.size() - 1), joueur.main, joueur.source);
			} else if (choix2 == "1 et 3") {
				joueur.deplacerCarte(joueur.main.get(joueur.main.size() - 2), joueur.main, joueur.source);
			} else if (choix2 == "2 et 3") {
				joueur.deplacerCarte(joueur.main.get(joueur.main.size() - 3), joueur.main, joueur.source);
			}
		}
	}
}
