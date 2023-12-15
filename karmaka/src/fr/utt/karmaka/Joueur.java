package fr.utt.karmaka;

import java.util.*;

public abstract class Joueur {

	protected String nom;
	protected Partie partie;
	protected boolean aGagne;
	protected int nbAnneauxKarmiques;
	protected NiveauKarmique niveauKarmique;
	protected LinkedList<Carte> main;
	protected LinkedList<Carte> pile;
	protected LinkedList<Carte> oeuvre;
	protected LinkedList<Carte> vieFuture;
	protected LinkedList<Carte> tempo;
	protected LinkedList<Carte> source;
	protected LinkedList<Carte> fosse;

	public Joueur(String nom, LinkedList<Carte> source, LinkedList<Carte> fosse, Partie partie) {
		this.nom = nom;
		this.partie = partie;
		this.aGagne = false;
		this.nbAnneauxKarmiques = 0;
		this.niveauKarmique = NiveauKarmique.BOUSIER;
		this.main = new LinkedList<Carte>();
		this.pile = new LinkedList<Carte>();
		this.oeuvre = new LinkedList<Carte>();
		this.vieFuture = new LinkedList<Carte>();
		this.tempo = new LinkedList<Carte>();
		this.source = source;
		this.fosse = fosse;
	}
	
	public void tour() {}
	
	public void seReincarner() {
		System.out.println("Vous tentez de vous réincarner...");
		int pointsMax;
		pointsMax = Math.max(calculerPointsRouges(), calculerPointsVerts());
		pointsMax = Math.max(pointsMax, calculerPointsBleus());
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

	public void jouer() {
		String choix = null;
		while (choix != "points" || choix != "pouvoir" || choix != "futur") {
			// A RAJOUTER : demander au joueur quelle carte il veut jouer et comment il veut
			// la jouer
		}
		if (choix == "points") {
			deplacerCarte(carte, main, oeuvre);
		} else if (choix == "pouvoir") {
			carte.activerCapacite(this);
		} else if (choix == "futur") {
			deplacerCarte(carte, main, vieFuture);
		}
	}
	
	public void deplacerCarte(Carte carte, LinkedList<Carte> empacementDepart, LinkedList<Carte> emplacementArrivee) {
		emplacementArrivee.add(carte);
		empacementDepart.remove(carte);
	}
	
	public void defausser(Carte carte) {
		deplacerCarte(carte, main, fosse);
	}
	
	public void defausser(Carte carte, LinkedList<Carte> emplacementDepart) {
		deplacerCarte(carte, emplacementDepart, fosse);
	}
	
	public void piocher() {
		Carte carte = pile.getLast();
		deplacerCarte(carte, pile, main);
	}
	
	public void puiser() {
		if (source.size()==0) {
			while(fosse.size()>3) {
				deplacerCarte(fosse.getFirst(),fosse, source);
			}
			partie.melangerSource();
		}
		Carte carte = source.getLast();
		deplacerCarte(carte, source, pile);
	}
	
	public void puiser(LinkedList<Carte> emplacementArrivee) {
		if (source.size()==0) {
			while(fosse.size()>3) {
				deplacerCarte(fosse.getFirst(),fosse, source);
			}
			partie.melangerSource();
		}
		Carte carte = source.getLast();
		deplacerCarte(carte, source, emplacementArrivee);
	}
	
	public int calculerPointsRouges() {
		int total = 0;
		Iterator<Carte> it = oeuvre.iterator();
		while (it.hasNext()) {
			total += it.next().getPointsRouges();
		}
		return total;
	}
	
	public int calculerPointsVerts() {
		int total = 0;
		Iterator<Carte> it = oeuvre.iterator();
		while (it.hasNext()) {
			total += it.next().getPointsVerts();
		}
		return total;
	}
	
	public int calculerPointsBleus() {
		int total = 0;
		Iterator<Carte> it = oeuvre.iterator();
		while (it.hasNext()) {
			total += it.next().getPointsBleus();
		}
		return total;
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

	public LinkedList<Carte> getMain() {
		return main;
	}

	public void setMain(LinkedList<Carte> main) {
		this.main = main;
	}

	public LinkedList<Carte> getPile() {
		return pile;
	}

	public void setPile(LinkedList<Carte> pile) {
		this.pile = pile;
	}

	public LinkedList<Carte> getOeuvre() {
		return oeuvre;
	}

	public void setOeuvre(LinkedList<Carte> oeuvre) {
		this.oeuvre = oeuvre;
	}

	public LinkedList<Carte> getVieFuture() {
		return vieFuture;
	}

	public void setVieFuture(LinkedList<Carte> vieFuture) {
		this.vieFuture = vieFuture;
	}

	public LinkedList<Carte> getTempo() {
		return tempo;
	}

	public void setTempo(LinkedList<Carte> tempo) {
		this.tempo = tempo;
	}

	public LinkedList<Carte> getSource() {
		return source;
	}

	public void setSource(LinkedList<Carte> source) {
		this.source = source;
	}

	public LinkedList<Carte> getFosse() {
		return fosse;
	}

	public void setFosse(LinkedList<Carte> fosse) {
		this.fosse = fosse;
	}
	
	public static void main(String[] args) {

	}

}
