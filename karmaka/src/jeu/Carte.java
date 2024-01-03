package jeu;

import java.io.Serializable;

/**
 * La classe Carte est une classe abstraite qui modélise une carte du jeu karmaka.
 */
public abstract class Carte implements Serializable {
	
	/**
	 * Sert à garantir que la version de la classe joueur du fichier de sauvegarde est la même que celle qui est exécutée.
	 */
	private static final long serialVersionUID = 8571543197401890585L;
	/**
	 * Console servant à afficher et lire des informations dans la console.
	 */
	protected static Console console = Partie.getConsole();
	/**
	 * Nombre de points de type rouge.
	 */
	protected int pointsRouges;
	/**
	 * Nombre de points de type vert.
	 */
	protected int pointsVerts;
	/**
	 * Nombre de points de type bleu.
	 */
	protected int pointsBleus;
	/**
	 * Nom de la carte.
	 */
	protected String nom;
	/**
	 * Description de la capacité de la carte.
	 */
	protected String description;
	/**
	 * Partie à laquelle la carte appartient.
	 */
	protected Partie partie;
	
	/**
	 * Construit une carte avec un nombre de points pour chaque type, un nom, une description de sa capacité, la référence de la partie à laquelle elle appartient.
	 * 
	 * @param pointsRouges nombre de points rouges
	 * @param pointsVerts nombre de points verts
	 * @param pointsBleus nombre de points bleus
	 * @param nom nom de la carte
	 * @param description description de la capacité de la carte
	 * @param partie partie à laquelle la carte appartient
	 */
	public Carte(int pointsRouges, int pointsVerts, int pointsBleus, String nom, String description, Partie partie) {
		this.pointsRouges = pointsRouges;
		this.pointsVerts = pointsVerts;
		this.pointsBleus = pointsBleus;
		this.nom = nom;
		this.description = description;
		this.partie = partie;
	}
	
	/**
	 * Affiche les informations utiles de la carte (le nom, le type, le nombre de points, et la description de sa capacité).
	 */
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
	
	/**
	 * Méthode abstraite qui active la capacité de la carte.
	 * 
	 * @param joueur joueur qui active la capacité de la carte
	 */
	public void activerCapacite(Joueur joueur) {}
	
	/**
	 * Retourne le joueur adverse à celui qui joue la carte.
	 * 
	 * @param joueur joueur qui joue la carte
	 * @return le joueur adverse à celui qui joue la carte
	 */
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
	
	/**
	 * Retourne le nombre de points de la carte en tenant compte de son type.
	 * 
	 * @return le nombre de points de la carte en tenant compte de son type
	 */
	public int getPoints() {
		int points = pointsRouges+pointsVerts+pointsBleus;
		if(getNom()=="Incarnation"||getNom()=="Mimétisme")
			points = 1;
		return points;
	}
	
	/**
	 * Retourne le nombre de points rouges de la carte.
	 * 
	 * @return le nombre de points rouges de la carte
	 */
	public int getPointsRouges() {
		return pointsRouges;
	}

	/**
	 * Définit le nombre de points rouges de la carte.
	 * 
	 * @param pointsRouges nombre de points rouges de la carte
	 */
	public void setPointsRouges(int pointsRouges) {
		this.pointsRouges = pointsRouges;
	}

	/**
	 * Retourne le nombre de points verts de la carte.
	 * 
	 * @return le nombre de points verts de la carte
	 */
	public int getPointsVerts() {
		return pointsVerts;
	}

	/**
	 * Définit le nombre de points verts de la carte.
	 * 
	 * @param pointsVerts nombre de points verts de la carte
	 */
	public void setPointsVerts(int pointsVerts) {
		this.pointsVerts = pointsVerts;
	}

	/**
	 * Retourne le nombre de points bleus de la carte.
	 * 
	 * @return le nombre de points bleus de la carte
	 */
	public int getPointsBleus() {
		return pointsBleus;
	}

	/**
	 * Définit le nombre de points bleus de la carte.
	 * 
	 * @param pointsBleus nombre de points bleus de la carte
	 */
	public void setPointsBleus(int pointsBleus) {
		this.pointsBleus = pointsBleus;
	}
	
	/**
	 * Retourne le nom de la carte.
	 * 
	 * @return le nom de la carte
	 */
	public String getNom() {
		return nom;
	}

	/**
	 * Définit le nom de la carte.
	 * 
	 * @param nom nom de la carte
	 */
	public void setNom(String nom) {
		this.nom = nom;
	}

	/**
	 * Retourne la description de la capacité de la carte.
	 * 
	 * @return la description de la capacité de la carte
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Définit la description de la capacité de la carte.
	 * 
	 * @param description description de la capacité de la carte
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	
	/**
	 * Retourne la partie à laquelle la carte appartient.
	 * 
	 * @return la partie à laquelle la carte appartient
	 */
	public Partie getPartie() {
		return partie;
	}
	
	/**
	 * Définit la partie à laquelle la carte appartient.
	 * 
	 * @param partie partie à laquelle la carte appartient
	 */
	public void setPartie(Partie partie) {
		this.partie = partie;
	}
	
	/**
	 * La méthode principale.
	 * 
	 * @param args arguments de la méthode principale
	 */
	public static void main(String[] args) {
		
	}

}
