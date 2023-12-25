package karmaka;

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
			console.afficher("L'adversaire vous a transmis " + tempo.size() + " cartes durant son tour.");
			Iterator<Carte> it = tempo.iterator();
			int numCarte = 1;
			while (it.hasNext()) {
				Carte carte = it.next();
				console.afficher("Carte [" + numCarte + "] : \n");
				console.afficher(carte);
				int choix = 0;
				while (choix!=1 && choix!=2) {
					console.afficher("Voulez-vous défausser cette carte ou la mettre dans votre main ?");
					choix = console.lireInt();
				}
				if (choix==1) {
					deplacerCarte(carte, tempo, main);
				}
				else {
					defausser(carte, tempo);
				}
				numCarte ++;
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
				System.out.println("Vous ne pouvez pas piocher. (votre Pile est vide)");
			}
			
			// on affiche les informations nécessaires pour la prise de décision au joueur
			console.afficher("Vous avez " + pile.size() + " carte(s) dans votre Pile, et " + vieFuture.size() + " carte(s) dans votre Vie Future.");
			console.afficher("Voici les cartes dans vos Oeuvres : \n");
			afficherCartes(oeuvre);
			console.afficher("Voici les cartes dans votre Main : \n");
			afficherCartes(main);
			
			// si le joueur peut passer, on lui demande s'il veut passer
			int choix = 0;
			if (pile.size()>0) {
				while (choix!=1 && choix!=2) {
					console.afficher("Voulez-vous passer votre tour ? (Entrez [1] pour passer, [2] pour jouer)");
					choix = console.lireInt();
				}
			}
			
			// si le joueur ne peut pas passer ou qu'il ne veut pas passer, il joue une carte
			if (pile.size()==0 || choix==2) {
				jouer();
			}
			
		}
		
	}
	
	public void seReincarner() {
		console.afficher("Vous tentez de vous réincarner...");
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
				console.afficher("Vous vous réincarnez en " + niveauKarmique.getNomNiveau() + " !");
				break;
			case SERPENT:
				niveauKarmique = NiveauKarmique.LOUP;
				console.afficher("Vous vous réincarnez en " + niveauKarmique.getNomNiveau() + " !");
				break;
			case LOUP:
				niveauKarmique = NiveauKarmique.SINGE;
				console.afficher("Vous vous réincarnez en " + niveauKarmique.getNomNiveau() + " !");
				break;
			case SINGE:
				aGagne = true;
				console.afficher("Vous accédez à la Transcendance !");
				break;
			default:
				console.afficher("Erreur dans l'augmentation du niveau karmique");
				break;
			}
		} else {
			nbAnneauxKarmiques += 1;
			console.afficher("Votre réincarnation a échoué, mais vous recevez 1 anneau karmique.");
		}
	}

	public void jouer() {
		int choixCarte = 0;
		while (choixCarte == 0 || choixCarte > main.size()) {
			console.afficher("Quelle carte voulez-vous jouer ? (Entrez le numéro de la carte)");
			choixCarte = console.lireInt();
		}
		Carte carteChoisie = main.get(choixCarte);
		int choix = 0;
		while (choix != 1 && choix != 2 && choix != 3) {
			console.afficher("Voulez-vous jouer cette carte pour ses points [1], son pouvoir [2], ou votre Vie Future [3] ?");
			choix = console.lireInt();
		}
		if (choix == 1) {
			deplacerCarte(carteChoisie, main, oeuvre);
		} else if (choix == 2) {
			carteChoisie.activerCapacite();
		} else if (choix == 3) {
			deplacerCarte(carteChoisie, main, vieFuture);
		}
	}
	
	public void afficherCartes(LinkedList<Carte> emplacement) {
		Iterator<Carte> it = emplacement.iterator();
		while (it.hasNext()) {
			console.afficher(it.next());
		}
	}
	
	public static void main(String[] args) {
		
	}

}
