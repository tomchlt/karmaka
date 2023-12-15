package fr.utt.karmaka;

import java.util.*;

public class Oeuvre extends Emplacement {
	
	public Oeuvre() {
		super();
	}
	
	public int calculerPointsRouges() {
		int total = 0;
		Iterator<Carte> it = listeCartes.iterator();
		while (it.hasNext()) {
			total += it.next().getPointsRouges();
		}
		return total;
	}
	
	public int calculerPointsVerts() {
		int total = 0;
		Iterator<Carte> it = listeCartes.iterator();
		while (it.hasNext()) {
			total += it.next().getPointsVerts();
		}
		return total;
	}
	
	public int calculerPointsBleus() {
		int total = 0;
		Iterator<Carte> it = listeCartes.iterator();
		while (it.hasNext()) {
			total += it.next().getPointsBleus();
		}
		return total;
	}
	
	public static void main(String[] args) {
		
	}

}
