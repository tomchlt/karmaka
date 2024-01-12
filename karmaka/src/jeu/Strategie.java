package jeu;

import java.util.LinkedList;

public interface Strategie {

	public Carte choisirCarteOeuvre(JoueurVirtuel joueurV);

	public Carte choisirCarteVieFuture(JoueurVirtuel joueurV);

	public Carte choisirCarteDefausser(JoueurVirtuel joueurV, LinkedList<Carte> emplacement);
	
	public Carte selectionnerCarte(LinkedList<Carte> cartesAChoisir);

	public int jouerCarte(JoueurVirtuel joueurV);

	public int garderCarte();

	public int passerTour();
	
	public Carte trouverCartePtsMax(LinkedList<Carte> cartesAChoisir);
	
	public Carte trouverCartePtsMin(LinkedList<Carte> cartesAChoisir);

}
