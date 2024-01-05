package cartes;

import java.io.Serializable;

import java.util.Random;

import jeu.Carte;
import jeu.Joueur;
import jeu.JoueurHumain;
import jeu.JoueurVirtuel;
import jeu.Partie;

public class Bassesse extends Carte implements Serializable {

	private static final long serialVersionUID = 9170075839040553100L;

	public Bassesse(Partie partie) {
		super(3, 0, 0, "Bassesse", "Défaussez au hasard 2 cartes de la Main d'un rival.", partie);
	}
	
	public void activerCapacite(Joueur joueur) {
		
		// Le joueur adverse défausse 2 cartes au hasard de sa main
		Joueur joueurAdverse = determinerJoueurAdverse(joueur);
		switch (joueurAdverse.getMain().size()) {
			case 0:
				if (joueur instanceof JoueurHumain) {
					console.afficher("Votre adversaire n'a aucune carte dans sa Main...");
				}
				if (joueur instanceof JoueurVirtuel) {
					console.afficher("Votre adversaire ne peut pas vous faire défausser de carte de votre Main, vous n'en avez pas");
				}
				break;
			case 1:
				if (joueur instanceof JoueurHumain) {
					console.afficher("Votre adversaire défausse une première carte de sa Main, mais il n'a plus aucune carte dans sa Main...");
				}
				if (joueur instanceof JoueurVirtuel) {
					console.afficher("Votre adversaire vous fait défausser la seule carte de votre Main");
				}
				joueurAdverse.defausser(joueurAdverse.getVieFuture().getLast(), joueurAdverse.getVieFuture());
				break;
			default:
				if (joueur instanceof JoueurHumain) {
					console.afficher("Votre adversaire défausse au hasard 2 cartes de sa Main.");
				}
				if (joueur instanceof JoueurVirtuel) {
					console.afficher("Votre adversaire vous fait défausser 2 cartes au hasard de votre Main");
				}
				Random rand = new Random();
				int randInt = rand.nextInt(joueurAdverse.getMain().size());
				Carte randCarte = joueurAdverse.getMain().get(randInt);
				for (int i=0;i<2;i++) {
					joueurAdverse.defausser(randCarte, joueurAdverse.getVieFuture());
				}
				break;
		}
		
	}
	
}
