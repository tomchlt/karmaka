package fr.utt.karmaka;

public class JoueurVirtuel extends Joueur {

	private Strategie strategie;

	public JoueurVirtuel(Strategie strategie, Source source, Fosse fosse) {
		super("Joueur 2", source, fosse);
		this.strategie = strategie;
	}

	public void tour() {
		
		// on vérifie si l'adversaire a transmis une carte au joueur
		if (tempo.compterCartes()>0) {
			int choix;
			while (tempo.compterCartes()>0) {
				choix = 0;
				while (choix!=1 && choix!=2) {
					// A RAJOUTER : demander au joueur s'il veut mettre la carte dans sa main ou s'il la défausse
				}
				if (choix==1) {
					tempo.deplacerCarteVers(carte, main);
				}
				else {
					tempo.deplacerCarteVers(carte, fosse);
				}
			}
		}
		
		// on vérifie si le joueur doit se réincarner
		if (pile.compterCartes()==0 && main.compterCartes()==0) {
			seReincarner();
		}
		
		// si le joueur ne se réincarne pas, il joue
		else {
			
			// si la pile du joueur n'est pas vide, il y pioche une carte
			if (pile.compterCartes()>0) {
				piocher();
			}
			else {
				System.out.println("Vous ne pouvez pas piocher (votre pile est vide)");
			}
			
			// si le joueur peut passer, on lui demande s'il veut passer
			boolean veutPasser = false;
			if (pile.compterCartes()>0) {
				// A RAJOUTER : demander au joueur s'il veut passer
			}
			
			// si le joueur ne peut pas passer ou qu'il ne veut pas passer, il joue une carte
			if (pile.compterCartes()==0 || veutPasser==false) {
				jouer();
			}
			
		}
		
	}

	public static void main(String[] args) {

	}

}
