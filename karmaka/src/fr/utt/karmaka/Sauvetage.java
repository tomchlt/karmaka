package fr.utt.karmaka;

import java.io.Serializable;

public class Sauvetage extends Carte implements Serializable {
	
	private static final long serialVersionUID = -3683141951588126118L;

	public Sauvetage(Partie partie) {
		super(0, 2, 0, "Sauvetage", "Ajoutez à votre Main une des 3 dernières cartes de la Fosse.", partie);
	}

	public void activerCapactite(Joueur joueur) {
		StringBuffer sb = new StringBuffer();
		sb.append(joueur.fosse.get(joueur.fosse.size() - 1));
		sb.append(joueur.fosse.get(joueur.fosse.size() - 2));
		sb.append(joueur.fosse.get(joueur.fosse.size() - 3));
		System.out.println(sb);
		// A RAJOUTER : demander au joueur quelle carte de la fosse il veut déplacer dans sa main
		joueur.deplacerCarte(carte, joueur.getFosse(), joueur.getMain());
	}
}
