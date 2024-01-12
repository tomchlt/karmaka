package cartes;

import java.io.Serializable;
import java.util.Random;

import jeu.Carte;
import jeu.Joueur;
import jeu.JoueurHumain;
import jeu.JoueurVirtuel;
import jeu.Partie;

public class Incarnation extends Carte implements Serializable {

	private static final long serialVersionUID = -6140027867046876427L;

	public Incarnation(Partie partie) {
		super(1, 1, 1, "Incarnation", "Choisissez une de vos Oeuvres. Copiez son pouvoir.", partie);
	}
	
	public void activerCapacite(Joueur joueur) {
		
		// Le joueur choisit une carte de ses oeuvres dont il veut copier le pouvoir
		if (joueur.getOeuvre().isEmpty()==false) {
			int choixCarte = -1;
			Carte carteChoisie =  null;
			if (joueur instanceof JoueurHumain) {
				console.afficher("Cartes dans vos Oeuvres :");
				((JoueurHumain) joueur).afficherCartes(joueur.getOeuvre());
				while (choixCarte<0 || choixCarte>=joueur.getOeuvre().size()) {
					console.afficher("De quelle carte voulez-vous copier le pouvoir ? (Entrez le numéro de cette carte)");
					choixCarte = console.lireInt();
				}
				carteChoisie = joueur.getOeuvre().get(choixCarte);
			} else if (joueur instanceof JoueurVirtuel) {
				console.afficher("Le Joueur Virtuel adverse choisit une de ses oeuvre et en copie le pouvoir");
				Random random = new Random();
				carteChoisie = joueur.getMain().get(random.nextInt(joueur.getMain().size()));
			}
			carteChoisie.activerCapacite(joueur);
		} else {
			if (joueur instanceof JoueurHumain) {
				console.afficher("Vous n'avez aucune carte dans vos Oeuvres...");
			}
			if (joueur instanceof JoueurVirtuel) {
				console.afficher("Le Joueur Virtuel adverse n'a pas de carte dans ses Oeuvre, il ne peut donc pas copier de pouvoir");
			}
		}
		
	}

}
