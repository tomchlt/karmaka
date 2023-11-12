package fr.utt.karmaka;

import java.util.Random;

public class Partie {
	
	private static Partie partie;
	
	private Source source;
	private Fosse fosse;
	private Joueur joueur1;
	private Joueur joueur2;
	
	private Partie() {
		this.source = Source.getInstance();
		this.fosse = Fosse.getInstance();
		this.joueur1 = new Joueur("Joueur 1", source, fosse);
		this.joueur2 = new Joueur("Joueur 2", source, fosse);
	}
	
	public static Partie getInstance() {
		if (partie==null) {
			partie = new Partie();
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
	
	public Source getSource() {
		return source;
	}
	
	public void setSource(Source source) {
		this.source = source;
	}
	
	public Fosse getFosse() {
		return fosse;
	}
	
	public void setFosse(Fosse fosse) {
		this.fosse = fosse;
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
	
	public static void main(String[] args) {
		
		Partie partie = getInstance();
		
		introduction();
		
		int choix=0;
		while (choix!=1 && choix!=2) {
			System.out.println("Voulez-vous jouer contre l'ordinateur [1] ou contre quelqu'un [2] ?");
			// A RAJOUTER : demander si le deuxième joueur sera humain ou virtuel
		}
		
		if (choix==1) {
			
			String nomJoueur1=null;
			while (nomJoueur1==null) {
				System.out.println("Quel est le nom du premier joueur ?");
				// A RAJOUTER : demander le nom du joueur 1
			}
			Joueur joueurHumain1 = new JoueurHumain(nomJoueur1, partie.source, partie.fosse);
			partie.setJoueur1(joueurHumain1);
			
			String nomJoueur2=null;
			while (nomJoueur2==null) {
				System.out.println("Quel est le nom du deuxième joueur ?");
				// A RAJOUTER : demander le nom du joueur 2
			}
			Joueur joueurHumain2 = new JoueurHumain(nomJoueur2, partie.source, partie.fosse);
			partie.setJoueur2(joueurHumain2);
		}
		
		else {
			
			String nomJoueur1=null;
			while (nomJoueur1==null) {
				System.out.println("Quel est votre nom ?");
				// A RAJOUTER : demander le nom du joueur 1
			}
			Joueur joueurHumain = new JoueurHumain(nomJoueur1, partie.source, partie.fosse);
			partie.setJoueur1(joueurHumain);
			
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
			Joueur joueurVirtuel = new JoueurVirtuel(strategie, partie.source, partie.fosse);
			partie.setJoueur2(joueurVirtuel);
			
		}
		
		partie.lancerPartie();
		
	}

}
