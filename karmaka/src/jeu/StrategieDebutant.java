package jeu;

import java.io.Serializable;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Random;

public class StrategieDebutant implements Strategie, Serializable {

	private static final long serialVersionUID = 2999330974130263138L;

	public StrategieDebutant() {
		
	}

	public Carte choisirCarteOeuvre(JoueurVirtuel joueurV) {
		Random random = new Random();
		Carte CarteAleatoire = joueurV.getMain().get(random.nextInt(joueurV.getMain().size()));
		return CarteAleatoire;
	}
	
	public Carte choisirCarteVieFuture(JoueurVirtuel joueurV) {
		Random random = new Random();
		Carte CarteAleatoire = joueurV.getMain().get(random.nextInt(joueurV.getMain().size()));
		return CarteAleatoire;
	}

	public Carte choisirCarteDefausser(JoueurVirtuel joueurV, LinkedList<Carte> emplacement) {
		Random random = new Random();
		Carte CarteAleatoire = joueurV.getMain().get(random.nextInt(joueurV.getMain().size()));
		return CarteAleatoire;
	}

	public Carte selectionnerCarte(LinkedList<Carte> cartesAChoisir) {
		Random random = new Random();
		Carte CarteAleatoire = cartesAChoisir.get(random.nextInt(cartesAChoisir.size()));
		return CarteAleatoire;
	}

	public int jouerCarte(JoueurVirtuel joueurV) {
		Random random = new Random();
		int choixJouerCarte = random.nextInt(3) + 1;
		return choixJouerCarte;
	}

	public int garderCarte() {
		Random random = new Random();
		int choixGarderCarte = random.nextInt(2) + 1;
		return choixGarderCarte;
	}

	public int passerTour() {
		return 2;
	}

	public Carte trouverCartePtsMax(LinkedList<Carte> cartesAChoisir) {
		Carte carteAJouer = null;
		Iterator<Carte> itChoisir = cartesAChoisir.iterator();

		// on parcourt cette liste pour déterminer celle qui à le plus
		// grand nombre de points
		int carteValeurTest = 0;
		Carte carteTest2 = null;
		while (itChoisir.hasNext()) {
			carteTest2 = itChoisir.next();
			// on isole celle qui vaut le plus de points de la bonne couleur
			if (carteTest2.getPoints() > carteValeurTest) {
				carteValeurTest = carteTest2.getPoints();
				carteAJouer = carteTest2;
			}
		}
		return carteAJouer;
	}

	public Carte trouverCartePtsMin(LinkedList<Carte> cartesAChoisir) {
		Carte carteAJouer = null;
		Iterator<Carte> itChoisir = cartesAChoisir.iterator();

		// on parcourt cette liste pour déterminer celle qui à le plus
		// grand nombre de points
		int carteValeurTest = 5;
		Carte carteTest2 = null;
		while (itChoisir.hasNext()) {
			carteTest2 = itChoisir.next();
			// on isole celle qui vaut le plus de points de la bonne couleur
			if (carteTest2.getPoints() < carteValeurTest) {
				carteValeurTest = carteTest2.getPoints();
				carteAJouer = carteTest2;
			}
		}
		return carteAJouer;
	}

	public static void main(String[] args) {

	}
	
}
