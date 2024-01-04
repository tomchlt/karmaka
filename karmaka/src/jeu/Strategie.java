package jeu;

import java.io.Serializable;
import java.util.Iterator;
import java.util.LinkedList;

public abstract class Strategie implements Serializable {

	private static final long serialVersionUID = 1388578098956615836L;

	public String getNomStrategie() {
		return nomStrategie;
	}

	public void setNomStrategie(String nomStrategie) {
		this.nomStrategie = nomStrategie;
	}

	public String getDescriptionStrategie() {
		return descriptionStrategie;
	}

	public void setDescriptionStrategie(String descriptionStrategie) {
		this.descriptionStrategie = descriptionStrategie;
	}

	private String nomStrategie;
	private String descriptionStrategie;

	public Strategie(String nomStrategie, String descriptionStrategie) {
		this.nomStrategie = nomStrategie;
		this.descriptionStrategie = descriptionStrategie;
	}

	public static void main(String[] args) {

	}

	public abstract Carte choisirCarteOeuvre(JoueurVirtuel joueurV);

	public abstract Carte choisirCarteVieFuture(JoueurVirtuel joueurV);

	public abstract Carte choisirCarteDéfausser(JoueurVirtuel joueurV);

	public abstract int jouerCarte(JoueurVirtuel joueurV);

	public abstract int garderCarte();

	public abstract int passerTour();

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
}
