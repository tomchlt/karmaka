package fr.utt.karmaka;

public class DernierSouffle extends Carte {

	public DernierSouffle(Partie partie) {
		super(1, 0, 0, "Dernier Souffle", "Le joueur de votre choix défausse une carte de sa Main.", partie);
	}
	
	public void activerCapacite(Joueur joueur) {
		Joueur joueurAdverse = determinerJoueurAdverse(joueur);
		// A RAJOUTER : demander au joueur adverse quelle carte de sa main il préfère défausser
		joueurAdverse.defausser(carte, joueurAdverse.getMain());
	}

}
