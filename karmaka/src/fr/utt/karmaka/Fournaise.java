package fr.utt.karmaka;

import java.io.Serializable;

public class Fournaise extends Carte implements Serializable {

	private static final long serialVersionUID = -6692743562136138444L;

	public Fournaise(Partie partie) {
		super(2, 0, 0, "Fournaise", "Défaussez les 2 premières cartes de la Vie Future d'un rival.", partie);
	}
	
	public void activerCapactite(Joueur joueur) {
		Joueur joueurAdverse = determinerJoueurAdverse(joueur);
		for (int i=0;i<2;i++) {
			joueurAdverse.defausser(joueurAdverse.getVieFuture().getFirst(), joueurAdverse.getVieFuture());
		}
	}

}
