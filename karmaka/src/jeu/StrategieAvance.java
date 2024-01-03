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

	public int choisirCarte(JoueurVirtuel joueurV) {
		int ptsKarmiquesNecessaires = joueurV.getNiveauKarmique().getPointsRequis();
		int[] ptsMain = compterPoints(joueurV.getMain());
		int[] ptsOeuvre = compterPoints(joueurV.getOeuvre());
		int ptsBleus = ptsMain[0] + ptsOeuvre[0];
		int ptsRouges = ptsMain[1] + ptsOeuvre[1];
		int ptsVerts = ptsMain[2] + ptsOeuvre[2];
		int indice = 0;
		int couleurMax = 0;
		Carte carteATester1 = null;
		if (ptsOeuvre[0] < ptsKarmiquesNecessaires || ptsOeuvre[1] < ptsKarmiquesNecessaires
				|| ptsOeuvre[2] < ptsKarmiquesNecessaires) {
			couleurMax = Math.max(Math.max(ptsBleus, ptsRouges), ptsVerts);
			LinkedList<Carte> cartesAChoisir = new LinkedList<>();
			Iterator<Carte> itChoisir = cartesAChoisir.iterator();
			Iterator<Carte> itMain = joueurV.getMain().iterator();
			while (itMain.hasNext()) {
				carteATester1 = itMain.next();
				if (carteATester1.getPoints() * couleurMax != 0) {
					cartesAChoisir.add(carteATester1);
				}
			}
			int carteValeurTest = 0;
			Carte carteTest2 = null;
			String carteTestNom = "";
			while (itChoisir.hasNext()) {
				carteTest2 = itChoisir.next();
				if (carteTest2.getPoints() > carteValeurTest) {
					carteValeurTest = carteTest2.getPoints();
					carteTestNom = carteTest2.getNom();
				}
			}
			for (int i = 0; i < joueurV.getMain().size(); i++) {
				if (joueurV.getMain().get(i).getNom() == carteTestNom) {
					indice = i;
				}
			}
			//on peut rajouter une méthode pour qu'il protège ses oeuvre ou qu'il joue une certaine couleur en vie future
		}
		else {
			Random random = new Random();
			indice = random.nextInt(joueurV.getMain().size());
		}
		return indice;
	}

	public int jouerCarte(JoueurVirtuel joueurV) {
		int ptsKarmiquesNecessaires = joueurV.getNiveauKarmique().getPointsRequis();
		int[] ptsMain = compterPoints(joueurV.getMain());
		int[] ptsOeuvre = compterPoints(joueurV.getOeuvre());
		if (ptsOeuvre[0] < ptsKarmiquesNecessaires || ptsOeuvre[1] < ptsKarmiquesNecessaires
				|| ptsOeuvre[2] < ptsKarmiquesNecessaires) {
			
		}
		return 0;
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
