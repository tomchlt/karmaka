package fr.utt.karmaka;

import java.util.*;

public class Emplacement {

	protected LinkedList<Carte> listeCartes;

	public Emplacement() {
		listeCartes = new LinkedList<Carte>();
	}

	public void deplacerCarteVers(Carte carte, Emplacement emplacement) {
		emplacement.getListeCartes().add(carte);
		listeCartes.remove(carte);
	}
	
	public LinkedList<Carte> getListeCartes() {
		return listeCartes;
	}
	
	public void setListeCartes(LinkedList<Carte> listeCartes) {
		this.listeCartes = listeCartes;
	}

	public int compterCartes() {
		return listeCartes.size();
	}

	public static void main(String[] args) {
		
	}

}
