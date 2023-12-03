package fr.utt.karmaka;

import java.util.*;

public class Partie {
	
	private static Partie partie;
	
	private Joueur joueur1;
	private Joueur joueur2;
	private LinkedList<Carte> source;
	private LinkedList<Carte> fosse;
	
	private Partie(JoueurHumain joueur1, JoueurVirtuel joueur2, LinkedList<Carte> source, LinkedList<Carte> fosse) {
		this.joueur1 = joueur1;
		this.joueur2 = joueur2;
		this.source = source;
		this.fosse = fosse;
	}
	
	private Partie(JoueurHumain joueur1, JoueurHumain joueur2, LinkedList<Carte> source, LinkedList<Carte> fosse) {
		this.joueur1 = joueur1;
		this.joueur2 = joueur2;
		this.source = source;
		this.fosse = fosse;
	}
	
	public static Partie getInstance(JoueurHumain joueur1, JoueurVirtuel joueur2, LinkedList<Carte> source, LinkedList<Carte> fosse) {
		if (partie==null) {
			partie = new Partie(joueur1, joueur2, source, fosse);
		}
		return partie;
	}
	
	public static Partie getInstance(JoueurHumain joueur1, JoueurHumain joueur2, LinkedList<Carte> source, LinkedList<Carte> fosse) {
		if (partie==null) {
			partie = new Partie(joueur1, joueur2, source, fosse);
		}
		return partie;
	}
	
	public void lancerPartie() {
		
		Joueur[] ordre = designerOrdreJoueurs(joueur1, joueur2);
		setJoueur1(ordre[0]);
		setJoueur2(ordre[1]);
		
		System.out.println("La partie commence !");
		while (joueur1.getAGagne()==false && joueur2.getAGagne()==false) {
			// le joueur 1 joue son tour
			joueur1.tour();
			// le joueur 2 joue son tour sauf si le joueur 1 a gagné la partie
			if (joueur1.getAGagne()==false) {
				joueur2.tour();
			}
		}
		if (joueur1.getAGagne()==true) {
			System.out.println(joueur1.getNom() + " a gagné !");
		}
		else {
			System.out.println(joueur2.getNom() + " a gagné !");
		}
		System.out.println("Fin de la partie.");
	}
	
	public Joueur[] designerOrdreJoueurs(Joueur joueur1, Joueur joueur2) {
		Joueur[] ordre = new Joueur[2];
		Random rand = new Random();
		int randomInt = rand.nextInt(2);
		if (randomInt==0) {
			ordre[0]=joueur1;
			ordre[1]=joueur2;
		}
		else {
			ordre[0]=joueur2;
			ordre[1]=joueur1;
		}
		return ordre; 
	}
	
	public static void introduction() {
		System.out.println("Bienvenue à Karmaka, le jeu de cartes compétitif se déroulant sur plusieurs vies.");
		System.out.println("Chaque joueur entame la partie en tant que Bousier avec des cartes en main et sa propre Pile. Lorsque vous êtres à court de cartes, votre vie s'achève et vous vous réincarnez pour une nouvelle vie qui se déroulera, espérons-le, un échelon plus haut sur l'Échelle Karmique. pour ce faire, vous devez cumuler suffisamment de points dans chaque vie, sous peine de devoir la revivre. Toutefois, ne vous attardez pas trop dans une vie, car un rival pourrait bien prendre les devants. Le premier joueur à atteindre la Transcendance gagne !");
		System.out.println("Marquez des points, préparez le terrain de votre prochaine vie et si nécessaire, semez des embûches sur le chemin de vos rivaux. Souvenez-vous cependant que l'on récolte ce que l'on sème, et que vos actions auront des conséquences dans cette vie... et dans la suivante.");
	}
	
	public Joueur getJoueur1() {
		return joueur1;
	}
	
	public void setJoueur1(Joueur joueur1) {
		this.joueur1 = joueur1;
	}
	
	public Joueur getJoueur2() {
		return joueur2;
	}
	
	public void setJoueur2(Joueur joueur2) {
		this.joueur2 = joueur2;
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
		
		introduction();
		
		// paramétrage de la partie
		
		int choix=0;
		while (choix!=1 && choix!=2) {
			System.out.println("Voulez-vous jouer contre l'ordinateur [1] ou contre quelqu'un [2] ?");
			// A RAJOUTER : demander si le deuxième joueur sera humain ou virtuel
		}
		
		Partie partie;
		Joueur joueur1;
		Joueur joueur2;
		LinkedList<Carte> source = new LinkedList<Carte>();
		LinkedList<Carte> fosse = new LinkedList<Carte>();
		
		if (choix==1) {
			
			String nomJoueur1=null;
			while (nomJoueur1==null) {
				System.out.println("Quel est le nom du premier joueur ?");
				// A RAJOUTER : demander le nom du joueur 1
			}
			joueur1 = new JoueurHumain(nomJoueur1, source, fosse);
			
			String nomJoueur2=null;
			while (nomJoueur2==null) {
				System.out.println("Quel est le nom du deuxième joueur ?");
				// A RAJOUTER : demander le nom du joueur 2
			}
			joueur2 = new JoueurHumain(nomJoueur2, source, fosse);
			
			partie = getInstance((JoueurHumain)joueur1, (JoueurHumain)joueur2, source, fosse);
			
		} else {
			
			String nomJoueur1=null;
			while (nomJoueur1==null) {
				System.out.println("Quel est votre nom ?");
				// A RAJOUTER : demander le nom du joueur 1
			}
			joueur1 = new JoueurHumain(nomJoueur1, source, fosse);
			
			choix=0;
			while (choix!=1 && choix!=2) {
				System.out.println("Choisissez le niveau de l'ordinateur : Débutant [1] ou Avancé [2]");
				// A RAJOUTER : demander le niveau du joueur virtuel
			}
			Strategie strategie;
			if (choix==1) {
				strategie = new StrategieDebutant();
			}
			else {
				strategie = new StrategieAvance();
			}
			joueur2 = new JoueurVirtuel(strategie, source, fosse);
			
			partie = getInstance((JoueurHumain)joueur1, (JoueurVirtuel)joueur2, source, fosse);
			
		}
		
		partie.lancerPartie();
		
	}

}
