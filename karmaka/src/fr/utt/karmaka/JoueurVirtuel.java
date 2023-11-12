package fr.utt.karmaka;

public class JoueurVirtuel extends Joueur {
	
	private Strategie strategie;
	
	public JoueurVirtuel(Strategie strategie, Source source, Fosse fosse) {
		super("Joueur 2", source, fosse);
		this.strategie = strategie;
	}
	
	public static void main(String[] args) {
		
	}

}
