package fr.utt.karmaka;

public abstract class Joueur {

	private String nom;
	private boolean aGagne;
	private int nbAnneauxKarmiques;
	private NiveauKarmique niveauKarmique;
	protected Main main;
	protected Pile pile;
	protected Oeuvre oeuvre;
	protected VieFuture vieFuture;
	protected Tempo tempo;
	protected Source source;
	protected Fosse fosse;

	public Joueur(String nom, Source source, Fosse fosse) {
		this.nom = nom;
		this.aGagne = false;
		this.nbAnneauxKarmiques = 0;
		this.niveauKarmique = NiveauKarmique.BOUSIER;
		this.main = new Main();
		this.pile = new Pile();
		this.oeuvre = new Oeuvre();
		this.vieFuture = new VieFuture();
		this.tempo = new Tempo();
		this.source = source;
		this.fosse = fosse;
	}

	public void tour() {
	}

	public void seReincarner() {
		System.out.println("Vous tentez de vous réincarner...");
		int pointsMax;
		pointsMax = Math.max(oeuvre.calculerPointsRouges(), oeuvre.calculerPointsVerts());
		pointsMax = Math.max(pointsMax, oeuvre.calculerPointsBleus());
		if (pointsMax + nbAnneauxKarmiques >= niveauKarmique.getPointsRequis()) {
			if (pointsMax < niveauKarmique.getPointsRequis()) {
				nbAnneauxKarmiques -= (niveauKarmique.getPointsRequis() - pointsMax);
			}
			switch (niveauKarmique) {
			case BOUSIER:
				niveauKarmique = NiveauKarmique.SERPENT;
				System.out.println("Vous vous réincarnez en " + niveauKarmique.getNomNiveau() + " !");
				break;
			case SERPENT:
				niveauKarmique = NiveauKarmique.LOUP;
				System.out.println("Vous vous réincarnez en " + niveauKarmique.getNomNiveau() + " !");
				break;
			case LOUP:
				niveauKarmique = NiveauKarmique.SINGE;
				System.out.println("Vous vous réincarnez en " + niveauKarmique.getNomNiveau() + " !");
				break;
			case SINGE:
				aGagne = true;
				System.out.println("Vous accédez à la Transcendance !");
				break;
			default:
				System.out.println("Erreur dans l'augmentation du niveau karmique");
				break;
			}
		} else {
			nbAnneauxKarmiques += 1;
			System.out.println("Votre réincarnation a échoué, vous recevez 1 anneau karmique.");
		}
	}

	public void piocher() {
		// A RAJOUTER : une carte est chosie au hasard dans la pile du joueur
		pile.deplacerCarteVers(carte, main);
		System.out.println("Vous avez pioché 1 carte");
	}

	public void jouer() {
		String choix = null;
		while (choix != "points" || choix != "pouvoir" || choix != "futur") {
			// A RAJOUTER : demander au joueur quelle carte il veut jouer et comment il veut
			// la jouer
		}
		if (choix == "points") {
			main.jouerCartePoints(carte);
		} else if (choix == "pouvoir") {
			main.jouerCartePouvoir(carte);
		} else if (choix == "futur") {
			main.jouerCarteFutur(carte);
		}
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public boolean getAGagne() {
		return aGagne;
	}

	public void setAGagne(boolean aGagne) {
		this.aGagne = aGagne;
	}

	public int getNbAnneauxKarmiques() {
		return nbAnneauxKarmiques;
	}

	public void setNbAnneauxKarmiques(int nbAnneauxKarmiques) {
		this.nbAnneauxKarmiques = nbAnneauxKarmiques;
	}

	public NiveauKarmique getNiveauKarmique() {
		return niveauKarmique;
	}

	public void setNiveauKarmique(NiveauKarmique niveauKarmique) {
		this.niveauKarmique = niveauKarmique;
	}

	public static void main(String[] args) {

	}

}
