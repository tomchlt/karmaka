package fr.utt.karmaka;

import java.util.*;

public class Emplacement {

	private LinkedList<Carte> emplacement;


	public Emplacement() {
		emplacement = new LinkedList<Carte>();
	}

	public void deplacerCarteVers(Carte carte, Emplacement emplacement) {
		//méthode add à corriger
		emplacement.add(carte);
		}


	public int compterCartes() {
		return emplacement.size();
	}

	public static void main(String[] args) {

	}

}
