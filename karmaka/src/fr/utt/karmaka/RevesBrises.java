package fr.utt.karmaka;

public class RevesBrises extends Carte {
	public RevesBrises(Partie partie) {
		super(0, 0, 2, "Rêves Brisés", "Placez la première carte de la Vie Future d'un rival sur la vôtre", partie);
	}

	public void activerCapacite(Joueur joueur) {
		Joueur joueurAdverse = determinerJoueurAdverse(joueur);
		joueur.deplacerCarte(joueurAdverse.getVieFuture().getLast(), joueurAdverse.getVieFuture(),
				joueur.getVieFuture());
	}
}
