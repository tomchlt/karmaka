package karmaka;

import java.io.Serializable;

public enum NiveauKarmique {
	
	BOUSIER("Bousier", 4),
	SERPENT("Serpent", 5),
	LOUP("Loup", 6),
	SINGE("Singe", 7);
	
	private String nomNiveau;
	private int pointsRequis;
	
	private NiveauKarmique(String nomNiveau, int pointsRequis) {
		this.nomNiveau = nomNiveau;
		this.pointsRequis = pointsRequis;
	}
	
	public String getNomNiveau() {
		return nomNiveau;
	}
	
	public int getPointsRequis() {
		return pointsRequis;
	}
	
	public String toString() {
		StringBuffer str = new StringBuffer();
		str.append("niveau actuel : ");
		str.append(nomNiveau);
		str.append("\t\t points requis pour passer au niveau suivant : ");
		str.append(pointsRequis);
		return str.toString();
	}
	
	public static void main(String[] args) {
		for (NiveauKarmique niveau: NiveauKarmique.values()) {
			System.out.println("niveau actuel : " + niveau.nomNiveau + "\t\t points requis pour passer au niveau suivant : " + niveau.pointsRequis);
		}
	}
	
}
