package fr.utt.karmaka;

public class Fournaise extends Carte {

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
