package jeu;

import java.io.Serializable;
import java.util.*;

public class JoueurVirtuel extends Joueur implements Serializable {

	private static final long serialVersionUID = 7136102809375697698L;

	private Strategie strategie;

	public JoueurVirtuel(Strategie strategie, LinkedList<Carte> source, LinkedList<Carte> fosse) {
		super("Joueur 2", source, fosse);
		this.strategie = strategie;
	}

	public void tour() {

		// on vérifie si l'adversaire a transmis une carte au joueur
		if (tempo.size() > 0) {
			Iterator<Carte> it = tempo.iterator();
			int numCarte = 1;
			while (it.hasNext()) {
				Carte carte = it.next();
				int choix = 0;
				choix = strategie.garderCarte();
				if (choix == 1) {
					deplacerCarte(carte, tempo, main);
				} else {
					defausser(carte, tempo);
				}
			}
		}

		// on vérifie si le joueur doit se réincarner
		if (pile.size() == 0 && main.size() == 0) {
			seReincarner();
		}

		// si le joueur ne se réincarne pas, il joue
		else {

			// si la pile du joueur n'est pas vide, il y pioche une carte
			if (pile.size() > 0) {
				piocher();
			} else {
				System.out.println("l'adversaire ne peut pas piocher. (sa pile est vide)");
			}

			// si le joueur peut passer, on lui demande s'il veut passer
			int choix = 0;
			if (pile.size() > 0) {
				choix = strategie.passerTour();
			}

			// si le joueur ne peut pas passer ou qu'il ne veut pas passer, il joue une
			// carte
			if (pile.size() == 0 || choix == 2) {
				jouer();
			}

		}

	}

	public void seReincarner() {
		console.afficher("Le joueur adverse tente de se réincarner...");
		int pointsMax;
		pointsMax = Math.max(calculerPointsRouges(), calculerPointsVerts());
		pointsMax = Math.max(pointsMax, calculerPointsBleus());
		if (pointsMax + nbAnneauxKarmiques >= niveauKarmique.getPointsRequis()) {
			if (pointsMax < niveauKarmique.getPointsRequis()) {
				nbAnneauxKarmiques -= (niveauKarmique.getPointsRequis() - pointsMax);
			}
			switch (niveauKarmique) {
			case BOUSIER:
				niveauKarmique = NiveauKarmique.SERPENT;
				console.afficher("Il se réincarne en " + niveauKarmique.getNomNiveau() + " !");
				break;
			case SERPENT:
				niveauKarmique = NiveauKarmique.LOUP;
				console.afficher("Il se réincarne en " + niveauKarmique.getNomNiveau() + " !");
				break;
			case LOUP:
				niveauKarmique = NiveauKarmique.SINGE;
				console.afficher("Il se réincarne en " + niveauKarmique.getNomNiveau() + " !");
				break;
			case SINGE:
				aGagne = true;
				console.afficher("Il accéde à la Transcendance !");
				break;
			default:
				console.afficher("Erreur dans l'augmentation du niveau karmique");
				break;
			}
		} else {
			nbAnneauxKarmiques += 1;
			console.afficher("Sa réincarnation a échoué, mais il reçoit 1 anneau karmique.");
		}
	}

	public void jouer() {
		int choix = strategie.jouerCarte(this);
		Carte carteChoisie = null;
		if (choix == 1) {
			carteChoisie = strategie.choisirCarteOeuvre(this);
			deplacerCarte(carteChoisie, main, oeuvre);
			console.afficher("Le joueur adverse place dans ses oeuvres la carte ");
			console.afficher(carteChoisie.toString());
		} else if (choix == 2) {
			carteChoisie = strategie.choisirCarteDéfausser(this);
			carteChoisie.activerCapacite(this);
			console.afficher("Le joueur adverse joue pour ses pouvoirs la carte ");
			console.afficher(carteChoisie.toString());
		} else if (choix == 3) {
			carteChoisie = strategie.choisirCarteVieFuture(this);
			deplacerCarte(carteChoisie, main, vieFuture);
			console.afficher("Le joueur adverse place une carte dans sa Vie Future");
		}
	}

	public Strategie getStrategie() {
		return strategie;
	}

	public void setStrategie(Strategie strategie) {
		this.strategie = strategie;
	}

	public static void main(String[] args) {

	}
	
}
