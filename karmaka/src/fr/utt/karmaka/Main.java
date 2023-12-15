package fr.utt.karmaka;

public class Main extends Emplacement {
	
	private Joueur joueur;
	
	public Main(Joueur joueur) {
		super();
	}
	
	public void jouerCartePoints(Carte carte) {
		deplacerCarteVers(carte, joueur.oeuvre);
	}
	
	public void jouerCartePouvoir(Carte carte) {
		carte.activerCapacite();
	}
	
	public void jouerCarteFutur(Carte carte) {
		deplacerCarteVers(carte, joueur.vieFuture);
	}
	
	public static void main(String[] args) {
		
	}

}
