package fr.utt.karmaka;

public class Joueur {
	
	private String nom;
	private int nbAnneauxKarmiques;
	private NiveauKarmique niveauKarmique;
	
	public Joueur(String nom) {
		this.nom = nom;
		this.nbAnneauxKarmiques = 0;
		this.niveauKarmique = NiveauKarmique.BOUSIER;
	}
	
	public String getNom() {
		return nom;
	}
	
	public void setNom(String nom) {
		this.nom = nom;
	}
	
	public int getNbAnneauxKarmiques() {
		return nbAnneauxKarmiques;
	}
	
	public void setNbAnneauxKarmiques(int nbAnneauxKarmiques) {
		this.nbAnneauxKarmiques = nbAnneauxKarmiques;
	}
	
	public NiveauKarmique getNiveauKarmique() {
		return niveauKarmique;
	}
	
	public void setNiveauKarmique(NiveauKarmique niveauKarmique) {
		this.niveauKarmique = niveauKarmique;
	}
	
	public static void main(String[] args) {
		Joueur joueur1 = new Joueur("Joueur 1");
		System.out.println(joueur1.getNom());
		System.out.println(joueur1.getNbAnneauxKarmiques());
		System.out.println(joueur1.getNiveauKarmique());
		System.out.println(joueur1.niveauKarmique.getNomNiveau());
		System.out.println(joueur1.niveauKarmique.getPointsRequis());
		joueur1.setNiveauKarmique(NiveauKarmique.SERPENT);
		System.out.println(joueur1.getNiveauKarmique());
		System.out.println(joueur1.niveauKarmique.getNomNiveau());
		System.out.println(joueur1.niveauKarmique.getPointsRequis());
	}

}
