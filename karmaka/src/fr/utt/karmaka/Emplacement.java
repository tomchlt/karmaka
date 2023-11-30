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
	
	public String afficherCartes(Emplacement emplacement) {
		StringBuffer sb = new StringBuffer();
		Iterator<Carte> itCartes = listeCartes.iterator();
		while(itCartes.hasNext()) {
			String nom = itCartes.next().getNom();
			sb.append(nom+ "\n");
			String description = itCartes.next().getDescriptionCapacite();
			sb.append(description+ "\n"+"Points :"+ "\n");
			if (itCartes.next().getPointsBleus()!=0) {
				int pointsBleus = itCartes.next().getPointsBleus();
				sb.append("Bleus : "+pointsBleus+"\n");
			}
			if (itCartes.next().getPointsRouges()!=0) {
				int pointsRouges = itCartes.next().getPointsRouges();
				sb.append("Rouges : "+pointsRouges+"\n");
			}
			if (itCartes.next().getPointsVerts()!=0) {
				int pointsVerts = itCartes.next().getPointsVerts();
				sb.append("Verts : "+pointsVerts);
			}
		}
		return sb.toString();
	}

	public static void main(String[] args) {
		
	}

}
