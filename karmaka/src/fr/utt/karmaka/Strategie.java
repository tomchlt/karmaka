package fr.utt.karmaka;

import java.io.Serializable;

public abstract class Strategie implements Serializable {
	
	private static final long serialVersionUID = 1388578098956615836L;
	
	private String nomStrategie;
	private String descriptionStrategie;
	
	public Strategie(String nomStrategie, String descriptionStrategie) {
		this.nomStrategie = nomStrategie;
		this.descriptionStrategie = descriptionStrategie;
	}
	
	public static void main(String[] args) {
		
	}

}
