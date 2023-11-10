package fr.utt.karmaka;

public class Main extends Emplacement {
	
	public Main() {
		
	}
	
	public void jouerCartePoints(Carte carte) {
		Oeuvre oeuvre;
		deplacerCarteVers(carte, oeuvre);
	}
	
	public void jouerCartePouvoir(Carte carte) {
		carte.activerCapacite();
	}
	
	public void jouerCarteFutur(Carte carte) {
		VieFuture vieFuture;
		deplacerCarteVers(carte, vieFuture);
	}
	
	public static void main(String[] args) {
		
	}

}
