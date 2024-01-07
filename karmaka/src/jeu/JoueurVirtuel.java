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
			while (it.hasNext()) {
				Carte carte = it.next();
				int choix = 0;
				choix = strategie.garderCarte();
				if (choix == 1) {
					console.afficher("L'adversaire garde la carte " + carte.getNom() + " que vous lui avez transmis.");
					deplacerCarte(carte, tempo, main);
				} else {
					console.afficher("L'adversaire défausse la carte " + carte.getNom() + " que vous lui avez transmis.");
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
		// le joueur renait :
		// 1. le joueur défausse ses oeuvres
		int nbCartesADefausser = oeuvre.size();
		for (int i=0;i<nbCartesADefausser;i++) {
			defausser(oeuvre.getLast(), oeuvre);
		}
		// 2. le joueur récupère les cartes de sa vie future dans sa main
		int nbCartesADeplacer = vieFuture.size();
		for (int i=0;i<nbCartesADeplacer;i++) {
			deplacerCarte(vieFuture.getLast(), vieFuture, main);
		}
		// 3. le joueur crée sa nouvelle pile
		if (main.size()<6) {
			while ( (main.size() + pile.size()) < 6 ) {
				puiser();
			}
		}
	}

	public void jouer() {
		int choix = strategie.jouerCarte(this);
		Carte carteChoisie = null;
		switch (choix) {
			case 1:
				carteChoisie = strategie.choisirCarteOeuvre(this);
				console.afficher("Le joueur adverse place dans ses oeuvres la carte " + carteChoisie.getNom());
				console.afficher(carteChoisie.toString());
				deplacerCarte(carteChoisie, main, oeuvre);
				break;
			case 2:
				carteChoisie = strategie.choisirCarteDéfausser(this, this.getMain());
				console.afficher("Le joueur adverse joue pour ses pouvoirs la carte " + carteChoisie.getNom());
				console.afficher(carteChoisie.toString());
				Joueur joueurAdverse = carteChoisie.determinerJoueurAdverse(this);
				deplacerCarte(carteChoisie, main, joueurAdverse.getTempo());
				carteChoisie.activerCapacite(this);
				break;
			case 3:
				carteChoisie = strategie.choisirCarteVieFuture(this);
				console.afficher("Le joueur adverse place une carte dans sa Vie Future");
				deplacerCarte(carteChoisie, main, vieFuture);
				break;
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
