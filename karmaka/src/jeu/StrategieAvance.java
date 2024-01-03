package jeu;

import java.io.Serializable;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Random;

public class StrategieAvance extends Strategie implements Serializable {

	private static final long serialVersionUID = 7342181931506016916L;

	public StrategieAvance() {
		super("Avancée", "Une difficulté qui apporte un peu plus de challenge");
	}

	public static void main(String[] args) {

	}

	public Carte choisirCarte(JoueurVirtuel joueurV, int choix) {
		// on récupère les informations du joueur
		int[] ptsMain = compterPoints(joueurV.getMain());
		int[] ptsOeuvre = compterPoints(joueurV.getOeuvre());
		// On recalcule les points globaux
		int ptsBleus = ptsMain[0] + ptsOeuvre[0];
		int ptsRouges = ptsMain[1] + ptsOeuvre[1];
		int ptsVerts = ptsMain[2] + ptsOeuvre[2];
		Carte carteAJoueur = null;
		int couleurMax = 0;
		Carte carteATester1 = null;
		// si le bot à encore des points à mettre pour compléter son oeuvre, alors il va
		// choisir de joueur des carte pour ses oeuvres
		if (choix==1) {
			// on détermine la couleur avec les plus hauts points, celle pour laquelle le
			// bot va jouer pour ses oeuvres
			couleurMax = Math.max(Math.max(ptsBleus, ptsRouges), ptsVerts);
			// on parcourt chaque carte de sa main
			LinkedList<Carte> cartesAChoisir = new LinkedList<>();
			Iterator<Carte> itChoisir = cartesAChoisir.iterator();
			Iterator<Carte> itMain = joueurV.getMain().iterator();
			while (itMain.hasNext()) {
				carteATester1 = itMain.next();
				// si la carte a des points de la bonne couleur on la met dans une seconde liste
				if (carteATester1.getPoints() * couleurMax != 0) {
					cartesAChoisir.add(carteATester1);
				}
			}
			// on parcourt ensuite cette seconde liste pour déterminer celle qui à le plus
			// grand nombre de points
			int carteValeurTest = 0;
			Carte carteTest2 = null;
			while (itChoisir.hasNext()) {
				carteTest2 = itChoisir.next();
				// on isole celle qui vaut le plus de points de la bonne couleur
				if (carteTest2.getPoints() > carteValeurTest) {
					carteValeurTest = carteTest2.getPoints();
					carteAJoueur = carteTest2;
				}
			}
			cartesAChoisir.clear();
			// on peut rajouter une méthode pour qu'il protège ses oeuvre ou qu'il joue une
			// certaine couleur en vie future
		} else {
			Random random = new Random();
			carteAJoueur = joueurV.getMain().get(random.nextInt(joueurV.getMain().size()));
		}
		return carteAJoueur;
	}

	public int jouerCarte(JoueurVirtuel joueurV) {
		int ptsKarmiquesNecessaires = joueurV.getNiveauKarmique().getPointsRequis();
		int[] ptsMain = compterPoints(joueurV.getMain());
		int[] ptsOeuvre = compterPoints(joueurV.getOeuvre());
		int choix = 0;
		// si le bot à encore des points à mettre pour atteindre le niveau supérieur
		// alors il va joueur ses cartes pour les oeuvres
		if (ptsOeuvre[0] < ptsKarmiquesNecessaires || ptsOeuvre[1] < ptsKarmiquesNecessaires
				|| ptsOeuvre[2] < ptsKarmiquesNecessaires) {
			choix = 1;
		} else {
			Random random = new Random();
			choix = random.nextInt(1) + 2;
		}
		return choix;
	}

	public int garderCarte() {
		Random random = new Random();
		int choixGarderCarte = random.nextInt(2) + 1;
		return choixGarderCarte;
	}

	public int passerTour() {
		return 2;
	}

	public int[] compterPoints(LinkedList<Carte> liste) {
		Iterator<Carte> itListe = liste.iterator();
		Carte objListe = null;
		int ptsBleusListe = 0;
		int ptsRougesListe = 0;
		int ptsVertsListe = 0;
		while (itListe.hasNext()) {
			objListe = itListe.next();
			ptsBleusListe = ptsBleusListe + objListe.getPointsBleus();
			ptsRougesListe = ptsRougesListe + objListe.getPointsRouges();
			ptsVertsListe = ptsVertsListe + objListe.getPointsVerts();
		}
		return new int[] { ptsBleusListe, ptsRougesListe, ptsVertsListe };
	}

}
