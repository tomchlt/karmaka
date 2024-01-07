package jeu;

import java.io.*;
import java.util.*;

import cartes.*;

/**
 * La classe Partie modélise une partie de jeu de karmaka.
 * 
 * Seule 1 Partie peut être instanciée (patron de conception Singleton).
 */
public class Partie implements Serializable {

	/**
	 * Sert à garantir que la version de la classe partie du fichier de sauvegarde est la même que celle qui est exécutée.
	 */
	private static final long serialVersionUID = -1753021790410524736L;
	/**
	 * Sert à garantir qu'on ne peut créer qu'une seule partie.
	 */
	private static Partie partie;
	/**
	 * Console servant à afficher et lire des informations dans la console.
	 */
	private static Console console;
	/**
	 * Premier joueur de la partie.
	 */
	private Joueur joueur1;
	/**
	 * Deuxième joueur de la partie.
	 */
	private Joueur joueur2;
	/**
	 * Collection de cartes servant de source.
	 */
	private LinkedList<Carte> source;
	/**
	 * Collection de cartes servant de fosse.
	 */
	private LinkedList<Carte> fosse;
	/**
	 * Sert à sauvegarder le numéro du tour de partie en cours.
	 */
	private int numTour;

	/**
	 * Construit une partie de jeu avec 2 joueurs, une source et une fosse.
	 * 
	 * @param joueur1 premier joueur qui jouera la partie
	 * @param joueur2 deuxième joueur qui jouera la partie
	 * @param source emplacement de cartes qui constituera la source pour cette partie
	 * @param fosse emplacement de cartes qui constituera la fosse pour cette partie
	 */
	private Partie(Joueur joueur1, Joueur joueur2, LinkedList<Carte> source, LinkedList<Carte> fosse) {
		this.joueur1 = joueur1;
		this.joueur2 = joueur2;
		this.source = source;
		this.fosse = fosse;
		this.numTour = 1;
	}
	
	/**
	 * Retourne la seule partie créée. Si aucune partie n'a encore été créée, elle est d'abord créée puis retournée.
	 * 
	 * @param joueur1 premier joueur qui jouera la partie
	 * @param joueur2 deuxième joueur qui jouera la partie
	 * @param source emplacement de cartes qui constituera la source pour cette partie
	 * @param fosse emplacement de cartes qui constituera la fosse pour cette partie
	 * @return une partie avec 2 joueurs, une source et une fosse (il ne peut y en avoir qu'une seule)
	 */
	public static Partie getInstance(Joueur joueur1, Joueur joueur2, LinkedList<Carte> source, LinkedList<Carte> fosse) {
		if (partie==null) {
			partie = new Partie(joueur1, joueur2, source, fosse);
		}
		return partie;
	}
	
	/**
	 * Crée une nouvelle partie, en demandant à l'utilisateur de paramétrer au travers de la console le type d'adversaire (humain ou virtuel), le nom des joueurs, et le niveau de l'adversaire s'il a choisi un adversaire virtuel.
	 * 
	 * @return une partie prête à démarrer
	 */
	public static Partie creerNouvellePartie() {
		
		Partie partie;
		Joueur joueur1;
		Joueur joueur2;
		LinkedList<Carte> source = new LinkedList<Carte>();
		LinkedList<Carte> fosse = new LinkedList<Carte>();
		
		int choix = 0;
		while (choix != 1 && choix != 2) {
			console.afficher("Voulez-vous jouer contre quelqu'un [1] ou contre l'ordinateur [2] ?");
			choix = console.lireInt();
		}

		if (choix == 1) {

			String nomJoueur1 = null;
			while (nomJoueur1 == null) {
				console.afficher("Quel est le nom du premier joueur ?");
				nomJoueur1 = console.lire();
			}
			joueur1 = new JoueurHumain(nomJoueur1, source, fosse);

			String nomJoueur2 = null;
			while (nomJoueur2 == null) {
				console.afficher("Quel est le nom du deuxième joueur ?");
				nomJoueur2 = console.lire();
			}
			joueur2 = new JoueurHumain(nomJoueur2, source, fosse);

		} else {

			String nomJoueur1 = null;
			while (nomJoueur1 == null) {
				console.afficher("Quel est votre nom ?");
				nomJoueur1 = console.lire();
			}
			joueur1 = new JoueurHumain(nomJoueur1, source, fosse);

			choix = 0;
			while (choix != 1 && choix != 2) {
				console.afficher("Choisissez le niveau de l'ordinateur : Débutant [1] ou Avancé [2]");
				choix = console.lireInt();
			}
			Strategie strategie;
			if (choix == 1) {
				strategie = new StrategieDebutant();
			} else {
				strategie = new StrategieAvance();
			}
			joueur2 = new JoueurVirtuel(strategie, source, fosse);
			
		}
		
		partie = getInstance(joueur1, joueur2, source, fosse);
		
		joueur1.setPartie(partie);
		joueur2.setPartie(partie);
		
		partie.initialiserPartie();
		
		return partie;
	}

	public void jouer() {
		console.afficher("\nLa partie commence !\n");
		while (joueur1.getAGagne() == false && joueur2.getAGagne() == false) {
			console.afficher("------------------------------| TOUR " + numTour + " |------------------------------\n");
			// le joueur 1 joue son tour
			console.afficher("------------------------------| Tour de " + joueur1.getNom() + " |------------------------------\n");
			joueur1.tour();
			// le joueur 2 joue son tour sauf si le joueur 1 a gagné la partie
			if (joueur1.getAGagne() == false) {
				console.afficher("------------------------------| Tour de " + joueur2.getNom() + " |------------------------------\n");
				joueur2.tour();
			}
			numTour ++;
			sauvegarderPartie();
		}
		if (joueur1.getAGagne() == true) {
			console.afficher(joueur1.getNom() + " a gagné !");
		} else {
			console.afficher(joueur2.getNom() + " a gagné !");
		}
		console.afficher("Fin de la partie.");
	}

	/**
	 * Désigne aléatoirement l'ordre dans lequel les joueurs vont jouer.
	 * 
	 * @param joueur1 premier joueur qui jouera la partie
	 * @param joueur2 deuxieme joueur qui jouera la partie
	 * @return un tableau contenant les 2 joueurs dans un ordre aléatoire
	 */
	public Joueur[] designerOrdreJoueurs(Joueur joueur1, Joueur joueur2) {
		Joueur[] ordre = new Joueur[2];
		Random rand = new Random();
		// tirage aléatoire d'un entier >= 0 et < 2 (donc 0 ou 1)
		int randomInt = rand.nextInt(2);
		if (randomInt == 0) {
			ordre[0] = joueur1;
			ordre[1] = joueur2;
		} else {
			ordre[0] = joueur2;
			ordre[1] = joueur1;
		}
		return ordre;
	}
	
	/**
	 * Initialise la partie en créant les cartes, les mélangeant, les distribuant aux joueurs. Puis en désignant au hasard l'ordre dans lequel les joueurs vont jouer.
	 */
	public void initialiserPartie() {
		creerCartes();
		melangerSource();
		distribuerCartes();
		Joueur[] ordre = designerOrdreJoueurs(joueur1, joueur2);
		setJoueur1(ordre[0]);
		setJoueur2(ordre[1]);
	}

	/**
	 * Crée les cartes dans les quantités adaptées pour une partie de jeu.
	 */
	public void creerCartes() {
		// création des cartes en 3 exemplaires
		for (int i = 0; i < 3; i++) {
			source.add(new Transmigration(partie));
			source.add(new CoupDOeil(partie));
			source.add(new Destinee(partie));
			source.add(new RevesBrises(partie));
			source.add(new Deni(partie));
			source.add(new Lendemain(partie));
			source.add(new Recyclage(partie));
			source.add(new Sauvetage(partie));
			source.add(new Longevite(partie));
			source.add(new Semis(partie));
			source.add(new Panique(partie));
			source.add(new DernierSouffle(partie));
			source.add(new Crise(partie));
			source.add(new Roulette(partie));
			source.add(new Fournaise(partie));
		}
		// création des cartes en 2 exemplaires
		for (int i = 0; i < 2; i++) {
			source.add(new Duperie(partie));
			source.add(new Vol(partie));
			source.add(new Voyage(partie));
			source.add(new Jubile(partie));
			source.add(new Vengeance(partie));
			source.add(new Bassesse(partie));
			source.add(new Mimetisme(partie));
		}
		// création des cartes en 5 exemplaires
		for (int i = 0; i < 5; i++) {
			source.add(new Incarnation(partie));
		}
	}
	
	/**
	 * Mélange les cartes contenues dans la source.
	 */
	public void melangerSource() {
		Collections.shuffle(source);
	}

	/**
	 * Distribue les cartes aux joueurs lors de l'initialisation de la partie.
	 */
	public void distribuerCartes() {
		// on distribue 4 cartes à chaque joueur pour constituer leur main de départ
		for (int i = 0; i < 4; i++) {
			joueur1.puiser(joueur1.main);
			joueur2.puiser(joueur2.main);
		}
		// on distribue 2 cartes à chaque joueur pour constituer leur pile de départ
		for (int i = 0; i < 2; i++) {
			joueur1.puiser();
			joueur2.puiser();
		}
	}

	/**
	 * Affichage de l'introduction de jeu.
	 */
	public static void introduction() {
		console.afficher("Bienvenue à Karmaka, le jeu de cartes compétitif se déroulant sur plusieurs vies.");
		console.afficher("Chaque joueur entame la partie en tant que Bousier avec des cartes en main et sa propre Pile. Lorsque vous êtes à court de cartes, votre vie s'achève et vous vous réincarnez pour une nouvelle vie qui se déroulera, espérons-le, un échelon plus haut sur l'Échelle Karmique. pour ce faire, vous devez cumuler suffisamment de points dans chaque vie, sous peine de devoir la revivre. Toutefois, ne vous attardez pas trop dans une vie, car un rival pourrait bien prendre les devants. Le premier joueur à atteindre la Transcendance gagne !");
		console.afficher("Marquez des points, préparez le terrain de votre prochaine vie et si nécessaire, semez des embûches sur le chemin de vos rivaux. Souvenez-vous cependant que l'on récolte ce que l'on sème, et que vos actions auront des conséquences dans cette vie... et dans la suivante.\n");
	}
	
	/**
	 * Sauvegarde la partie en cours.
	 */
	public static void sauvegarderPartie() {
		try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("save.ser"))) {
			oos.writeObject(partie);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Charge une partie précédemment sauvegardée dans un fichier nommé "save.ser".
	 * 
	 * @return la partie chargée
	 */
	public static Partie chargerPartie() {
		Partie partie = null;
		try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("save.ser"))) {
			partie = (Partie) ois.readObject();
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		return partie;
	}

	/**
	 * Retourne le joueur 1 de la partie.
	 * 
	 * @return le joueur 1 de la partie
	 */
	public Joueur getJoueur1() {
		return joueur1;
	}

	/**
	 * Définit le joueur 1 de la partie.
	 * 
	 * @param joueur1 un joueur qui va devenir le joueur 1 de la partie
	 */
	public void setJoueur1(Joueur joueur1) {
		this.joueur1 = joueur1;
	}

	/**
	 * Retourne le joueur 2 de la partie.
	 * 
	 * @return le joueur 2 de la partie
	 */
	public Joueur getJoueur2() {
		return joueur2;
	}

	/**
	 * Définit le joueur 2 de la partie.
	 * 
	 * @param joueur2 un joueur qui va devenir le joueur 2 de la partie
	 */
	public void setJoueur2(Joueur joueur2) {
		this.joueur2 = joueur2;
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
	 * Retourne la console de la partie.
	 * 
	 * @return la console de la partie
	 */
	public static Console getConsole() {
		return console;
	}
	
	/**
	 * Définit la console de la partie.
	 * 
	 * @param console console qui deviendra la console de la partie
	 */
	public static void setConsole(Console console) {
		Partie.console = console;
	}
	
	/**
	 * La méthode principale.
	 * 
	 * @param args arguments de la méthode principale
	 */
	public static void main(String[] args) {
		
		console = new Console();

		introduction();

		File f = new File("save.ser");
		int choix = 0;
		// si un fichier de sauvegarde existe déjà, on propose à l'utilisateur de le charger ou de créer une nouvelle partie
		if (f.exists()) {
			while (choix != 1 && choix !=2) {
				console.afficher("Voulez-vous continuer la partie précédente [1] ou créer une nouvelle partie [2] ?");
				choix = console.lireInt();
			}
		if (choix == 1) {
				partie = chargerPartie();	
			} else {
				partie = creerNouvellePartie();
			}
		// sinon, on crée directement une nouvelle partie
		} else {
			partie = creerNouvellePartie();
		}
		
		partie.jouer();

	}

}
