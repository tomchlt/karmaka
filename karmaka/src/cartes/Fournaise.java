package cartes;

import java.io.Serializable;

import karmaka.Carte;
import karmaka.Joueur;
import karmaka.Partie;

public class Fournaise extends Carte implements Serializable {

	private static final long serialVersionUID = -6692743562136138444L;

	public Fournaise(Partie partie) {
		super(2, 0, 0, "Fournaise", "Défaussez les 2 premières cartes de la Vie Future d'un rival.", partie);
	}
	
	public void activerCapactite(Joueur joueur) {
		
		// Le joueur adverse défausse les 2 premières cartes de sa vie future
		Joueur joueurAdverse = determinerJoueurAdverse(joueur);
		for (int i=0;i<2;i++) {
			joueurAdverse.defausser(joueurAdverse.getVieFuture().getLast(), joueurAdverse.getVieFuture());
		}
		
	}

}
