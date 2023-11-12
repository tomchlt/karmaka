package fr.utt.karmaka;

public class Main extends Emplacement {
	
	public Main() {
		super();
	}
	
	public void jouerCartePoints(Carte carte) {
		Oeuvre oeuvre;
		deplacerCarteVers(carte, getOeuvre());
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
