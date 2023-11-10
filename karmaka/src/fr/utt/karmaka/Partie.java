package fr.utt.karmaka;

public class Partie {
	
	private static Partie partie;
	
	private Partie() {
		
	}
	
	public static Partie getInstance() {
		if (partie==null) {
			partie = new Partie();
		}
		return partie;
	}
	
	public static void main(String[] args) {
		
	}

}
