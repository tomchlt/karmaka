package fr.utt.karmaka;

public interface Observable {
	
	//ajouter en attribut une collection d' Observer
	
	public void addObserver();
	public void delObserver();
	public void notifyObservers();
	
}
