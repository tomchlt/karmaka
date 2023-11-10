package fr.utt.karmaka;

public class Source extends Emplacement {
	
	private static Source source;
	
	private Source() {
		
	}
	
	public static Source getInstance() {
		if (source==null) {
			source = new Source();
		}
		return source;
	}
	
	public static void main(String[] args) {
		
	}

}
