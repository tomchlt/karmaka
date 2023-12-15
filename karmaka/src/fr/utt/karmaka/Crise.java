package fr.utt.karmaka;

public class Crise extends Carte{

	public Crise(Partie partie) {
		super(2, 0, 0, "Crise", "Le rival de votre choix défausse une de ses Oeuvres.", partie);
	}
	
	public void activerCapacite(Joueur joueur) {
		Joueur joueurAdverse = determinerJoueurAdverse(joueur);
		// A RAJOUTER : demander au joueur adverse quelle carte de ses oeuvres il préfère défausser
		joueurAdverse.defausser(carte, joueurAdverse.getOeuvre());
	}

}
