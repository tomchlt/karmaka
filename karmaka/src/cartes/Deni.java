package cartes;

import java.io.Serializable;

import jeu.Carte;
import jeu.Joueur;
import jeu.JoueurHumain;
import jeu.JoueurVirtuel;
import jeu.Partie;

public class Deni extends Carte implements Serializable {

	private static final long serialVersionUID = 666075134350208299L;

	public Deni(Partie partie) {
		super(0, 0, 2, "Déni", "Défaussez une carte de votre Main. Copiez le pouvoir de cette carte", partie);
	}

	public void activerCapactite(Joueur joueur) {
		//on vérifie si le joueur a bien des cartes dans sa main
		if (joueur.getMain().isEmpty() == false) {
			int choixCarte = -1;
			Carte carteChoisie = null;
			//si le joueur est un humain, on lui demande quelle carte il veut copier parmi celles de sa main
			if (joueur instanceof JoueurHumain) {
				console.afficher("Cartes dans votre Main :");
				((JoueurHumain) joueur).afficherCartes(joueur.getMain());
				while (choixCarte < 0 || choixCarte >= joueur.getMain().size()) {
					console.afficher(
							"Quelle carte voulez-vous défausser mais en copier le pouvoir ? (Entrez le numéro de la carte)");
					choixCarte = console.lireInt();
				}
				carteChoisie = joueur.getMain().get(choixCarte);
				//si le joueur est virtuel on lui demande quelle carte il veut défausser
			} else if (joueur instanceof JoueurVirtuel) {
				carteChoisie = ((JoueurVirtuel) joueur).getStrategie().choisirCarteDéfausser((JoueurVirtuel) joueur);
				console.afficher("Le Joueur virtuel défausse mais copie le pouvoir de la carte");
				console.afficher(carteChoisie.toString());
			}
			//on active la capacité de la carte sélectionner avant de la défausser
			carteChoisie.activerCapacite(joueur);
			joueur.defausser(carteChoisie, joueur.getMain());
		} else {
			//sinon message d'erreur à l'intention de l'humain
			if (joueur instanceof JoueurHumain) {
				console.afficher("Votre Main est vide, vous n'avez pas de pouvoir à copier");
			}
		}
	}
}
