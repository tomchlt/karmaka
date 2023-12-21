package cartes;

import java.io.Serializable;

import karmaka.Carte;
import karmaka.Joueur;
import karmaka.Partie;

public class Recyclage extends Carte implements Serializable {
	
	private static final long serialVersionUID = 6644982015570858271L;

	public Recyclage(Partie partie) {
		super(0, 1, 0, "Recyclage", "Ajoutez à votre Vie Future une des 3 dernières cartes de la Fosse.", partie);
	}

	public void activerCapactite(Joueur joueur) {
		StringBuffer sb = new StringBuffer();
		sb.append(joueur.fosse.get(joueur.fosse.size() - 1));
		sb.append(joueur.fosse.get(joueur.fosse.size() - 2));
		sb.append(joueur.fosse.get(joueur.fosse.size() - 3));
		System.out.println(sb);
		// A RAJOUTER : demander au joueur quelle carte de la fosse il veut déplacer
		// dans sa main
		joueur.deplacerCarte(carte, joueur.fosse, joueur.vieFuture);
	}
}
