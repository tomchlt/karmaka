package jeu;

import java.io.Serializable;
import java.util.*;

/**
 * La classe Joueur est une classe abstraite qui modélise un joueur d'une partie de karmaka.
 */
public abstract class Joueur implements Serializable {
	
	/**
	 * Sert à garantir que la version de la classe joueur du fichier de sauvegarde est la même que celle qui est exécutée.
	 */
	private static final long serialVersionUID = -2427293790425690941L;
	/**
	 * Console servant à afficher et lire des informations dans la console.
	 */
	protected static Console console = Partie.getConsole();
	/**
	 * Nom du joueur.
	 */
	protected String nom;
	/**
	 * Partie dans laquelle le joueur joue.
	 */
	protected Partie partie;
	/**
	 * Indique si le joueur a gagné la partie ou non.
	 */
	protected boolean aGagne;
	/**
	 * Permet de compter le nombre d'anneaux karmiques que le joueur possède.
	 */
	protected int nbAnneauxKarmiques;
	/**
	 * Niveau karmique du joueur.
	 */
	protected NiveauKarmique niveauKarmique;
	/**
	 * Collection de cartes servant de main.
	 */
	protected LinkedList<Carte> main;
	/**
	 * Collection de cartes servant de pile.
	 */
	protected LinkedList<Carte> pile;
	/**
	 * Collection de cartes servant d'emplacement pour les oeuvres.
	 */
	protected LinkedList<Carte> oeuvre;
	/**
	 * Collection de cartes servant d'emplacement pour la vie future.
	 */
	protected LinkedList<Carte> vieFuture;
	/**
	 * Collection de cartes servant de stock temporaire de cartes données par le joueur adverse pendant son tour.
	 */
	protected LinkedList<Carte> tempo;
	/**
	 * Collection de cartes servant de source.
	 */
	protected LinkedList<Carte> source;
	/**
	 * Collection de cartes servant de fosse.
	 */
	protected LinkedList<Carte> fosse;

	/**
	 * Construit un joueur avec un nom, des références sur la source et la fosse de la partie.
	 * 
	 * @param nom nom du joueur
	 * @param source emplacement de cartes qui constitue la source de la partie
	 * @param fosse emplacement de cartes qui constitue la fosse de la partie
	 */
	public Joueur(String nom, LinkedList<Carte> source, LinkedList<Carte> fosse) {
		this.nom = nom;
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
	
	/**
	 * Méthode abstraite qui décrit le tour d'un joueur.
	 */
	public abstract void tour();
	
	/**
	 * Méthode abstraite qui décrit la réincarnation d'un joueur pendant son tour.
	 */
	public abstract void seReincarner();

	/**
	 * Méthode abstraite qui décrit comment un joueur peut jouer une carte.
	 */
	public abstract void jouer();
	
	/**
	 * Déplace une carte d'un emplacement vers un autre.
	 * 
	 * @param carte carte à déplacer
	 * @param empacementDepart emplacement de cartes duquel la carte part
	 * @param emplacementArrivee emplacement de cartes dans lequel la carte arrive
	 */
	public void deplacerCarte(Carte carte, LinkedList<Carte> empacementDepart, LinkedList<Carte> emplacementArrivee) {
		emplacementArrivee.add(carte);
		empacementDepart.remove(carte);
	}
	
	/**
	 * Défausse une carte contenue dans la main.
	 * 
	 * @param carte carte à défausser
	 */
	public void defausser(Carte carte) {
		deplacerCarte(carte, main, fosse);
	}
	
	/**
	 * Défausse une carte contenue dans un emplacement.
	 * 
	 * @param carte carte à défausser
	 * @param emplacementDepart emplacement de cartes duquel la carte part
	 */
	public void defausser(Carte carte, LinkedList<Carte> emplacementDepart) {
		deplacerCarte(carte, emplacementDepart, fosse);
	}
	
	/**
	 * Pioche une carte dans la pile pour la mettre dans la main.
	 */
	public void piocher() {
		Carte carte = pile.getLast();
		deplacerCarte(carte, pile, main);
	}
	
	/**
	 * Puise une carte dans la source pour la mettre dans la pile.
	 */
	public void puiser() {
		if (source.size()==0) {
			while(fosse.size()>3) {
				deplacerCarte(fosse.getLast(),fosse, source);
			}
			partie.melangerSource();
		}
		Carte carte = source.getLast();
		deplacerCarte(carte, source, pile);
	}
	
	/**
	 * Puise une carte dans la source pour la mettre dans un emplacement de cartes.
	 * 
	 * @param emplacementArrivee emplacement de cartes dans lequel la carte arrive
	 */
	public void puiser(LinkedList<Carte> emplacementArrivee) {
		if (source.size()==0) {
			while(fosse.size()>3) {
				deplacerCarte(fosse.getLast(),fosse, source);
			}
			partie.melangerSource();
		}
		Carte carte = source.getLast();
		deplacerCarte(carte, source, emplacementArrivee);
	}
	
	/**
	 * Calcule la somme des points des cartes rouges qui se trouvent dans les oeuvres.
	 * 
	 * @return la somme des points des cartes rouges qui se trouvent dans les oeuvres
	 */
	public int calculerPointsRouges() {
		int total = 0;
		Iterator<Carte> it = oeuvre.iterator();
		while (it.hasNext()) {
			total += it.next().getPointsRouges();
		}
		return total;
	}
	
	/**
	 * Calcule la somme des points des cartes vertes qui se trouvent dans les oeuvres.
	 * 
	 * @return la somme des points des cartes vertes qui se trouvent dans les oeuvres
	 */
	public int calculerPointsVerts() {
		int total = 0;
		Iterator<Carte> it = oeuvre.iterator();
		while (it.hasNext()) {
			total += it.next().getPointsVerts();
		}
		return total;
	}
	
	/**
	 * Calcule la somme des points des cartes bleues qui se trouvent dans les oeuvres.
	 * 
	 * @return la somme des points des cartes bleues qui se trouvent dans les oeuvres
	 */
	public int calculerPointsBleus() {
		int total = 0;
		Iterator<Carte> it = oeuvre.iterator();
		while (it.hasNext()) {
			total += it.next().getPointsBleus();
		}
		return total;
	}
	
	/**
	 * Retourne la console de la partie.
	 * 
	 * @return la console de la partie
	 */
	public Console getConsole() {
		return console;
	}

	/**
	 * Retourne le nom du joueur.
	 * 
	 * @return le nom du joueur
	 */
	public String getNom() {
		return nom;
	}

	/**
	 * Définit le nom du joueur.
	 * 
	 * @param nom nom du joueur
	 */
	public void setNom(String nom) {
		this.nom = nom;
	}
	
	/**
	 * Retourne la partie dans laquelle le joueur joue.
	 * 
	 * @return la partie dans laquelle le joueur joue
	 */
	public Partie getPartie() {
		return partie;
	}
	
	/**
	 * Définit la partie dans laquelle le joueur joue.
	 * 
	 * @param partie partie dans laquelle le joueur joue
	 */
	public void setPartie(Partie partie) {
		this.partie = partie;
	}

	/**
	 * Retourne true si le joueur a gagné la partie, false sinon.
	 * 
	 * @return true si le joueur a gagné la partie, false sinon
	 */
	public boolean getAGagne() {
		return aGagne;
	}

	/**
	 * Définit si le joueur a gagné la partie.
	 * 
	 * @param aGagne true indique que le joueur a gagne la partie
	 */
	public void setAGagne(boolean aGagne) {
		this.aGagne = aGagne;
	}

	/**
	 * Retourne le nombre d'anneaux karmiques possédés par le joueur.
	 * 
	 * @return le nombre d'anneaux karmiques possédés par le joueur
	 */
	public int getNbAnneauxKarmiques() {
		return nbAnneauxKarmiques;
	}

	/**
	 * Définit le nombre d'anneaux karmiques possédés par le joueur.
	 * 
	 * @param nbAnneauxKarmiques nombre d'anneaux karmiques possédés par le joueur
	 */
	public void setNbAnneauxKarmiques(int nbAnneauxKarmiques) {
		this.nbAnneauxKarmiques = nbAnneauxKarmiques;
	}

	/**
	 * Retourne le niveau karmique du joueur.
	 * 
	 * @return le niveau karmique du joueur
	 */
	public NiveauKarmique getNiveauKarmique() {
		return niveauKarmique;
	}

	/**
	 * Définit le niveau karmique du joueur.
	 * 
	 * @param niveauKarmique niveau karmique du joueur
	 */
	public void setNiveauKarmique(NiveauKarmique niveauKarmique) {
		this.niveauKarmique = niveauKarmique;
	}

	/**
	 * Retourne la main du joueur.
	 * 
	 * @return la main du joueur
	 */
	public LinkedList<Carte> getMain() {
		return main;
	}

	/**
	 * Définit la main du joueur.
	 * 
	 * @param main main du joueur
	 */
	public void setMain(LinkedList<Carte> main) {
		this.main = main;
	}

	/**
	 * Retourne la pile du joueur.
	 * 
	 * @return la pile du joueur
	 */
	public LinkedList<Carte> getPile() {
		return pile;
	}

	/**
	 * Définit la pile du joueur.
	 * 
	 * @param pile pile du joueur
	 */
	public void setPile(LinkedList<Carte> pile) {
		this.pile = pile;
	}

	/**
	 * Retourne l'emplacement de cartes contenant les oeuvres du joueur.
	 * 
	 * @return l'emplacement de cartes contenant les oeuvres du joueur
	 */
	public LinkedList<Carte> getOeuvre() {
		return oeuvre;
	}

	/**
	 * Définit l'emplacement de cartes contenant les oeuvres du joueur.
	 * 
	 * @param oeuvre emplacement de cartes contenant les oeuvres du joueur
	 */
	public void setOeuvre(LinkedList<Carte> oeuvre) {
		this.oeuvre = oeuvre;
	}

	/**
	 * Retourne l'emplacement de cartes contenant la vie future du joueur.
	 * 
	 * @return l'emplacement de cartes contenant la vie future du joueur
	 */
	public LinkedList<Carte> getVieFuture() {
		return vieFuture;
	}

	/**
	 * Définit l'emplacement de cartes contenant la vie future du joueur.
	 * 
	 * @param vieFuture emplacement de cartes contenant la vie future du joueur
	 */
	public void setVieFuture(LinkedList<Carte> vieFuture) {
		this.vieFuture = vieFuture;
	}

	/**
	 * Retourne l'emplacement de cartes servant de stock temporaire de cartes données par le joueur adverse pendant son tour.
	 * 
	 * @return l'emplacement de cartes servant de stock temporaire de cartes données par le joueur adverse pendant son tour
	 */
	public LinkedList<Carte> getTempo() {
		return tempo;
	}

	/**
	 * Définit l'emplacement de cartes servant de stock temporaire de cartes données par le joueur adverse pendant son tour.
	 * 
	 * @param tempo emplacement de cartes servant de stock temporaire de cartes données par le joueur adverse pendant son tour
	 */
	public void setTempo(LinkedList<Carte> tempo) {
		this.tempo = tempo;
	}

	/**
	 * Retourne la source de la partie.
	 * 
	 * @return la source de la partie
	 */
	public LinkedList<Carte> getSource() {
		return source;
	}

	/**
	 * Définit la source de la partie.
	 * 
	 * @param source collection qui deviendra la source de la partie
	 */
	public void setSource(LinkedList<Carte> source) {
		this.source = source;
	}

	/**
	 * Retourne la fosse de la partie.
	 * 
	 * @return la fosse de la partie
	 */
	public LinkedList<Carte> getFosse() {
		return fosse;
	}

	/**
	 * Définit la fosse de la partie.
	 * 
	 * @param fosse collection qui deviendra la fosse de la partie
	 */
	public void setFosse(LinkedList<Carte> fosse) {
		this.fosse = fosse;
	}
	
	/**
	 * La méthode principale.
	 * 
	 * @param args arguments de la méthode principale
	 */
	public static void main(String[] args) {

	}

}
