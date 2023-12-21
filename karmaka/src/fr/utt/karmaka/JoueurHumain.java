package fr.utt.karmaka;

import java.io.Serializable;
import java.util.*;

public class JoueurHumain extends Joueur implements Serializable {
	
	private static final long serialVersionUID = 267935898355358012L;

	public JoueurHumain(String nom, LinkedList<Carte> source, LinkedList<Carte> fosse) {
		super(nom, source, fosse);
	}
	public void tour() {
		
		// on vérifie si l'adversaire a transmis une carte au joueur
		if (tempo.size()>0) {
			int choix;
			while (tempo.size()>0) {
				choix = 0;
				while (choix!=1 && choix!=2) {
					// A RAJOUTER : demander au joueur s'il veut mettre la carte dans sa main ou s'il la défausse
				}
				if (choix==1) {
					deplacerCarte(carte, tempo, main);
				}
				else {
					defausser(carte, tempo);
				}
			}
		}
		
		// on vérifie si le joueur doit se réincarner
		if (pile.size()==0 && main.size()==0) {
			seReincarner();
		}
		
		// si le joueur ne se réincarne pas, il joue
		else {
			
			// si la pile du joueur n'est pas vide, il y pioche une carte
			if (pile.size()>0) {
				piocher();
			}
			else {
				System.out.println("Vous ne pouvez pas piocher (votre pile est vide)");
			}
			
			// si le joueur peut passer, on lui demande s'il veut passer
			boolean veutPasser = false;
			if (pile.size()>0) {
				// A RAJOUTER : demander au joueur s'il veut passer
			}
			
			// si le joueur ne peut pas passer ou qu'il ne veut pas passer, il joue une carte
			if (pile.size()==0 || veutPasser==false) {
				jouer();
			}
			
		}
		
	}
	
	public static void main(String[] args) {
		
	}

}
