package fr.utt.karmaka;

public class Fosse extends Emplacement {
	
	private static Fosse fosse;
	
	private Fosse() {
		
	}
	
	public static Fosse getInstance() {
		if (fosse==null) {
			fosse = new Fosse();
		}
		return fosse;
	}
	
	public static void main(String[] args) {
		
	}

}
