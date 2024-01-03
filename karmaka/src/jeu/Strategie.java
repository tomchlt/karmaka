package jeu;

import java.io.Serializable;
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
	
	public abstract Carte choisirCarte(JoueurVirtuel joueurV, int choix);
	
	public abstract int jouerCarte(JoueurVirtuel joueurV);
	
	public abstract int garderCarte();
	
	public abstract int passerTour();

}
