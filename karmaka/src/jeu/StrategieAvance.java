package jeu;

import java.io.Serializable;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Random;

public class StrategieAvance extends Strategie implements Serializable {

	private static final long serialVersionUID = 7342181931506016916L;
	private boolean oeuvreProtegee;

	public StrategieAvance() {
		super("Avancée", "Une difficulté qui apporte un peu plus de challenge");
		this.oeuvreProtegee = false;
	}

	public static void main(String[] args) {

	}

	public Carte choisirCarteOeuvre(JoueurVirtuel joueurV) {
		LinkedList<Carte> cartesAChoisir = trouverCartesCouleurMaxOeuvre(joueurV);
		Carte carteAJouer = trouverCartePtsMax(cartesAChoisir);
		return carteAJouer;
	}

	public Carte choisirCarteVieFuture(JoueurVirtuel joueurV) {
		LinkedList<Carte> cartesAChoisir = trouverCartesCouleurMoyenneEmplacement(joueurV, joueurV.getMain());
		Carte carteAJouer = trouverCartePtsMax(cartesAChoisir);
		return carteAJouer;
	}

	public Carte choisirCarteDéfausser(JoueurVirtuel joueurV) {
		LinkedList<Carte> cartesAChoisir = trouverCartesCouleurMinEmplacement(joueurV, joueurV.getMain());
		Carte carteAJouer = trouverCartePtsMin(cartesAChoisir);
		return carteAJouer;
	}

	public int jouerCarte(JoueurVirtuel joueurV) {
		int choix = 0;
		int ptsKarmiquesNecessaires = joueurV.getNiveauKarmique().getPointsRequis();
		int[] ptsOeuvre = compterPoints(joueurV.getOeuvre());
		LinkedList<Carte> listeCouleurMaxOeuvre = trouverCartesCouleurMaxOeuvre(joueurV);
		if (listeCouleurMaxOeuvre.isEmpty() == false) {
			// si le bot à encore des points à mettre pour atteindre le niveau supérieur
			// alors il va joueur ses cartes pour les oeuvres
			if (ptsOeuvre[0] < ptsKarmiquesNecessaires || ptsOeuvre[1] < ptsKarmiquesNecessaires
					|| ptsOeuvre[2] < ptsKarmiquesNecessaires) {
				choix = 1;
			} else if (isOeuvreProtegee() == false) {
				choix = 1;
				setOeuvreProtegee(true);
			}
		} else if (joueurV.getVieFuture().size() < 4) {
			choix = 3;
		} else {
			choix = 2;
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

	public LinkedList<Carte> trouverCartesCouleurMaxOeuvre(JoueurVirtuel joueurV) {
		// on récupère les informations du joueur
		int[] ptsMain = compterPoints(joueurV.getMain());
		int[] ptsOeuvre = compterPoints(joueurV.getOeuvre());
		// On recalcule les points globaux
		int ptsBleus = ptsMain[0] + ptsOeuvre[0];
		int ptsRouges = ptsMain[1] + ptsOeuvre[1];
		int ptsVerts = ptsMain[2] + ptsOeuvre[2];
		int couleurMax = 0;
		Carte carteATester1 = null;
		// on détermine la couleur avec les plus hauts points, celle pour laquelle le
		// bot va jouer pour ses oeuvres
		couleurMax = Math.max(Math.max(ptsBleus, ptsRouges), ptsVerts);
		// on parcourt chaque carte de sa main
		LinkedList<Carte> cartesAChoisir = new LinkedList<>();
		Iterator<Carte> itMain = joueurV.getMain().iterator();
		while (itMain.hasNext()) {
			carteATester1 = itMain.next();
			// si la carte a des points de la bonne couleur on la met dans une seconde liste
			if (couleurMax == ptsBleus) {
				if (carteATester1.getPointsBleus() != 0) {
					cartesAChoisir.add(carteATester1);
				}
			}
			if (couleurMax == ptsRouges) {
				if (carteATester1.getPointsRouges() != 0) {
					cartesAChoisir.add(carteATester1);
				}
			}
			if (couleurMax == ptsVerts) {
				if (carteATester1.getPointsVerts() != 0) {
					cartesAChoisir.add(carteATester1);
				}
			}
		}
		return cartesAChoisir;
	}

	public LinkedList<Carte> trouverCartesCouleurMaxEmplacement(JoueurVirtuel joueurV, LinkedList<Carte> emplacement) {
		// on récupère les informations du joueur
		int[] pts = compterPoints(emplacement);
		int couleurMax = 0;
		Carte carteATester1 = null;
		// on détermine la couleur avec les plus hauts points, celle pour laquelle le
		// bot va jouer pour ses oeuvres
		couleurMax = Math.max(Math.max(pts[0], pts[1]), pts[2]);
		// on parcourt chaque carte de sa main
		LinkedList<Carte> cartesAChoisir = new LinkedList<>();
		Iterator<Carte> itMain = joueurV.getMain().iterator();
		while (itMain.hasNext()) {
			carteATester1 = itMain.next();
			// si la carte a des points de la bonne couleur on la met dans une seconde liste
			if (couleurMax == pts[0]) {
				if (carteATester1.getPointsBleus() != 0) {
					cartesAChoisir.add(carteATester1);
				}
			}
			if (couleurMax == pts[1]) {
				if (carteATester1.getPointsRouges() != 0) {
					cartesAChoisir.add(carteATester1);
				}
			}
			if (couleurMax == pts[2]) {
				if (carteATester1.getPointsVerts() != 0) {
					cartesAChoisir.add(carteATester1);
				}
			}
		}
		return cartesAChoisir;
	}

	public LinkedList<Carte> trouverCartesCouleurMinEmplacement(JoueurVirtuel joueurV, LinkedList<Carte> emplacement) {
		// on récupère les informations du joueur
		int[] pts = compterPoints(emplacement);
		int couleurMin = 0;
		Carte carteATester1 = null;
		// on détermine la couleur avec les plus hauts points, celle pour laquelle le
		// bot va jouer pour ses oeuvres
		couleurMin = Math.min(Math.min(pts[0], pts[1]), pts[2]);
		// on parcourt chaque carte de sa main
		LinkedList<Carte> cartesAChoisir = new LinkedList<>();
		Iterator<Carte> itMain = joueurV.getMain().iterator();
		while (itMain.hasNext()) {
			carteATester1 = itMain.next();
			// si la carte a des points de la bonne couleur on la met dans une seconde liste
			if (couleurMin == pts[0]) {
				if (carteATester1.getPointsBleus() != 0) {
					cartesAChoisir.add(carteATester1);
				}
			}
			if (couleurMin == pts[1]) {
				if (carteATester1.getPointsRouges() != 0) {
					cartesAChoisir.add(carteATester1);
				}
			}
			if (couleurMin == pts[2]) {
				if (carteATester1.getPointsVerts() != 0) {
					cartesAChoisir.add(carteATester1);
				}
			}
		}
		return cartesAChoisir;
	}

	public LinkedList<Carte> trouverCartesCouleurMoyenneEmplacement(JoueurVirtuel joueurV,
			LinkedList<Carte> emplacement) {
		// on récupère les informations du joueur
		int[] pts = compterPoints(emplacement);
		int couleurMax = 0;
		int couleurMin = -1;
		int couleurVieFuture = -1;
		Carte carteATester1 = null;
		// on détermine la couleur avec les plus hauts points, et celle avec les plus
		// bas points
		couleurMax = Math.max(Math.max(pts[0], pts[1]), pts[2]);
		couleurMin = Math.min(Math.min(pts[0], pts[1]), pts[2]);
		// si le joueur n'a seulement qu'une seule couleur dans sa main, alors ce sera
		// sa
		// couleur max, la seule couleur qu'il peut jouer
		if ((pts[0] == 0 && pts[1] == 0) || (pts[0] == 0 && pts[2] == 0) || (pts[1] == 0 && pts[2] == 0)) {
			couleurVieFuture = couleurMax;
		} else {
			// sinon ce sera la couleur qui n'est ni maximale ni minimale
			for (int i = 1; i <= 3; i++) {
				if (pts[i] != couleurMax && pts[i] != couleurMin) {
					couleurVieFuture = pts[i];
				}
			}
		}
		// on parcourt chaque carte de sa main
		LinkedList<Carte> cartesAChoisir = new LinkedList<>();
		Iterator<Carte> itMain = joueurV.getMain().iterator();
		while (itMain.hasNext()) {
			carteATester1 = itMain.next();
			// si la carte a des points de la bonne couleur on la met dans une seconde liste
			if (couleurVieFuture == pts[0]) {
				if (carteATester1.getPointsBleus() != 0) {
					cartesAChoisir.add(carteATester1);
				}
			}
			if (couleurVieFuture == pts[1]) {
				if (carteATester1.getPointsRouges() != 0) {
					cartesAChoisir.add(carteATester1);
				}
			}
			if (couleurVieFuture == pts[2]) {
				if (carteATester1.getPointsVerts() != 0) {
					cartesAChoisir.add(carteATester1);
				}
			}
		}
		return cartesAChoisir;
	}

	public Carte trouverCartePtsMax(LinkedList<Carte> cartesAChoisir) {
		return super.trouverCartePtsMax(cartesAChoisir);
	}

	public Carte trouverCartePtsMin(LinkedList<Carte> cartesAChoisir) {
		return super.trouverCartePtsMin(cartesAChoisir);
	}

	public boolean isOeuvreProtegee() {
		return oeuvreProtegee;
	}

	public void setOeuvreProtegee(boolean oeuvreProtegee) {
		this.oeuvreProtegee = oeuvreProtegee;
	}

	public Carte selectionnerCarte(LinkedList<Carte> cartesAChoisir) {
		return super.trouverCartePtsMax(cartesAChoisir);
	}
}
