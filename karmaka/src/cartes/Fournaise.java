package cartes;

import java.io.Serializable;

import jeu.Carte;
import jeu.Joueur;
import jeu.JoueurHumain;
import jeu.Partie;

public class Fournaise extends Carte implements Serializable {

	private static final long serialVersionUID = -6692743562136138444L;

	public Fournaise(Partie partie) {
		super(2, 0, 0, "Fournaise", "Défaussez les 2 premières cartes de la Vie Future d'un rival.", partie);
	}
	
	public void activerCapactite(Joueur joueur) {
		
		// Le joueur adverse défausse les 2 premières cartes de sa vie future
		Joueur joueurAdverse = determinerJoueurAdverse(joueur);
		switch (joueurAdverse.getVieFuture().size()) {
			case 0:
				if (joueur instanceof JoueurHumain) {
					console.afficher("Votre adversaire n'a aucune carte dans sa Vie Future...");
				}
				break;
			case 1:
				if (joueur instanceof JoueurHumain) {
					console.afficher("Votre adversaire défausse une première carte de sa Vie Future, mais il n'a plus aucune carte dans sa Vie Future...");
				}
				joueurAdverse.defausser(joueurAdverse.getVieFuture().getLast(), joueurAdverse.getVieFuture());
				break;
			default:
				if (joueur instanceof JoueurHumain) {
					console.afficher("Votre adversaire défausse les 2 premières cartes de sa Vie Future.");
				}
				for (int i=0;i<2;i++) {
					joueurAdverse.defausser(joueurAdverse.getVieFuture().getLast(), joueurAdverse.getVieFuture());
				}
				break;
		}
		
	}

}
