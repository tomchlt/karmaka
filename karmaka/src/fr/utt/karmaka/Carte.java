package fr.utt.karmaka;

public class Carte {
	
	private int pointsRouges;
	private int pointsVerts;
	private int pointsBleus;
	private String descriptionCapacite;

	public Carte(int pointsRouges, int pointsVerts, int pointsBleus, String descriptionCapacite) {
		this.descriptionCapacite = descriptionCapacite;
		this.pointsRouges = pointsRouges;
		this.pointsVerts = pointsVerts;
		this.pointsBleus = pointsBleus;
	}
	
	public void activerCapacite() {
		
	}
	
	public static void main(String[] args) {
		
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

	public String getDescriptionCapacite() {
		return descriptionCapacite;
	}

	public void setDescriptionCapacite(String descriptionCapacite) {
		this.descriptionCapacite = descriptionCapacite;
	}

}
