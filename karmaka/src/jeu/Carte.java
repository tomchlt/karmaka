package jeu;

import java.io.Serializable;

public abstract class Carte  implements Serializable {
	
	private static final long serialVersionUID = 8571543197401890585L;
	
	protected static Console console = Partie.getConsole();
	protected int pointsRouges;
	protected int pointsVerts;
	protected int pointsBleus;
	protected String nom;
	protected String description;
	protected Partie partie;
	
	public Carte(int pointsRouges, int pointsVerts, int pointsBleus, String nom, String description, Partie partie) {
		this.pointsRouges = pointsRouges;
		this.pointsVerts = pointsVerts;
		this.pointsBleus = pointsBleus;
		this.nom = nom;
		this.description = description;
		this.partie = partie;
	}
	
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append("Nom : ");
		sb.append(nom + "\t");
		if ( (pointsRouges == pointsVerts) && (pointsVerts == pointsBleus) ) {
			sb.append("Type : Mosaïque \t");
			sb.append(pointsRouges + "\t");
		} else if (pointsRouges > 0) {
			sb.append("Type : Rouge \t");
			sb.append(pointsRouges + "\t");
		} else if (pointsVerts > 0) {
			sb.append("Type : Vert \t");
			sb.append(pointsVerts + "\t");
		} else if (pointsBleus > 0) {
			sb.append("Type : Bleu \t");
			sb.append(pointsBleus + "\t");
		}
		sb.append("Capacité : ");
		sb.append(description + "\n");
		return sb.toString();
	}
	
	public void activerCapacite(Joueur joueur) {}
	
	public Joueur determinerJoueurAdverse(Joueur joueur) {
		Joueur joueurAdverse;
		if (joueur==partie.getJoueur1()) {
			joueurAdverse = partie.getJoueur2();
		} else if (joueur==partie.getJoueur2()) {
			joueurAdverse = partie.getJoueur1();
		} else {
			joueurAdverse=null;
			System.out.println("Erreur : le joueur adverse n'a pas pu être trouvé");
		}
		return joueurAdverse;
	}
	public int getPoints() {
		int points = pointsRouges+pointsVerts+pointsBleus;
		if(getNom()=="Incarnation"||getNom()=="Mimétisme")
			points =1;
		return points;
	}
	
	
	public int getPointsRouges() {
		return pointsRouges;
	}

	public void setPointsRouges(int pointsRouges) {
		this.pointsRouges = pointsRouges;
	}

	public int getPointsVerts() {
		return pointsVerts;
	}

	public void setPointsVerts(int pointsVerts) {
		this.pointsVerts = pointsVerts;
	}

	public int getPointsBleus() {
		return pointsBleus;
	}

	public void setPointsBleus(int pointsBleus) {
		this.pointsBleus = pointsBleus;
	}
	
	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	public Partie getPartie() {
		return partie;
	}
	
	public void setPartie(Partie partie) {
		this.partie = partie;
	}
	
	public static void main(String[] args) {
		
	}

}
