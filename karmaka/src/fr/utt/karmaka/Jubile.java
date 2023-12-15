package fr.utt.karmaka;

public class Jubile extends Carte {

	public Jubile(Partie partie) {
		super(0, 3, 0, "Jubilé", "Placez 2 cartes de votre Main sur vous Oeuvres", partie);
	}
	
	public void activerCapacite(Joueur joueur) {
		// A RAJOUTER : demander au joueur quelle carte de sa main il veut envoyer dans ses oeuvres
		joueur.deplacerCarte(carte, joueur.getMain(), joueur.getOeuvre());
		// A RAJOUTER : demander au joueur si il veut continuer avec une 2e carte
		if (veutContinuer) {
			// A RAJOUTER : demander au joueur quelle carte de sa main il veut envoyer dans ses oeuvres
			joueur.deplacerCarte(carte, joueur.getMain(), joueur.getOeuvre());
		}
	}

}
