package fr.utt.karmaka;

public class Voyage extends Carte {

	public Voyage(Partie partie) {
		super(0, 3, 0, "Voyage", "Puisez 3 cartes à la Source. Vous pouvez ensuite jouer une autre carte.", partie);
	}
	
	public void activerCapacite(Joueur joueur) {
		for (int i=0;i<3;i++) {
			joueur.puiser(joueur.main);
		}
		// A RAJOUTER : demander au joueur quelle carte il veut maintenant jouer
		carte.activerCapacite();
	}

}
