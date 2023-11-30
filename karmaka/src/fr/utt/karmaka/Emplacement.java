package fr.utt.karmaka;

import java.util.*;

public class Emplacement {

	private LinkedList<Carte> listeCartes;


	public Emplacement() {
		listeCartes = new LinkedList<Carte>();
	}

	public void deplacerCarteVers(Carte carte, LinkedList<Carte> listeCartes) {
		//méthode add à corriger
		listeCartes.add(carte);
		}


	public int compterCartes() {
		return listeCartes.size();
	}

	public static void main(String[] args) {
		
	}

}
