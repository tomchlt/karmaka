package fr.utt.karmaka;

public class Roulette extends Carte {

	public Roulette(Partie partie) {
		super(2, 0, 0, "Roulette", "Défaussez jusqu'à 2 cartes de votre Main. Vous pouvez ensuite puiser à la Source autant de cartes + 1.", partie);
	}
	
	public void activerCapacite(Joueur joueur) {
		// A RAJOUTER : demander au joueur quelle carte de sa main il veut défausser
		joueur.defausser(carte, joueur.getMain());
		// A RAJOUTER : demander au joueur si il veut continuer avec une 2e carte
		if (veutContinuer) {
			// A RAJOUTER : demander au joueur quelle carte de sa main il veut défausser
			joueur.defausser(carte, joueur.getMain());
		}
	}

}
