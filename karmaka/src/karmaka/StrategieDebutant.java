package karmaka;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.Random;

public class StrategieDebutant extends Strategie implements Serializable {

	private static final long serialVersionUID = 2999330974130263138L;

	public StrategieDebutant() {
		super("Débutant", "Une difficulté adaptée pour commencer le jeu");
	}

	public int choisirCarte(JoueurVirtuel joueurV) {
		Random random = new Random();
		int indiceCarteAleatoire = random.nextInt(joueurV.getMain().size());
		return indiceCarteAleatoire;
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

	public static void main(String[] args) {

	}
}
